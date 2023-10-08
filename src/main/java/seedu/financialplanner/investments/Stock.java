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

    public Stock(String symbol, String market) {
        this.symbol = symbol;
        this.market = market;
        this.stockName = getStockName(symbol,market);
    }

    public String getStockName(String symbol, String market) {
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
            if (ja.size() != 1) {
                throw new FinancialPlannerException("stock not found");
            } else {
                JSONObject stock = (JSONObject) ja.get(0);
                System.out.println(stock.get("name"));
                return (String) stock.get("name");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (FinancialPlannerException e) {
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
