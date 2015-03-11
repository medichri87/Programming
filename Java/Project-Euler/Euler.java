import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Chris Medina
 * <p>
 * This class represents all of my solved problems from Project Euler (Located: www.projecteuler.net).
 * All methods denoted "public" are answers, usually named after the problem being solved.
 * All methods denoted "private" are merely helper methods and carry no other significance.
 * <p>
 * Date: 2015
 */
public class Euler {
    private static List<String> permutations = new ArrayList<>();

    public static void main(String[] args) {
        String self = selfPowers(1000);
        System.out.println(self.substring(self.length() - 10));
    }

    /**
     * The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
     * <p>
     * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
     */
    public static String selfPowers(int upTo) {
        BigInteger sum = BigInteger.ZERO;
        for (long i = 1; i <= upTo; i++) {
            BigInteger temp = BigInteger.valueOf(i).pow((int) i);
            sum = sum.add(temp);
        }
        return sum.toString();
    }

    /**
     * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
     * <p>
     * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
     * <p>
     * (Please note that the palindromic number, in either base, may not include leading zeros.)
     */
    /*
    public static int doubleBasePalindromes() {
        List<Integer> basePalindromic = new ArrayList<>();
        for (int i = 1; i < 1000000; i++) {
            if (isPalindrome(String.valueOf(i)) && isPalindrome(toBinary(i))) {
                basePalindromic.add(i);
            }
        }
        System.out.println(basePalindromic);
        return basePalindromic.stream().mapToInt(Integer::intValue).sum();
    }
    */

    /**
     * HELPER METHOD ONLY
     * <p>
     * Convert a number to a binary String
     * (ex. 8 -> 1000, 12 -> 1100, 21 -> 10101
     * </p>
     *
     * @param num the number to convert
     * @return the number as a binary String
     */
    private static String toBinary(int num) {
        int pow = 0;
        int temp = num;
        while (temp > 1) {
            temp >>= 1;
            pow++;
        }

        String binary = "";
        while (pow >= 0) {
            int tempPow = (int) Math.pow(2, pow--);
            if (tempPow <= num) {
                binary += "1";
                num -= tempPow;
            } else {
                binary += "0";
            }
        }

        return binary;
    }

