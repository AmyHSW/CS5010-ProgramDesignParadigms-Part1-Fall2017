package edu.neu.ccs.cs5010.assignment2.section2;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The <code>ERSimulator</code> class models the treatment of patients in a hospital emergency room.
 *
 * When a patient arrives, it will firstly be added to the arrival queue. The simulator constantly checks if
 * any patient finishes treatment at current local time. When a patient finishes examination, it will be removed
 * from examination queue, and the corresponding room will be added to the room queue. If the arrival queue
 * is not empty, the program will pick the patient with highest priority and start examination on that patient.
 *
 * At the end of simulation, the ERSimulator will analyze and print to console the average wait of patients
 * for treatment and the usage information of examination rooms.
 *
 * @author Shuwan Huang
 */
public class ERSimulator implements IERSimulator, ERSimulatorConstants {

    // set protected only for testing!!
    protected final IPriorityQueue<IPatient> arrivalQueue;
    protected final IPriorityQueue<IExaminationRoom> roomQueue;
    protected final IPriorityQueue<IPatient> examinationQueue;
    private final List<IPatient> patientsList;

    /**
     * Constructs a new ERSimulator with given number of examination rooms.
     * @param numRoom number of examination rooms.
     * @throws InvalidNumRoomException if numRoom is not positive.
     */
    public ERSimulator(int numRoom) {
        if (numRoom <= 0) {
            throw new InvalidNumRoomException("number of examination rooms " + numRoom + " is not positive.");
        }

        arrivalQueue = new MyPriorityQueue<>();
        roomQueue = new MyPriorityQueue<>();
        examinationQueue = new MyPriorityQueue<>(Patient.BY_DEPARTURE_TIME);
        patientsList = new ArrayList<>();

        initRoom(numRoom);
    }

    private void initRoom(int numRoom) {
        for (int i = 0; i < numRoom; i++) {
            roomQueue.insert(new ExaminationRoom());
        }
    }

    /**
     * Adds a patient to this simulator. Updates the examination queue and room queue after addition of patient.
     * @param patient the Patient to be added.
     */
    @Override
    public void addPatient(IPatient patient) {
        arrivalQueue.insert(patient);
        System.out.println((char)27 + "[31mAdded to line: " + (char)27 + "[0m" + patient);
        patientsList.add(patient);
        update();
    }

    /**
     * Checks if all the patients have been treated.
     * @return true if all the patients have finished treatment; false otherwise.
     */
    @Override
    public boolean isFree() {
        return examinationQueue.isEmpty() && arrivalQueue.isEmpty();
    }

