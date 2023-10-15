package seedu.financialplanner.commands;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class WatchListCommand extends Command {
    @Override
    public void execute(Ui ui, CashflowList cashflowList, WatchList watchList) {
        JSONArray stocks = watchList.fetchFMPStockPrices();
        ui.printWatchListHeader();
        for (Object o : stocks) {
            JSONObject stock = (JSONObject) o;
            ui.printStockInfo(stock);
        }
    }
}
