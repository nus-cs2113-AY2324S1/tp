package seedu.financialplanner.investments;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class WatchList {
    private ArrayList<Stock> stocks;
    private final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY = "rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1";

    public WatchList() {
        stocks = new ArrayList<>();
        stocks.add(new Stock("AAPL", "NASDAQ"));
        stocks.add(new Stock("META", "NASDAQ"));
        stocks.add(new Stock("GOOGL", "NASDAQ"));
    }

    public JSONArray fetchFMPStockPrices() {
        HttpClient client = HttpClient.newHttpClient();
        StringBuilder queryStocks = new StringBuilder();
        for (Stock stock : stocks) {
            queryStocks.append(stock.toString());
        }
        String requestURI = String.format("%s%s?apikey=%s", API_ENDPOINT, queryStocks,API_KEY);
        HttpRequest request = HttpRequest.newBuilder(URI.create(requestURI))
                .header("accept", "application/json")
                .GET()
                .timeout(Duration.ofSeconds(10))
                .build();
        Object obj;
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            obj = new JSONParser().parse(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return (JSONArray) obj;
    }
}