    /**
     * Updates the examination queue and room queue based on the current local time. If there are patients
     * waiting on the arrival queue while examination room is available, start examination on the patient
     * and add the patient to examination queue.
     */
    @Override
    public void update() {
        LocalDateTime now = LocalDateTime.now();

        while (!examinationQueue.isEmpty() && examinationQueue.front().getDepartureTime().compareTo(now) <= 0) {
            IPatient patient = examinationQueue.remove();
            System.out.println(now.toLocalTime());
            System.out.println((char)27 + "[34mFinished treating patient (ID-" + patient.getID() + ")" + (char)27 + "[0m");
            roomQueue.insert(patient.getRoom());
        }

        while (!roomQueue.isEmpty() && !arrivalQueue.isEmpty()) {
            IPatient patient = arrivalQueue.front();
            patient.startExamination(roomQueue.remove(), now);
            System.out.println((char)27 + "[32mStarted treating patient (ID-" + patient.getID() + ")" + (char)27 + "[0m");
            patient.getRoom().addBusyTime(patient.getTreatmentDuration());
            examinationQueue.insert(arrivalQueue.remove());
        }
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
     * @param startTime the start time of this simulation.
     * @param simulationTime the duration of time for generating patients.
     * @param maxPauseMs the max pause of milliseconds between patient generation
     * @param maxTreatmentMin the max treatment (minutes) of patient.
     */
    @Override
    public void runSimulation(LocalDateTime startTime, Duration simulationTime, int maxPauseMs, int maxTreatmentMin) {
        if (!isFree()) {
            System.out.println("The simulator is already busy.");
        }
        IPatientGenerator patientGenerator = new PatientGenerator(maxPauseMs, maxTreatmentMin);
        while (LocalDateTime.now().compareTo(startTime.plus(simulationTime)) <= 0) {
            IPatient patient = patientGenerator.next();
            if (patient != null) {
                addPatient(patient);
            } else {
                update();
            }
        }
        while (!isFree()) {
            update();
        }
        Duration actualRunTime = Duration.between(startTime, LocalDateTime.now());
        System.out.println("The simulation is over. It has run " + actualRunTime.toMinutes() + " min.");
        System.out.println("Now analyzing the results..");
        reportPatientsInfo();
        reportRoomUsage(actualRunTime);
    }

     // Analyzes the average wait time for treatment. Prints to the console the overall average wait time,
     // average wait time for patients with urgency levels from1 to 4, average wait time for patients with
     // urgency levels 9 or 10.
    private void reportPatientsInfo() {
        if (!isFree()) {
            System.out.println("The treatment has not ended for all patients.");
            return;
        }

        Duration overallWait = Duration.ZERO;
        Duration urgencyOneToFourWait = Duration.ZERO;
        Duration urgencyNineToTenWait = Duration.ZERO;
        Duration overallTreatmentDuration = Duration.ZERO;

        int nPatients = patientsList.size();
        int nPatientsUrgencyOneToFour = 0;
        int nPatientsUrgencyNineToTen = 0;

        for (IPatient patient : patientsList) {
            int urgency = patient.getUrgencyLevel();
            Duration wait = patient.getWaitDuration();
            overallWait = overallWait.plus(wait);
            overallTreatmentDuration = overallTreatmentDuration.plus(patient.getTreatmentDuration());
            if (urgency >= 1 && urgency <= 4) {
                urgencyOneToFourWait = urgencyOneToFourWait.plus(wait);
                nPatientsUrgencyOneToFour++;
            } else if (urgency == 9 || urgency == 10) {
                urgencyNineToTenWait = urgencyNineToTenWait.plus(wait);
                nPatientsUrgencyNineToTen++;
            }
        }

        double avrgOverallWait = (double) overallWait.toMinutes() / nPatients;
        double urgencyOneToFourAvrgWait = (double)urgencyOneToFourWait.toMinutes() / nPatientsUrgencyOneToFour;
        double urgencyNineToTenAvrgWait = (double)urgencyNineToTenWait.toMinutes() / nPatientsUrgencyNineToTen;
        double avrgTreatmentDuration = (double) overallTreatmentDuration.toMinutes() / nPatients;

        System.out.println("This emergency room has treated " + nPatients + " patients.");
        System.out.println("Average overall wait = " + avrgOverallWait + " min.");
        System.out.println("Patients (urgency lv 1 - 4) waited " + urgencyOneToFourAvrgWait + " min for average.");
        System.out.println("Patients (urgency lv 9 - 10) waited " + urgencyNineToTenAvrgWait + " min for average.");
        System.out.println("Average duration of treatment is " + avrgTreatmentDuration + " min.");
    }

    /**
     * Analyzes the usage of examination rooms. For each examination room, prints to the console the
     * number of patients treated in the room, and the percentage of time when the room is busy.
     *
     * @param duration the duration of the simulation.
     */
    private void reportRoomUsage(Duration duration) {
        if (!isFree()) {
            System.out.println("Some examination rooms are still being used.");
            return;
        }

        List<IExaminationRoom> roomList = roomQueue.testForwardTraversal();
        int roomID = 0;
        long totalMinutes = duration.toMinutes();
        for (IExaminationRoom room : roomList) {
            System.out.println("(" + ++roomID  + ") " + room
                               + ": busy% = " + (double)room.getBusyTime().toMinutes() / totalMinutes);
        }
    }

    /**
     * Creates a ERSimulator object. Reads in 4 integers from user to set the time for simulation, the number
     * of examination rooms, the max pause between patient generation, and the max treatment duration.
     * Then run the simulation with given parameters.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numRoom;
        Duration simulationTime;
        int maxPause, maxTreatment;
        System.out.print("Please enter simulation time (min), the number of examination rooms,");
        System.out.println(" max pause between generation of patients (ms), and max treatment time (min): ");
        while (true) {
            try {
                simulationTime = Duration.ofMinutes(sc.nextInt());
                numRoom = sc.nextInt();
                maxPause = sc.nextInt();
                maxTreatment = sc.nextInt();
                if (simulationTime.compareTo(SIMULATION_MAX_TIME) <= 0 && numRoom > 0
                        && simulationTime.compareTo(Duration.ZERO) > 0 && maxPause > 0 && maxTreatment > 0
                        && maxPause <= MAX_PAUSE_ADD_PATIENT && maxTreatment <= MAX_TREATMENT_MINUTES) {
                    break;
                }
                System.out.print("Please enter number of minutes less than " + SIMULATION_MAX_TIME.toMinutes());
                System.out.println(" and positive number of rooms.");
                System.out.println("Please enter max pause less than " + MAX_PAUSE_ADD_PATIENT + " ms.");
                System.out.println("Please enter max treatment less than " + MAX_TREATMENT_MINUTES + "min.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Please enter 4 integers.");
            }
        }
        sc.close();
        IERSimulator simulator = new ERSimulator(numRoom);
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("Starts ER simulation with " + numRoom + " rooms at " + startTime);
        simulator.runSimulation(startTime, simulationTime, maxPause, maxTreatment);
    }

}
