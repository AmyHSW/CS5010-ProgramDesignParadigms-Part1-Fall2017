package edu.neu.ccs.cs5010;

import java.util.Random;

/**
 * The <code>RandomHomework</code> class selects a random course name and prints to the console.
 *
 * @author Shuwan Huang
 */
public class RandomHomework {

    private static final int N_COURSES = 4;
    private static final String[] COURSES = {
            "Programming Design Paradigms",
            "Object Oriented Design",
            "Data Structures and Algorithms",
            "Data Mining"
    };

    /**
     * Constructs a RandomHomework object.
     */
    public RandomHomework() { }

    /**
     * @return a random course name from a list of four possible courses.
     */
    public String getCourse() {
        Random r = new Random();
        return COURSES[r.nextInt(N_COURSES)];
    }

    /**
     * Uses a RandomHomework object to print a random course name to the console.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        RandomHomework randomHomework = new RandomHomework();
        System.out.println(randomHomework.getCourse());
    }
}