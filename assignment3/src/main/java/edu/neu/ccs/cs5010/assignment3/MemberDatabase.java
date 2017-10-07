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
public class MemberDatabase implements IMemberDatabase {

    private final List<IMember> memberList;
    private static final String CSV_SPLIT_BY = "\",\""; // regular expression to split the csv file

    /**
     * Initializes the member list. Parses the csv file and stores the member information in Member objects.
     * Adds the Member objects to the member list.
     *
     * @param csvFileName the name of a csv file
     */
    public MemberDatabase(String csvFileName) {
        memberList = new ArrayList<>();
        parseCsvFile(csvFileName);
    }

    // reads each record in the csv file using a BufferedReader, extracts the member information from it
    private void parseCsvFile(String csvFileName) {
        try (BufferedReader csvFile = new BufferedReader(new FileReader(csvFileName))) {
            String line = csvFile.readLine();
            if (line == null) {
                throw new EmptyCsvFileException("The csv file is empty: " + csvFileName);
            }
            String[] attributes = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
            while ((line = csvFile.readLine()) != null) {
                String[] memberInfo = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
                addMember(attributes, memberInfo);
            }
        } catch (IOException ioe) {
            System.out.println("Something went wrong!: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    // puts the member information in a map, uses that map to initializes a Member object,
    // and adds it to the member list.
    private void addMember(String[] attributes, String[] memberInfo) {
        Map<String, String> info = new HashMap<>();
        for (int i = 0; i < memberInfo.length; i++) {
            info.put(attributes[i], memberInfo[i]);
        }
        memberList.add(new Member(info));
    }

    /**
     * Returns a list that contains all members in the csv file.
     * @return a list that contains all members in the csv file.
     */
    @Override
    public List<IMember> getMemberList() {
        return memberList;
    }

}