    /**
     * HELPER METHOD ONLY ----
     * <p>Determine if a String is palindrome
     * NOTE: Palindrome means that the String reversed is equal to the original String</p>
     *
     * @param s the String to determine if palindrome for
     * @return true. if palindrome
     */
    private static boolean isPalindrome(String s) {
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length / 2; i++) {
            if (letters[i] != letters[(s.length() - 1) - i])
                return false;
        }
        return true;
    }

    /**
     * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in
     * some order, but it also has a rather interesting sub-string divisibility property.
     * <p>
     * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
     * <p>
     * d2d3d4=406 is divisible by 2
     * d3d4d5=063 is divisible by 3
     * d4d5d6=635 is divisible by 5
     * d5d6d7=357 is divisible by 7
     * d6d7d8=572 is divisible by 11
     * d7d8d9=728 is divisible by 13
     * d8d9d10=289 is divisible by 17
     * <p>
     * Find the sum of all 0 to 9 pan-digital numbers with this property.
     */

    /*
    public static BigInteger subStringDivisible() {
        BigInteger sum = BigInteger.ZERO;
        List<BigInteger> panDigitals = new ArrayList<>();
        for (long i = 1000000000L; i <= 9999999999L; i++) {
            if (isPandigital(i))
                panDigitals.add(BigInteger.valueOf(i));
        }

        System.out.println(panDigitals.size());
        return sum;
    }
    */
    private static boolean isPandigital(long num) {
        //must be 9 digits
        if (String.valueOf(num).length() != 10)
            return false;

        //Digits 0-9
        int[] digits = new int[11];
        while (num > 0) {
            int digit = (int) (num % 10);

            //already seen, false
            if (digits[digit] == 1)
                return false;
            digits[digit]++;
            num /= 10;
        }

        return true;
    }

    /**
     * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two
     * ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one
     * another.
     * <p>
     * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but
     * there is one other 4-digit increasing sequence.
     * <p>
     * What 12-digit number do you form by concatenating the three terms in this sequence?
     */
    public static String primePermutations() {
        List<Integer> matches = new ArrayList<>();

        int a = 1000, b = 0, c = 0;
        for (; a < 3340; a++) {
            b = (a + 3330);
            c = (b + 3330);
            System.out.println("[" + a + ", " + b + ", " + c + "]");

            //Determine that all three numbers are prime and contain same digits
            if (sameDigits(a, b) && sameDigits(b, c) && allPrime(a, b, c)) {
                matches.addAll(Arrays.asList(a, b, c));
            }
        }

        System.out.println(matches);
        String concat = "";
        for (int i = 3; i > 0; i--)
            concat += ("" + matches.get(matches.size() - i));
        return concat;
    }

    /**
     * <p>HELPER METHOD ONLY</p>
     * Determines that all numbers in argument list(varargs) are all prime numbers
     *
     * @param a varargs (array) of numbers to determine if all prime for
     * @return true, if all numbers in argument are prime
     */
    private static boolean allPrime(int... a) {
        BigInteger temp = BigInteger.ONE;
        for (int val : a) {
            temp = new BigInteger(String.valueOf(val));
            if (!temp.isProbablePrime(80))
                return false;
        }
        return true;
    }

    /**
     * <p>HELPER METHOD ONLY</p>
     * Determines that both numbers in argument contain the same number of digits and the same digits are found
     * throughout them (regardless of order)
     *
     * @param a first number
     * @param b second number
     * @return true, if both numbers contain the same number of digits and the same digits are found throughout them
     * (regardless of order)
     */
    private static boolean sameDigits(int a, int b) {
        String aString = String.valueOf(a);
        String bString = String.valueOf(b);
        int aLen = aString.length();
        int bLen = bString.length();

        if (aLen != bLen)
            return false;

        int[] One = new int[10];
        int[] Two = new int[10];

        for (; a > 0; a /= 10, b /= 10) {
            One[(a % 10)]++;
            Two[(b % 10)]++;
        }

        for (int i = 0; i <= 9; i++) {
            if (One[i] != Two[i])
                return false;
        }

        return true;
    }

    /**
     * The prime 41, can be written as the sum of six consecutive primes:
     * 41 = 2 + 3 + 5 + 7 + 11 + 13
     * <p>
     * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
     * <p>
     * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal
     * to 953.
     * <p>
     * Which prime, below one-million, can be written as the sum of the most consecutive primes?
     */
    public static int consecutivePrimeSum(int lessThan) {
        List<Integer> primes = primes(1000000);
        System.out.println(primes);
        int prev = 0;
        int len = 0;

        for (int i = 0; i < primes.size(); i++) {
            for (int j = i + 1; j < primes.size(); j++) {
                List<Integer> subList = primes.subList(i, j);
                int sumOfSubList = subList.stream().reduce(0, (x, y) -> (x + y));

                if (sumOfSubList > lessThan) break;

                if (primes.contains(sumOfSubList)) {
                    if (subList.size() > len) {
                        len = subList.size();
                        prev = sumOfSubList;
                        System.out.println(primes.get(i) + " = " + subList + " = " + sumOfSubList);
                    }
                }
            }
        }

        return prev;
    }

    private static List<Integer> primes(int upTo) {
        BigInteger temp = BigInteger.ONE;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < upTo; i++) {
            temp = new BigInteger(String.valueOf(i));
            if (temp.isProbablePrime(80)) {
                primes.add(i);
            }
        }
        return primes;
    }

    /**
     * Helper Method only
     * Rotates a String to the right by character, n times
     */
    private static String rotate(String s, int n) {
        String front = s.substring(0, (s.length() - n));
        String rear = s.substring(s.length() - n);
        return rear + front;
    }

    /**
     * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are
     * themselves prime.
     * <p>
     * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
     * <p>
     * How many circular primes are there below one million?
     */
    public static int circularPrimes(int upTo) {
        int cPrimes = 0;

        for (int i = 2; i < upTo; i++) {
            List<BigInteger> rotations = new ArrayList<>();
            String s = String.valueOf(i);

            for (int j = 0; j < s.length(); j++) {
                String rotated = rotate(s, j + 1);
                rotations.add(new BigInteger(rotated));
            }

            if (rotations.stream().allMatch(o -> o.isProbablePrime(80))) {
                System.out.println(rotations);
                cPrimes++;
            }
        }

        return cPrimes;
    }

    /**
     * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
     * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called
     * amicable numbers.
     * <p>
     * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284
     * . The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
     * <p>
     * Evaluate the sum of all the amicable numbers under 10000.
     */
    public static int sumOfAmicableNumbers(int upTo) {
        if (upTo < 0) throw new IllegalArgumentException("Number must be positive");
        Map<Integer, Integer> map = new TreeMap<>();
        Set<Integer> amicables = new TreeSet<>();

        for (int i = 2; i < upTo; i++) {
            int b = factors(i, true).stream().mapToInt(Integer::intValue).sum();
            map.put(i, b);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            int sum = factors(value, true).stream().mapToInt(Integer::intValue).sum();

            if ((!Objects.equals(key, value)) && (key == sum)) {
                amicables.add(key);
                amicables.add(sum);
            }
        }

        return amicables.stream().reduce(0, (x, y) -> (x + y));
    }


    /**
     * HELPER METHOD ONLY
     * Finds all factors for a number
     * Note: Proper factors are all divisible n's less than 'num'
     *
     * @param n      the number to find proper factors of
     * @param proper if requires proper factors only
     * @return a Set(no duplications) of the factors of n
     */
    private static Set<Integer> factors(int n, boolean proper) {
        Set<Integer> factors = new TreeSet<>();
        factors.add(1);

        for (int i = (proper ? 2 : 1); i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    /**
     * In the 20×20 grid below (as String):
     * What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or
     * diagonally) in the 20×20 grid?
     */
    public static int largestProductInGrid() {
        String s = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\n" +
                "49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\n" +
                "81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\n" +
                "52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\n" +
                "22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\n" +
                "24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\n" +
                "32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\n" +
                "67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\n" +
                "24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\n" +
                "21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\n" +
                "78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\n" +
                "16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\n" +
                "86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\n" +
                "19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\n" +
                "04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\n" +
                "88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\n" +
                "04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\n" +
                "20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\n" +
                "20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\n" +
                "01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48";

        String edit = s.replaceAll("\\n", "").replaceAll("\\s+", "").replaceAll("\\r", "");

        int[][] array = new int[20][20];

        int i = 0;
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                array[row][col] = Integer.valueOf(edit.substring(i, i + 2));
                i += 2;
            }
        }

        System.out.println(Arrays.deepToString(array));

        int prevProduct = 0;
        //Go through array to find the largest four adjacent numbers with greatest product
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                int tempProduct = 0;

                //HORIZONTAL
                if (col < 17) {
                    Integer[] t = new Integer[4];
                    for (int k = col, index = 0; k < (col + 4); k++, index++) {
                        t[index] = array[row][k];
                    }

                    //System.out.println("HORIZONTAL: " + Arrays.toString(t));
                    tempProduct = Arrays.asList(t).stream().reduce(1, (x, y) -> (x * y));
                    if (tempProduct > prevProduct) {
                        prevProduct = tempProduct;
                        System.out.println("RIGHT: " + Arrays.toString(t) + " PRODUCT: " + tempProduct);
                    }
                }

                //VERTICAL
                if (row < 17) {
                    Integer[] t = new Integer[4];
                    for (int k = row, index = 0; k < (row + 4); k++, index++) {
                        t[index] = array[k][col];
                    }

                    tempProduct = Arrays.asList(t).stream().reduce(1, (x, y) -> (x * y));
                    if (tempProduct > prevProduct) {
                        prevProduct = tempProduct;
                        System.out.println("DOWN: " + Arrays.toString(t) + " PRODUCT: " + tempProduct);
                    }
                }

                /*2 DIAGONALS(NORTHWEST, NORTHEAST) NEEDED
                *
                * NOTE: NORTHWEST is equivalent to SOUTHEAST and NORTHEAST is equivalent to SOUTHWEST
                * As such, only 2 of 4 directions are required for this problem to encompass all possible
                * diagonal values
                * */

                //NORTHWEST (ROW-, COL-)
                if (row > 2 && col > 2) {
                    Integer[] t = new Integer[4];
                    for (int r = row, c = col, index = 0; (r > (row - 4) && c > (col - 4)); r--, c--, index++) {
                        t[index] = array[r][c];
                    }
                    tempProduct = Arrays.asList(t).stream().reduce(1, (x, y) -> (x * y));
                    if (tempProduct > prevProduct) {
                        prevProduct = tempProduct;
                        System.out.println("NW: " + Arrays.toString(t) + " PRODUCT: " + tempProduct);
                    }
                }

                //NORTHEAST (ROW-, COL+)
                if (row > 2 && col < 17) {
                    Integer[] t = new Integer[4];
                    for (int r = row, c = col, index = 0; (r > (row - 4) && c < (col + 4)); r--, c++, index++) {
                        t[index] = array[r][c];
                    }

                    tempProduct = Arrays.asList(t).stream().reduce(1, (x, y) -> (x * y));
                    if (tempProduct > prevProduct) {
                        prevProduct = tempProduct;
                        System.out.println("NE: " + Arrays.toString(t) + " PRODUCT: " + tempProduct);
                    }
                }

            }
        }

        return prevProduct;
    }

    /**
     * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value
     * of this product?
     */

    public static BigInteger largestProduct(int n) {
        String s = "73167176531330624919225119674426574742355349194934\n" +
                "  96983520312774506326239578318016984801869478851843\n" +
                "  85861560789112949495459501737958331952853208805511\n" +
                "  12540698747158523863050715693290963295227443043557\n" +
                "  66896648950445244523161731856403098711121722383113\n" +
                "  62229893423380308135336276614282806444486645238749\n" +
                "  30358907296290491560440772390713810515859307960866\n" +
                "  70172427121883998797908792274921901699720888093776\n" +
                "  65727333001053367881220235421809751254540594752243\n" +
                "  52584907711670556013604839586446706324415722155397\n" +
                "  53697817977846174064955149290862569321978468622482\n" +
                "  83972241375657056057490261407972968652414535100474\n" +
                "  82166370484403199890008895243450658541227588666881\n" +
                "  16427171479924442928230863465674813919123162824586\n" +
                "  17866458359124566529476545682848912883142607690042\n" +
                "  24219022671055626321111109370544217506941658960408\n" +
                "  07198403850962455444362981230987879927244284909188\n" +
                "  84580156166097919133875499200524063689912560717606\n" +
                "  05886116467109405077541002256983155200055935729725\n" +
                "  71636269561882670428252483600823257530420752963450";

        String edit = s.replaceAll("\\n\\W\\s+", "");

        BigInteger prevProduct = BigInteger.ZERO;
        for (int i = 0; i <= edit.length() - n; i++) {
            String temp = edit.substring(i, i + n);
            BigInteger tempProduct = BigInteger.ONE;

            String[] split = temp.split("");

            for (char c : temp.toCharArray()) {
                tempProduct = tempProduct.multiply(BigInteger.valueOf((int) (c - '0')));
            }

            if (tempProduct.compareTo(prevProduct) > 0) {
                prevProduct = tempProduct;
                System.out.println(Arrays.toString(split));
            }
        }

        return prevProduct;
    }

    /**
     * The four adjacent digits in the 1000-digit number that have the greatest product are 9 × 9 × 8 × 9 = 5832.
     * <p>
     * 73167176531330624919225119674426574742355349194934
     * 96983520312774506326239578318016984801869478851843
     * 85861560789112949495459501737958331952853208805511
     * 12540698747158523863050715693290963295227443043557
     * 66896648950445244523161731856403098711121722383113
     * 62229893423380308135336276614282806444486645238749
     * 30358907296290491560440772390713810515859307960866
     * 70172427121883998797908792274921901699720888093776
     * 65727333001053367881220235421809751254540594752243
     * 52584907711670556013604839586446706324415722155397
     * 53697817977846174064955149290862569321978468622482
     * 83972241375657056057490261407972968652414535100474
     * 82166370484403199890008895243450658541227588666881
     * 16427171479924442928230863465674813919123162824586
     * 17866458359124566529476545682848912883142607690042
     * 24219022671055626321111109370544217506941658960408
     * 07198403850962455444362981230987879927244284909188
     * 84580156166097919133875499200524063689912560717606
     * 05886116467109405077541002256983155200055935729725
     * 71636269561882670428252483600823257530420752963450
     * <p>
     * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value
     * of this product?
     */
    private static BigInteger greatestProduct(int n) {
        int[][] array = new int[20][50];
        String s = "73167176531330624919225119674426574742355349194934\n" +
                "96983520312774506326239578318016984801869478851843\n" +
                "85861560789112949495459501737958331952853208805511\n" +
                "12540698747158523863050715693290963295227443043557\n" +
                "66896648950445244523161731856403098711121722383113\n" +
                "62229893423380308135336276614282806444486645238749\n" +
                "30358907296290491560440772390713810515859307960866\n" +
                "70172427121883998797908792274921901699720888093776\n" +
                "65727333001053367881220235421809751254540594752243\n" +
                "52584907711670556013604839586446706324415722155397\n" +
                "53697817977846174064955149290862569321978468622482\n" +
                "83972241375657056057490261407972968652414535100474\n" +
                "82166370484403199890008895243450658541227588666881\n" +
                "16427171479924442928230863465674813919123162824586\n" +
                "17866458359124566529476545682848912883142607690042\n" +
                "24219022671055626321111109370544217506941658960408\n" +
                "07198403850962455444362981230987879927244284909188\n" +
                "84580156166097919133875499200524063689912560717606\n" +
                "05886116467109405077541002256983155200055935729725\n" +
                "71636269561882670428252483600823257530420752963450";

        String edit = s.replaceAll("\\n", "");

        //Place string into correct position in array
        int index = 0;
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 50; col++) {
                int val = Integer.valueOf(edit.substring(index, index + 1));
                array[row][col] = val;
                index++;
            }
        }

        System.out.println(Arrays.deepToString(array));

        BigInteger prevProduct = BigInteger.ZERO;
        //Go through array and find the four numbers whose product is largest
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 50; col++) {
                BigInteger tempProduct = BigInteger.ZERO;

                //LEFT
                if (col > (n - 2)) {
                    Long[] t = new Long[n];
                    for (int k = col, i = 0; k > (col - n); k--, i++)
                        t[i] = (long) array[row][k];

                    tempProduct = productOfList(Arrays.asList(t));
                    if (tempProduct.compareTo(prevProduct) > 0) {
                        System.out.println("L: " + Arrays.toString(t));
                        prevProduct = tempProduct;
                    }
                }

                //RIGHT
                if (col < (50 - n) + 1) {
                    Long[] t = new Long[n];
                    for (int k = col, i = 0; k < (col + n); k++, i++)
                        t[i] = (long) array[row][k];

                    tempProduct = productOfList(Arrays.asList(t));
                    if (tempProduct.compareTo(prevProduct) > 0) {
                        System.out.println("L: " + Arrays.toString(t));
                        prevProduct = tempProduct;
                    }
                }

                //UP
                if (row > (n - 2)) {
                    Long[] t = new Long[n];
                    for (int k = row, i = 0; k > (row - n); k--, i++)
                        t[i] = (long) array[k][col];

                    tempProduct = productOfList(Arrays.asList(t));
                    if (tempProduct.compareTo(prevProduct) > 0) {
                        System.out.println("L: " + Arrays.toString(t));
                        prevProduct = tempProduct;
                    }
                }

                //DOWN
                if (row < (20 - n) + 1) {
                    Long[] t = new Long[n];
                    for (int k = row, i = 0; k < (row + n); k++, i++)
                        t[i] = (long) array[k][row];

                    tempProduct = productOfList(Arrays.asList(t));
                    if (tempProduct.compareTo(prevProduct) > 0) {
                        System.out.println("L: " + Arrays.toString(t));
                        prevProduct = tempProduct;
                    }
                }

            }
        }

        return prevProduct;
    }

    private static BigInteger productOfList(List<? extends Number> list) {
        Iterator<? extends Number> it = list.iterator();
        long product = 1;

        while (it.hasNext()) {
            product *= it.next().longValue();
        }

        return BigInteger.valueOf(product);
    }

    /**
     * Consider all integer combinations of ab for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:
     * <p>
     * 2^2=4, 2^3=8, 2^4=16, 2^5=32
     * 3^2=9, 3^3=27, 3^4=81, 3^5=243
     * 4^2=16, 4^3=64, 4^4=256, 4^5=1024
     * 5^2=25, 5^3=125, 5^4=625, 5^5=3125
     * <p>
     * If they are then placed in numerical order, with any repeats removed, we get the following sequence of 15
     * distinct terms:
     * <p>
     * 4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
     * <p>
     * How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?
     */
    public static int distinctPowers(int from, int to) {
        Set<Double> set = new TreeSet<>();

        for (int a = from; a <= to; a++) {
            for (int b = from; b <= to; b++) {
                double result = Math.pow(a, b);
                set.add(result);
            }
        }

        return set.size();
    }

    /**
     * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
     * <p>
     * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
     * <p>
     * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
     */
    public static long digitFactorials(int upTo) {
        List<Long> list = new ArrayList<>();
        for (int i = 3; i < upTo; i++) {
            //If sum of all digit's factorial equals original number, add to list for summation
            if (sumOfFactorialDigits(i) == i) {
                list.add((long) i);
            }
        }

        System.out.println(list);
        return list.stream().mapToLong(Long::intValue).sum();
    }

    /**
     * HELPER METHOD ONLY
     * <p>
     * Adds(summation) of the factorial of each digit in the number
     * </p>
     */
    private static long sumOfFactorialDigits(long num) {
        long result = 0;
        while (num > 0) {
            long digit = (num % 10);
            result += factorial((int) digit).longValue();
            num /= 10;
        }

        return result;
    }

    /**
     * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for
     * example, the 5-digit number, 15234, is 1 through 5 pandigital.
     * <p>
     * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and
     * product is 1 through 9 pandigital.
     * <p>
     * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
     * pandigital.
     * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
     */
    public static long panDigitals() {
        Set<Long> list = new HashSet<>();
        long multiplicand = 0, multiplier = 0, product = 0;

        for (int i = 1; i < 15000; i++) {
            multiplicand = i;
            for (int j = 1; j < 15000; j++) {
                multiplier = j;
                product = (multiplicand * multiplier);

                String allDigits = String.valueOf(multiplicand) + String.valueOf(multiplier) + String.valueOf(product);
                long val = Long.valueOf(allDigits);
                if (panDigital(val) && !list.contains(product)) {
                    list.add(product);
                    System.out.println(multiplicand + " * " + multiplier + " = " + product);
                }
            }
        }

        return list.stream().mapToLong(Long::intValue).sum();
    }

    /**
     * HELPER METHOD ONLY
     * Determines if a number contains all digits 1-9
     *
     * @return true. if all digits are found (once only)
     */
    private static boolean panDigital(long num) {
        if (String.valueOf(num).length() != 9)
            return false;
        else {
            String val = String.valueOf(num);
            for (int i = 1; i <= 9; i++)
                if (!val.contains(String.valueOf(i)))
                    return false;
        }
        return true;
    }


    /**
     * Problem #30
     * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
     * <p>
     * 1634 = 14 + 64 + 34 + 44
     * 8208 = 84 + 24 + 04 + 84
     * 9474 = 94 + 44 + 74 + 44
     * <p>
     * As 1 = 14 is not a sum it is not included.
     * <p>
     * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
     * <p>
     * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
     */
    public static long digitFifthPower(int pow) {
        List<Long> list = new ArrayList<>();
        for (int i = 2; i < 1000000; i++) {
            long tempSum = 0;
            int tempNum = i;

            //get sum of the digits each raised to 'pow' power
            while (tempNum > 0) {
                tempSum += (long) Math.pow((tempNum % 10), pow);
                tempNum /= 10;
            }

            //if the sum of those digits raised to 'pow' equals current iteration('i' above), return number
            if (tempSum == i) {
                list.add(tempSum);
            }
        }

        System.out.println(list);

        return list.stream().mapToLong(Long::intValue).sum();
    }

    /**
     * The Fibonacci sequence is defined by the recurrence relation:
     * <p>
     * Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
     * <p>
     * Hence the first 12 terms will be:
     * <p>
     * F1 = 1
     * F2 = 1
     * F3 = 2
     * F4 = 3
     * F5 = 5
     * F6 = 8
     * F7 = 13
     * F8 = 21
     * F9 = 34
     * F10 = 55
     * F11 = 89
     * F12 = 144
     * <p>
     * The 12th term, F12, is the first term to contain three digits.
     * <p>
     * What is the first term in the Fibonacci sequence to contain 1000 digits?
     */
    public static List<BigInteger> fibonacciUpToDigit(int digits) {
        List<BigInteger> fibonacci = new ArrayList<>();
        fibonacci.add(BigInteger.ONE);
        fibonacci.add(BigInteger.ONE);

        BigInteger sum = BigInteger.ZERO;
        for (int i = 0, currIndex = 1, term = 3; ; i++, currIndex++, term++) {
            sum = fibonacci.get(i).add(fibonacci.get(currIndex));
            fibonacci.add(sum);

            if (sum.toString().length() == 1000) {
                System.out.println("F: " + term);
                break;
            }
        }

        return fibonacci;
    }

    private static String longestRepeatingSequence(String s) {
        int prev = 0;
        int curr = 0;
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String match = s.substring(i, j);
                curr = match.length();

                if (s.substring(j).contains(match)) {
                    if (curr > prev) {
                        prev = curr;
                        res = match;
                    }
                }
            }
        }
        return res;
    }

    /**
     * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the
     * digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it
     * lexicographic order. The lexicographic permutations of 0, 1 and 2 are:
     * <p>
     * 012   021   102   120   201   210
     * <p>
     * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
     */
    public static long lexicographicPermutations(String s) {
        permute("", s);
        return Long.valueOf(permutations.stream().sorted().collect(Collectors.toList()).get(999999));
    }

    private static void permute(String prefix, String s) {
        if (s.length() <= 1) {
            permutations.add(prefix + s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                String pre = s.substring(0, i);
                String curr = s.substring(i, i + 1);
                String post = s.substring(i + 1);
                permute((prefix + curr), (pre + post));
            }
        }
    }

    private static List<Integer> abundantNumbers(int upTo) {
        //abundant = (sum of divisors > number itself)
        //deficient = (sum of divisors < number itself)
        //perfect = (sum of divisors == number itself)

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < upTo; i++) {
            if (sumOfDivisors(i) > i)
                list.add(i);
        }
        return list;
    }

    private static int sumOfDivisors(long num) {
        return divisors(num).stream().mapToInt(Long::intValue).sum();
    }

    private static List<Long> divisors(long num) {
        List<Long> list = new ArrayList<>();

        for (int i = 1; i <= (int) Math.sqrt(num); i++) {
            if (num % i == 0) {
                list.add((long) i);
                if (i > 1)
                    list.add(num / i);
            }
        }

        return list.stream().distinct().sorted().collect(Collectors.toList());
    }

    /**
     * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand
     * first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each
     * name, multiply this value by its alphabetical position in the list to obtain a name score.
     * <p>
     * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
     * is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
     * <p>
     * What is the total of all the name scores in the file?
     */
    public static long sumNames(String location) {
        String result = "";
        Scanner scanner;

        List<String> names = new ArrayList<>();
        try {
            File f = new File(location);
            scanner = new Scanner(f);

            while (scanner.hasNextLine()) {
                result += scanner.nextLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int curr = result.indexOf("\"");
        int next;
        while (curr != -1) {
            next = result.indexOf("\"", curr + 1);
            names.add(result.substring(curr + 1, next).toLowerCase());
            curr = result.indexOf("\"", next + 1);
        }

        Collections.sort(names);
        //System.out.println(names);

        long sum = 0;

        for (int i = 0, rank = i + 1; i < names.size(); i++, rank++) {
            String n = names.get(i);
            int nameValue = 0;

            for (char c : n.toCharArray()) {
                nameValue += (c - 'a') + 1;
            }

            sum += (rank * nameValue);
        }

        return sum;
    }

    private static boolean leapYear(int year) {
        if (year % 4 == 0) {
            //check if century
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else
                return true;
        }

        return false;
    }

    /**
     * n! means n × (n − 1) × ... × 3 × 2 × 1
     * <p>
     * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
     * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
     * <p>
     * Find the sum of the digits in the number 100!
     */
    public static int factorialDigitSum(int n) {
        BigInteger factorial = factorial(n);
        int sum = 0;

        while (factorial.compareTo(BigInteger.ZERO) > 0) {
            sum += factorial.mod(BigInteger.TEN).intValue();
            factorial = factorial.divide(BigInteger.TEN);
        }

        return sum;
    }

    /**
     * Factorial of n!
     * <p>
     * HELPER METHOD ONLY
     */
    private static BigInteger factorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 1; i < n; i++) {
            factorial = factorial.add(factorial.multiply(BigInteger.valueOf(i)));
        }

        return factorial;
    }

    /**
     * Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.
     * <p>
     * 37107287533902102798797998220837590246510135740250
     * 46376937677490009712648124896970078050417018260538
     * 74324986199524741059474233309513058123726617309629
     * 91942213363574161572522430563301811072406154908250
     * 23067588207539346171171980310421047513778063246676
     * 89261670696623633820136378418383684178734361726757
     * 28112879812849979408065481931592621691275889832738
     * 44274228917432520321923589422876796487670272189318
     * 47451445736001306439091167216856844588711603153276
     * 70386486105843025439939619828917593665686757934951
     * 62176457141856560629502157223196586755079324193331
     * 64906352462741904929101432445813822663347944758178
     * 92575867718337217661963751590579239728245598838407
     * 58203565325359399008402633568948830189458628227828
     * 80181199384826282014278194139940567587151170094390
     * 35398664372827112653829987240784473053190104293586
     * 86515506006295864861532075273371959191420517255829
     * 71693888707715466499115593487603532921714970056938
     * 54370070576826684624621495650076471787294438377604
     * 53282654108756828443191190634694037855217779295145
     * 36123272525000296071075082563815656710885258350721
     * 45876576172410976447339110607218265236877223636045
     * 17423706905851860660448207621209813287860733969412
     * 81142660418086830619328460811191061556940512689692
     * 51934325451728388641918047049293215058642563049483
     * 62467221648435076201727918039944693004732956340691
     * 15732444386908125794514089057706229429197107928209
     * 55037687525678773091862540744969844508330393682126
     * 18336384825330154686196124348767681297534375946515
     * 80386287592878490201521685554828717201219257766954
     * 78182833757993103614740356856449095527097864797581
     * 16726320100436897842553539920931837441497806860984
     * 48403098129077791799088218795327364475675590848030
     * 87086987551392711854517078544161852424320693150332
     * 59959406895756536782107074926966537676326235447210
     * 69793950679652694742597709739166693763042633987085
     * 41052684708299085211399427365734116182760315001271
     * 65378607361501080857009149939512557028198746004375
     * 35829035317434717326932123578154982629742552737307
     * 94953759765105305946966067683156574377167401875275
     * 88902802571733229619176668713819931811048770190271
     * 25267680276078003013678680992525463401061632866526
     * 36270218540497705585629946580636237993140746255962
     * 24074486908231174977792365466257246923322810917141
     * 91430288197103288597806669760892938638285025333403
     * 34413065578016127815921815005561868836468420090470
     * 23053081172816430487623791969842487255036638784583
     * 11487696932154902810424020138335124462181441773470
     * 63783299490636259666498587618221225225512486764533
     * 67720186971698544312419572409913959008952310058822
     * 95548255300263520781532296796249481641953868218774
     * 76085327132285723110424803456124867697064507995236
     * 37774242535411291684276865538926205024910326572967
     * 23701913275725675285653248258265463092207058596522
     * 29798860272258331913126375147341994889534765745501
     * 18495701454879288984856827726077713721403798879715
     * 38298203783031473527721580348144513491373226651381
     * 34829543829199918180278916522431027392251122869539
     * 40957953066405232632538044100059654939159879593635
     * 29746152185502371307642255121183693803580388584903
     * 41698116222072977186158236678424689157993532961922
     * 62467957194401269043877107275048102390895523597457
     * 23189706772547915061505504953922979530901129967519
     * 86188088225875314529584099251203829009407770775672
     * 11306739708304724483816533873502340845647058077308
     * 82959174767140363198008187129011875491310547126581
     * 97623331044818386269515456334926366572897563400500
     * 42846280183517070527831839425882145521227251250327
     * 55121603546981200581762165212827652751691296897789
     * 32238195734329339946437501907836945765883352399886
     * 75506164965184775180738168837861091527357929701337
     * 62177842752192623401942399639168044983993173312731
     * 32924185707147349566916674687634660915035914677504
     * 99518671430235219628894890102423325116913619626622
     * 73267460800591547471830798392868535206946944540724
     * 76841822524674417161514036427982273348055556214818
     * 97142617910342598647204516893989422179826088076852
     * 87783646182799346313767754307809363333018982642090
     * 10848802521674670883215120185883543223812876952786
     * 71329612474782464538636993009049310363619763878039
     * 62184073572399794223406235393808339651327408011116
     * 66627891981488087797941876876144230030984490851411
     * 60661826293682836764744779239180335110989069790714
     * 85786944089552990653640447425576083659976645795096
     * 66024396409905389607120198219976047599490197230297
     * 64913982680032973156037120041377903785566085089252
     * 16730939319872750275468906903707539413042652315011
     * 94809377245048795150954100921645863754710598436791
     * 78639167021187492431995700641917969777599028300699
     * 15368713711936614952811305876380278410754449733078
     * 40789923115535562561142322423255033685442488917353
     * 44889911501440648020369068063960672322193204149535
     * 41503128880339536053299340368006977710650566631954
     * 81234880673210146739058568557934581403627822703280
     * 82616570773948327592232845941706525094512325230608
     * 22918802058777319719839450180888072429661980811197
     * 77158542502016545090413245809786882778948721859617
     * 72107838435069186155435662884062257473692284509516
     * 20849603980134001723930671666823555245252804609722
     * 53503534226472524250874054075591789781264330331690
     * <p>
     *
     * @return Summation of all numbers, written line by line, as a BigInteger
     * <p>
     * Note: I figured reading from a file line by line would be easier here than entering the lines myself for addition
     */
    public static BigInteger largeSum(String fileLocation) {
        String temp = "";
        Scanner scanner;
        BigInteger sum = BigInteger.ZERO;

        try {
            File f = new File(fileLocation);
            scanner = new Scanner(f);
            while ((scanner.hasNextLine())) {
                temp = scanner.nextLine();
                sum = sum.add(new BigInteger(temp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    /**
     * 3n + 1 sequence
     * <p>
     * While a number is larger than 1, do the following:
     * <p>
     * If the number is odd: multiply number by 3 and add 1
     * If the number is even: divide number by 2
     */
    private static List<BigInteger> collatzSequence(long number) {
        List<BigInteger> list = new ArrayList<>();
        BigInteger temp = BigInteger.valueOf(number);

        while (temp.compareTo(BigInteger.ONE) > 0) {
            list.add(temp);
            //odd
            if (!temp.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
                temp = temp.multiply(BigInteger.valueOf(3)).add(BigInteger.ONE);
                //else, even
            } else
                temp = temp.divide(BigInteger.valueOf(2));
        }
        list.add(temp);

        //System.out.println(list);
        return list;
    }

    /**
     * The following iterative sequence is defined for the set of positive integers:
     * <p>
     * n → n/2 (n is even)
     * n → 3n + 1 (n is odd)
     * <p>
     * Using the rule above and starting with 13, we generate the following sequence:
     * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
     * <p>
     * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not
     * been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
     * <p>
     * Which starting number, under one million, produces the longest chain?
     */
    public static long longestCollatz(int upTo) {
        //number with longest sequence
        long number = 0;

        //count checkers
        int prev = 0;
        int curr = 0;

        List<BigInteger> collatz;
        List<Integer> counts = new ArrayList<>();
        for (int i = 1; i < upTo; i++) {
            collatz = collatzSequence(i);

            curr = collatz.size();
            counts.add(curr);

            if (curr > prev) {
                prev = collatz.size();
                number = i;
            }
        }
        return number;
    }

    /**
     * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4
     * = 19 letters used in total.
     * <p>
     * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would
     * be used?
     * <p>
     * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and
     * 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance
     * with British usage.
     */
    public static int numberLetterCount(int upTo) {
        String temp = "";
        List<String> words = new ArrayList<>();
        for (int i = 1; i <= upTo; i++) {
            temp = numberToWord(i);
            words.add(temp);
        }

        int count = 0;
        for (String s : words) {
            count += s.length();
        }
        return count;
    }

    private enum NumberWords {
        One(1, "One"), Two(2, "Two"), Three(3, "Three"), Four(4, "Four"), Five(5, "Five"), Six(6, "Six"), Seven(7,
                "Seven"), Eight(8, "Eight"), Nine(9, "Nine"), Ten(10, "Ten"), Eleven(11, "Eleven"), Twelve(12,
                "Twelve"), Thirteen(13, "Thirteen"), Fourteen(14, "Fourteen"), Fifteen(15, "Fifteen"), Sixteen(16,
                "Sixteen"), Seventeen(17, "Seventeen"), Eighteen(18, "Eighteen"), Nineteen(19, "Nineteen"), Twenty
                (20, "Twenty"), Thirty(30, "Thirty"), Forty(40, "Forty"), Fifty(50, "Fifty"), Sixty(60, "Sixty"),
        Seventy(70, "Seventy"), Eighty(80, "Eighty"), Ninety(90, "Ninety"), Hundred(100, "OneHundred"),
        Thousand(1000, "OneThousand");

        private int value;
        private String title;

        NumberWords(int value, String title) {
            this.value = value;
            this.title = title;
        }

        public int getValue() {
            return value;
        }

        public String getTitle() {
            return title;
        }
    }

    /**
     * HELPER METHOD ONLY
     * <p>
     * Convert a number to a word (good up to 1000) </p>
     *
     * @param number the number to convert to a String
     * @return a String representation of the number (i.e. 1 = One, 10 = Ten, etc...)
     */
    private static String numberToWord(int number) {
        String result = "";
        NumberWords[] array = NumberWords.values();

        while (number > 0) {
            int index = array.length - 1;
            while (index > 0 && array[index].getValue() > number) {
                index--;
            }
            //Between 200-1000 (inclusive)
            if (number > 199 && number < 1000) {
                int temp = (number / 100);
                index = array.length - 1;
                while (index > 0 && array[index].getValue() > temp) {
                    index--;
                }
                result += (array[index].getTitle() + (number % 100 == 0 ? "Hundred" : "HundredAnd"));
                number -= (temp * 100);
            } else {
                result += array[index].getTitle();
                if (number > 100 && (number % 100 != 0))
                    result += "And";
                number -= array[index].getValue();
            }
        }

        return result;
    }

    /**
     * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
     * <p>
     * What is the sum of the digits of the number 2^1000?
     */
    public static long powerDigitSum(int pow) {
        List<Integer> digits = new ArrayList<>();
        BigInteger result = new BigInteger("2");
        result = result.pow(pow);

        System.out.println(result);

        while (result.compareTo(BigInteger.ZERO) > 0) {
            digits.add((result.mod(BigInteger.TEN)).intValue());
            result = result.divide(BigInteger.TEN);
        }

        System.out.println(digits);

        return digits.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * The sequence of triangle numbers is generated by adding the natural numbers. So the 7th triangle number would
     * be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:
     * <p>
     * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
     * <p>
     * Let us list the factors of the first seven triangle numbers:
     * <p>
     * 1: 1
     * 3: 1,3
     * 6: 1,2,3,6
     * 10: 1,2,5,10
     * 15: 1,3,5,15
     * 21: 1,3,7,21
     * 28: 1,2,4,7,14,28
     * <p>
     * We can see that 28 is the first triangle number to have over five divisors.
     * <p>
     * What is the value of the first triangle number to have over five hundred divisors?
     */
    public static long firstTriangleNumber() {

        //get triangle numbers
        int itr = 0;
        List<Long> divs;

        long sum = 0;
        for (long j = 1; j < 20000; j++) {
            sum += j;

            divs = new ArrayList<>();
            //test divisibility
            for (long i = 1; i <= (long) Math.sqrt(sum); i++, itr++) {
                //if current sum is odd, don't test for even divisibility (never happens)
                if ((sum % 2) != 0)
                    break;
                if (sum % i == 0) {
                    divs.add(i);
                    if (!divs.contains((sum / i)))
                        divs.add((sum / i));
                }
            }

            if (divs.size() > 500) {
                System.out.println("Iterations: " + itr);
                System.out.println(divs);
                return sum;
            }
        }

        return 0;
    }

    private static List<Long> triangleNumbersUpTo(long upTo) {
        if (upTo < 0) throw new IllegalArgumentException();
        List<Long> triangles;
        triangles = new ArrayList<>();

        //get triangle numbers up to 'upTo' amount
        for (long sum = 0, j = 1; j < upTo; j++) {
            sum += j;
            triangles.add(sum);
        }

        return triangles;
    }

    /**
     * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
     * Find the sum of all the primes below two million.
     */
    public static long summationOfPrimes(long upTo) {
        List<Long> primes = new ArrayList<>();
        BigInteger temp;
        //Get primes below two million
        for (long i = 2; i < upTo; i++) {
            temp = BigInteger.valueOf(i);
            if (temp.isProbablePrime(80))
                primes.add(i);
        }
        System.out.println(primes);
        return primes.stream().mapToLong(Long::longValue).sum();
    }

    /**
     * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
     * a^2 + b^2 = c^2
     * <p>
     * For example, 32 + 42 = 9 + 16 = 25 = 52.
     * <p>
     * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
     * Find the product abc.
     */
    public static void pythagoreanTriplet() {
        for (int a = 1; a < 1000; a++) {
            for (int b = a + 1; b < 1000; b++) {
                int c = ((a * a) + (b * b));
                int d = ((int) Math.sqrt(c));

                if (((a * a) + (b * b) == (d * d)) && (c > b) && (a + b + d == 1000))
                    System.out.println("A: " + a + " B: " + b + " C: " + d);
            }
        }
    }

    /**
     * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
     * What is the 10 001st prime number?
     */
    public static long findNthPrime(int n) {
        List<Long> primes = new ArrayList<>();
        BigInteger temp;

        if (n < 0) throw new IllegalArgumentException("Number must be positive");
        for (long i = 2; i < Long.MAX_VALUE; i++) {
            temp = BigInteger.valueOf(i);
            if (temp.isProbablePrime(75)) {
                primes.add(i);
                if (primes.size() == n)
                    break;
            }
        }

        return primes.get(n - 1);
    }

    /**
     * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the
     * sum.
     */
    public static long sumSquareDifference(long upTo) {
        return (squareOfSumsUpTo(upTo) - sumOfSquaresUpTo(upTo));
    }

    //HELPER METHODS FOR ABOVE
    private static long sumOfSquaresUpTo(long upTo) {
        long total = 0;
        if (upTo < 0)
            throw new IllegalArgumentException("Number must be positive");
        for (long i = 1; i <= upTo; i++) {
            total += (long) Math.pow(i, 2);
        }

        return total;
    }

    private static long squareOfSumsUpTo(long upTo) {
        long total = 0;
        if (upTo < 0)
            throw new IllegalArgumentException("Number must be positive");
        for (long i = 1; i <= upTo; i++) {
            total += i;
        }

        return (long) Math.pow(total, 2);
    }
    //END HELPER METHOD


    /**
     * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
     * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
     */
    public static long smallestMultiple() {
        for (long i = 1; i < Long.MAX_VALUE; i++) {
            boolean capture = true;
            for (long j = 1; j < 21; j++) {
                if (i % j != 0) {
                    capture = false;
                    break;
                }
            }

            if (capture) {
                return i;
            }
        }

        return 1;
    }


    /**
     * A palindromic number reads the same both ways.
     * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */
    public static long largestPalindromeProduct() {
        StringBuilder sb;
        String temp = "";
        long curr = 0;

        long pair = 0;
        for (long i = 100; i < 1000; i++) {
            for (long j = 100; j < 1000; j++) {
                temp = String.valueOf(i * j);
                sb = new StringBuilder(temp).reverse();

                if (temp.equals(sb.toString())) {
                    if (i + j > pair) {
                        pair = (i + j);
                        System.out.println("I: " + i + " J: " + j);
                        curr = Long.valueOf(temp);
                    }
                }
            }
        }

        return curr;
    }

    /**
     * HELPER METHOD ONLY
     * Prime factors for a number
     */
    private static List<BigInteger> primeFactors(BigInteger val) {
        List<BigInteger> primes = new ArrayList<BigInteger>();

        long max = (long) Math.sqrt(val.longValue());
        for (long i = 2; i <= max; i++) {
            BigInteger temp = BigInteger.valueOf(i);
            if (temp.isProbablePrime(75) && val.mod(temp).equals(BigInteger.ZERO))
                primes.add(temp);
        }
        return primes;
    }


    /**
     * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and
     * 2, the first 10 terms will be:
     * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
     * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the
     * even-valued terms.
     */
    public static int sumOfEvenFibonaccis(int upTo) {
        if (upTo < 0 || upTo >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("Number must be greater than 0 and less than MAX Integer value");
        else {
            List<Integer> fibo = new ArrayList<Integer>();
            int total = 2;
            int index = 1;
            int currFib = 0;
            fibo.add(1);
            fibo.add(2);

            while (true) {
                currFib = fibo.get(index) + fibo.get(index - 1);
                if (currFib > upTo)
                    break;
                if (currFib % 2 == 0)
                    total += currFib;
                index++;
                fibo.add(currFib);
            }

            fibo.stream().filter((o) -> o % 2 == 0).forEach(System.out::println);
            return total;
        }
    }

     /*
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these
multiples is 23.
Find the sum of all the multiples of 3 or 5 below 1000.
     */

    public static int multiplesOf(int upTo) {
        if (upTo < 0 || upTo >= Integer.MAX_VALUE)
            throw new IllegalArgumentException("Number must be greater than 0 and less than MAX Integer value");
        else {
            int sum = 0;
            List<Integer> multiples = new ArrayList<Integer>();
            for (int i = 3; i < upTo; i++) {
                if (i % 3 == 0 || i % 5 == 0) {
                    sum += i;
                    multiples.add(i);
                }
            }

            System.out.println(multiples);
            return sum;
        }
    }

}

