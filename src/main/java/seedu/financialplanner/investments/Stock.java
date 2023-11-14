package seedu.financialplanner.investments;

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
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a stock within the financial planner app
 */
public class Stock {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private static final String API_ENDPOINT = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=";
    ///private static final String API_KEY = "LNKL0548PHY2F0QU";
    private static final String API_KEY = "1AKJMAX4CNWFKSE6";
    private String symbol;
    private String exchange;
    private String stockName;
    private String price;
    private String dayHigh;
    private String dayLow;
    private Date lastUpdated = null;
    private long lastFetched = 0;
    private int hashCode = 0;

    /**
     * Constructor for stock that sets the symbol and searches the api
     * for stock name for it
     *
     * @param symbol
     * @throws FinancialPlannerException
     */
    public Stock(String symbol) throws FinancialPlannerException {
        this.symbol = symbol;
        this.stockName = getStockNameFromAPI(symbol);
    }

    /**
     * Constructor that sets the symbol and stock name directly
     *
     * @param symbol
     * @param stockName
     */
    public Stock(String symbol, String stockName) {
        this.symbol = symbol;
        this.stockName = stockName;
    }

    public String getStockName() {
        return stockName;
    }

    /**
     * Method that gets the stock name from the Alpha vantage api. Will throw financial planner exception for any errors
     * when attempting this. If succuessful, will return the stock name for the symbol provided
     *
     * @param symbol
     * @return stock name
     * @throws FinancialPlannerException
     */
    public String getStockNameFromAPI(String symbol) throws FinancialPlannerException {
        String requestURI = String.format("%s%s&apikey=%s", API_ENDPOINT,symbol,API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(requestURI))
                .header("accept", "application/json")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();

        logger.log(Level.INFO, "Requesting API for stock info");
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new FinancialPlannerException("API might be down at the moment...");
            }

            Object obj = new JSONParser().parse(response.body());

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray ja = (JSONArray) jsonObject.get("bestMatches");
            if (ja == null) {
                throw new FinancialPlannerException("API limit Reached");
            }
            if (ja.isEmpty()) {
                throw new FinancialPlannerException("Stock not found");
            }
            JSONObject stock = (JSONObject) ja.get(0);
            String symbolFound = (String) stock.get("1. symbol");
            // TODO: Separate based on market
            // TODO: testing
            if (!symbolFound.equals(symbol)) {
                throw new FinancialPlannerException("Stock not found");
            }

            String region = (String) stock.get("4. region");
            if (!region.equals("United States")) {
                throw new FinancialPlannerException("Only US stocks are available due to free nature of API :(");
            }

            assert stock.get("2. name") != null;
            return (String) stock.get("2. name");
        } catch (IOException e) {
            throw new FinancialPlannerException("Error sending request to API..  Are you connected to the internet?");
        } catch (InterruptedException e) {
            throw new FinancialPlannerException("Command was interrupted... Try again");
        } catch (ParseException e) {
            throw new FinancialPlannerException("Error parsing JSON response from API... Try again");
        }
    }

    public void setHashCode() {
        if (lastFetched == 0) {
            return;
        }
        this.hashCode = Objects.hashCode(symbol + exchange + stockName + price + dayHigh
                + dayLow + lastUpdated + lastFetched);
    }

    public int checkHashCode() {
        return Objects.hashCode(symbol + exchange + stockName + price + dayHigh
                + dayLow + lastUpdated + lastFetched);
    }

    public int getHashCode() {
        return this.hashCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * toString method is override to output its symbol appended with a comma
     *
     * @return string representing stock
     */
    @Override
    public String toString() {
        return symbol + ",";
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(String dayHigh) {
        this.dayHigh = dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }

    public void setDayLow(String dayLow) {
        this.dayLow = dayLow;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public long getLastFetched() {
        return lastFetched;
    }

    public void setLastFetched(long lastFetched) {
        this.lastFetched = lastFetched;
    }
}
