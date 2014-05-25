using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OOP
{
    /// <summary>
    /// Utility class for converting Roman numerals to integers and integers to Roman numerals
    /// </summary>
    class Roman
    {
        private String roman;
        private int value;

        private static IList<Roman> basePairs;
        private static IList<KeyValuePair<String, int>> allPairs;

        private static Roman ONE = new Roman("I", 1);
        private static Roman FOUR = new Roman("IV", 4);
        private static Roman FIVE = new Roman("V", 5);
        private static Roman NINE = new Roman("IX", 9);
        private static Roman TEN = new Roman("X", 10);
        private static Roman FORTY = new Roman("XL", 40);
        private static Roman FIFTY = new Roman("L", 50);
        private static Roman NINETY = new Roman("XC", 90);
        private static Roman ONE_HUNDRED = new Roman("C", 100);
        private static Roman FOUR_HUNDRED = new Roman("CD", 400);
        private static Roman FIVE_HUNDRED = new Roman("D", 500);
        private static Roman NINE_HUNDRED = new Roman("CM", 900);
        private static Roman ONE_THOUSAND = new Roman("C", 1000);

        static Roman() {
            basePairs = new List<Roman>();
            allPairs = new List<KeyValuePair<String, int>>();

            basePairs.Add(ONE);
            basePairs.Add(FOUR);
            basePairs.Add(FIVE);
            basePairs.Add(NINE);
            basePairs.Add(TEN);
            basePairs.Add(FORTY);
            basePairs.Add(FIFTY);
            basePairs.Add(NINETY);
            basePairs.Add(ONE_HUNDRED);
            basePairs.Add(FOUR_HUNDRED);
            basePairs.Add(FIVE_HUNDRED);
            basePairs.Add(NINE_HUNDRED);
            basePairs.Add(ONE_THOUSAND);

            //build full list from 1-4000 for converting String input to Roman values
            for (int i = 1; i <= 4000; i++)
                allPairs.Add(new KeyValuePair<string, int>(IntToRoman(i), i));
        }

        private Roman(String roman, int value) {
            this.roman = roman;
            this.value = value;
        }

        private String getRoman() {
            return this.roman;
        }

        private int getValue() {
            return this.value;
        }

        /// <summary>
        /// Convert this number to a Roman numeral string
        /// </summary>
        /// <param name="num">the number to convert to a numeral</param>
        /// <returns>Roman numeral as a String</returns>
        public static String IntToRoman(int num) {
            if (num < 0 || num > 4000)
                throw new ArgumentException("Number must be between 0 and 4000, inclusive");
            else {
                if (num == 0) return "0";
                StringBuilder sb = new StringBuilder();
                while (num > 0) {
                    int index = basePairs.Count - 1;
                    while (index > 0 && basePairs.ElementAt(index).getValue() > num) {
                        index--;
                    }
                    num -= basePairs.ElementAt(index).getValue();
                    sb.Append(basePairs.ElementAt(index).getRoman());
                }

                return sb.ToString();
            }
        }

        /// <summary>
        /// Convert a Roman numeral to an integer
        /// </summary>
        /// <param name="roman">the Roman numeral to convert to int form</param>
        /// <returns>an int which represents the value of the argument Roman numeral, or -1 if not found</returns>
        public static int RomanToInt(String roman) {
            int result = -1;
            //Java = for(Map.Entry<String, Integer> entry : map)...
            foreach (KeyValuePair<String, int> entry in allPairs) {
                if (entry.Key.Equals(roman))
                    return entry.Value;
            }
            return result;
        }

    }
}
