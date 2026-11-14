
import java.util.*;

public class B4FileMigration {

    public static void main(String[] args) {

        System.out.println("TASK B4: FILE TO DATABASE MIGRATION");
        System.out.println("-----------------------------------");

        // Simulated source file records
        String[] fileRecords = {
            "P1001,Arun,12,4500",
            "P1002,Divya,7,3500",
            "P1003,Ravi,8,4000",
            "P1004,Meena,5,3000",
            "P1005,Kiran,9,2500"
        };

        // Simulated database table
        List<String> database = new ArrayList<>();

        System.out.println("\n1. READING SOURCE FILE");

        for (String record : fileRecords) {
            System.out.println(record);
        }

        System.out.println("\n2. MIGRATION PROCESS");

        int migrated = 0;

        try {
            // Simulate a transaction using a temporary list
            List<String> transaction = new ArrayList<>();

            for (String record : fileRecords) {

                String[] fields = record.split(",");

                if (fields.length != 4) {
                    throw new Exception("Invalid record");
                }

                transaction.add(record);
                System.out.println("Added to batch: " + fields[0]);
            }

            // Commit simulated transaction
            database.addAll(transaction);
            migrated = transaction.size();

            System.out.println("\nTransaction committed.");

        } catch (Exception e) {
            System.out.println("Migration failed: " + e.getMessage());
            System.out.println("Transaction rolled back.");
        }

        System.out.println("\n3. MIGRATION RESULTS");
        System.out.println("Total source records: " + fileRecords.length);
        System.out.println("Successfully migrated: " + migrated);
        System.out.println("Database records: " + database.size());

        System.out.println("\n4. DATABASE TABLE CONTENTS");

        for (String record : database) {
            System.out.println(record);
        }

        System.out.println("\n5. PRODUCTION DECISION");
        System.out.println("Use database storage for large datasets.");
        System.out.println("Use batch processing for efficient migration.");
        System.out.println("Use transactions to maintain data consistency.");

        System.out.println("\nB4 MIGRATION SIMULATION COMPLETED");
    }
}

