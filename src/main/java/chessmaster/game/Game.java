//@@author TongZhengHong
package chessmaster.game;

import chessmaster.commands.AbortCommand;
import chessmaster.commands.Command;
import chessmaster.commands.CommandResult;
import chessmaster.commands.MoveCommand;
import chessmaster.exceptions.ChessMasterException;
import chessmaster.parser.Parser;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.user.Player;

public class Game {

    private static final String[] START_HELP_STRINGS = {
        "Thank you for choosing ChessMaster! Here are the commands that you can use:",
        "Move piece - Input coordinate of piece, followed by coordinate to move to",
        "   Format: [column][row] [column][row]",
        "   E.g. a2 a3",
        "Show board - Shows the current state of the chess board",
        "   Format: show",
        "Show available moves - Lists all the available moves for a piece at a coordinate",
        "   Format: moves [column][row]",
        "   E.g. moves a2",
        "Abort game - Exit programme",
        "   Format: abort",
        "Obtain rules - Obtain a quick refresher on the rules of chess",
        "   Format: rules",
        "Obtain help - Show a list of commands and what they do",
        "   Format: help"
    };

    private CPU cpu;
    private Human human;
    private Player currentPlayer;
    private int numMoves;

    private TextUI ui;
    private ChessBoard board;
    private Storage storage;
    private int difficulty;

    private Command command;
    private boolean hasEnded;

    public Game(Color playerColour, Color currentTurnColor, ChessBoard board, 
        Storage storage, TextUI ui, int difficulty) {

        this.ui = ui;
        this.board = board;
        this.storage = storage;
        this.difficulty = difficulty;

        this.human = new Human(playerColour, board);
        Color cpuColor = playerColour.getOppositeColour();
        this.cpu = new CPU(cpuColor, board);

        this.numMoves = 0;

        // Choose which player goes first
        currentPlayer = currentTurnColor == playerColour ? human : cpu;

        assert playerColour != Color.EMPTY : "Human player color should not be EMPTY!";
        assert cpuColor != Color.EMPTY : "CPU player color should not be EMPTY!";
        assert currentPlayer != null : "A player should always exist in a game!";
        assert (1 <= difficulty) && (difficulty <= 3) : "Difficulty should be between 1 and 3!";
    }

    public void run() {
        ui.printText(START_HELP_STRINGS);
        ui.printChessBoard(board.getBoard());

        while (!hasEnded && !AbortCommand.isAbortCommand(command)) {
            try {
                assert currentPlayer.isCPU() || currentPlayer.isHuman() : 
                    "Player should only either be human or CPU!";

                if (currentPlayer.isHuman()) {
                    command = getAndExecuteUserCommand();
                    if (!command.isMoveCommand()) {
                        continue; // Get next command
                    }
                    Move playedMove = handleHumanMove();
                    ui.printChessBoardWithMove(board.getBoard(), playedMove);
                    
                } else if (currentPlayer.isCPU()) {
                    Move playedMove = handleCPUMove();
                    ui.printChessBoardWithMove(board.getBoard(), playedMove);
                } 

                currentPlayer = togglePlayerTurn();
                storage.saveBoard(board, currentPlayer.getColour());
                hasEnded = checkEndState(); // Resets board if end

            } catch (ChessMasterException e) {
                ui.printErrorMessage(e);
            }
        }
    }

    private Command getAndExecuteUserCommand() throws ChessMasterException {
        String userInputString = ui.getUserInput(false);
        this.command = Parser.parseCommand(userInputString);

        CommandResult result = command.execute(this);
        ui.printCommandResult(result);
        return this.command;
    }

    private Move handleHumanMove() throws ChessMasterException {
        Move humanMove = ((MoveCommand) this.command).getMove();
        board.executeMove(humanMove);
        human.addMove(humanMove);
        numMoves++;

        // Handle human promotion
        if (!board.isEndGame()) {
            if (board.canPromote(humanMove)) {
                human.handlePromote(board, ui, humanMove);
            }
        }

        return humanMove;
    }

    private Move handleCPUMove() throws ChessMasterException {
        ui.printCPUThinkingMessage();

        Move cpuMove = cpu.getBestMove(board, difficulty);
        ui.printCPUMove(cpuMove);
        board.executeMove(cpuMove);

        cpu.addMove(cpuMove);
        numMoves++;

        return cpuMove;
    }

    private boolean checkEndState() throws ChessMasterException {
        boolean end = board.isEndGame();
        if (end) {
            Color winningColor = board.getWinningColor();
            Player winnerPlayer = human.getColour() == winningColor ? human : cpu;
            ui.printEndMessage(winnerPlayer);
            storage.resetBoard();
        }
        return end;
    }

    private Player togglePlayerTurn() {
        return currentPlayer.isHuman() ? cpu : human;
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public TextUI getUI() {
        return this.ui;
    }

    public CPU getCPU() {
        return this.cpu;
    }

    public Human getHuman() {
        return this.human;
    }

    public int getNumMoves() {
        return this.numMoves;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
