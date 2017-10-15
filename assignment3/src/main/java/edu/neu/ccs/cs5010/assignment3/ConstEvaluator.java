package edu.neu.ccs.cs5010.assignment3;

public class ConstEvaluator implements Evaluator {

    private final FlightInfo info;

    public ConstEvaluator(FlightInfo info) {
        this.info = info;
    }

    @Override
    public String getValue(String placeholder) {
        return info.getInfo(placeholder);
    }
}
