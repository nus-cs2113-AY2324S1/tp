package cashleh.parser;

import exceptions.CashLehParsingException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringTokenizerTest {

    @Test
    public void stringTokenizerDefaultTest() throws CashLehParsingException {
        HashMap<String, String> map = StringTokenizer.tokenize("hello world, " +
                "this is a test /date 01/01/2020 " +
                "/amount 200 /category pocket money",
                new String[]{"/date:optional", "/amount", "/category"});
        assertEquals(map.get("/date"), "01/01/2020");
        assertEquals(map.get("/amount"), "200");
        assertEquals(map.get("/category"), "pocket money");
        assertEquals(map.get(""), "hello world, this is a test");
    }

    @Test
    public void stringTokenizerNoDescriptionTest() throws CashLehParsingException {
        HashMap<String, String> map = StringTokenizer.tokenize("/date 01/01/2020 " +
                        "/amount 200 /category pocket money",
                new String[]{"/date", "/amount", "/category"});
        assertEquals(map.get("/date"), "01/01/2020");
        assertEquals(map.get("/amount"), "200");
        assertEquals(map.get("/category"), "pocket money");
        assertEquals(map.get(""), "");
    }

    @Test
    public void stringTokenizerOptionalPrefixTest() throws CashLehParsingException {
        HashMap<String, String> map = StringTokenizer.tokenize("hello world, " +
                        "this is a test " +
                        "/amount 200 /category pocket money",
                new String[]{"/date:optional", "/amount", "/category"});
        assertNull(map.get("/date"));
        assertEquals(map.get("/amount"), "200");
        assertEquals(map.get("/category"), "pocket money");
        assertEquals(map.get(""), "hello world, this is a test");
    }
}
