package edu.neu.ccs.cs5010.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDatabase implements IMemberDatabase {

    private final List<IMember> memberList;
    private static final String CSV_SPLIT_BY = "\",\"";

    public MemberDatabase(String csvFileName) {
        memberList = new ArrayList<>();
        parseCsvFile(csvFileName);
    }

    private void parseCsvFile(String csvFileName) {
        try (BufferedReader csvFile = new BufferedReader(new FileReader(csvFileName))) {
            String line = csvFile.readLine();
            if (line == null) {
                throw new EmptyCsvFileException("The csv file is empty: " + csvFileName);
            }
            String[] attribute = line.substring(1, line.length() - 1).split(CSV_SPLIT_BY);
            while ((line = csvFile.readLine()) != null) {
                String[] memberInfo = line.split(CSV_SPLIT_BY);
                addMember(attribute, memberInfo);
            }
        } catch (IOException ioe) {
            System.out.println("Something went wrong!: " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    private void addMember(String[] attribute, String[] memberInfo) {
        Map<String, String> info = new HashMap<>();
        for (int i = 0; i < memberInfo.length; i++) {
            info.put(attribute[i], memberInfo[i]);
        }
        memberList.add(new Member(info));
    }

    @Override
    public List<IMember> getMemberList() {
        return memberList;
    }

}
