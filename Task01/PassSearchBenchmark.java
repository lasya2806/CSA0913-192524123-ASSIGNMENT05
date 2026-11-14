
import java.io.*;

public class PassSearchBenchmark {

    static String file = "searchdata.txt";

    // Generate bus-pass records
    static void createRecords(int count) throws IOException {

        try (BufferedWriter bw =
                     new BufferedWriter(new FileWriter(file))) {

            for (int i = 1; i <= count; i++) {
                bw.write("P" + i + ",Student" + i + "," + (i % 20));
                bw.newLine();
            }
        }
    }

    // Search for a pass ID
    static boolean search(String id) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data[0].equals(id)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        int[] sizes = {1000, 10000, 100000};

        System.out.println("BUS PASS SEARCH PERFORMANCE");
        System.out.println("--------------------------------");

        for (int size : sizes) {

            createRecords(size);

            // Search for the last record
            String target = "P" + size;

            long start = System.nanoTime();

            boolean found = search(target);

            long end = System.nanoTime();

            double time = (end - start) / 1000000.0;

            System.out.println("\nRecords: " + size);
            System.out.println("Search ID: " + target);
            System.out.println("Pass Found: " + found);
            System.out.println("Search Time: " + time + " ms");
        }

        System.out.println("\nTEST COMPLETED");
    }
}

