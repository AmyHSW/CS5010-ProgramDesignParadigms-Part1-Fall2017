package edu.neu.ccs.cs5010.assignment3;

import java.util.Map;

public class Member implements IMember {

    private final Map<String, String> info;

    public Member(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public boolean hasAttribute(String attribute) {
        return info.containsKey(attribute);
    }

    @Override
    public String getAttribute(String attribute) {
        if (!hasAttribute(attribute)) {
            return null;
        }
        return info.get(attribute);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return info != null ? info.equals(member.info) : member.info == null;
    }

    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }
}
