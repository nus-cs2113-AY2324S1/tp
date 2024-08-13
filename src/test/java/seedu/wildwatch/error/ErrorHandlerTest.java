package seedu.wildwatch.error;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.command.HelpCommand;
import seedu.wildwatch.ui.DeleteCommandPrinter;
import seedu.wildwatch.ui.EmptyDescriptionPrinter;
import seedu.wildwatch.ui.ErrorPrinter;
import seedu.wildwatch.ui.ListCommandPrinter;

class ErrorHandlerTest {
    private MockedStatic<Logger> mockedLogger;
    private MockedStatic<ErrorPrinter> mockedErrorPrinter;
    private MockedStatic<ListCommandPrinter> mockedListCommandPrinter;
    private MockedStatic<DeleteCommandPrinter> mockedDeleteCommandPrinter;
    private MockedStatic<EmptyDescriptionPrinter> mockedEmptyDescriptionPrinter;
    private MockedStatic<HelpCommand> mockedHelpCommand;
    private Logger logger;

    @BeforeEach
    void setUp() {
        mockedLogger = Mockito.mockStatic(Logger.class);
        mockedErrorPrinter = Mockito.mockStatic(ErrorPrinter.class);
        mockedListCommandPrinter = Mockito.mockStatic(ListCommandPrinter.class);
        mockedDeleteCommandPrinter = Mockito.mockStatic(DeleteCommandPrinter.class);
        mockedEmptyDescriptionPrinter = Mockito.mockStatic(EmptyDescriptionPrinter.class);
        mockedHelpCommand = Mockito.mockStatic(HelpCommand.class);
        logger = mock(Logger.class);

        // Assume LOGGER is obtained somewhere in the ErrorHandler
        mockedLogger.when(() -> Logger.getLogger(ErrorHandler.class.getName()))
                .thenReturn(logger);
    }

    @AfterEach
    void tearDown() {
        mockedLogger.close();
        mockedErrorPrinter.close();
        mockedListCommandPrinter.close();
        mockedDeleteCommandPrinter.close();
        mockedEmptyDescriptionPrinter.close();
        mockedHelpCommand.close();
    }

    @Test
    void handleInputError_invalidDate_logsAndPrintsDateErrorMessage() {
        InvalidInputException exception = new InvalidInputException(InvalidInputErrorType.INVALID_DATE);

        ErrorHandler.handleInputError(exception);

        verify(logger).warning("Date is invalid.");
        mockedErrorPrinter.verify(() -> ErrorPrinter.invalidDateMessagePrinter());
        mockedHelpCommand.verify(() -> HelpCommand.printNeedHelpMessage());
    }
}
