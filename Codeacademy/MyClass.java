package Codeacademy;
//For Cheatsheet
public class MyClass {
  private static int j;
private static int result;

public static void main(String[] args) {
    System.out.println("Hello, world!");
    // Output: Hello, world!
    // I am a single line comment!

    /*
    And I am a 
    multi-line comment!
    */
    System.out.println("Example of a statement");

    System.out.println("Another statement");

    // Output:
    // Example of a statement
    // Another statement
    System.out.println("Java Programming ☕️");



    boolean isMarried = false;
    // Creating a String variable
    String name = "Bob";

    // The following will print "false" because strings are case-sensitive
    System.out.println(name.equals("bob"));
    int num = 10;   // positive value
    int num2 = -5;   // negative value
    int num3 = 0;    // zero value
    int num4 = (int) 12.5; // not allowed without "(int)""
    char answer = 'y';
    int age = 28; 

    char grade = 'A';

    boolean late = true;

    byte b = 20;

    long num1 = 1234567;

    short no = 10;

    float k = (float)12.5;

    double pi = 3.14;

    int i = 10;         // type is int
    char ch = 'a';      // type is char

    j = 20;             // won't compile, no type is given, field can be created
    //char name = "Lil";  // won't compile, wrong data type
    // Value cannot be changed:
    final double PI = 3.14;
    double PI2 = 3.14;
    double price = 5.75;
    int a2 = 20;
    int b2 = 10;

    int result2;

    result2 = a2 + b2;  // 30

    result2 = a2 - b2;  // 10

    result2 = a2 * b2;  // 200

    result2 = a2 / b2;  // 2

    result2 = a2 % b2;  // 0
    int a3 = 5;
    int b3 = 3;

    boolean result3 = a3 > b3;
    // result now holds the boolean value true
    int number = 5;

    number += 3; // Value is now 8
    number -= 4; // Value is now 4
    number *= 6; // Value is now 24
    number /= 2; // Value is now 12
    number %= 7; // Value is now 5
    int numApples = 5;
    numApples++; // Value is now 6

    int numOranges = 5;
    numOranges--; // Value is now 4
  }
}