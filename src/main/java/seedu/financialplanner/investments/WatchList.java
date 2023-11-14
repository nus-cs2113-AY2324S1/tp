package seedu.financialplanner.investments;

import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.storage.LoadData;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents the watchlist in the financial planner app
 */
public class WatchList {
    private static WatchList watchlist = null;
    private static final String FILE_PATH = "data/watchlist.json";
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    private static final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private static final String API_KEY = "iFumtYryBCbHpS3sDqLdVKi2SdP63vSV";
    private HashMap<String, Stock> stocks;

    /**
     * Constructor for the watchlist. It will load up the watchlist data from watchlist.json file and clean up
     * any erroneous inputs
     */
    private WatchList() {
        stocks = LoadData.loadWatchList(FILE_PATH);
        cleanUpLoadedWatchList();
    }

    /**
     * Method that helps to clean up the watchlist loaded up after application start up
     * If watchlist stocks is null, it will help to initialize a new base watchlist.
     * It will call another method checkValidStock to clean up the watchlist stocks loaded if it is not empty
     */
    private void cleanUpLoadedWatchList() {
        if (stocks == null) {
            stocks = initalizeNewWatchlist();
            return;
        }
        stocks.entrySet().removeIf(stockPair -> !checkValidStock(stockPair.getKey(), stockPair.getValue()));
        if (stocks.isEmpty()) {
            stocks = initalizeNewWatchlist();
        }
    }

    /**
     * Checks the validity of the stock key, and the Stock class value in the watchlist stocks hashmap
     * If it is not valid, it will return false
     *
     * @param key
     * @param stockToCheck
     * @return isValid
     */
    public boolean checkValidStock(String key, Stock stockToCheck) {
        boolean isValid = true;
        if (!key.toUpperCase().equals(key)) {
            isValid = false;
        }
        if (stockToCheck.getStockName() == null || stockToCheck.getSymbol() == null) {
            isValid = false;
        }
        if(!key.equals(stockToCheck.getSymbol())) {
            isValid = false;
        }
        if (stockToCheck.getHashCode() == 0) {
            if (!ObjectUtils.allNull(
                    stockToCheck.getPrice(),
                    stockToCheck.getDayHigh(),
                    stockToCheck.getDayLow(),
                    stockToCheck.getLastUpdated(),
                    stockToCheck.getExchange()) || stockToCheck.getLastFetched() != 0) {
                isValid = false;
            }
        }
        if (!isValid) {
            Ui.getInstance().printInvalidStockLoaded(key);
        }
        return isValid;
    }

    /**
     * Initialize a new watchlist stocks hashmap with base stocks (AAPL and GOOGL)
     *
     * @return Hashmap of base stocks
     */
    public HashMap<String, Stock> initalizeNewWatchlist() {
        HashMap<String, Stock> baseStocks = new HashMap<>();
        Ui.getInstance().showMessage("Initializing New watchlist.. adding AAPL and GOOGL for your reference");

        Stock apple = new Stock("AAPL", "Apple Inc");
        assert apple.getSymbol() != null && apple.getStockName() != null;
        baseStocks.put(apple.getSymbol(), apple);

        Stock google = new Stock("GOOGL", "Alphabet Inc - Class A");
        assert google.getSymbol() != null && google.getStockName() != null;
        baseStocks.put(google.getSymbol(), google);

        return baseStocks;
    }

    /**
     * Method to get the watchlist singleton or create one if it does not exist and returns it
     *
     * @return watchlist singleton
     */
    public static WatchList getInstance() {
        if (watchlist == null) {
            watchlist = new WatchList();
        }
        return watchlist;
    }

    /**
     * Method to get the latest watchlist info such as price and daily lowest
     *
     * @throws FinancialPlannerException
     */
    public void getLatestWatchlistInfo() throws FinancialPlannerException {
        StringBuilder queryStocks = getExpiredStocks();
        fetchFMPStockPrices(queryStocks);
    }

    /**
     * Checks the watchlist stocks hashmap for stocks that are expired meaning their data should be refreshed using
     * the api. Returns a string of stocks that are expired separated by a comma
     *
     * @return String containing stocks that needs to be queried
     */
    public StringBuilder getExpiredStocks() {
        StringBuilder queryStocks = new StringBuilder();
        long currentTime = System.currentTimeMillis();
        long tenMin = 600000;
        for (Map.Entry<String, Stock> set: stocks.entrySet()) {
            Stock currentStock = set.getValue();
            if (currentStock.getLastFetched() + tenMin < currentTime) {
                queryStocks.append(set.getKey());
                queryStocks.append(",");
            }
        }
        return queryStocks;
    }

