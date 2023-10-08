package seedu.financialplanner.commands;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class WatchList extends Command {

    private final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY = "rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1";
    @Override
    public void execute(Ui ui) {

        HttpClient client = HttpClient.newHttpClient();
        ArrayList<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("AAPL", "NASDAQ"));
        stocks.add(new Stock("META", "NASDAQ"));
        stocks.add(new Stock("GOOGL", "NASDAQ"));
        // String stocks = "META,AAPL,VOO";
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

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            Object obj = new JSONParser().parse(response.body());

            JSONArray ja = (JSONArray) obj;
            // System.out.println(ja.toJSONString());
            Iterator itr = ja.iterator();
            System.out.print("Symbol");
            System.out.print("    ");
            System.out.print("Price");
            System.out.print("     ");
            System.out.print("Company Name");
            System.out.println();
            while (itr.hasNext()) {
                JSONObject stock = (JSONObject) itr.next();
                printStockInfo(stock);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void printStockInfo(JSONObject stock) {
        String symbol = StringUtils.rightPad((String) stock.get("symbol"), 10);
        String price = StringUtils.rightPad(stock.get("price").toString(), 10);
        String name = StringUtils.rightPad((String) stock.get("name"), 10);
        System.out.println(symbol + price + name);
    }
}
