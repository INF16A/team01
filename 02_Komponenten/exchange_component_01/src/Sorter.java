// source: https://www.cs.waikato.ac.nz/~bernhard/317/source/IntroSort.java

public class Sorter implements ISorter {
    private Sorter() {
        port = new SortPort();
    }

    public SortPort port;
    private static Sorter instance = new Sorter();

    public String getName() {
        return "IntroSort";
    }

    public void _sort(int[] a) {
        introsort_loop(a, 0, a.length, 2 * floor_lg(a.length));
    }

    private int size_threshold = 16;

    public void sort(int[] a, int begin, int end) {
        if (begin < end) {
            introsort_loop(a, begin, end, 2 * floor_lg(end - begin));
        }
    }

    public static Sorter getInstance() {
        return instance;
    }

    /*
     * Quicksort algorithm modified for Introsort
     */
    private void introsort_loop(int[] a, int lo, int hi, int depth_limit) {
        while (hi - lo > size_threshold) {
            if (depth_limit == 0) {
                heapsort(a, lo, hi);
                return;
            }
            depth_limit = depth_limit - 1;
            int p = partition(a, lo, hi, medianof3(a, lo, lo + ((hi - lo) / 2) + 1, hi - 1));
            introsort_loop(a, p, hi, depth_limit);
            hi = p;
        }
        insertionsort(a, lo, hi);
    }

    private int partition(int[] a, int lo, int hi, int x) {
        int i = lo, j = hi;
        while (true) {
            while (a[i] < x) i++;
            j = j - 1;
            while (x < a[j]) j = j - 1;
            if (!(i < j))
                return i;
            exchange(a, i, j);
            i++;
        }
    }

    private int medianof3(int[] a, int lo, int mid, int hi) {
        if (a[mid] < a[lo]) {
            if (a[hi] < a[mid])
                return a[mid];
            else {
                if (a[hi] < a[lo])
                    return a[hi];
                else
                    return a[lo];
            }
        } else {
            if (a[hi] < a[mid]) {
                if (a[hi] < a[lo])
                    return a[lo];
                else
                    return a[hi];
            } else
                return a[mid];
        }
    }

    /*
     * Heapsort algorithm
     */
    private void heapsort(int[] a, int lo, int hi) {
        int n = hi - lo;
        for (int i = n / 2; i >= 1; i = i - 1) {
            downheap(a, i, n, lo);
        }
        for (int i = n; i > 1; i = i - 1) {
            exchange(a, lo, lo + i - 1);
            downheap(a, 1, i - 1, lo);
        }
    }

    private void downheap(int[] a, int i, int n, int lo) {
        int d = a[lo + i - 1];
        int child;
        while (i <= n / 2) {
            child = 2 * i;
            if (child < n && a[lo + child - 1] < a[lo + child]) {
                child++;
            }
            if (d >= a[lo + child - 1]) break;
            a[lo + i - 1] = a[lo + child - 1];
            i = child;
        }
        a[lo + i - 1] = d;
    }

    /*
     * Insertion sort algorithm
     */
    private void insertionsort(int[] a, int lo, int hi) {
        int i, j;
        int t;
        for (i = lo; i < hi; i++) {
            j = i;
            t = a[i];
            while (j != lo && t < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = t;
        }
    }

    /*
     * Common methods for all algorithms
     */
    private void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int floor_lg(int a) {
        return (int) (Math.floor(Math.log(a) / Math.log(2)));
    }

    public class SortPort implements ISorter {
        public void sort(int[] array) {
            _sort(array);
        }
    }
}