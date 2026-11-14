
import java.util.*;

public class B3DatabaseBenchmark {

    static final int RECORDS = 1000;

    public static void main(String[] args) {

        System.out.println("TASK B3: DATABASE PERFORMANCE COMPARISON");
        System.out.println("-----------------------------------------");

        // Sample records
        String[] students = new String[RECORDS];

        for (int i = 0; i < RECORDS; i++) {
            students[i] = "Student" + (i + 1);
        }

        // 1. Statement simulation
        long start = System.nanoTime();

        for (int i = 0; i < RECORDS; i++) {
            String sql = "INSERT INTO students VALUES ("
                    + (i + 1) + ", '" + students[i] + "')";
        }

        long statementTime = System.nanoTime() - start;

        // 2. PreparedStatement simulation
        start = System.nanoTime();

        String sql = "INSERT INTO students VALUES (?, ?)";

        for (int i = 0; i < RECORDS; i++) {
            String name = students[i];
            int id = i + 1;
        }

        long preparedTime = System.nanoTime() - start;

        // 3. Batch processing simulation
        start = System.nanoTime();

        List<String> batch = new ArrayList<>();

        for (int i = 0; i < RECORDS; i++) {
            batch.add(students[i]);
        }

        long batchTime = System.nanoTime() - start;

        // Display results
        System.out.println("Number of records: " + RECORDS);

        System.out.println("\nPERFORMANCE RESULTS");
        System.out.println("Statement simulation: "
                + statementTime / 1_000_000.0 + " ms");

        System.out.println("PreparedStatement simulation: "
                + preparedTime / 1_000_000.0 + " ms");

        System.out.println("Batch simulation: "
                + batchTime / 1_000_000.0 + " ms");

        System.out.println("\nTRANSACTION CONCEPT");
        System.out.println("Commit: Save all database changes.");
        System.out.println("Rollback: Undo changes if an error occurs.");

        System.out.println("\nB3 SIMULATION COMPLETED");
    }
}

