package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * The <code>IPatientGenerator</code> interface represents the patient generator.
 *
 * @author Shuwan Huang
 */
public interface IPatientGenerator {

  /**
   * Generates a new patient.
   *
   * @return an IPatient object.
   */
  IPatient next();

}
