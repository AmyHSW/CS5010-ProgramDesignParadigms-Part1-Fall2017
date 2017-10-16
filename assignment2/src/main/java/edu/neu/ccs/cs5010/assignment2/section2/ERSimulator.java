package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The <code>ERSimulator</code> class models the treatment of patients in a hospital emergency room.
 * <p>
 * When a patient arrives, it will firstly be added to the arrival queue. The simulator constantly checks if
 * any patient finishes treatment at current local time. When a patient finishes examination, it will be removed
 * from examination queue, and the corresponding room will be added to the room queue. If the arrival queue
 * is not empty, the program will pick the patient with highest priority and start examination on that patient.
 * <p>
 * At the end of simulation, the ERSimulator will analyze and print to console the average wait of patients
 * for treatment and the usage information of examination rooms.
 *
 * @author Shuwan Huang
 */
public class ERSimulator implements IERSimulator {

  private static final int PRESET = 1;
  private final IPriorityQueue<IPatient> arrivalQueue = new MyPriorityQueue<>();
  private final IPriorityQueue<IExaminationRoom> roomQueue = new MyPriorityQueue<>();
  private final IPriorityQueue<IPatient> examinationQueue = new MyPriorityQueue<>(Patient.BY_DEPARTURE_TIME);
  private final List<IPatient> patientsTreated = new ArrayList<>();

  private final IPrinter printer = new Printer();

  /**
   * Constructs a new ERSimulator with given number of examination rooms.
   * @param numRoom number of examination rooms.
   * @throws InvalidNumRoomException if numRoom is not positive.
   */
  public ERSimulator(int numRoom) {
    if (numRoom <= 0) {
      throw new InvalidNumRoomException("number of examination rooms " + numRoom + " is not positive.");
    }

    initRoom(numRoom);
  }

  private void initRoom(int numRoom) {
    for (int i = 0; i < numRoom; i++) {
      roomQueue.insert(new ExaminationRoom());
    }
  }

  /**
   * Adds a patient to this simulator. Updates the examination queue and room queue after addition of patient.
   *
   * @param patient the Patient to be added.
   */
  private void addPatient(IPatient patient) {
    arrivalQueue.insert(patient);
    printer.printNewPatient(patient);
    patientsTreated.add(patient);
    update();
  }

  /**
   * Updates the examination queue and room queue based on the current local time. If there are patients
   * waiting on the arrival queue while examination room is available, start examination on the patient
   * and add the patient to examination queue.
   */
  private void update() {
    LocalDateTime now = LocalDateTime.now();

    while (someoneHasFinishedTreatment(now)) {
      IPatient patient = examinationQueue.remove();
      printer.printFinishMsg(now, patient.getID());
      roomQueue.insert(patient.getRoom());
    }

    while (hasVacantRoomForPatient()) {
      IPatient patient = arrivalQueue.remove();
      patient.startExamination(roomQueue.remove(), now);
      printer.printStartMsg(now, patient.getID());
      patient.getRoom().addBusyTime(patient.getTreatmentDuration());
      examinationQueue.insert(patient);
    }
  }

  private boolean someoneHasFinishedTreatment(LocalDateTime now) {
    return !examinationQueue.isEmpty() && examinationQueue.front().getDepartureTime().isBefore(now);
  }

  private boolean hasVacantRoomForPatient() {
    return !roomQueue.isEmpty() && !arrivalQueue.isEmpty();
  }

  /**
   * Checks if all the patients have been treated.
   *
   * @return true if all the patients have finished treatment; false otherwise.
   */
  private boolean isFree() {
    return examinationQueue.isEmpty() && arrivalQueue.isEmpty();
  }

  /**
   * Runs the simulation at given start time, keeps generating patients during the given duration
   * of simulation and updating the simulation as it goes. If the simulator is already busy, prints message
   * to console and stops.
   * Sets the max pause and max treatment as the given parameters to patient generator.
   * During the simulation period, generates patients and adds to the simulator.
   * After the simulation period, stops generating patients. When all the patients have finished treatment,
   * reports the results.
   *
   * @param simulationTime  the duration of time for generating patients.
   */
  @Override
  public void runSimulation(Duration simulationTime, IPatientGenerator patientGenerator) {
    LocalDateTime start = LocalDateTime.now();
    while (LocalDateTime.now().isBefore(start.plus(simulationTime))) {
      IPatient patient = patientGenerator.next();
      if (patient != null) {
        addPatient(patient);
      } else {
        update();
      }
    }
    while (!isFree()) update();
    Duration actualRunTime = Duration.between(start, LocalDateTime.now());
    System.out.println("The simulation is over. It has run " + actualRunTime.toMinutes() + " min.");
    System.out.println("Now analyzing the results..");
    reportPatientsInfo();
    reportRoomUsage(actualRunTime);
  }

  // Analyzes the average wait time for treatment. Prints to the console the overall average wait time,
  // average wait time for patients with urgency levels from1 to 4, average wait time for patients with
  // urgency levels 9 or 10.
  private void reportPatientsInfo() {
    String results = Analyzer.analyzePatients(patientsTreated);
    printer.printResults(results);
  }

  /**
   * Analyzes the usage of examination rooms. For each examination room, prints to the console the
   * number of patients treated in the room, and the percentage of time when the room is busy.
   *
   * @param duration the duration of the simulation.
   */
  private void reportRoomUsage(Duration duration) {
    List<IExaminationRoom> roomList = roomQueue.testForwardTraversal();
    String results = Analyzer.analyzeRooms(duration, roomList);
    printer.printResults(results);
  }

  /**
   * Creates a ERSimulator object. Reads in 4 integers from user to set the time for simulation, the number
   * of examination rooms, the max pause between patient generation, and the max treatment duration.
   * Then run the simulation with given parameters.
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
    IPatientGenerator patientGenerator;
    if (cmdHandler.getMode() == PRESET) {
      patientGenerator = new PresetGenerator(cmdHandler.getMaxPause(), cmdHandler.getMaxTreatment());
    } else {
      patientGenerator = new RandomGenerator(cmdHandler.getMaxPause(), cmdHandler.getMaxTreatment());
    }
    simulator.runSimulation(cmdHandler.getSimulationTime(), patientGenerator);
  }

}
