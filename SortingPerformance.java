import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class receives input from a file of various size containing numbers that
 * are either in ascending, descending, random with high number of duplicates,
 * or random with low or no duplicates. After receiving the file, the program
 * writes to an output file they types of sorting algorithm used to sort the
 * file and the time (in nanoseconds) that it took. Additionally, if the file is
 * of size 50, the program will write to a another file the numbers in ascending
 * order and the type of sorting algorithm used. The sorting algorithms used
 * include a heapsort and various forms of a quicksort.
 * 
 * @version 28 April 2016
 * @author Eric Vogel
 *
 */
public class SortingPerformance {

    /**
     * This is the main method to the program.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        // Output file that contains the type of sort used, type of file, size
        // of file, and time in nanoseconds to sort.
        String sortingTimesOutput = "SortingTimesOutput.txt";
        // Output file that contains the type of sort used and the file contents
        // in ascending order if the file is of size 50.
        String size50FilesOutput = "Size50FilesOutput.txt";

        // FileWriter writes into the output file.
        FileWriter sortingTimesFileWriter = new FileWriter(sortingTimesOutput);
        FileWriter size50FilesFileWriter = new FileWriter(size50FilesOutput);

        // Writes text to a character-output stream.
        BufferedWriter sortingTimesBufferedWriter = new BufferedWriter(
                sortingTimesFileWriter);
        BufferedWriter size50FilesBufferedWriter = new BufferedWriter(
                size50FilesFileWriter);

        Boolean keepSorting = true;

        // While the user still wants to enter an input file to sort, the loop
        // will run.
        while (keepSorting) {

            // Prompts the user to pick a file to sort.
            System.out
                    .println("Ascending Ordered:             asc50.txt, asc500.txt, asc1K.txt, asc2K.txt, asc5K.txt\n"
                            + "Random with High Duplicates:   dup500.txt, dup1K.txt, dup2K.txt, dup5K.txt, dup10K.txt\n"
                            + "Random with low/no Duplicates: ran50.txt, ran500.txt, ran1K.txt, ran2K.txt, ran5K.txt\n"
                            + "Revered Ordered:               rev50.txt, rev500.txt, rev1K.txt, rev2K.txt, rev5K.txt\n");
            System.out
                    .print("Please Enter a file to sort from the list above: ");
            String fileToSort = sc.next();

            try {
                // FileReader reads text file in containing various amounts of
                // numbers in various orders.
                FileReader fileReader = new FileReader(fileToSort);

                // Reads text from a character-input stream.
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // ArrayList used to hold the numbers coming if from the file.
                ArrayList<Integer> numbers = new ArrayList<Integer>();
                // This array of integers holds the the numbers from the file
                // and is used by the sorting algorithms.
                Integer[] valuesToBeSorted = null;
                // HeapSort object used to sort with this algorithm.
                HeapSort heap = new HeapSort();
                // QuickSort object used to sort with this algorithm.
                QuickSort quick = new QuickSort();
                // Stopwatch object created to track how long each algorithm
                // takes to sort the file.
                Stopwatch time = new Stopwatch();

                // Sorting time.
                long elapsed;
                // String used to store the type of file that was sorted.
                String type = fileToSort.substring(0, 3);
                // String used to store the size of the file sorted.
                String size = fileToSort.substring(3, (fileToSort.length() - 1)).split("\\.")[0];

                // While the file still is not empty, the program will continue
                // to read it.
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    // Takes out the whitespace.
                    Scanner s = new Scanner(line).useDelimiter("\\s+");
                    // While there are still numbers in the file, the program
                    // will continue to add the numbers to the ArrayList
                    // numbers.
                    while (s.hasNextInt()) {
                        numbers.add(s.nextInt());
                    }
                }

                // Recursive quicksort with first-item pivot selection,and
                // treats partitions of size 1 or 2 as a special case.
                valuesToBeSorted = numbers.toArray(new Integer[numbers.size()]);
                // Starts time.
                time.start();
                quick.quickSortFirst(valuesToBeSorted, 0,
                        valuesToBeSorted.length - 1);
                // Ends time.
                time.stop();
                // Stores time in nanoseconds.
                elapsed = time.split();
                // Writes to output file the type of sort used, type of file,
                // size of file, and time in nanoseconds to sort.
                sortingTimesBufferedWriter.write("\nQuicksort Recursive First Item Pivot\nFile type: "
                                + type
                                + "\nFile size: "
                                + size
                                + "\n"
                                + elapsed + " nanoseconds\n");
                // Writes to output file the type of sort used and the file
                // contents in ascending order if the file is of size 50.
                if (size.equals("50")) {
                    size50FilesBufferedWriter.write("\nQuicksort Recursive First Item Pivot - "
                                    + type + "\n"
                                    + printSize50Files(valuesToBeSorted));
                }

                // Recursive quicksort with a stopping case of k = 100, then
                // uses insertion sort to finish.
                valuesToBeSorted = numbers.toArray(new Integer[numbers.size()]);
                // Starts time.
                time.start();
                quick.quickSortK100(valuesToBeSorted, 0,
                        valuesToBeSorted.length - 1);
                // Ends time.
                time.stop();
                // Stores time in nanoseconds.
                elapsed = time.split();
                // Writes to output file the type of sort used, type of file,
                // size of file, and time in nanoseconds to sort.
                sortingTimesBufferedWriter.write("\nQuicksort Recursive Partition Size 100\nFile type: "
                                + type
                                + "\nFile size: "
                                + size
                                + "\n"
                                + elapsed + " nanoseconds\n");
                // Writes to output file the type of sort used and the file
                // contents in ascending order if the file is of size 50.
                if (size.equals("50")) {
                    size50FilesBufferedWriter.write("\nQuicksort Recursive Partition Size 100 - "
                                    + type
                                    + "\n"
                                    + printSize50Files(valuesToBeSorted));
                }

                // Recursive quicksort with a stopping case of k = 50, then uses
                // insertion sort to finish.
                valuesToBeSorted = numbers.toArray(new Integer[numbers.size()]);
                // Starts time.
                time.start();
                quick.quickSortK50(valuesToBeSorted, 0,
                        valuesToBeSorted.length - 1);
                // Ends time.
                time.stop();
                // Stores time in nanoseconds.
                elapsed = time.split();
                // Writes to output file the type of sort used, type of file,
                // size of file, and time in nanoseconds to sort.
                sortingTimesBufferedWriter.write("\nQuicksort Recursive Partition Size 50\nFile type: "
                                + type
                                + "\nFile size: "
                                + size
                                + "\n"
                                + elapsed + " nanoseconds\n");
                // Writes to output file the type of sort used and the file
                // contents in ascending order if the file is of size 50.
                if (size.equals("50")) {
                    size50FilesBufferedWriter.write("\nQuicksort Recursive Partition Size 50 - "
                                    + type + "\n"
                                    + printSize50Files(valuesToBeSorted));
                }

                // Recursive quicksort with median-of-three pivot selection,and
                // treats partitions of size 1 or 2 as a special case.
                valuesToBeSorted = numbers.toArray(new Integer[numbers.size()]);
                // Starts time.
                time.start();
                quick.quickSortMedianOfThree(valuesToBeSorted, 0,
                        valuesToBeSorted.length - 1);
                // Ends time.
                time.stop();
                // Stores time in nanoseconds.
                elapsed = time.split();
                // Writes to output file the type of sort used, type of file,
                // size of file, and time in nanoseconds to sort.
                sortingTimesBufferedWriter.write("\nQuicksort Recursive Median-Of-Three\nFile type: "
                                + type
                                + "\nFile size: "
                                + size
                                + "\n"
                                + elapsed + " nanoseconds\n");
                // Writes to output file the type of sort used and the file
                // contents in ascending order if the file is of size 50.
                if (size.equals("50")) {
                    size50FilesBufferedWriter.write("\nQuicksort Recursive Median-Of-Three - "
                                    + type + "\n"
                                    + printSize50Files(valuesToBeSorted));
                }

                // Recursive heapsort
                valuesToBeSorted = numbers.toArray(new Integer[numbers.size()]);
                // Starts time.
                time.start();
                heap.sort(valuesToBeSorted);
                // Ends time.
                time.stop();
                // Stores time in nanoseconds.
                elapsed = time.split();
                // Writes to output file the type of sort used, type of file,
                // size of file, and time in nanoseconds to sort.
                sortingTimesBufferedWriter.write("\nHeapsort Recursive\nFile type: " + type
                                + "\nFile size: " + size + "\n" + elapsed
                                + " nanoseconds\n");
                // Writes to output file the type of sort used and the file
                // contents in ascending order if the file is of size 50.
                if (size.equals("50")) {
                    size50FilesBufferedWriter.write("\nHeapsort Recursive - "
                            + type + "\n" + printSize50Files(valuesToBeSorted));
                }

                // Prompts the user to continue sorting or to stop.
                System.out.print("\nWould you like to keep sorting? Enter yes or no: ");
                String answer = sc.next().toUpperCase();
                if (answer.equals("YES")) {
                    keepSorting = true;
                    // Ends the program
                } else {
                    keepSorting = false;
                    System.out.println("Done.");
                }

                // Closes reader.
                bufferedReader.close();

                // Used if the input file can't opened.
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file.");

                // Used if there is an error reading the input file.
            } catch (IOException ex) {
                System.out.println("Error reading file.");

            }
        }
        // Closes scanner and writer.
        sc.close();
        sortingTimesBufferedWriter.close();
        size50FilesBufferedWriter.close();

    }

    /**
     * This method is used to store the numbers in ascending order and returns
     * the string.
     * 
     * @param valuesToBeSorted
     * @return string in ascending order.
     */
    public static String printSize50Files(Integer[] valuesToBeSorted) {
        String orderedValues = "";
        for (int i = 0; i < valuesToBeSorted.length; i++) {
            orderedValues = orderedValues + valuesToBeSorted[i] + " ";
        }
        return orderedValues;
    }
}
