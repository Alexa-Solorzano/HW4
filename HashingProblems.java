/*
 * *** Alexa Solorzano COMP 272 001 ***
 *
 * This HashingProblems object contains three methods / problems that you must
 * complete utilize the HashMap object within the Java's Collection Framework Library.
 *
 * The three methods / problems you need to solve are:
 *  - getAverage
 *  - odd
 *  - twoSums
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class HashingProblems {

    /*
     * Method getAverage()
     *
     * This method accepts two parameters. The first is a HashMap object, while the second
     * is an array of integers. This method must compute the average of the values for each
     * 'key' that is found in BOTH the HashMap and the array.
     *
     * For example, if only the keys 1 and 2 from the array are present in the HashMap, and
     * lets say their values were 10 and 20, respectively, then the average is calculated
     * as (10+20)/2 = 15. Lets also say the keys ‘7’ and ‘8’ are in the array, but those keys
     * are not present in the HashMap. That means their corresponding values in the HashMap
     * are not included in the average calculation.
     */

    public double getAverage(HashMap<Integer, Integer> map, int[] array) {

        /*
         * initialize sum which will store the total of the values found in the map for matching keys
         * initialize the count which will count how many valid keys (aka those that are both present in the hashmap and array) are found
         * this way we can easily get the average later
         *
         * iterate over each key in the array:
         * for each key in the array
         * check if the key exists in the map:
         *     if key is present in the hashmap
         *        since the key is found, add the corresponding valye from the map to sum and increment count
         *
         * if count is 0 / no valid keys are found 
         * return 0.0/0.0
         *
         * otherwise, return the avg (sum/count)
         */
        double sum = 0.0; //store sum of values from the HashMap
        int count = 0; //store the count of valid keys (found both in HashMap and array)
        //iterate through the array
        for(int i = 0; i < array.length; i++){
            int key = array[i]; //get the key from the array using the index
            if(map.containsKey(key)){ //refers to public method in myHashMap.java where containsKey(k) returns true if the map contains a mapping for this specific key
               sum += map.get(key); //add the value from the map for the matching key
               count++; //increment the count of valid keys
            }
        }
        // if no valid keys are found (count is zero), return 0.0/0.0
        if(count == 0){
        return 0.0 / 0.0;
        }

        return sum / count; //average of the values
  }


    /*
     * Method odd()
     *
     * This method accepts a HashMap object, and returns an ArrayList object with the
     * values of the corresponding keys that are odd.
     */

  public ArrayList<String> odd(HashMap<Integer, String> map) {
      /*
       * Create an empty list to store the results
       * Get the set of keys from the map
       * Create an iterator for the set of keys
       *
       * Iterate over the keySet using the iterator 
       * while iterator has more elements 
       *    get the next key from the iterator 
       *    if the key is odd
       *        add the corresponding value to the result list
       *
       * return the result list 
       */
    
      ArrayList<String> result = new ArrayList<>(); //initialize new array list that will hold the result of all the odd keys

      Set<Integer> keySet = map.keySet(); // refers to public method in myHashMap.java where it returns a 'Set' view of the keys contained in the map.

      Iterator<Integer> iterator = keySet.iterator();
//iterate over the keySet using the iterator 
      while(iterator.hasNext()){
          Integer key = iterator.next(); //get the next key from the iterator

          if(key % 2 != 0){ //check if the key is odd
              result.add(map.get(key)); //add the corresponding value to the result list
          }
      }
      
      return result;
  }


  /*
   * Method twoSums()
   *
   * You ARE to solve this problem in time complexity O(n). The submittals will be spot checked.
   *
   * Problem statement:
   * Suppose you are given an integer array containing the values [1,4,5,7,8,9] along with the
   * value k=4, where k is the difference between two array elements. How many times does k appear
   * in that list?
   *
   * With the above numbers, it will be three times:
   *    k = 4
   *    (5 - 1) = k
   *    (8 - 4) = k
   *    (9 - 5) = k
   *    k appears 3 times.
   *
   * All combinations must be considered. But, any other combination of the numbers in the array
   * results in a difference value that is not equal to k (k=4 in this case).
   *
   * This can be solved using nested for-loops, checking all combinations of the values in the array.
   * But the time complexity would be O(n^2).
   *
   * In order to solve this problem in O(n) complexity, utilize a HashMap (or a HashSet).
   *
   * You are two solve this using a HashMap (or you can use a HashSet, which is implemented
   * using HashMap). To solve this, you should populate the HashMap (or HashSet) based on
   * the array (this will be complexity time on the order of 'n'). After populating the HashMap,
   * consider a for-loop that does a lookup (probe) of the HashMap (or HashSet) on each iteration
   * of the loop. This will also have a complexity on the order of 'n', as the hashing probes are a
   * constant time complexity (after removing any constant based on collisions).
   *
   * This will result in a time complexity of O(n) for the overall method.
   *
   * NOTE: Solving using a HashMap or HashSet is fine (either is okay). HashSet may be easier to code?
   */

  public int twoSums(int[] numbers, int k) {
       /*
       * The goal is to find how many pairs of numbers in an array have an absolute difference of k
       * We have to check for all pairs (a, b) where |a - b| = k 
       * Goal is to try to avoid nested loops and achieve a time complexity of O(n) where the code runs in linear time 
       * Using a HashMap will quickly check if certain values exist in the array & allow to check in constant time 0(1)
       * The difference between 2 numbers can be both positive and negative --> in order to find all pairs where the absolute difference is exactly k, we have to account for both num - k and num + k 
       * We have to check for pair difference: for each num, how many other numbers in the array form a pair with num so that their absolute diff. is k?
       * num - k shows if there is a number in the array that k is less than num
       * num + k shows if there is a number in the array that k is greater than num
       *
       * Create a HashMap called arrayCount to store the count of numbers we encounter while iterating over the array (store numbers & their frequencies)
       * Initialize a counter count to keep track of the number of pairs that have a difference of k
       *
       * Iterate over the array and for each number in the array
       *     check if num-k is already in the arrayCount HashMap
       *        if yes, increment the count 
       *
       *     check if num+k is already in the arrayCount HashMap
       *        if yes, increment the count 
       *
       *    if num is already in the map, increment its count by 1
       *    if nim is not in the map, add it to the map with an initial count of 1
       *
       * return the count which contains the total number of valid pairs with a difference of k
       */
      
      //Initialize a HashMap to store the numbers and their freq.
      HashMap<Integer, Integer> arrayCount = new HashMap<>();
      int count = 0;
      //Iterate through numbers array
      for(int num : numbers) {
        //containsKey is a public method in myHashMap where it returns true if this map contains a mapping for the specified key.
          if(arrayCount.containsKey(num - k)){ //check if num - k exists in the map
              count++; //increment the count 
          }

          if(arrayCount.containsKey(num + k)){ //check if num + k exists in the map
              count++;
          }
          // add the current number to the HashMap or increment its count 
          if(arrayCount.containsKey(num)){ // checks if the key num already exists in the arrayCount HashMap
              arrayCount.put(num, arrayCount.get(num) +1); //if it returns true then we retrieve the current count (freq.) of num from the hashmap (arrayCount.get(num)) and increment that count by 1 to indicate that we've encountered num one more time. then update the hasmap with the new count (arrayCount.put())
          } else { //if containsKey returns false and the number does not exist in the map
              arrayCount.put(num, 1); //add it to the arrayCount hashmap with a count of appearing once
          }
      }
      //return the total count of valid pairs
      return count;
  }

} /* end class HashingProblems */
