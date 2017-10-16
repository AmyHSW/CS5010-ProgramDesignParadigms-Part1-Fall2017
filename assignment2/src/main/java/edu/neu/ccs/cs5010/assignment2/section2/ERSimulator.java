package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ERSimulator</code> class models the treatment of patients in an emergency room.
 * <p>
 * When a patient arrives, it will firstly be added to the arrival queue. The simulator
 * constantly checks if any patient finishes treatment at current local time. When a patient
 * finishes examination, it will be removed from examination queue, and the corresponding
 * room will be added to the room queue. If the arrival queue is not empty, the program
 * will pick the patient with highest priority and start examination on that patient.
 * <p>
 * At the end of simulation, the ERSimulator will analyze and print to console the average
 * wait of patients for treatment and the usage information of examination rooms.
 *
 * @author Shuwan Huang
 */
public class ERSimulator implements IERSimulator {

  private static final int PRESET = 1;
  private final IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
  private final IPriorityQueue<IExaminationRoom> roomQueue = new MyPriorityQueue<>();
  private final IPriorityQueue<IPatient> examQueue = new MyPriorityQueue<>(Patient.BY_DEPARTURE_TIME);
  private final List<IPatient> patientsTreated = new ArrayList<>();

  private final IPrinter printer = new Printer();

  /**
   * Constructs a new ERSimulator with given number of examination rooms.
   * @param numRoom number of examination rooms.
   */
  public ERSimulator(int numRoom) {
    initRoom(numRoom);
  }

  // throws InvalidNumRoomException if numRoom is not positive.
  private void initRoom(int numRoom) {
    if (numRoom <= 0) {
      throw new InvalidNumRoomException("number of examination rooms "
                                        + numRoom + " is not positive.");
    }
    for (int i = 0; i < numRoom; i++) {
      roomQueue.insert(new ExaminationRoom());
    }
  }

  // adds a patient to this simulator. Updates the examination queue and room queue after
  // addition of patient.
  private void addPatient(IPatient patient) {
    arrivalQueue.insert(patient);
    printer.printNewPatient(patient);
    update();
  }


   // updates the examination queue and room queue based on the current local time. If
   // there are patients waiting on the arrival queue while examination room is available,
   // start examination on the patient and add the patient to examination queue.
  private void update() {
    LocalDateTime now = LocalDateTime.now();

    while (someoneHasFinishedTreatment(now)) {
      IPatient patient = examQueue.remove();
      patientsTreated.add(patient);
      printer.printFinishExamMsg(now, patient.getID());
      roomQueue.insert(patient.getRoom());
    }

    while (hasVacantRoomForPatient()) {
      IPatient patient = arrivalQueue.remove();
      patient.startExamination(roomQueue.remove(), now);
      printer.printStartExamMsg(now, patient.getID());
      patient.getRoom().addBusyTime(patient.getTreatmentDuration());
      examQueue.insert(patient);
    }
  }

  private boolean someoneHasFinishedTreatment(LocalDateTime now) {
    return !examQueue.isEmpty() && examQueue.front().getDepartureTime().isBefore(now);
  }

  private boolean hasVacantRoomForPatient() {
    return !roomQueue.isEmpty() && !arrivalQueue.isEmpty();
  }

  // returns true if some rooms are still busy or if some patients are still waiting,
  // returns false if all patients have finished treatment.
  private boolean isBusy() {
    return !examQueue.isEmpty() || !arrivalQueue.isEmpty();
  }

  /**
   * Runs the simulation at current local time, keeps generating patients during the given
   * duration of simulation and updating the simulation as it goes. If the simulator is
   * already busy, prints message to console and stops.
   * During the simulation period, generates patients and adds to the simulator.
   * After the simulation period, stops generating patients. When all the patients
   * have finished treatment, reports the results.
   *
   * @param simulationTime  the duration of time for generating patients.
   * @param patientGenerator a patient generator that generates patients.
   */
  @Override
  public void runSimulation(Duration simulationTime, IPatientGenerator patientGenerator) {
    if (isBusy()) {
      printer.printBusyMsg();
      return;
    }

    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plus(simulationTime);
    printer.printStartMsg(start);

    while (LocalDateTime.now().isBefore(end)) {
      IPatient patient = patientGenerator.next();
      if (patient != null) {
        addPatient(patient);
      } else {
        update();
      }
    }
    while (isBusy()) update();

    Duration actualRunTime = Duration.between(start, LocalDateTime.now());
    printer.printEndMsg(actualRunTime);
    reportPatientsInfo();
    reportRoomUsage(actualRunTime);
  }

  // Analyzes the average wait time for treatment. Prints to the console the overall average
  // wait time, average wait time for patients with urgency levels from 1 to 4, average wait
  // time for patients with urgency levels 9 or 10.
  private void reportPatientsInfo() {
    String results = Analyzer.analyzePatients(patientsTreated);
    printer.printMsg(results);
  }

  /**
   * Analyzes the usage of examination rooms. For each examination room, prints to the console the
   * number of patients treated in the room, and the percentage of time when the room is busy.
   *
   * @param duration the actual duration of this simulation.
   */
  private void reportRoomUsage(Duration duration) {
    List<IExaminationRoom> roomList = roomQueue.testForwardTraversal();
    String results = Analyzer.analyzeRooms(duration, roomList);
    printer.printMsg(results);
  }

  /**
   * Creates a ERSimulator object. Reads in command-line arguments from user. Uses a CMD
   * handler to parse the arguments. The arguments should contain information including
   * the simulation time, the number of examination rooms, the max pause between patient
   * generation, and the max treatment duration. Then run the simulation with given
   * parameters.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    ICMDHandler cmdHandler = new CMDHandler(args);
    if (!cmdHandler.isLegalFormat()) {
      System.out.println(cmdHandler.getErrorMessage());
      throw new InvalidInputFormatException("arguments are in wrong format.");
    }
    IERSimulator simulator = new ERSimulator(cmdHandler.getNumRooms());
    IPatientGenerator generator;
    if (cmdHandler.getMode() == PRESET) {
      generator = new PresetGenerator(cmdHandler.getMaxPause(), cmdHandler.getMaxTreatment());
    } else {
      generator = new RandomGenerator(cmdHandler.getMaxPause(), cmdHandler.getMaxTreatment());
    }
    simulator.runSimulation(cmdHandler.getSimulationTime(), generator);
  }

}
