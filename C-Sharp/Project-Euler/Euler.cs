using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

/*
* Author: Chris Medina
* Date: 3-2015
*/
namespace Euler
{
    public static class Euler
    {
        public static void Main(string[] args)
        {
            Console.WriteLine(SmallestMultiple());

            Console.ReadKey();
        }

        /// <summary>
        /// <url>https://projecteuler.net/problem=5</url>
        /// </summary>
        public static int SmallestMultiple()
        {
            for (int num = 20; num < int.MaxValue; num++)
            {
                bool flag = true;
                for (int divisor = 1; divisor < 20; divisor++)
                {
                    if (num % divisor != 0)
                        flag = false;
                }

                if (flag)
                {
                    return num;
                }
            }

            return -1;
        }

        /// <summary>
        /// <url>https://projecteuler.net/problem=4</url>
        /// </summary>
        /// <returns></returns>
        public static int LargestPalindromicProduct()
        {
            int prev = 0;

            for (int i = 1; i < 1000; i++)
            {
                for (int j = 1; j < 1000; j++)
                {
                    String temp = Convert.ToString(i * j);
                    if (IsPalindrome(temp) && ((i * j) > prev))
                    {
                        prev = (i * j);
                    }
                }
            }


            return prev;
        }

        private static bool IsPalindrome(this String s)
        {
            char[] array = s.ToCharArray();
            for (int i = 0; i <= s.Length / 2; i++)
            {
                if (array[i] != array[(s.Length - 1) - i])
                    return false;
            }

            return true;
        }

        /// <summary>
        /// <url>https://projecteuler.net/problem=1</url>
        /// </summary>
        public static long SumOfPrimes(long upTo)
        {
            long sum = 0;
            ISet<long> multiples = new SortedSet<long>();
            for (int i = 2; i < upTo; i++)
            {
                if (i % 3 == 0 || i % 5 == 0)
                    multiples.Add(i);
            }

            return multiples.Sum();
        }

        /// <summary>
        /// <url>https://projecteuler.net/problem=2</url>
        /// </summary>
        public static long SumOfEvenFibonacci(long upTo)
        {
            IList<long> fibonacci = new List<long>();
            long prev = 1;
            long next = 2;
            long curr = (prev + next);

            fibonacci.Add(prev);
            fibonacci.Add(next);

            while (curr < upTo)
            {
                prev = next;
                next = curr;
                fibonacci.Add(curr);
                curr = (prev + next);
            }

            return fibonacci.Where(n => (n % 2 == 0)).Sum();
        }

        /// <summary>
        /// <url>https://projecteuler.net/problem=3</url>
        /// </summary>
        public static long LargestPrimeFactor(long num)
        {
            IList<long> primeFactors = new List<long>();
            for (long i = 2; i <= (long)Math.Sqrt(num); i++)
            {
                if ((num % i == 0) && IsPrime(i))
                    primeFactors.Add(i);
            }

            return primeFactors[primeFactors.Count - 1];
        }

        /// <summary>
        /// Determine if a number is prime
        /// </summary>
        /// <param name="num">The number to test for primality</param>
        /// <returns>true, if number is prime</returns>
        private static bool IsPrime(long num)
        {
            for (long i = 2; i <= (long)Math.Sqrt(num); i++)
            {
                if ((i != num) && (num % i == 0))
                    return false;
            }
            return true;
        }
    }
}
