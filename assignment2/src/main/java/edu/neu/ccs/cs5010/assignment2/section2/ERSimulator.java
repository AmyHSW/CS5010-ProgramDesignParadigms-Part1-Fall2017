package edu.neu.ccs.cs5010.assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The <code>ERSimulator</code> class models the treatment of patients in a hospital emergency room.
 *
 * @author Shuwan Huang
 */
public class ERSimulator implements ERSimulatorConstants {

    // set protected for testing
    protected final IPriorityQueue<Patient> arrivalQueue;
    protected final IPriorityQueue<ExaminationRoom> roomQueue;
    protected final IPriorityQueue<Patient> examinationQueue;
    private final List<Patient> patientsList;

    /**
     * Constructs a new ERSimulator with given number of examination rooms.
     * @param numRoom number of examination rooms.
     */
    public ERSimulator(int numRoom) {
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
    public void addPatient(Patient patient) {
        arrivalQueue.insert(patient);
        System.out.println((char)27 + "[31mAdded to line: " + (char)27 + "[0m" + patient);
        patientsList.add(patient);
        update();
    }

    /**
     * Checks if all the patients have been treated.
     * @return true if all the patients have finished treatment; false otherwise.
     */
    public boolean isFree() {
        return examinationQueue.isEmpty() && arrivalQueue.isEmpty();
    }

    /**
     * Updates the examination queue and room queue based on the current local time. If there are patients
     * waiting on the arrival queue while examination room is available, start examination on the patient
     * and add the patient to examination queue.
     */
    public void update() {
        LocalDateTime now = LocalDateTime.now();

        while (!examinationQueue.isEmpty() && examinationQueue.front().getDepartureTime().compareTo(now) <= 0) {
            Patient patient = examinationQueue.remove();
            System.out.println(now.toLocalTime());
            System.out.println((char)27 + "[34mFinished treating patient (ID-" + patient.getID() + ")" + (char)27 + "[0m");
            roomQueue.insert(patient.getRoom());
        }

        while (!roomQueue.isEmpty() && !arrivalQueue.isEmpty()) {
            Patient patient = arrivalQueue.front();
            patient.startExamination(roomQueue.remove(), now);
            System.out.println((char)27 + "[32mStarted treating patient (ID-" + patient.getID() + ")" + (char)27 + "[0m");
            patient.getRoom().addBusyTime(patient.getTreatmentDuration());
            examinationQueue.insert(arrivalQueue.remove());
        }
    }

    /**
     * Analyzes the average wait time for treatment. Prints to the console the overall average wait time,
     * average wait time for patients with urgency levels from1 to 4, average wait time for patients with
     * urgency levels 9 or 10.
     */
    public void reportPatientsAvrgWaitAndTreatment() {
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

        for (Patient patient : patientsList) {
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

        System.out.println("Average overall wait = " + avrgOverallWait + " min.");
        System.out.println("Patients (urgency lv 1 - 4) waited " + urgencyOneToFourAvrgWait + " min for average.");
        System.out.println("Patients (urgency lv 9 - 10) waited " + urgencyNineToTenAvrgWait + " min for average.");
        System.out.println("Average duration of treatment is " + avrgTreatmentDuration + " min.");
    }

    /**
     * Analyzes the usage of examination rooms. For each examination room, prints to the console the
     * number of patients treated in the room, and the percentage of time when the room is busy.
     * @param duration the duration of the simulation.
     */
    public void reportRoomUsage(Duration duration) {
        if (!isFree()) {
            System.out.println("Some examination rooms are still being used.");
            return;
        }

        List<ExaminationRoom> roomList = roomQueue.testForwardTraversal();
        int roomID = 0;
        long totalMinutes = duration.toMinutes();
        for (ExaminationRoom room : roomList) {
            System.out.println("(" + ++roomID  + ") " + room
                               + ": busy% = " + (double)room.getBusyTime().toMinutes() / totalMinutes);
        }
    }

    /**
     * Creates a ERSimulator and a PatientGenerator. Reads in an integer from user to set the time for simulation.
     * During the simulation period, generates patients and adds to the simulator in a certain frequency.
     * After the simulation period, stops generating patients. When all the patients have finished treatment,
     * reports the average wait of patients and the usage information of examination rooms.
     *
     * @param args the command-line arguments
     * @throws InterruptedException if any thread has interrupted the current thread, which will not occur
     * in this simulation.
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int numRoom = 0;
        Duration simulationTime = null;
        System.out.println("Please enter the time (minutes) for simulation and the number of examination rooms:");
        while (true) {
            try{
                simulationTime = Duration.ofMinutes(sc.nextInt());
                numRoom = sc.nextInt();
                if (simulationTime.compareTo(SIMULATION_MAX_TIME) <= 0 && numRoom > 0) {
                    break;
                }
                System.out.print("Please enter number of minutes less than " + SIMULATION_MAX_TIME.toMinutes());
                System.out.println(" and positive number of rooms.");
            } catch (InputMismatchException ex) {
                sc.nextLine();
                System.out.println("Please enter two integers.");
            }
        }
        sc.close();

        ERSimulator simulator = new ERSimulator(numRoom);
        PatientGenerator patientGenerator = new PatientGenerator();
        LocalDateTime startTime = LocalDateTime.now();

        System.out.println("Starts ER simulation with " + numRoom + " rooms at " + startTime);
        while (LocalDateTime.now().compareTo(startTime.plus(simulationTime)) <= 0) {
            simulator.addPatient(patientGenerator.next());
            Thread.sleep(PAUSE_ADD_PATIENT);
        }
        while (!simulator.isFree()) {
            simulator.update();
            Thread.sleep(PAUSE_BETWEEN_UPDATE);
        }

        simulator.reportPatientsAvrgWaitAndTreatment();
        simulator.reportRoomUsage(Duration.between(startTime, LocalDateTime.now()));
    }

}
