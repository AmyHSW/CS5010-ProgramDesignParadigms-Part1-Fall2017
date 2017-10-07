package edu.neu.ccs.cs5010.assignment3;

public class EmailAutomationTool {

    private final MemberDatabase memberDB;
    private final EmailGenerator emailGenerator;

    public EmailAutomationTool(String[] args) {
        ArgumentsParser argumentParser = new ArgumentsParser(args);
        if (!argumentParser.isLegalFormat()) {
            System.out.println(argumentParser.getErrorMessage());
            throw new InvalidInputFormatException("command-line arguments are in wrong format");
        }
        memberDB = new MemberDatabase(argumentParser.getCsvFile());
        emailGenerator = new EmailGenerator(argumentParser.getEmailTemplate(), argumentParser.getFlightInfo());
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
