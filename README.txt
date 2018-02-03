The source code in this assignment can be compiled from the command line in the Terminal.

To compile the program, locate the directory where the files are in and enter:javac SortingPerformance.java QuickSort.java Stopwatch.java InsertionSort.java HeapSort.java

To run the program, locate the directory where the complied files are in and enter: java SortingPerformance

The user will be promoted to enter a input file to sort from the list above. This contains files of various sizes and orders and is shown below:

	asc50.txt, asc500.txt, asc1K.txt, asc2K.txt, asc5K.txt
	dup500.txt, dup1K.txt, dup2K.txt, dup5K.txt, dup10K.txt
	ran50.txt, ran500.txt, ran1K.txt, ran2K.txt, ran5K.txt
	rev50.txt, rev500.txt, rev1K.txt, rev2K.txt, rev5K.txt

After the user has selected a input file to sort the program write to an output file called, SortingTimesOutput.txt, the type of sorting algorithm used, the type of file, the size of the file, and the time in nanoseconds it took to sort. If the file is of size 50, the program will write to a different output file called, Size50FilesOutput.txt, the type of sorting algorithm used, the type of file, and the contents of the file in ascending order. 

After the sorting of the file is completed, the program will ask the user if they would like to keep sorting? If the user selects ‘yes’, then the program will prompt them for another input file. If the user selects ‘no’, then the program will end.

For grading purposed, in both output files SortingTimesOutput.txt and Size50FilesOutput.txt, I sorted every file in the list above starting with asc50.txt and working horizontally then vertically through the list.