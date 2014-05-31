package Sorting;

import java.util.Comparator;

/**
 * Package: Sorting <p>
 * Date: 3/14/14 <p>
 * Class Purpose: Provides MergeSort and Quick Sort implementations for a variety of types (int, char, long, 
 * double, float, and generic types T, which allow for sorting by "natural ordering" or by using a specific Comparator)
 */
public class Sorts {

    /*Ensure non-instanstiablity with private constructor*/
    private Sorts () {}

    private static void swap (int[] array, int one, int two) {
        array[one] = array[one] ^ array[two];
        array[two] = array[one] ^ array[two];
        array[one] = array[one] ^ array[two];
    }

    private static void swap (long[] array, int one, int two) {
        long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private static void swap (char[] array, int one, int two) {
        char temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private static void swap (double[] array, int one, int two) {
        double temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private static void swap (float[] array, int one, int two) {
        float temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    private static <T> void swap (T[] array, int one, int two) {
        T temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public static void sort (int[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1);
    }

    public static void sort (long[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1);
    }

    public static void sort (float[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1);
    }

    public static void sort (double[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1);
    }

    public static void sort (char[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1);
    }

    public static <T> void sort (T[] array) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array);
    }

    public static <T> void sort (T[] array, Comparator<T> c) {
        if (array.length <= 100)
            insertionSort(array);
        else
            quickSort(array, 0, array.length - 1, c);
    }

    /*QUICKSORTS*/
    private static void quickSort (int array[], int low, int high) {
        if (high - low > 0) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static int partition (int[] array, int low, int high) {
        int pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (array[left] < pivot)
                left++;
            while (right > 0 && array[right] > pivot)
                right--;
            if (left >= right)
                break;
            swap(array, left++, right--);
        }
        return right;
    }


    private static <T> void quickSort (T[] array, Comparator<T> c) {
        quickSort(array, 0, array.length - 1, c);
    }

    private static <T> void quickSort (T[] array, int low, int high, Comparator<T> c) {
        if (low < high) {
            int partition = partition(array, low, high, c);
            quickSort(array, low, partition, c);
            quickSort(array, partition + 1, high, c);
        }
    }

    private static <T> int partition (T[] array, int low, int high, Comparator<T> c) {
        T pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (c.compare(array[left], pivot) < 0) {
                left++;
            }
            while (right > 0 && c.compare(array[right], pivot) > 0) {
                right--;
            }
            if (left < right)
                swap(array, left++, right--);
            else
                break;
        }

        return right;
    }

    private static void quickSort (char array[], int low, int high) {
        if (high - low > 0) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static int partition (char[] array, int low, int high) {
        int pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (array[left] < pivot)
                left++;
            while (right > 0 && array[right] > pivot)
                right--;
            if (left >= right)
                break;
            swap(array, left++, right--);
        }
        return right;
    }

    private static void quickSort (long array[], int low, int high) {
        if (high - low > 0) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static int partition (long[] array, int low, int high) {
        long pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (array[left] < pivot)
                left++;
            while (right > 0 && array[right] > pivot)
                right--;
            if (left < right)
                swap(array, left++, right--);
            else
                break;
        }
        return right;
    }

    private static void quickSort (double array[], int low, int high) {
        if (high - low > 0) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static int partition (double[] array, int low, int high) {
        double pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (array[left] < pivot)
                left++;
            while (right > 0 && array[right] > pivot)
                right--;
            if (left < right)
                swap(array, left++, right--);
            else
                break;
        }
        return right;
    }

    private static void quickSort (float array[], int low, int high) {
        if (high - low > 0) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static int partition (float[] array, int low, int high) {
        float pivot = array[(low + high) / 2];
        int left = low;
        int right = high;

        while (true) {
            while (array[left] < pivot)
                left++;
            while (right > 0 && array[right] > pivot)
                right--;
            if (left < right)
                swap(array, left++, right--);
            else
                break;
        }
        return right;
    }

    public static <T> boolean isSorted (T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (((Comparable<T>) array[i]).compareTo(array[i + 1]) > 0)
                return false;
        }
        return true;
    }

    public static <T> boolean isSorted (char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i])
                return false;
        }
        return true;
    }

    public static boolean isSorted (float[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i])
                return false;
        }
        return true;
    }

    public static boolean isSorted (double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i])
                return false;
        }
        return true;
    }

    public static boolean isSorted (long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i])
                return false;
        }
        return true;
    }

    public static boolean isSorted (int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] > array[i])
                return false;
        }
        return true;
    }

    /*MERGESORTS*/
    public static void mergeSort (int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort (double[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort (float[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort (char[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort (long[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort (int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge (int[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        int[] temp = new int[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    private static void mergeSort (double[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge (double[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        double[] temp = new double[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    private static void mergeSort (float[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge (float[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        float[] temp = new float[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    private static void mergeSort (char[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge (char[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        char[] temp = new char[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    private static void mergeSort (long[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static void merge (long[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        long[] temp = new long[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    public static <T> void mergeSort (T[] array, Comparator<T> c) {
        mergeSort(array, 0, array.length - 1, c);
    }


    private static <T> void mergeSort (T[] array, int low, int high, Comparator<T> c) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid, c);
            mergeSort(array, mid + 1, high, c);
            merge(array, low, mid, high, c);
        }
    }

    private static <T> void merge (T[] array, int low, int mid, int high, Comparator<T> c) {
        int left = low;
        int right = mid + 1;
        T[] temp = (T[]) new Object[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (c.compare(array[left], array[right]) <= 0) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    public static <T> void mergeSort (T[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static <T> void mergeSort (T[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    private static <T> void merge (T[] array, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        T[] temp = (T[]) new Comparable<?>[(high - low) + 1];
        int index = 0;

        while (left <= mid && right <= high) {
            if (((Comparable<T>) array[left]).compareTo(array[right]) < 0) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = array[left++];
        }

        while (right <= high) {
            temp[index++] = array[right++];
        }

        for (int i = 0; i < temp.length; i++) {
            array[low + i] = temp[i];
        }
    }

    private static <T> void quickSort (T[] array) {
        //alternate --- quickSort(array, (Comparator<T>) Comparator.naturalOrder();
        quickSort(array, 0, array.length - 1);
    }

    private static <T> void quickSort (T[] array, int low, int high) {
        if (low < high) {
            int partition = partition(array, low, high);
            quickSort(array, low, partition);
            quickSort(array, partition + 1, high);
        }
    }

    private static <T> int partition (T[] array, int low, int high) {
        int left = low;
        int right = high;
        T pivot = array[(low + high) / 2]; //midpoint

        while (true) {
            while (((Comparable<T>) array[left]).compareTo(pivot) < 0) {
                left++;
            }
            while (right > 0 && ((Comparable<T>) array[right]).compareTo(pivot) > 0) {
                right--;
            }
            if (left < right) {
                swap(array, left++, right--);
            } else
                break;
        }
        return right;
    }

    public static <T> void insertionSort (T[] array) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int in = i - 1;

            while (in >= 0 && ((Comparable<T>) temp).compareTo(array[in]) < 0) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }

    public static <T> void insertionSort (T[] array, Comparator<T> c) {
        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int in = i - 1;

            for (; in >= 0 && c.compare(temp, array[in]) < 0; in--) {
                array[in + 1] = array[in];
            }

            array[in + 1] = temp;
        }
    }

    public static void insertionSort (int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int in = i - 1;

            while (in >= 0 && temp < array[in]) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }

    public static void insertionSort (double[] array) {
        for (int i = 1; i < array.length; i++) {
            double temp = array[i];
            int in = i - 1;

            while (in >= 0 && temp < array[in]) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }

    public static void insertionSort (char[] array) {
        for (int i = 1; i < array.length; i++) {
            char temp = array[i];
            int in = i - 1;

            while (in >= 0 && temp < array[in]) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }

    public static void insertionSort (float[] array) {
        for (int i = 1; i < array.length; i++) {
            float temp = array[i];
            int in = i - 1;

            while (in >= 0 && temp < array[in]) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }

    public static <T> void insertionSort (long[] array) {
        for (int i = 1; i < array.length; i++) {
            long temp = array[i];
            int in = i - 1;

            while (in >= 0 && temp < array[in]) {
                array[in + 1] = array[in];
                in--;
            }
            array[in + 1] = temp;
        }
    }
}
