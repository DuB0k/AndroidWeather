package com.talentum.dubok.weatherworkd.model;


public class FizzBuzz {
    public static String fizzBuzz(int i) {

        if (isDivisibleByThree(i) && isDivisibleByFive(i)){
            return "FizzBuzz";
        }

        if (isDivisibleByThree(i)){
            return "Fizz";
        }

        if (isDivisibleByFive(i)){
            return "Buzz";
        }

        return "1";
    }

    public static boolean isDivisibleByThree(int i){
        return i % 3 == 0;
    }

    public static boolean isDivisibleByFive(int i){
        return i % 5 == 0;
    }

}
