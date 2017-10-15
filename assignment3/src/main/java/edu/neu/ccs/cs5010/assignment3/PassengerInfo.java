package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The <code>MemberDatabase</code> class stores the member information in a list of members.
 * The member information is provided by the csv file.
 *
 * This class provides a method <code>getMemberList</code> that returns the list of members.
 *
 * @author Shuwan Huang
 */
public class PassengerInfo {

    private final List<Passenger> psList = new ArrayList<>();
    private static final String CSV_SPLIT_BY = "\",\""; // regular expression to split the csv file
    private int psID;

    /**
     * Initializes the member list. Parses the csv file and stores the member information in Member objects.
     * Adds the Member objects to the member list.
     *
     * @param csvFileName the name of a csv file
     */
    public PassengerInfo(String csvFileName) throws IOException {
        parseCsvFile(csvFileName);
    }

    // reads each record in the csv file using a BufferedReader, extracts the member information from it
    private void parseCsvFile(String csvFileName) throws IOException {
        BufferedReader csvFile = new BufferedReader(new FileReader(csvFileName));
            String line = csvFile.readLine();
            if (line == null) {
                throw new EmptyCsvFileException("The csv file is empty: " + csvFileName);
            }
            String[] headers = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
            while ((line = csvFile.readLine()) != null) {
                String[] values = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
                addPassenger(headers, values);
            }
    }

    // puts the member information in a map, uses that map to initializes a Member object,
    // and adds it to the member list.
    private void addPassenger(String[] headers, String[] values) {
        Map<String, String> info = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            info.put(headers[i], values[i]);
        }
        psList.add(new Passenger(info));
    }

    public boolean hasNextPassenger() {
        return psID < psList.size();
    }

    public Passenger nextPassenger() {
        return psList.get(psID++);
    }

}
