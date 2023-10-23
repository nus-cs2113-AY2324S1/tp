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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stock {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private String symbol;
    private String market;
    private String stockName;
    private String price;

    public Stock(String symbol) throws FinancialPlannerException {
        this.symbol = symbol;
        this.stockName = getStockNameFromAPI(symbol);
    }

    public String getStockName() {
        return stockName;
    }
    public String getStockNameFromAPI(String symbol) throws FinancialPlannerException {
        final String API_ENDPOINT = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=";
        final String API_KEY = "LNKL0548PHY2F0QU";
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
            // TODO: Might need to use AMEX when NYSE is used
            // TODO: Need to check if it is added already
            // TODO: add a cap to adding
            // TODO: Separate based on market
            // TODO: add other info
            // TODO: testing
            if (!symbolFound.equals(symbol)) {
                throw new FinancialPlannerException("Stock not found");
            }

            assert stock.get("2. name") != null;
            market = (String) stock.get("4. region");
            return (String) stock.get("2. name");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

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
}
