package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

/**
 * The <code>TemplateTest</code> is a test class for Template.
 *
 * @author Shuwan Huang
 */
public class TemplateTest {

  private ITemplate template = null;

  /**
   * Initializes a new Template object.
   */
  @Before
  public void setUp() throws Exception {
    template = new Template("email-template.txt");
  }

  /**
   * Tests that Template throws an InvalidPlaceholderException when placeholder info cannot
   * be determined.
   */
  @Test(expected = InvalidPlaceholderException.class)
  public void expectedInvalidPlaceholderException() throws Exception {
    IEmail email = template.toEmail(new DateEvaluator());
  }

}