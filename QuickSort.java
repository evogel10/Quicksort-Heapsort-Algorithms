/**
 * This class receives input from a file containing a various amounts of numbers
 * in various orders and uses a quicksort algorithm to sort them. This class
 * contains 4 types of sorting algorithms: a recursive quicksort with first-item
 * pivot selection, a recursive quicksort with a special stopping case of k =
 * 100, a recursive quicksort with a special stopping case of k = 50, and a
 * recursive quicksort with a median-of-three pivot selection. The sorted file
 * is then returned back to the SortingPerformance class.
 * 
 * @version 28 April 2016
 * @author Eric Vogel
 *
 */
public class QuickSort {

    // Insertion sorting algorithm object used to sort in special cases.
    InsertionSort inSort = new InsertionSort();

    /**
     * Quicksort to partition by first element pivot.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     */
    public void quickSortFirst(Integer[] list, int first, int last) {
        // Checks size of the array to see if it is 2 or less elements.
        int size = last - first + 1;
        if (size <= 2) {
            manualSort(list, first, last);
            // Recursively sorts the array if larger than 2 elements.
        } else {
            int pivotIndex = partitionFirst(list, first, last);
            quickSortFirst(list, first, pivotIndex - 1);
            quickSortFirst(list, pivotIndex + 1, last);
        }
    }

    /**
     * Quicksort to partition by first element pivot stopping case 100.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     */
    public void quickSortK100(Integer[] list, int first, int last) {
        // If the partition size is 100 items or less, the program will use an
        // insertion sorting algorithm
        if ((last - first + 1) < 100) {
            inSort.insertionSort(list);
            // Recursively sorts the array if larger than 100 elements.
        } else {
            int pivotIndex = partitionFirst(list, first, last);
            if ((pivotIndex - first + 1) < 100) {
                inSort.insertionSort(list);
            } else {
                quickSortFirst(list, first, pivotIndex - 1);
            }
            if ((last - pivotIndex + 1) < 100) {
                inSort.insertionSort(list);
            } else {
                quickSortFirst(list, pivotIndex + 1, last);
            }
        }
    }

    /**
     * Quicksort to partition by first element pivot stopping case 50.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     */
    public void quickSortK50(Integer[] list, int first, int last) {
        // If the partition size is 50 items or less, the program will use an
        if ((last - first + 1) < 50) {
            inSort.insertionSort(list);
            // Recursively sorts the array if larger than 50 elements.
        } else {
            int pivotIndex = partitionFirst(list, first, last);
            if ((pivotIndex - first + 1) < 50) {
                inSort.insertionSort(list);
            } else {
                quickSortFirst(list, first, pivotIndex - 1);
            }
            if ((last - pivotIndex + 1) < 50) {
                inSort.insertionSort(list);
            } else {
                quickSortFirst(list, pivotIndex + 1, last);
            }
        }
    }

    /**
     * Quicksort to partition by median-of-three pivot.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     */
    public void quickSortMedianOfThree(Integer[] list, int first, int last) {
        // Checks size of the array to see if it is 2 or less elements.
        int size = last - first + 1;
        if (size <= 2) {
            manualSort(list, first, last);
            // Recursively sorts the array if larger than 2 elements.
        } else {
            int median = medianOf3(list, first, last);
            int pivotIndex = partitionMedian(list, first, last, median);
            quickSortMedianOfThree(list, first, pivotIndex - 1);
            quickSortMedianOfThree(list, pivotIndex + 1, last);
        }
    }

    /**
     * Median of 3 method used to find median of the first, last, and middle
     * element
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     * @return
     */
    public int medianOf3(Integer[] list, int first, int last) {
        int center = (first + last) / 2;
        // Compares the 3 elements and their values.
        if (list[first] > list[center])
            swap(list, first, center);
        if (list[first] > list[last])
            swap(list, first, last);
        if (list[center] > list[last])
            swap(list, center, last);

        swap(list, center, last - 1);
        return list[last - 1];
    }

    /**
     * Partition for first element pivot to last element.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     * @return
     */
    public int partitionFirst(Integer[] list, int first, int last) {
        // Selects the first element as the pivot.
        int pivot = list[first];
        // Indexes for a search forward and backward.
        int low = first + 1;
        int high = last;

        while (high > low) {
            // Search forward from first.
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from last.
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list.
            if (high > low) {
                swap(list, high, low);
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[last].
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    /**
     * Partition for median element pivot
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     * @param median: middle element in the array.
     * @return
     */
    public int partitionMedian(Integer[] list, int first, int last, int median) {
        //
        int firstPtr = first; // right of first elem
        int lastPtr = last - 1; // left of pivot

        while (true) {
            // Find bigger element.
            while (list[++firstPtr] < median)
                ;
            // Find smaller element.
            while (list[--lastPtr] > median)
                ;
            // If the pointer cross each other, the partition is done.
            if (firstPtr >= lastPtr)
                break;
            else
                // If the pointers did not cross, swap the elements
                swap(list, firstPtr, lastPtr);
        }
        // Restore the pivot.
        swap(list, firstPtr, last - 1);
        return firstPtr;
    }

    /**
     * Method used to swap two elements in the array.
     * 
     * @param list: array of numbers to be sorted.
     * @param i
     * @param j
     */
    public void swap(Integer list[], int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    /**
     * Method used to manually sort the array if the array is 2 values or less.
     * 
     * @param list: array of numbers to be sorted.
     * @param first: first element in the array.
     * @param last: last element in the array.
     */
    public void manualSort(Integer[] list, int first, int last) {
        int size = last - first + 1;
        // No need to sort.
        if (size <= 1)
            return;
        // Sorts the 2 elements
        if (size == 2) {
            if (list[first] > list[last])
                swap(list, first, last);
            return;
        }
    }
}
