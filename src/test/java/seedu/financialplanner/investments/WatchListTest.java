package seedu.financialplanner.investments;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WatchListTest {

    @Test
    @Order(1)
    void fetchFMPStockPrices() throws FinancialPlannerException {
        WatchList wl = WatchList.getInstance();
        wl.fetchFMPStockPrices();
        ArrayList<Stock> stocks = wl.getStocks();
        assertNotNull(stocks.get(0).getPrice());
        assertNotNull(stocks.get(1).getPrice());
    }

    @Test
    @Order(2)
    void addStock() throws Exception {
        WatchList wl = WatchList.getInstance();
        String stockCode = "GME";
        assertEquals("Gamestop Corporation - Class A", wl.addStock(stockCode));
    }

    @Test
    @Order(3)
    void deleteStock() throws FinancialPlannerException {
        WatchList wl = WatchList.getInstance();
        String stockCode = "GME";
        assertEquals("Gamestop Corporation - Class A", wl.deleteStock(stockCode));
    }
}
