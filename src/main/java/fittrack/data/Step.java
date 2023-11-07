package fittrack.data;

import fittrack.parser.DateFormatException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

import java.time.DateTimeException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step {
    private final int steps;
    private Date date;
    private static final String DATE_CG = "date";
    private static final String STEP_CG = "step";
    private static final Pattern STEP_PATTERN = Pattern.compile(
            "(?<" + STEP_CG + ">\\S+)(\\s+d/(?<" + DATE_CG + ">\\S+))?"
    );

    public Step(int steps, Date date) {
        assert steps >= 0 && date != null;
        this.steps = steps;
        this.date = date;
    }

    public static Step parseStep(String steps) throws PatternMatchFailException, NumberFormatException, DateFormatException {

        final Matcher matcher = STEP_PATTERN.matcher(steps);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }

        final String step = matcher.group(STEP_CG);
        final String date = matcher.group(DATE_CG);

        try {
            if (Integer.parseInt(step) <= 0) {
                throw new NumberFormatException("Steps must be a positive integer.");
            }
            return new Step(Integer.parseInt(step), new Date(date));
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        } catch (DateTimeException e) {
            throw new DateFormatException();
        }
    }

    public String formatToFile() {
        return String.format("%s | %s", steps, date);
    }

    public int getSteps() {
        return steps;
    }

    public Date getDate() {
        return this.date;
    }

    public Step sum(Step another) {
        return new Step(this.steps + another.steps, this.date);
    }
    @Override
    public String toString() {
        return String.format("[S] %s steps (%s)", steps, date);
    }
}
