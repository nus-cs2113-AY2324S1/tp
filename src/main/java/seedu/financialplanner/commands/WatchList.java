package seedu.financialplanner.commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;

public class WatchList extends Command {

    private final String API_ENDPOINT = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY = "rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1";
    @Override
    public void execute(Ui ui) {

        HttpClient client = HttpClient.newHttpClient();
        String stocks = "META,AAPL";
        String requestURI = String.format("%s%s?apikey=%s", API_ENDPOINT,stocks,API_KEY);

        HttpRequest request = HttpRequest.newBuilder(URI.create(requestURI))
                .header("accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            Object obj = new JSONParser().parse(response.body());

            JSONArray ja = (JSONArray) obj;
            // System.out.println(ja.toJSONString());
            Iterator itr = ja.iterator();
            System.out.print("Symbol");
            System.out.print("  ");
            System.out.print("Price");
            System.out.print("     ");
            System.out.print("Company Name");
            System.out.println();
            while (itr.hasNext()) {
                JSONObject stock = (JSONObject) itr.next();
                System.out.print(stock.get("symbol"));
                System.out.print("    ");
                System.out.print(stock.get("price"));
                System.out.print("    ");
                System.out.print(stock.get("name"));
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
