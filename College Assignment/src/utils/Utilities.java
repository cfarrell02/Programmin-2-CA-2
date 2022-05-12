package utils;

import java.util.List;

public class Utilities {
    /**
     * A class full of various utilities used throughout the code
     */


    public static boolean validPPS(String text) {
        if((text.length()==9)){
            return (text.substring(0,7).matches("[0-9]+"))&&(text.substring(7,9).matches("[a-zA-Z]+"));
           }else{
            return false;
        }
    }

    /**
     * Checks if the parameter only contains 30 characters at most
     * @param string a string to be checked
     * @return true if max at 30 characters
     */
    public static boolean max30Chars(String string) {
        return string.length() <= 30;
    }

    public static boolean max10Chars(String string){ return string.length() <= 10;}

    /**
     * Checks if the integer passed through is non negative
     * @param number int number to be checked for these characteristics
     * @return true if the conditions are met
     */
    public static boolean validIntNonNegative(int number) {
        return (number >= 0);
    }

    public static boolean validDoubleNonNegative(double number){ return (number>= 0);}



    /**
     * Checks if the provided int index provides a valid object from the provided array
     * @param index an index to be checked if valid
     * @param list an array for the index to be check in
     * @return true if the index is valid
     */
    public static boolean validIndex(int index, List list) {
        return ((index >= 0) && (index < list.size()));
    }


    /**
     * Checks if the third parameter is within the range of the first two paramters
     * @param start start of range
     * @param end end of range
     * @param value value to be checked if in between
     * @return Returns true if the conditions are met
     */
    public static boolean validIntRange(int start, int end, int value) {
        return (value >= start) && (value <= end);
    }

    public static boolean validLecturerLevel(int level){return (level>=1&&level<=3);}
    public static boolean validAdminGrade(int grade){return (grade>=1&&grade<=4);}
    public static boolean validManagerGrade(int grade){return (grade>=1&&grade<=7);}

    public static double getSalaryForLecturerLevel(int salary){
        if(Utilities.validLecturerLevel(salary)){
            return salary*1000;
        }else{
            return -1;}
    }
    public static double getSalaryForAdminGrade(int salary){
        if(Utilities.validAdminGrade(salary)){
        return salary*700;
    }else{
        return -1;}}
    public static double getSalaryForManagerGrade(int salary){
        if(Utilities.validManagerGrade(salary)){
            return salary*700;
        }else{
            return -1;}}

}