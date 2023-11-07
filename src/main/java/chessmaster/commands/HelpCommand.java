//@@author TriciaBK
package chessmaster.commands;

import chessmaster.game.ChessBoard;
import chessmaster.ui.TextUI;

public class HelpCommand extends Command {

    public static final String HELP_COMAMND_STRING = "help";

    private static final String[] HELP_STRINGS = { 
        "Seems like you need some help!",
        "Here are the following commands to play:",
        "Move piece - Input coordinate of piece, followed by coordinate to move to",
        "   Format: [column][row] [column][row]",
        "   E.g. a2 a3",
        "Show available moves - Lists all the available moves for a piece",
        "   Format: moves [column][row]",
        "   E.g. moves a2",
        "Show board - Shows the current state of the chess board",
        "   Format: show",
        "Obtain rules - Obtain a quick refresher on the rules of chess",
        "   Format: rules",
        "View pieces representation - Display a legend that explains " ,
        "                             piece representations",
        "   Format: legend",
        "Abort game - Exit programme",
        "   Format: abort",

    };

    @Override
    public CommandResult execute(ChessBoard board, TextUI ui) {
        return new CommandResult(HELP_STRINGS);
    }

}
