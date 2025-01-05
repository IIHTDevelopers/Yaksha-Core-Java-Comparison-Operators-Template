package testutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.ExpressionStmt;

public class AutoGrader {

	public boolean testVariableDeclarationsAndComparisonOperations(String filePath) throws IOException {
		System.out.println("Starting testVariableDeclarationsAndComparisonOperations with file: " + filePath);

		// Load participant's file
		File participantFile = new File(filePath); // Path to participant's file
		if (!participantFile.exists()) {
			System.out.println("File does not exist at path: " + filePath);
			return false;
		}

		// Parse the file using JavaParser
		FileInputStream fileInputStream = new FileInputStream(participantFile);
		JavaParser javaParser = new JavaParser();
		CompilationUnit cu;
		try {
			cu = javaParser.parse(fileInputStream).getResult()
					.orElseThrow(() -> new IOException("Failed to parse the Java file"));
		} catch (IOException e) {
			System.out.println("Error parsing the file: " + e.getMessage());
			throw e;
		}

		System.out.println("Parsed the Java file successfully.");

		// Variables to check conditions
		int variableCount = 0;
		boolean hasComparisonOperation = false;

		// Check for variable declarations (primitive types like int)
		System.out.println("------ Checking Variable Declarations ------");
		for (VariableDeclarator varDecl : cu.findAll(VariableDeclarator.class)) {
			variableCount++;
			System.out.println("✓ Found variable declaration: " + varDecl.getNameAsString());
		}

		// Check for comparison operations (==, !=, <, >, <=, >=)
		System.out.println("------ Checking Comparison Operations ------");
		for (ExpressionStmt expr : cu.findAll(ExpressionStmt.class)) {
			String expressionString = expr.getExpression().toString(); // Convert the expression to string
			System.out.println("Checking expression: " + expressionString);

			// Check if the expression contains any comparison operators
			if (expressionString.contains("==") || expressionString.contains("!=") || expressionString.contains("<")
					|| expressionString.contains(">") || expressionString.contains("<=")
					|| expressionString.contains(">=")) {
				hasComparisonOperation = true;
				System.out.println("✓ Found comparison operation: " + expressionString);
				break; // Stop once we find the first comparison operation
			}
		}

		// Output the result
		System.out.println("Total number of variable declarations found: " + variableCount);
		System.out.println("Has comparison operation: " + hasComparisonOperation);

		// Both conditions must be true for the test to pass
		boolean result = variableCount >= 2 && hasComparisonOperation;
		System.out.println("Test result: " + result);

		return result;
	}
}
