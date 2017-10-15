package edu.neu.ccs.cs5010.assignment3;

public class PassengerEvaluator implements Evaluator {

    private Passenger ps;

    public PassengerEvaluator(Passenger ps) {
        this.ps = ps;
    }

    @Override
    public String getValue(String placeholder) {
        return ps.getAttribute(placeholder);
    }

}
