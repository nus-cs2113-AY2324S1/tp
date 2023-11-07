package fittrack.data;

import fittrack.parser.IllegalValueException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Workout {
    private static final String NAME_GRP = "name";
    private static final String CALORIES_GRP = "calories";
    private static final String DATE_GRP = "date";
    private static final Pattern WORKOUT_PATTERN = Pattern.compile(
            "(?<" + NAME_GRP + ">.+)\\s+c/(?<" + CALORIES_GRP + ">\\S+)(\\s+d/(?<" + DATE_GRP + ">\\S+))?"
    );

    private final String name;
    private final Calories calories;
    private final Date date;

    public Workout(String name, Calories calories, Date date) {
        assert name != null && calories != null && date != null;

        this.name = name;
        this.calories = calories;
        this.date = date;
    }

    public Calories getCalories() {
        assert calories.value != 0;
        return calories;
    }

    public Date getDate() {
        return this.date;
    }

    public String getName() {
        return name;
    }

    public String formatToFile() {
        return String.format("%s | %s | %s", name, calories, date);
    }

    @Override
    public String toString() {
        return String.format("[W] %s (%s, %s)", name, calories, date);
    }

    public static Workout parseWorkout(String s) throws ParseException {
        assert s != null;
        String workout = s.strip();

        final Matcher matcher = WORKOUT_PATTERN.matcher(workout);
        if (!matcher.matches()) {
            throw new PatternMatchFailException();
        }
        final String name = matcher.group(NAME_GRP);
        final String calories = matcher.group(CALORIES_GRP);
        final String date = matcher.group(DATE_GRP);

        try {
            double caloriesInDouble = Double.parseDouble(calories);
            if (caloriesInDouble < 0) {
                throw new IllegalValueException("Calories must not be a negative value.");
            }
            if (date == null) {
                return new Workout(name, new Calories(caloriesInDouble), Date.today());
            } else {
                return new Workout(name, new Calories(caloriesInDouble), new Date(date));
            }
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Calories must be a number.");
        }
    }
}
