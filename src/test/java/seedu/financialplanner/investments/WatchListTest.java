package seedu.financialplanner.investments;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WatchListTest {

    @Test
    void fetchFMPStockPrices() {
        WatchList wl = new WatchList();
        JSONArray obj = wl.fetchFMPStockPrices();
        JSONObject apple = (JSONObject) obj.get(0);
        assertNotNull(apple.get("price"));
        JSONObject meta = (JSONObject) obj.get(1);
        assertNotNull(meta.get("price"));
        JSONObject google = (JSONObject) obj.get(2);
        assertNotNull(google.get("price"));
    }

    @Test
    void addStock() throws Exception {
        WatchList wl = new WatchList();
        String market = "NYSE";
        String stockCode = "GME";
        assertEquals("GameStop Corp.", wl.addStock(market, stockCode));
    }
}
