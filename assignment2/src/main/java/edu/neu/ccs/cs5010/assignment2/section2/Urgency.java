package edu.neu.ccs.cs5010.assignment2.section2;

public class Urgency implements IUrgency {

  private static final int MIN_URGENCY_LEVEL = 1;
  private static final int MAX_URGENCY_LEVEL = 10;
  private final int level;

  public Urgency(int level) {
    if (level < MIN_URGENCY_LEVEL || level > MAX_URGENCY_LEVEL) {
      throw new InvalidUrgencyLevelException("urgency level (" + level + ") is not between 1 and 10.");
    }
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  @Override
  public int compareTo(IUrgency that) {
    return this.level - that.getLevel();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Urgency urgency = (Urgency) o;

    return level == urgency.level;
  }

  @Override
  public int hashCode() {
    return level;
  }
}
