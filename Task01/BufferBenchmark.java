
import java.io.*;

public class BufferBenchmark {

    // Create test files of different sizes
    static void createFile(String filename, int sizeMB)
            throws IOException {

        byte[] buffer = new byte[10000];

        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (i % 100 == 99 ? '\n' : 'A');
        }

        long totalBytes = sizeMB * 1024L * 1024L;

        try (BufferedOutputStream out =
                     new BufferedOutputStream(
                             new FileOutputStream(filename))) {

            while (totalBytes > 0) {
                int count = (int) Math.min(
                        buffer.length, totalBytes);

                out.write(buffer, 0, count);
                totalBytes -= count;
            }
        }
    }

    // Read without buffering
    static long unbuffered(String filename)
            throws IOException {

        long lines = 0;

        try (FileInputStream in =
                     new FileInputStream(filename)) {

            int ch;

            while ((ch = in.read()) != -1) {
                if (ch == '\n') {
                    lines++;
                }
            }
        }

        return lines;
    }

    // Read with buffering
    static long buffered(String filename)
            throws IOException {

        long lines = 0;

        try (BufferedInputStream in =
                     new BufferedInputStream(
                             new FileInputStream(filename))) {

            int ch;

            while ((ch = in.read()) != -1) {
                if (ch == '\n') {
                    lines++;
                }
            }
        }

        return lines;
    }

    public static void main(String[] args)
            throws IOException {

        int[] sizes = {1, 10, 50};

        System.out.println("BUFFERING PERFORMANCE TEST");
        System.out.println("--------------------------------");

        for (int size : sizes) {

            String filename = "test" + size + "MB.txt";

            createFile(filename, size);

            long start = System.nanoTime();
            long lines1 = unbuffered(filename);
            long end = System.nanoTime();

            double time1 = (end - start) / 1000000.0;

            start = System.nanoTime();
            long lines2 = buffered(filename);
            end = System.nanoTime();

            double time2 = (end - start) / 1000000.0;

            System.out.println("\nFile Size: " + size + " MB");

            System.out.println(
                    "Unbuffered Time: " + time1 + " ms");

            System.out.println(
                    "Buffered Time: " + time2 + " ms");

            System.out.println(
                    "Line Count Correct: " + (lines1 == lines2));
        }

        System.out.println("\nTEST COMPLETED");
    }
}

