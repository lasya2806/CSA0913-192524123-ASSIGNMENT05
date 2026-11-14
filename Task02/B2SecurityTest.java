
import java.util.*;

public class B2SecurityTest {

    // Simulated unsafe login
    static boolean unsafeLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username='"
                + username + "' AND password='" + password + "'";

        System.out.println("Unsafe SQL: " + sql);

        // Demonstrates how SQL injection can bypass a weak login
        if (username.equals("admin' -- ")) {
            return true;
        }

        return username.equals("admin")
                && password.equals("correct123");
    }

    // Simulated safe login using parameter matching
    static boolean safeLogin(String username, String password) {
        String storedUsername = "admin";
        String storedPassword = "correct123";

        return username.equals(storedUsername)
                && password.equals(storedPassword);
    }

    // Check damaged bus-pass records
    static void validatePassRecords() {

        String[] records = {
            "P1001,192311001,Arun,12,4500",
            "P1002,DAMAGED",
            "P1003,192311003,Divya,7,notANumber",
            "",
            "P1004,192311004,Ravi,8,3500"
        };

        System.out.println("\nBUS PASS FILE VALIDATION");

        int valid = 0;
        int damaged = 0;

        for (String record : records) {

            if (record.trim().isEmpty()) {
                damaged++;
                System.out.println("Skipped: Empty record");
                continue;
            }

            String[] fields = record.split(",", -1);

            try {
                if (fields.length != 5) {
                    throw new IllegalArgumentException();
                }

                Integer.parseInt(fields[3]);
                Double.parseDouble(fields[4]);

                valid++;
                System.out.println("Valid record: " + fields[0]);

            } catch (Exception e) {
                damaged++;
                System.out.println("Damaged record skipped: " + record);
            }
        }

        System.out.println("\nValid records: " + valid);
        System.out.println("Damaged records: " + damaged);
    }

    public static void main(String[] args) {

        System.out.println("TASK B2: ADVERSARIAL INPUT TESTING");
        System.out.println("----------------------------------");

        String username = "admin' -- ";
        String wrongPassword = "wrong123";

        System.out.println("\n1. UNSAFE LOGIN TEST");

        boolean unsafe = unsafeLogin(username, wrongPassword);

        System.out.println("Login successful: " + unsafe);

        System.out.println("\n2. SAFE LOGIN TEST");

        boolean safe = safeLogin(username, wrongPassword);

        System.out.println("Login successful: " + safe);

        System.out.println("\n3. DAMAGED FILE TEST");

        validatePassRecords();

        System.out.println("\nB2 TEST COMPLETED");
    }
}

