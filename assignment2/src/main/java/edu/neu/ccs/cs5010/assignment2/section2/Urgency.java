package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * The <code>Urgency</code> class represents the urgency rating of patients.
 *
 * @author Shuwan Huang
 */
public class Urgency implements IUrgency {

  private static final int MIN_URGENCY_LEVEL = 1;
  private static final int MAX_URGENCY_LEVEL = 10;
  private final int level;

  /**
   * Constructs a new Urgency object with the urgency level.
   *
   * @param level an integer between 1 and 10
   */
  public Urgency(int level) {
    if (level < MIN_URGENCY_LEVEL || level > MAX_URGENCY_LEVEL) {
      throw new InvalidUrgencyLevelException("urgency level (" + level
                                             + ") is not between 1 and 10.");
    }
    this.level = level;
  }

  /**
   * Returns the urgency level of this urgency
   * @return the urgency level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Compares this urgency with other urgency.
   * @param that the other urgency object
   * @return a negative integer if this urgency has higher priority, returns a positive
   * integer if the other urgency has higher priority, returns zero if theses two
   * urgency objects have equal priority.
   */
  @Override
  public int compareTo(IUrgency that) {
    return this.level - that.getLevel();
  }

  /**
   * Compares this urgency to the specified urgency.
   *
   * @param other the other urgency.
   * @return true if this urgency equals <code>other</code>; false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    Urgency urgency = (Urgency) other;

    return level == urgency.level;
  }

  /**
   * Returns an integer hash code for this urgency.
   *
   * @return an integer hash code for this urgency.
   */
  @Override
  public int hashCode() {
    return level;
  }
}
