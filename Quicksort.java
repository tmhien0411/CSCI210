import java.util.Comparator ;

/**
 * <h2>Quicksort.java - Implements the quicksort algorithm.</h2>
 * <p><b>Algorithm:</b></p>
 * <ol style="margin-left: 25px;">
 *   <li>find the middle element index, then sort the elements at the first, middle, and last elements
 *       using a simple bubble sort</li>
 *   <li>swap the elements at the first index and the middle index (the first element is now the pivot). </li>
 *   <li>set "up" to 0 and "down" to the size of the array - 1</li>
 *   <li>keep adding 1 to "up" until the element at "up" greater than or equal to the pivot</li>
 *   <li>keep subtracting 1 from "down" until the element at "down" is less than the pivot</li>
 * </ol>
 * @author Koffman & Wolfgang
 * @version Module 13, Demonstration
 */

public class Quicksort {

    private static Comparator comp = null ;

    // No-arg constructor doesn't take a comparator (uses compareTo method)
    public Quicksort() {}

    // Optional comparator for comparing two items in the array
    public Quicksort(Comparator newComparator) {
        comp = newComparator ;
    }

    /**
     * Sort the entire table using the quicksort algorithm.
     * @param table The array to be sorted
     */
    public <E extends Comparable<E>>  void sort(E[] table) {
        quickSort(table, 0, table.length - 1) ;
    }

    /**
     * Sort a part of the table using the quicksort algorithm.
     * @param table The array to be sorted
     * @param first The index of the low bound
     * @param last The index of the high bound
     */
    private <E extends Comparable<E>>  void quickSort(E[] table, int first, int last) {
        if (first >= last) {
            return ;                             // No data to sort
        }

        // Find the pivot (use the first element in the array), then pivot the table
        // so that all elements "less than" the pivot are on the left, and all
        // elements "greter than" the pivot point are on its right in the array
        int pivIndex = partition(table, first, last) ;

        // Recursively sort the table up to (but not including) the pivot point
        quickSort(table, first, pivIndex - 1) ;

        // Recursively sort the table after the pivot point
        quickSort(table, pivIndex + 1, last) ;
    }

    /* Partition the table so that values from first to pivIndex
     * are less than or equal to the pivot value, and values from
     * pivIndex to last are greater than the pivot value.
     * @param table The table to be partitioned
     * @param first The index of the low bound
     * @param last  The index of the high bound
     * @return The location of the pivot value
     */
    protected <E extends Comparable<E>> int partition(E[] table, int first, int last) {

        // Find the pivot point by locating the first, middle, and last elements in the
        // subtable.  Put them in order by doing a simple bubble sort, then swap the first and
        // middle elements so the pivot is now in the first element in the table
        bubbleSort(table, first, last) ;
        swap(table, first, first + (last - first) / 2) ;

        E pivot = table[first] ;               // from the bubble sort above
        int up = first ;                       // items less than the pivot
        int down = last ;                      // items greater than the pivot
        do {
            // If the comparator is null, then use the "compareTo" method", otherwise
            // use the comparator to order elements inthe array
            if (comp == null) {
                while ((up < last) && (pivot.compareTo(table[up]) >= 0)) {
                    up++ ;
                }
                while (pivot.compareTo(table[down]) < 0) {
                    down-- ;
                }
            } else {
                while ((up < last) && (comp.compare(pivot, table[up]) >= 0)) {
                    up++ ;
                }
                while (comp.compare(pivot, table[down]) < 0) {
                    down-- ;
                }
            }

            // If up is to the left of down, then change the elements in "up" with "down"
            if (up < down) {
                swap(table, up, down) ;
            }
        } while (up < down) ; // Repeat while up is left of down.

        // Exchange table[first] and table[down] thus putting the
        // pivot value where it belongs.
        swap(table, first, down) ;

        // Return the index of the pivot value.
        return down ;
    }

    /**
     * Sort table[first], table[middle], table[last].
     * @param table The table to be sorted
     * @param first Index of the first element
     * @param last Index of the last element
     */
    private <E extends Comparable<E>> void bubbleSort(E[] table, int first, int last) {

        // Find the index of the element in the middle of first and last
        int middle = first + (last - first) / 2 ;

        // Compare middle to first, then last to middle, then middle to last
        // Those three elements should now be in order
        if (comp == null) {
            if ((table[middle].compareTo(table[first]) < 0)) {
                swap(table, first, middle) ;
            }
            if ((table[last].compareTo(table[middle])) < 0) {
                swap(table, middle, last) ;
            }
            if ((table[middle].compareTo(table[first]) < 0)) {
                swap(table, first, middle) ;
            }
        } else {
            if (comp.compare(table[middle], table[first]) < 0) {
                swap(table, first, middle) ;
            }
            if (comp.compare(table[last], table[middle]) < 0) {
                swap(table, middle, last) ;
            }
            if (comp.compare(table[middle], table[first]) < 0) {
                swap(table, first, middle) ;
            }
        }
    }

    /**
     * Swap the items in table[i] and table[j].
     * @param table The array that contains the items
     * @param i The index of one item
     * @param j The index of the other item
     */
    protected <E extends Comparable<E>> void swap(E[] table, int i, int j) {
        E temp = table[i] ;
        table[i] = table[j] ;
        table[j] = temp ;
    }
}
