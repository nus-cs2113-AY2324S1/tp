package seedu.financialplanner.investments;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WatchListTest {

    @Test
    void fetchFMPStockPrices() throws FinancialPlannerException {
        WatchList wl = WatchList.INSTANCE;
        wl.fetchFMPStockPrices();
        ArrayList<Stock> stocks = wl.getStocks();
        assertNotNull(stocks.get(0).getPrice());
        assertNotNull(stocks.get(1).getPrice());
        assertNotNull(stocks.get(2).getPrice());
    }

    @Test
    void addStock() throws Exception {
        WatchList wl = WatchList.INSTANCE;
        String stockCode = "GME";
        assertEquals("Gamestop Corporation - Class A", wl.addStock(stockCode));
    }

    @Test
    void deleteStock() throws FinancialPlannerException {
        WatchList wl = WatchList.INSTANCE;
        String stockCode = "AAPL";
        assertEquals("Apple Inc", wl.deleteStock(stockCode));
    }
}
