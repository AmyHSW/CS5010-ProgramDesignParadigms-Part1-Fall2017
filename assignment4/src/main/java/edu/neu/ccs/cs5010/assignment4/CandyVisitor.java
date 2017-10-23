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

}
