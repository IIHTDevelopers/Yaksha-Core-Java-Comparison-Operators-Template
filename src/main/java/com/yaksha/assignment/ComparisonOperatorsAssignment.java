package com.yaksha.assignment;

public class ComparisonOperatorsAssignment {

	public static void main(String[] args) {

		// Declare variables
		int a = 10;
		int b = 20;

		// Perform comparison operations
		boolean isEqual = (a == b);
		boolean isNotEqual = (a != b);
		boolean isGreaterThan = (a > b);
		boolean isLessThan = (a < b);
		boolean isGreaterThanOrEqual = (a >= b);
		boolean isLessThanOrEqual = (a <= b);

		// Print the results of comparison operations
		System.out.println("Is a equal to b? " + isEqual);
		System.out.println("Is a not equal to b? " + isNotEqual);
		System.out.println("Is a greater than b? " + isGreaterThan);
		System.out.println("Is a less than b? " + isLessThan);
		System.out.println("Is a greater than or equal to b? " + isGreaterThanOrEqual);
		System.out.println("Is a less than or equal to b? " + isLessThanOrEqual);
	}
}
