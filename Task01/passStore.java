
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PassStore {

    static String file = "passes.csv";

    // Add a bus pass
    public static void add(String id, String reg,
                          String name, int route,
                          double amount) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file, true))) {

            bw.write(id + "," + reg + "," + name
                    + "," + route + "," + amount);
            bw.newLine();
        }
    }

    // Find a bus pass
    public static String[] find(String id) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length != 5)
                    continue;

                if (data[0].equals(id))
                    return data;
            }
        }

        return null;
    }

    // List passes by route
    public static void listByRoute(int route) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length == 5 &&
                    data[3].equals(String.valueOf(route))) {

                    System.out.println(Arrays.toString(data));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Files.deleteIfExists(Paths.get(file));

        add("P1001", "192311001", "Arun", 12, 4500);
        add("P1002", "192311002", "Priya", 7, 3800);
        add("P1003", "192311003", "Divya", 12, 4200);

        // Add damaged record
        Files.write(Paths.get(file),
                Arrays.asList("P1004,DAMAGED"),
                StandardOpenOption.APPEND);

        System.out.println("BUS PASS RECORDS");

        System.out.println(Arrays.toString(find("P1003")));

        System.out.println("Searching missing pass:");
        System.out.println(find("P9999"));

        System.out.println("Passes on Route 12:");
        listByRoute(12);
    }
}

