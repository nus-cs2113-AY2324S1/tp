package chessmaster.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chessmaster.storage.Storage;
import org.junit.jupiter.api.Test;

import chessmaster.ui.TextUI;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ChessBoardTest {

    // @@author onx001
    @Test
    public void pointTest() {
        TextUI ui = new TextUI();
        ChessBoard board = new ChessBoard(Color.WHITE);
        
        ui.printChessBoard(board.getBoard());
        int points = board.getPoints(Color.WHITE);
        assertEquals(0, points);
    }
}
