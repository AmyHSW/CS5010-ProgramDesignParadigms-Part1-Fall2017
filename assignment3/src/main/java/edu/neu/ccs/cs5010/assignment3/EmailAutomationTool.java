package edu.neu.ccs.cs5010.assignment3;

public class EmailAutomationTool {

    private final MemberDatabase memberDB;
    private final EmailGenerator emailGenerator;

    public EmailAutomationTool(String[] args) {
        ArgumentParser argumentParser = new ArgumentParser(args);
        if (!argumentParser.isLegalFormat()) {
            throw new InvalidInputFormatException(argumentParser.getErrorMessage());
        }
        memberDB = new MemberDatabase(argumentParser.getCsvFile());
        emailGenerator = new EmailGenerator(argumentParser.getEmailTemplate(),
                                            argumentParser.getEvent(),
                                            argumentParser.getDepartureCity(),
                                            argumentParser.getArrivalCity());
    }

    public void automateEmailGeneration() {
        for (IMember member : memberDB.getMemberList()) {
            String email = emailGenerator.getEmail(member);

        }
    }

    public static void main(String[] args) {
        EmailAutomationTool emailAutomationTool = new EmailAutomationTool(args);
        emailAutomationTool.automateEmailGeneration();
    }

}
