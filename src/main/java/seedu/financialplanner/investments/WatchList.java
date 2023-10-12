package seedu.financialplanner.investments;

import org.json.simple.JSONArray;
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

public class WatchList {
    private ArrayList<Stock> stocks;
    private final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY = "rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1";

    public WatchList() {
        stocks = new ArrayList<>();
        try {
            Stock apple = new Stock("AAPL", "NASDAQ");
            stocks.add(apple);
            Stock meta = new Stock("META", "NASDAQ");
            stocks.add(meta);
            Stock google = new Stock("GOOGL", "NASDAQ");
            stocks.add(google);
        } catch (FinancialPlannerException e) {
            System.out.println(e.getMessage());
        }
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

    public String addStock(String market, String stockCode) throws FinancialPlannerException {
        Stock newStock = null;
        newStock = new Stock(stockCode, market);
        stocks.add(newStock);
        return newStock.getStockName();
    }

    public int size(){
        return stocks.size();
    }

    public Stock get(int index){
        return stocks.get(index);
    }
}
