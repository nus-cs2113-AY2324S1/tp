package seedu.financialplanner.commands;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class WatchListCommand extends Command {
    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) {
        JSONArray stocks = watchList.fetchFMPStockPrices();
        ui.printWatchListHeader();
        for (Object o : stocks) {
            JSONObject stock = (JSONObject) o;
            ui.printStockInfo(stock);
        }
    }
}
