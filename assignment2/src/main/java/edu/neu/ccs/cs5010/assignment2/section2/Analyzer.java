package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.util.List;

/**
 * The <code>Analyzer</code> class analyzes the patients information and room usage after
 * the simulation, and returns the results.
 *
 * @author Shuwan Huang
 */
public class Analyzer {

  private static final IUrgency LATER_URGENCY = new Urgency(9);
  private static final IUrgency PRIOR_URGENCY = new Urgency(4);

  /**
   *  Analyzes the average wait time for treatment. Returns a string that contains info
   *  of the overall average wait time, average wait time for patients with urgency
   *  levels from 1 to 4, average wait time for patients with urgency levels 9 or 10.
   *
   *  @param patients the patients that have been treated
   *  @return a string that contains analysis results
   */
  public static String analyzePatients(List<IPatient> patients) {
    Duration overallWait = Duration.ZERO;
    Duration laterUrgencyWait = Duration.ZERO;
    Duration priorUrgencyWait = Duration.ZERO;
    Duration overallTreatmentDuration = Duration.ZERO;

    int nPatients = patients.size();
    int nPatientsLaterUrgency = 0;
    int nPatientsPriorUrgency = 0;

    for (IPatient patient : patients) {
      IUrgency urgency = patient.getUrgency();
      Duration wait = patient.getWaitDuration();
      overallWait = overallWait.plus(wait);
      overallTreatmentDuration = overallTreatmentDuration.plus(patient.getTreatmentDuration());
      if (urgency.compareTo(PRIOR_URGENCY) <= 0) {
        priorUrgencyWait = priorUrgencyWait.plus(wait);
        nPatientsPriorUrgency++;
      } else if (urgency.compareTo(LATER_URGENCY) >= 0) {
        laterUrgencyWait = laterUrgencyWait.plus(wait);
        nPatientsLaterUrgency++;
      }
    }

    double avrgOverallWait = (double) overallWait.toMinutes() / nPatients;
    double priorUrgencyAvrgWait = (double) priorUrgencyWait.toMinutes() / nPatientsPriorUrgency;
    double laterUrgencyAvrgWait = (double) laterUrgencyWait.toMinutes() / nPatientsLaterUrgency;
    double avrgTreatmentDuration = (double) overallTreatmentDuration.toMinutes() / nPatients;

    return getPatientsAnalysisResults(nPatients, avrgOverallWait,
                                      priorUrgencyAvrgWait, laterUrgencyAvrgWait,
                                      avrgTreatmentDuration);
  }


  private static String getPatientsAnalysisResults(int nPatients, double avrgOverallWait,
                                                   double priorUrgencyAvrgWait,
                                                   double laterUrgencyAvrgWait,
                                                   double avrgTreatmentDuration) {
    return "This emergency room has treated " + nPatients + " patients.\n"
         + "Average overall wait = " + avrgOverallWait + " min.\n"
         + "Patients (urgency lv 1 - 4) waited " + priorUrgencyAvrgWait + " min for average.\n"
         + "Patients (urgency lv 9 - 10) waited " + laterUrgencyAvrgWait + " min for average.\n"
         + "Average duration of treatment is " + avrgTreatmentDuration + " min.";
  }

  /**
   * Analyzes the usage of examination rooms. For each examination room, analyzes the
   * number of patients treated in the room, and the percentage of time when the room is busy.
   *
   * @param duration the actual duration of this simulation.
   * @param rooms the examination rooms
   * @return a string that contains the analysis results
   */
  public static String analyzeRooms(Duration duration, List<IExaminationRoom> rooms) {
    int roomID = 0;
    int totalMinutes = (int)duration.toMinutes();

    StringBuilder sb = new StringBuilder();

    for (IExaminationRoom room : rooms) {
      String msg = "(" + ++roomID + ") " + room + ": busy% = "
                  + (double) room.getBusyTime().toMinutes() / totalMinutes + "\n";
      sb.append(msg);
    }
    return sb.toString();
  }
}
