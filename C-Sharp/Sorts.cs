using System;
using System.Collections.Generic;
using System.Diagnostics.Tracing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace OOP {
    /// <summary>
    /// A static utility class which provides methods for Quick, Merge, and Insertion sorting, either by "natural order" or by provided IComparer
    /// </summary>
    /// 
    static class Sorts {
        public static void MergeSort<T>(T[] array) {
            MergeSort(array, 0, array.Length - 1);
        }

        public static void MergeSort<T>(T[] array, IComparer<T> comparer) {
            MergeSort(array, 0, array.Length - 1, comparer);
        }

        private static void MergeSort<T>(T[] array, int low, int high) {
            if (low < high) {
                int mid = (low + high) / 2;
                MergeSort(array, low, mid);
                MergeSort(array, mid + 1, high);
                Merge(array, low, mid, high);
            }
        }

        private static void MergeSort<T>(T[] array, int low, int high, IComparer<T> comparer) {
            if (low < high) {
                int mid = (low + high) / 2;
                MergeSort(array, low, mid, comparer);
                MergeSort(array, mid + 1, high, comparer);
                Merge(array, low, mid, high, comparer);
            }
        }

        private static void Merge<T>(T[] array, int low, int mid, int high) {
            int index = 0;
            int left = low;
            int right = mid + 1;
            int nElems = (high - low) + 1;
            T[] temp = new T[nElems];

            while (left <= mid && right <= high) {
                if (((IComparable)array[left]).CompareTo(array[right]) <= 0) {
                    temp[index++] = array[left++];
                } else {
                    temp[index++] = array[right++];
                }
            }

            while (left <= mid)
                temp[index++] = array[left++];

            while (right <= high)
                temp[index++] = array[right++];

            for (int i = 0; i < nElems; i++)
                array[low + i] = temp[i];

        }

        private static void Merge<T>(T[] array, int low, int mid, int high, IComparer<T> comparer) {
            int index = 0;
            int left = low;
            int right = mid + 1;
            int nElems = (high - low) + 1;
            T[] temp = new T[nElems];

            while (left <= mid && right <= high) {
                if (comparer.Compare(array[left], array[right]) < 0) {
                    temp[index++] = array[left++];
                } else {
                    temp[index++] = array[right++];
                }
            }

            while (left <= mid)
                temp[index++] = array[left++];

            while (right <= high)
                temp[index++] = array[right++];

            for (int i = 0; i < nElems; i++)
                array[low + i] = temp[i];
        }

        public static void InsertionSort<T>(T[] array) {
            for (int i = 1; i < array.Length; i++) {
                T temp = array[i];
                int index = i - 1;
                for (; index >= 0 && ((IComparable)temp).CompareTo(array[index]) < 0; ) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = temp;
            }
        }

        public static void InsertionSort<T>(T[] array, IComparer<T> comparer) {
            for (int i = 1; i < array.Length; i++) {
                T temp = array[i];
                int index = i - 1;
                for (; index >= 0 && comparer.Compare(temp, array[index]) < 0; ) {
                    array[index + 1] = array[index];
                    index--;
                }
                array[index + 1] = temp;
            }
        }

        public static void QuickSort<T>(T[] array) {
            QuickSort(array, 0, array.Length - 1);
        }

        private static void QuickSort<T>(T[] array, int low, int high) {
            if (low < high) {
                int partition = Partition(array, low, high);
                QuickSort(array, low, partition);
                QuickSort(array, partition + 1, high);
            }
        }

        private static int Partition<T>(T[] array, int low, int high) {
            T pivot = array[(low + high) / 2];
            int left = low;
            int right = high;

            while (true) {
                while (((IComparable)array[left]).CompareTo(pivot) < 0) {
                    left++;
                }
                while (right > 0 && ((IComparable)array[left]).CompareTo(pivot) > 0) {
                    right--;
                }
                if (left < right) {
                    Swap(array, left, right);
                    left++;
                    right--;
                } else {
                    break;
                }
            }

            return right;
        }

        public static void QuickSort<T>(T[] array, IComparer<T> comparer) {
            QuickSort(array, 0, array.Length - 1, comparer);
        }

        private static void QuickSort<T>(T[] array, int low, int high, IComparer<T> comparer) {
            if (low < high) {
                int partition = Partition(array, low, high, comparer);
                QuickSort(array, low, partition, comparer);
                QuickSort(array, partition + 1, high, comparer);
            }
        }

        private static int Partition<T>(T[] array, int low, int high, IComparer<T> comparer) {
            T pivot = array[(low + high) / 2];
            int left = low;
            int right = high;

            while (true) {
                while (comparer.Compare(array[left], pivot) < 0) {
                    left++;
                }
                while (right > 0 && comparer.Compare(array[right], pivot) > 0) {
                    right--;
                }
                if (left < right) {
                    Swap(array, left, right);
                    left++;
                    right--;
                } else {
                    break;
                }
            }

            return right;
        }

        private static void Swap<T>(T[] array, int one, int two) {
            T temp = array[one];
            array[one] = array[two];
            array[two] = temp;
        }


    }
}
