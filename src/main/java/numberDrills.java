import org.junit.Test;

import java.util.*;

public class numberDrills {
    @Test
    public void plusMinusCounts() {
        List<Integer> arr = Arrays.asList(-4, 3, -9, 0, 4, 1);

        int posCount = 0;
        int negCount = 0;
        int zeroCount = 0;
        for (int i : arr) {
            if (i == 0) {
                zeroCount++;
            } else if (i < 0) {
                negCount++;
            } else {
                posCount++;
            }
        }

        System.out.printf("%.6f\n", (float) posCount / arr.size());
        System.out.printf("%.6f\n", (float) negCount / arr.size());
        System.out.printf("%.6f\n", (float) zeroCount / arr.size());
    }

    @Test
    public void minMaxSum() {
        List<Integer> arr = Arrays.asList(-4, 3, -9, 0, 4, 1);

        // Write your code here
        Collections.sort(arr);

        System.out.println(((long) arr.get(0) + arr.get(1) + arr.get(2) + arr.get(3) + arr.get(4)) + " " +
                (long) (arr.get(1) + arr.get(2) + arr.get(3) + arr.get(4) + arr.get(5)));
    }

    /*
    Find the mean, given a List<Integer> with an odd number of integers
     */
    @Test
    public void findMean() {
        List<Integer> array = Arrays.asList(55, 7, 5, 70, 100, 6, 23, 8, 9);
        System.out.println("Unsorted list: " + array);
        Collections.sort(array, Collections.reverseOrder());
        System.out.println("Sorted list: " + array);
        int meanIndex = array.size()/2;
        System.out.println("List sorted. Mean value is " + array.get(meanIndex));
    }

    /*
    Given a matrix, maximize the sum of the top left quadrant by reversing rows and columns
     */
    @Test
    public void flippingMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(112, 42, 83, 119)); //154, 202
        matrix.add(Arrays.asList(56, 125, 56, 49)); // 181, 105
        matrix.add(Arrays.asList(15, 78, 101, 43)); // 99, 144
        matrix.add(Arrays.asList(62, 98, 114, 108)); // 160, 222
        //                      168, 167, 199, 168
        //                      77,  176, 215, 151


        int matrixSize = matrix.size();
        int n = matrixSize / 2;

        List<Integer> q1Sum = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> potentialVals = new ArrayList<>();
                potentialVals.add(matrix.get(i).get(matrixSize-1-j));
                potentialVals.add(matrix.get(i).get(j));
                potentialVals.add(matrix.get(matrixSize-1-i).get(j));
                potentialVals.add(matrix.get(matrixSize-1-i).get(matrixSize-1-j));
                potentialVals.sort(Collections.reverseOrder());
                q1Sum.add(potentialVals.get(0));
            }
        }

        System.out.println("Largest quadrant sum is " + q1Sum.stream().mapToInt(i -> i.intValue()).sum());
    }

    @Test
    public void findZzSequence() {
        int n = 7;
        int [] a = {10, 7, 4, 13, 9, 6, 25, 5, 2}; // {1, 2, 3, 4, 5, 6, 7};

        // Sort by increasing number and figure out the mid point
        Arrays.sort(a);
        int mid = (n + 1)/2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        int st = mid + 1;
        int ed = n - 1;
        while(st <= ed){
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed + 1;
        }
        for(int i = 0; i < n; i++){
            if(i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }

    @Test
    public void caesarCipher() {
        String s = "www.abc.xy";
        int k = 87;


        char [] charList = s.toCharArray();

        String result = "";
        char valC;
        for (char c : charList) {
            boolean isAlpha = false;
            int val = (int) c;
            int start = 64;
            int wrap = 90;

            if (val > 64 && val < 91) {
                isAlpha = true;
            } else if (val > 96 && val < 123) {
                start = 96;
                wrap = 122;
                isAlpha = true;
            }

            if (isAlpha) {
                k = k % 26;
                int valf = val + k;
                if (valf > wrap) {
                    valf = start + valf - wrap;
                }

                val = valf;
            }

            valC = (char) val;
            result += valC;
        }

        System.out.println("Result: " + result);
    }
}
