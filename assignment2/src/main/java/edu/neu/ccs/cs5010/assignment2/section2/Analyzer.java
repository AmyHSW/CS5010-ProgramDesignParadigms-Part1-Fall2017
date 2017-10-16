package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.util.List;

public class Analyzer {

  private static final IUrgency LOW_URGENCY = new Urgency(9);
  private static final IUrgency HIGH_URGENCY = new Urgency(4);

  public static String analyzePatients(List<IPatient> patients) {
    Duration overallWait = Duration.ZERO;
    Duration lowUrgencyWait = Duration.ZERO;
    Duration highUrgencyWait = Duration.ZERO;
    Duration overallTreatmentDuration = Duration.ZERO;

    int nPatients = patients.size();
    int nPatientsLowUrgency = 0;
    int nPatientsHighUrgency = 0;

    for (IPatient patient : patients) {
      IUrgency urgency = patient.getUrgency();
      Duration wait = patient.getWaitDuration();
      overallWait = overallWait.plus(wait);
      overallTreatmentDuration = overallTreatmentDuration.plus(patient.getTreatmentDuration());
      if (urgency.compareTo(HIGH_URGENCY) <= 0) {
        highUrgencyWait = highUrgencyWait.plus(wait);
        nPatientsHighUrgency++;
      } else if (urgency.compareTo(LOW_URGENCY) >= 0) {
        lowUrgencyWait = lowUrgencyWait.plus(wait);
        nPatientsLowUrgency++;
      }
    }

    double avrgOverallWait = (double) overallWait.toMinutes() / nPatients;
    double highUrgencyAvrgWait = (double) highUrgencyWait.toMinutes() / nPatientsHighUrgency;
    double lowUrgencyAvrgWait = (double) lowUrgencyWait.toMinutes() / nPatientsLowUrgency;
    double avrgTreatmentDuration = (double) overallTreatmentDuration.toMinutes() / nPatients;

    StringBuilder sb = new StringBuilder();

    sb.append("This emergency room has treated " + nPatients + " patients.\n");
    sb.append("Average overall wait = " + avrgOverallWait + " min.\n");
    sb.append("Patients (urgency lv 1 - 4) waited " + highUrgencyAvrgWait + " min for average.\n");
    sb.append("Patients (urgency lv 9 - 10) waited " + lowUrgencyAvrgWait + " min for average.\n");
    sb.append("Average duration of treatment is " + avrgTreatmentDuration + " min.");

    return sb.toString();
  }

  public static String analyzeRooms(Duration duration, List<IExaminationRoom> rooms) {
    int roomID = 0;
    int totalMinutes = (int)duration.toMinutes();

    StringBuilder sb = new StringBuilder();

    for (IExaminationRoom room : rooms) {
      sb.append("(" + ++roomID + ") " + room
                + ": busy% = " + (double) room.getBusyTime().toMinutes() / totalMinutes);
    }
    return sb.toString();
  }
}
