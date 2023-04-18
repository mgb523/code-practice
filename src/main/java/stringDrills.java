import org.junit.Test;

import java.util.*;

import static java.util.Map.entry;

public class stringDrills {

    //  Print integers one-to-N, but print “Fizz” if an integer is divisible by three, “Buzz”
    //  if an integer is divisible by five, and “FizzBuzz” if an integer is divisible by both three and five.
    @Test
    public void fizzBuzz() {
        int n = 30;

        for (int i = 1; i <= n; i++) {
            String message = String.valueOf(i);
            if (i % 3 == 0) {
               message = message + ": Fizz";
            }
            if (i % 5 == 0) {
                message = message.contains("Fizz") ? message + "Buzz" : message + ": Buzz";
            }

            System.out.println(message);
        }
    }


    // Determine whether something is a palindrome
    @Test
    public void isItPalindrome(){
        //String pattern = "Was it a car or a cat I saw?";
        //String pattern = "a man, a plan, a canal. panama";
        //String pattern = "Tacocat";
        String pattern = "asdffdsaasdffdsa";

        // Strip non alpha characters
        pattern = pattern.replaceAll("[^a-zA-Z0-9]", "");

        // Determine the sub patterns
        int patternLength = pattern.length();
        String firstHalf = pattern.substring(0, patternLength/2).toLowerCase();
        // If total string is of odd length, the middle letter is not needed for comparison
        String secondHalf = (patternLength % 2 == 0) ?
                pattern.substring(patternLength/2, patternLength).toLowerCase() :
                pattern.substring(patternLength/2 + 1, patternLength).toLowerCase();

        char[] array1 = firstHalf.toCharArray();
        char[] array2 = secondHalf.toCharArray();

        Boolean isPalindrome = true;
        int halfLength = firstHalf.length();
        for (int i = 0; i < halfLength; i++) {
            if (array1[i] != array2[halfLength - 1 - i]) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("Is string palindrome? " + (isPalindrome ? "yes" : "no"));

    }

    @Test
    public void palindromeIndex() {
        String s = "aaa";
        int result = -1;

        // Store string in array
        String[] list = s.split("");

        // Check if already a palindrome
        int y = list.length;
        boolean isPalindrome = true;
        for (int i = 0; i < list.length; i++) {
            y--;
            if (! list[i].equals(list[y])) {
                isPalindrome = false;
                break;
            }
        }
        if (isPalindrome) {
            result = 0;
        } else {
            // For each char in the array, check if the remaining string makes a palindrome
            for (int i = 0; i < s.length(); i++) {
                // Build the substring
                ArrayList<String> substring = new ArrayList<>();
                for (int j = 0; j < s.length(); j++) {
                    if (j == i) {
                        continue;
                    }
                    substring.add(list[j]);
                }

                isPalindrome = true;
                y = substring.size();
                for (int z = 0; z < substring.size(); z++) {
                    y--;
                    if (! substring.get(z).equals(substring.get(y))) {
                        isPalindrome = false;
                        break;
                    }
                }

                if (isPalindrome) {
                    result = i;
                    break;
                }
            }
        }

        System.out.println("Result = " + result);
    }

    @Test
    public void palindromeIndex2() {
        String s = "aaabbbvaaa";
        int result = -1;

        // Check if already a palindrome
        if (isPalindrome(s)) {
            result = -1;
        } else {
            for (int i = 0; i < s.length(); i++) {
                String substr = (i == 0) ?
                        s.substring(1) :
                        s.substring(0, i) + s.substring(i+1);

                if (isPalindrome(substr)) {
                    result = i;
                    break;
                }
            }
        }

        System.out.println("Result = " + result);
    }

    private boolean isPalindrome(String a) {
        boolean isPalindrome = true;
        String[] list = a.split("");
        int index = -1;
        for (String c : list) {
            index++;
            if (! c.equals(list[a.length() -1 -index])) {
                isPalindrome = false;
                break;
            }
        };

        return isPalindrome;
    }

    // Find the longest repeating pattern
    @Test
    public void repeatingStringPattern() {
        List<String> words = new ArrayList<>();
        words.add("mmasefasdfblahlasdfabcd");
        words.add("casdfabcdarpblahasdf");
        words.add("zzzzzzzasdfabcdzzzzzzzzzzzblahyyyyy");
        words.add("blahpoolasdfabcdcarpool");
        words.add("blah");

        // Determine the shortest word of the array
        String shortestWord = null;
        for (String word : words) {
            if (null == shortestWord) {
                shortestWord = word;
            } else {
                if (word.length() < shortestWord.length()) {
                   shortestWord = word;
                }
            }
        }
        System.out.println("Shortest word: " + shortestWord);

        // Iterate over all substrings from shortest word
        String longestRepeatedString = null;
        int patternLength = 2;
        if (null != shortestWord) {

            for (int i = 0; i < shortestWord.length(); i++) {
                for (int j = i + patternLength; j < shortestWord.length(); j++) {
                    String substring = shortestWord.substring(i, j);

                    List<String> filteredWords = words.stream().filter(w -> w.contains(substring)).toList();
                    if (filteredWords.size() == words.size()) {
                        if (substring.length() > patternLength) {
                            patternLength = substring.length();
                            longestRepeatedString = substring;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        if (null != longestRepeatedString) {
            System.out.println("Longest repeated pattern: " + longestRepeatedString);
        }
    }

    @Test
    public void romanToInt() {
        String s = "DCXXI";

        final Map<String, Integer> map = Map.ofEntries(
                entry("I", 1),
                entry("V", 5),
                entry("X", 10),
                entry("L", 50),
                entry("C", 100),
                entry("D", 500),
                entry("M", 1000));

        int i = s.length() - 1;
        String[] sArray = s.split("");
        int finalValue = map.get(sArray[i]);
        while (i > 0) {
            i--;
            finalValue += (map.get(sArray[i]) < map.get(sArray[i + 1])) ?
                    -map.get(sArray[i]) :
                    map.get(sArray[i]);

        }

        System.out.println(finalValue);
    }

    @Test
    public void findRepetitions() {
        HashMap<String, String> map = new HashMap<>();
        map.put("drywall", "workerA@email.com");
        map.put("plumbing", "workerB@email.com");
        map.put("framing", "workerC@email.com");
        map.put("brickwork", "workerA@email.com");

        for(String value : map.values()) {
            if (map.values().stream().filter(v -> v == value).toList().size() > 1) {
                System.out.println("Double-booked worker: " + value);
            }
        }
    }

//    @Test
//    public void sortingMatrixRows() {
//        List<String> grid = new ArrayList();
//        grid.add("abcde");
//        grid.add("gfijh");
//        grid.add("klnmo");
//        grid.add("tsrqp");
//        grid.add("zxyvu");
//
//            // Alpha sort each row into a matrix
//            List<String[]> sortedRows = new ArrayList();
//            List<List<String>> matrix = new ArrayList<>();
//            grid.stream().forEach( row -> {
//                List<String> rowArray = String.split("");
//                Arrays.sort(rowArray);
//                sortedRows.add(rowArray);
//                matrix.add(Arrays.asList(rowArray));
//            });
//
//            // Analize each column
//            System.out.println("r0c0 " + matrix.get(0).get(0));
//            System.out.println("r1c0 " + matrix.get(1).get(0));
//            System.out.println("r2c0 " + matrix.get(2).get(0) );
//
//        }
}
