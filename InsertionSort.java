/**
 * This class is used to store the insertion sort algorithm, used to sort the
 * file
 * 
 * @version 28 April 2016
 * @author Eric Vogel
 *
 */
public class InsertionSort {

    /**
     * Method used to sort the file with the insertion sorting algorithm.
     * 
     * @param list: the array to be sorted.
     */
    public void insertionSort(Integer[] list) {
        int i, j, temp;
        for (i = 1; i < list.length; i++) {
            j = i - 1;
            while (j >= 0 && list[j] > list[i]) {
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i = j;
                j--;
            }
        }
    }
}
