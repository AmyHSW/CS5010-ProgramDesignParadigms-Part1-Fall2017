package edu.neu.ccs.cs5010.Assignment2.section2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ERSimulator implements ERSimulatorConstants {

    private final IPriorityQueue<Patient> arrivalQueue;
    private final IPriorityQueue<ExaminationRoom> roomQueue;
    private final IPriorityQueue<Patient> examinationQueue;
    private final List<Patient> patientsList;

    public ERSimulator(int nRoom) {
        arrivalQueue = new MyPriorityQueue<>();
        roomQueue = new MyPriorityQueue<>();
        examinationQueue = new MyPriorityQueue<>(Patient.BY_DEPARTURE_TIME);
        patientsList = new ArrayList<>();
        initRoom(nRoom);
    }

    private void initRoom(int nRoom) {
        for (int i = 0; i < nRoom; i++) {
            roomQueue.insert(new ExaminationRoom());
        }
    }

    public void addPatient(Patient patient) {
        arrivalQueue.insert(patient);
        System.out.println((char)27 + "[31mAdded to line: " + (char)27 + "[0m" + patient);
        patientsList.add(patient);
        update();
    }

    public boolean isFree() {
        return examinationQueue.isEmpty() && arrivalQueue.isEmpty();
    }

    public void update() {
        LocalDateTime now = LocalDateTime.now();

        while (!examinationQueue.isEmpty() && examinationQueue.front().getDepartureTime().compareTo(now) <= 0) {
            Patient p = examinationQueue.remove();
            System.out.println(now.toLocalTime());
            System.out.println((char)27 + "[34mFinished treating patient (ID-" + p.getID() + ")" + (char)27 + "[0m");
            roomQueue.insert(p.getRoom());
        }

        while (!roomQueue.isEmpty() && !arrivalQueue.isEmpty()) {
            Patient p = arrivalQueue.front();
            p.startExamination(roomQueue.remove(), now);
            System.out.println((char)27 + "[32mStarted treating patient (ID-" + p.getID() + ")" + (char)27 + "[0m");
            p.getRoom().addBusyTime(Duration.between(now, p.getDepartureTime()));
            examinationQueue.insert(arrivalQueue.remove());
        }
    }

    public void reportPatientsAvrgWait() {
        if (!isFree()) {
            System.out.println("The treatment has not ended for all patients.");
            return;
        }

        Duration overallWait = Duration.ZERO;
        Duration urgencyOneToFourWait = Duration.ZERO;
        Duration urgencyNineToTenWait = Duration.ZERO;

        int nPatients = patientsList.size();
        int nPatientsUrgencyOneToFour = 0;
        int nPatientsUrgencyNineToTen = 0;

        for (Patient p : patientsList) {
            int urgency = p.getUrgencyLevel();
            Duration wait = p.getWaitTime();
            overallWait = overallWait.plus(wait);
            if (urgency >= 1 && urgency <= 4) {
                urgencyOneToFourWait = urgencyOneToFourWait.plus(wait);
                nPatientsUrgencyOneToFour++;
            } else if (urgency == 9 || urgency == 10) {
                urgencyNineToTenWait = urgencyNineToTenWait.plus(wait);
                nPatientsUrgencyNineToTen++;
            }
        }

        double overallAvrgWait = (double) overallWait.toMinutes() / nPatients;
        double urgencyOneToFourAvrgWait = (double)urgencyOneToFourWait.toMinutes() / nPatientsUrgencyOneToFour;
        double urgencyNineToTenAvrgWait = (double)urgencyNineToTenWait.toMinutes() / nPatientsUrgencyNineToTen;

        System.out.println("Average overall wait = " + overallAvrgWait + " min.");
        System.out.println("Patients (urgency lv 1 - 4) waited " + urgencyOneToFourAvrgWait + " min for average.");
        System.out.println("Patients (urgency lv 9 - 10) waited " + urgencyNineToTenAvrgWait + " min for average.");
    }

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

    public static void main(String[] args) throws Exception {
        ERSimulator simulator = new ERSimulator(N_ROOMS);
        PatientGenerator patientGenerator = new PatientGenerator();
        LocalDateTime startTime = LocalDateTime.now();

        System.out.println("Starts ER simulation with " + N_ROOMS + " rooms at " + startTime);

        while (LocalDateTime.now().compareTo(startTime.plus(SIMULATION_MAX_TIME)) <= 0) {
            simulator.addPatient(patientGenerator.next());
            Thread.sleep(PAUSE_MILLI_SECONDS);
        }
        while (!simulator.isFree()) {
            simulator.update();
            Thread.sleep(PAUSE_MILLI_SECONDS);
        }

        simulator.reportPatientsAvrgWait();
        simulator.reportRoomUsage(Duration.between(startTime, LocalDateTime.now()));
    }

}
