package edu.neu.ccs.cs5010.assignment3;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateEvaluator implements Evaluator {

    private final String date;

    public DateEvaluator() {
        date = (new SimpleDateFormat()).format(Calendar.getInstance().getTime());
    }

    @Override
    public String getValue(String placeholder) {
        if (placeholder.equals("Date")) {
            return date;
        }
        return null;
    }
}