    /**
     * Request the Financial Modeling prep API for the latest stock info using the query stocks parameter passed in
     * by the getExpiredStocks method. Will throw a FinancialPlannerException if an error is encountered in the attempt
     * If the attempt is successful, it will update the stock information in the watchlist stocks.
     *
     * @param queryStocks
     * @throws FinancialPlannerException
     */
    public void fetchFMPStockPrices(StringBuilder queryStocks) throws FinancialPlannerException {
        if (stocks.isEmpty()) {
            throw new FinancialPlannerException("Empty Watchlist. Nothing to display...");
        }
        if (queryStocks.toString().isEmpty()) {
            // all stocks prices are up-to-date, just display
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        String requestURI = String.format("%s%s?apikey=%s", API_ENDPOINT, queryStocks, API_KEY);
        HttpRequest request = HttpRequest.newBuilder(URI.create(requestURI))
                .header("accept", "application/json")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        Object obj;

        logger.log(Level.INFO, "Requesting API endpoint FMP");
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new FinancialPlannerException("API might be down at the moment...");
            }
            obj = new JSONParser().parse(response.body());
            extractWatchlistInfoFromJSONArray((JSONArray) obj);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO exception when sending request or receiving response");
            throw new FinancialPlannerException("Is your internet down?");
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted");
            throw new FinancialPlannerException("Request to API was interrupted");
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Could not parse to JSON");
            throw new FinancialPlannerException("Could not parse to JSON format");
        } catch (ClassCastException e) {
            logger.log(Level.SEVERE, "Did not receive object of class JSON Object");
            throw new FinancialPlannerException("Something went wrong when fetching API. Please try again");
        }
    }

    /**
     * Method to extract out required information from the full JSON array received from the API
     *
     * @param jsonStocks
     * @throws FinancialPlannerException
     */
    public void extractWatchlistInfoFromJSONArray(JSONArray jsonStocks) throws FinancialPlannerException {
        if (jsonStocks == null) {
            throw new FinancialPlannerException("Incorrect API Response Received. Please try again");
        }
        if (jsonStocks.isEmpty()) {
            return;
        }
        long fetchTime = System.currentTimeMillis();
        for (Object jo : jsonStocks) {
            JSONObject stock = (JSONObject) jo;
            if (stocks.containsKey(stock.get("symbol").toString().toUpperCase())) {
                Stock stockLocal = stocks.get(stock.get("symbol").toString().toUpperCase());
                extractStockInfoFromJSONObject(stock, stockLocal);
            }
        }
        setLastFetched(fetchTime);
    }

    public void setLastFetched(long fetchTime) {
        for (Stock stock : stocks.values()) {
            stock.setLastFetched(fetchTime);
        }
    }

    /**
     * Method called by extractWatchlistInfoFromJSONArray to set stock info for individual stocks using information
     * obtained from the API (eg. setting latest price)
     *
     * @param stock
     * @param stockLocal
     */
    public void extractStockInfoFromJSONObject(JSONObject stock, Stock stockLocal) {
        String price = stock.get("price").toString();
        assert price != null;
        stockLocal.setPrice(price);

        String exchange = stock.get("exchange").toString();
        assert exchange != null;
        stockLocal.setExchange(exchange);

        String dayHigh = stock.get("dayHigh").toString();
        assert dayHigh != null;
        stockLocal.setDayHigh(dayHigh);

        String dayLow = stock.get("dayLow").toString();
        assert dayLow != null;
        stockLocal.setDayLow(dayLow);

        String timestamp = stock.get("timestamp").toString();
        long lastUpdated = Long.parseLong(timestamp) * 1000;
        stockLocal.setLastUpdated(new Date(lastUpdated));
    }

    /**
     * Method used to add a new stock to the watchlist that has the stockCode given by the parameter provided
     *
     * @param stockCode
     * @return stockName
     * @throws FinancialPlannerException
     */
    public String addStock(String stockCode) throws FinancialPlannerException {
        if (stocks.size() >= 5) {
            throw new FinancialPlannerException("Watchlist is full (max 5). Delete a stock to add a new one");
        }
        if (stocks.containsKey(stockCode.toUpperCase())) {
            throw new FinancialPlannerException("Stock is already present in Watchlist. Use watchlist to view it!");
        }
        if (stocks.containsKey(stockCode)) {
            throw new FinancialPlannerException("Stock is already present in Watchlist. Use watchlist to view it!");
        }

        Stock newStock;
        newStock = new Stock(stockCode.toUpperCase());

        assert newStock.getSymbol() != null && newStock.getStockName() != null;
        stocks.put(newStock.getSymbol(), newStock);
        return newStock.getStockName();
    }

    /**
     * Method for deleting a stock from the watchlist that has the stockCode given by the parameter provided
     *
     * @param stockCode
     * @return deleted stock name
     * @throws FinancialPlannerException
     */
    public String deleteStock(String stockCode) throws FinancialPlannerException {
        if (stocks.isEmpty()) {
            throw new FinancialPlannerException("No stock in watchlist!");
        }
        Stock removedStock = stocks.remove(stockCode);
        if (removedStock == null) {
            removedStock = stocks.remove(stockCode.toUpperCase());
        }
        if (removedStock == null) {
            throw new FinancialPlannerException("Does not Exist in Watchlist");
        }
        return removedStock.getStockName();
    }

    public int size() {
        return stocks.size();
    }

    public Stock get(int index) {
        return stocks.get(index);
    }

    public HashMap<String, Stock> getStocks() {
        return stocks;
    }

    public void setStocks(HashMap<String, Stock> stocks) {
        this.stocks = stocks;
    }
}
