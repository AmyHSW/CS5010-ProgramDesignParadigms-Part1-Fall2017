package edu.neu.ccs.cs5010.assignment3;

import java.util.ArrayList;
import java.util.List;

public class EvaluatorContainer implements Evaluator {

    private final List<Evaluator> generalEvaluators = new ArrayList<>();
    private Evaluator psEvaluator;

    public EvaluatorContainer(Evaluator dateEvaluator) {
        generalEvaluators.add(dateEvaluator);
    }

    @Override
    public String getValue(String placeholder) {
        if (psEvaluator.getValue(placeholder) != null) {
            return psEvaluator.getValue(placeholder);
        }
        for (Evaluator evaluator : generalEvaluators) {
            if (evaluator.getValue(placeholder) != null) {
                return evaluator.getValue(placeholder);
            }
        }
        return null;
    }

    public void add(Evaluator evaluator) {
        if (evaluator instanceof PassengerEvaluator) {
            psEvaluator = evaluator;
        } else {
            generalEvaluators.add(evaluator);
        }
    }

}
