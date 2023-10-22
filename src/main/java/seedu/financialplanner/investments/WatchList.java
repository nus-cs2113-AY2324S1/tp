package seedu.financialplanner.investments;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchList {

    public static final WatchList INSTANCE = new WatchList();
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    private final ArrayList<Stock> stocks;
    private final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY = "iFumtYryBCbHpS3sDqLdVKi2SdP63vSV";
    private WatchList() {
        stocks = new ArrayList<>();
        try {
            Stock apple = new Stock("AAPL");
            assert apple.getSymbol() != null && apple.getStockName() != null;
            stocks.add(apple);

            Stock meta = new Stock("META");
            assert meta.getSymbol() != null && meta.getStockName() != null;
            stocks.add(meta);

            Stock google = new Stock("GOOGL");
            assert google.getSymbol() != null && google.getStockName() != null;
            stocks.add(google);
        } catch (FinancialPlannerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fetchFMPStockPrices() throws FinancialPlannerException {
        if (stocks.isEmpty()) {
            throw new FinancialPlannerException("Empty Watchlist. Nothing to display...");
        }

        HttpClient client = HttpClient.newHttpClient();
        StringBuilder queryStocks = new StringBuilder();
        assert !stocks.isEmpty();
        for (Stock stock : stocks) {
            queryStocks.append(stock.toString());
        }
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
            // System.out.println(response.body());
            obj = new JSONParser().parse(response.body());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cant request API endpoint");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Could not parse to JSON");
            throw new RuntimeException(e);
        }
        JSONArray ja = (JSONArray) obj;
        int i = 0;
        for (Object jo : ja) {
            JSONObject stock = (JSONObject) jo;
            String price = StringUtils.rightPad(stock.get("price").toString(), 10);
            stocks.get(i).setPrice(price);
            i += 1;
        }
    }

    public String addStock(String stockCode) throws FinancialPlannerException {
        Stock newStock = null;
        newStock = new Stock(stockCode);

        assert newStock.getSymbol() != null && newStock.getStockName() != null;
        stocks.add(newStock);
        return newStock.getStockName();
    }

    public String deleteStock(String stockCode) throws FinancialPlannerException {
        if (stocks.isEmpty()) {
            throw new FinancialPlannerException("No stock in watchlist!");
        }
        Stock toBeRemoved = stocks
                .stream()
                .filter(stock -> stockCode.equals(stock.getSymbol()))
                .findFirst()
                .orElseThrow(() -> new FinancialPlannerException("Does not Exist in Watchlist"));
        stocks.remove(toBeRemoved);
        return toBeRemoved.getStockName();
    }
    public int size() {
        return stocks.size();
    }

    public Stock get(int index) {
        return stocks.get(index);
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

}
