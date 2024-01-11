package dao;

//Java program to demonstrate working of ThreadLocalRandom
//to generate random numbers.
import java.util.concurrent.ThreadLocalRandom;

class generateRandom {
public static void main( String args[] ) {
int min = 11111;
int max = 99999;

//Generate random int value from 50 to 100
System.out.println("Random value in int from "+min+" to "+max+ ":");
int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
System.out.println(random_int);
}
}
