package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

public class TemplateTest {

    private String[] args = {
            "--email-template", "email-template.txt",
            "--output-dir", "emails",
            "--csv-file", "Flight3FromVancouverToSeattle.csv",
            "--event", "arrival",
    };
    private Template template = null;

    @Before
    public void setUp() throws Exception {
        template = new Template("email-template.txt");
    }

    @Test
    public void generateEmail() throws Exception {
        EvaluatorContainer ec = new EvaluatorContainer(new DateEvaluator());
        PassengerInfo psInfo = new PassengerInfo("Flight3FromVancouverToSeattle.csv");
        CMDHandler cmdHandler = new CMDHandler(args);
        Evaluator constEvaluator = new ConstEvaluator(new FlightInfo(cmdHandler));
        ec.add(constEvaluator);
        while (psInfo.hasNextPassenger()) {
            ec.add(new PassengerEvaluator(psInfo.nextPassenger()));
            System.out.println(template.generateEmail(ec));
        }
    }

}