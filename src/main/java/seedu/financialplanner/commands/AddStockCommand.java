package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class AddStockCommand extends Command {
    private final String market;
    private final String stockCode;

    public AddStockCommand(String market, String stockCode) {
        this.market = market;
        this.stockCode = stockCode;
    }

    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) {
        String stockName = null;
        try {
            stockName = watchList.addStock(market, stockCode);
            ui.printAddStock(stockName);
        } catch (FinancialPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
