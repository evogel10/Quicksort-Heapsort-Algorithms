/**
 * This class is used to implement a heapsort sorting algorithm.
 * 
 * @version 28 April 2016
 * @author Eric Vogel
 *
 */
public class HeapSort {
    private int N;

    /**
     * Method used to sort the array of numbers.
     * 
     * @param list: array to be sorted.
     */
    public void sort(Integer[] list) {
        heapify(list);
        for (int i = N; i > 0; i--) {
            swap(list, 0, i);
            N = N - 1;
            maxheap(list, 0);
        }
    }

    /**
     * Method used to build the heap.
     * 
     * @param list: array to be sorted.
     */
    public void heapify(Integer[] list) {
        N = list.length - 1;
        for (int i = N / 2; i >= 0; i--)
            maxheap(list, i);
    }

    /**
     * Method used to swap the largest number in the maxheap.
     * 
     * @param list: array to be sorted.
     * @param i
     */
    public void maxheap(Integer[] list, int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int max = i;
        if (left <= N && list[left] > list[i])
            max = left;
        if (right <= N && list[right] > list[max])
            max = right;
        if (max != i) {
            swap(list, i, max);
            maxheap(list, max);
        }
    }

    /**
     * Method used to swap two number in the array.
     * 
     * @param list: array to be sorted.
     * @param i
     * @param j
     */
    public void swap(Integer[] list, int i, int j) {
        int tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }
}