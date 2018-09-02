/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        //find the max length of the string
        int maxlength = 0;
        for (String s : asciis) {
            maxlength = s.length() > maxlength ? s.length() : maxlength;
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
        }

        for (int i = 0; i < maxlength; i++) {
            sortHelperLSD(sorted, i);
        }

        return sorted;


    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        int[] counts = new int[256];
        int maxlength = 0;
        for (String s : asciis) {
            maxlength = s.length() > maxlength ? s.length() : maxlength;
        }
        for (String s : asciis) {
            if (maxlength - index - 1 > s.length() - 1) {
                counts[0]++;
            } else {
                counts[(int) s.charAt(maxlength - index - 1)]++;
            }

        }

        int[] started = new int[256];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            started[i] = pos;
            pos = pos + counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            String item = s;
            if (maxlength - index - 1 > s.length() - 1) {
                int place = started[0];
                sorted[place] = item;
                started[0]++;
            } else {
                int place = started[(int) s.charAt(maxlength - index - 1)];
                sorted[place] = item;
                started[(int) s.charAt(maxlength - index - 1)]++;
            }



        }
        for (int i = 0; i < sorted.length; i++) {
            asciis[i] = sorted[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] a = new String[5];
        a[0] = "bbc";
        a[1] = "bcc";
        a[2] = "cab";
        a[3] = "100";
        a[4] = "2";


        String[] b = RadixSort.sort(a);
        for (String s : b) {
            System.out.println(s);
        }



    }


}
