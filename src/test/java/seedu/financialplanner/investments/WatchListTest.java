package seedu.financialplanner.investments;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WatchListTest {

    @Test
    @Order(1)
    void fetchFMPStockPrices() throws FinancialPlannerException {
        WatchList wl = WatchList.getInstance();
        wl.getLatestWatchlistInfo();
        HashMap<String, Stock> stocks = wl.getStocks();
        assertNotNull(stocks.get("AAPL").getPrice());
        assertNotNull(stocks.get("GOOGL").getPrice());
    }

    @Test
    @Order(2)
    void addStock() throws Exception {
        WatchList wl = WatchList.getInstance();
        String stockCode = "gME";
        assertEquals("Gamestop Corporation - Class A", wl.addStock(stockCode));
    }

    @Test
    @Order(3)
    void checkValidStock() {
        HashMap<String, Stock> stocks = WatchList.getInstance().getStocks();
        boolean valid = WatchList.getInstance().checkValidStock("GME", stocks.get("GME"));
        assertTrue(valid);
    }

    @Test
    @Order(4)
    void deleteStock() throws FinancialPlannerException {
        WatchList wl = WatchList.getInstance();
        String stockCode = "GMe";
        assertEquals("Gamestop Corporation - Class A", wl.deleteStock(stockCode));
    }

    @Test
    @Order(5)
    void initializeNewWatchlist() {
        HashMap<String, Stock> stocks = WatchList.getInstance().initalizeNewWatchlist();
        assertEquals(2, stocks.size());
        assertNotNull(stocks.get("AAPL").getStockName());
        assertNotNull(stocks.get("GOOGL").getStockName());
        assertEquals(0, stocks.get("AAPL").getHashCode());
        assertEquals(0, stocks.get("GOOGL").getHashCode());
    }
}
