package edu.neu.ccs.cs5010.assignment3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentsParser implements IArgumentsParser {

    private static final String EMAIL_TEMPLATE = "--email-template";
    private static final String OUTPUT_DIR = "--output-dir";
    private static final String CSV_FILE = "--csv-file";
    private static final String EVENT = "--event";
    private static final String USAGE_MSG =
            "Usage: \n\n"
            + "--email-template <file>      accepts a filename that holds the email template\n\n"
            + "--output-dir <path>          accepts the name of a folder, all output is placed\n"
            + "                             in this folder\n\n"
            + "--csv-file <path>            accepts the name of the csv file to process, in\n"
            + "                             a following format\n"
            + "                             Flight<id>From<departure-city>To<destination-city>.csv\n\n"
            + "--event <details>            specifies if the delay refers to departure/arrival\n\n"
            + "For example:\n\n"
            + "--email-template  email-template.txt  --output-dir  emails  --csv-file\n"
            + "Flight363FromSeattleToBoston.csv  --event  arrival\n";

    private final Map<String, String> input; // stores the input arguments
    private final StringBuilder errorMsg; // stores error message
    private final Map<String, String> flightInfo;

    public ArgumentsParser(String[] args) {
        input = new HashMap<>();
        input.put(EMAIL_TEMPLATE, null);
        input.put(OUTPUT_DIR, null);
        input.put(CSV_FILE, null);
        input.put(EVENT, null);

        errorMsg = new StringBuilder();
        flightInfo = new HashMap<>();

        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        int i = 0;
        while (i < args.length) {
            String arg = args[i++];
            if (!arg.startsWith("--")) continue;
            if (!input.containsKey(arg)) {
                errorMsg.append(arg);
                errorMsg.append(" cannot be recognized\n");
                continue;
            }
            if (args[i].startsWith("-")) {
                errorMsg.append(arg);
                errorMsg.append(" was given without providing appropriate argument\n");
                continue;
            }
            switch (arg) {
                case EMAIL_TEMPLATE: parseEmailTemplate(args[i++]); continue;
                case CSV_FILE: parseCsvFile(args[i++]); continue;
                case EVENT: parseEvent(args[i++]); continue;
                case OUTPUT_DIR: parseOutputDir(args[i++]); continue;
            }
        }
    }

    private void parseEmailTemplate(String template) {
        if (template.endsWith(".txt")) {
            input.put(EMAIL_TEMPLATE, template);
        } else {
            errorMsg.append(EMAIL_TEMPLATE);
            errorMsg.append(" argument does not provide a text filename\n");
        }
    }

    private void parseCsvFile(String file) {
        if (!file.endsWith(".csv")) {
            errorMsg.append(CSV_FILE);
            errorMsg.append(" argument is not a csv file;\n");
            return;
        }
        Pattern pattern = Pattern.compile("(Flight[0-9]+)From([A-Z][a-z]+)To([A-Z][a-z]+).csv");
        Matcher matcher = pattern.matcher(file);
        if (matcher.matches()) {
            input.put(CSV_FILE, file);
            flightInfo.put("flight-number", matcher.group(1));
            flightInfo.put("departure-city", matcher.group(2));
            flightInfo.put("destination-city", matcher.group(3));
        } else {
            errorMsg.append(CSV_FILE);
            errorMsg.append(" argument does not contain flight number or departure-city or destination-city\n");
        }
    }

    private void parseEvent(String event) {
        if (event.equals("arrival") || event.equals("departure")) {
            input.put(EVENT, event);
            flightInfo.put("event", event);
        } else {
            errorMsg.append(EVENT);
            errorMsg.append(" argument does not contain a departure/arrival event\n");
        }
    }

    private void parseOutputDir(String outputDir) {
        input.put(OUTPUT_DIR, outputDir);
    }

    @Override
    public boolean isLegalFormat() {
            return input.get(EMAIL_TEMPLATE) != null
                    && input.get(OUTPUT_DIR) != null
                    && input.get(CSV_FILE) != null
                    && input.get(EVENT) != null;
        }

    @Override
    public String getErrorMessage() {
        if (isLegalFormat()) return null;
        return "Error:\n\n" + errorMsg.toString() + "\n" + USAGE_MSG;
    }

    @Override
    public String getEmailTemplate() {
        if (!isLegalFormat()) return null;
        return input.get(EMAIL_TEMPLATE);
        }

    @Override
    public String getOutputDir() {
        if (!isLegalFormat()) return null;
        return input.get(OUTPUT_DIR);
        }

    @Override
    public String getCsvFile() {
        if (!isLegalFormat()) return null;
        return input.get(CSV_FILE);
        }

    @Override
    public Map<String, String> getFlightInfo() {
        if (!isLegalFormat()) return null;
        return flightInfo;
    }

}