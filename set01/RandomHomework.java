package Assignment1;

import java.util.Random;

/**
 * RandomHomework selects a random course name to display to the user.
 */
public class RandomHomework {

    // number of courses
    private static final int N_COURSES = 4;
    // array of courses
    private static final String[] COURSES = new String[N_COURSES];

    /**
     * Initializes a new randomhomework generator
     * with the courses' names defined.
     */
    public RandomHomework() {
        COURSES[0] = "Programming Design Paradigms";
        COURSES[1] = "Object Oriented Design";
        COURSES[2] = "Data Structures and Algorithms";
        COURSES[3] = "Data Mining";
    }

    /**
     * @return a random course name from a list of four possible courses.
     */
    public String getCourse() {
        Random r = new Random();
        return COURSES[r.nextInt(N_COURSES)];
    }

    /**
     * Uses a RandomHomework object to print
     * a random course name to the console.
     */
    public static void main(String[] args) {
        RandomHomework randomHomework = new RandomHomework();
        System.out.println(randomHomework.getCourse());
    }
}
