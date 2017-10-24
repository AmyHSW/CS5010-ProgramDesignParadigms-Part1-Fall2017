package edu.neu.ccs.cs5010.assignment4;

/**
 * The <code>CandyVisitor</code> class is a boolean type visitor. When a CandyVisitor is declared,
 * it will be given a candy name. The job of this visitor is to visit candies to see if they are the
 * candy that it is looking for.
 *
 * @author Shuwan Huang
 */
public class CandyVisitor implements GenericVisitor<Boolean> {

  private final String candyName;

  /**
   * Constructs a new CandyVisitor with a candy name.
   * @param candyName a string of candy name.
   */
  public CandyVisitor(String candyName) {
    this.candyName = evaluate(candyName);
  }

  private String evaluate(String candyName) {
    if (candyName == null) {
      throw new IllegalArgumentException("candy name is null");
    }
    candyName = candyName.toLowerCase();
    if (!candyName.contains("size")) {
      return "regular size " + candyName;
    } else {
      return candyName;
    }
  }

  /**
   * Returns true if the candidate candy matches the one that this visitor is looking for.
   * @param candy a candidate candy
   * @return true if the candidate candy matches the one that this visitor is looking for
   */
  @Override
  public Boolean visit(Candy candy) {
    return candyName.equals((candy.getSize() + " " + candy.getType()).toLowerCase());
  }

  /**
   * Compares this candy visitor to the specified object.
   * @param other the other object
   * @return true if this candy visitor equals the other
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    CandyVisitor that = (CandyVisitor) other;

    return candyName != null ? candyName.equals(that.candyName) : that.candyName == null;
  }

  /**
   * Returns an integer that represents the hash code of this object.
   * @return an integer that represents the hash code of this object.
   */
  @Override
  public int hashCode() {
    return candyName.hashCode();
  }
}
