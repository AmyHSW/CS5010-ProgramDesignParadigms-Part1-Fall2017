package edu.neu.ccs.cs5010.assignment3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightInfo {

    private Map<String, String> info = new HashMap<>();

    public FlightInfo(CMDHandler cmdHandler) {
        parseFilename(cmdHandler.getCsvFile());
        parseEvent(cmdHandler.getEvent());
    }

    private void parseFilename(String file) {
        Pattern pattern = Pattern.compile("Flight([0-9]+)From([A-Z][a-z]+)To([A-Z][a-z]+).csv");
        Matcher matcher = pattern.matcher(file);
        if (matcher.matches()) {
            info.put("id", matcher.group(1));
            info.put("departure-city", matcher.group(2));
            info.put("destination-city", matcher.group(3));
        }
    }

    private void parseEvent(String event) {
        info.put("event", event);
    }

    public String getInfo(String header) {
        return info.getOrDefault(header, null);
    }

}
