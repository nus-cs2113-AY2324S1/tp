package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Iterator;

public class WatchList extends Command {
    @Override
    public void execute(Ui ui) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://financialmodelingprep.com/api/v3/quote/META,AAPL?apikey=rNCNMmSLUR3BAyeKFHwN69QGzE8fmig1"))
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
