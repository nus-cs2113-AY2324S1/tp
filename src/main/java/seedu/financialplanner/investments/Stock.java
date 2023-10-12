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

public class Stock {
    private String symbol;
    private String market;
    private String stockName;

    public Stock(String symbol, String market) throws FinancialPlannerException {
        this.symbol = symbol;
        this.market = market;
        this.stockName = getStockNameFromAPI(symbol,market);
    }

    public String getStockName() {
        return stockName;
    }
    public String getStockNameFromAPI(String symbol, String market) throws FinancialPlannerException {
        final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/search-ticker?query=";
        final String API_KEY = "rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1";
        String requestURI = String.format("%s%s&exchange=%s&apikey=%s", API_ENDPOINT,symbol,market,API_KEY);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(requestURI))
                .header("accept", "application/json")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Object obj = new JSONParser().parse(response.body());

            JSONArray ja = (JSONArray) obj;
            if (ja.isEmpty()) {
                throw new FinancialPlannerException("stock not found");
            }
            JSONObject stock = (JSONObject) ja.get(0);
            String symbolFound = (String) stock.get("symbol");
            // TODO: Might need to use AMEX when NYSE is used
            // TODO: Need to check if it is added already
            // TODO: add a cap to adding
            // TODO: Separate based on market
            // TODO: add other info
            // TODO: testing
            if (!symbolFound.equals(symbol)) {
                throw new FinancialPlannerException("Stock not found");
            }
            return (String) stock.get("name");
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
}
