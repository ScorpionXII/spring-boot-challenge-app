package com.challenge.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MetaSQLParser {
	
	private char[] mqlQuery = new char[0];
	
	private List<String> booleans = Arrays.asList("and", "or");
	private List<String> operators = Arrays.asList(">", "<", "=");
	
	private Stack<String> prelimStack = new Stack<String>();
	private Stack<String> tempStack = new Stack<String>();
	private Stack<String> queryStack = new Stack<String>();
	
	private String word = "";
	
	public MetaSQLParser() {
		
	}
	
	public String toSQL(String mql) {
		this.mqlQuery = mql.toCharArray();
		
		for (int i = 0; i < mqlQuery.length; i++) {
			if (mqlQuery[i] == '(') {
				checkWord(word);
				prelimStack.push("(");
			} else if (operators.contains(String.valueOf(mqlQuery[i]))) {
				checkWord(word);
				prelimStack.push(String.valueOf(mqlQuery[i]));
			} else if (mqlQuery[i] == ')') {
				checkWord(word);
				if (tempStack.size() == 0) {
					prelimStack.push(String.valueOf(mqlQuery[i]));
				} else {
					String operand2 = tempStack.pop();
					String operand1 = tempStack.pop();
					String operator = prelimStack.pop();
					prelimStack.pop(); // remove parenthesis
					String exp = operand1+operator+operand2;
					prelimStack.push(exp);
				}
			} else {
				word+=String.valueOf(mqlQuery[i]);
			}
		}
		
		for (int i = prelimStack.size() - 1; i >= 0; i--) {
			if (!prelimStack.elementAt(i).equals("(")) {
				queryStack.push(prelimStack.elementAt(i));
			} else {
				tempStack.clear();
				String operator = queryStack.pop().toUpperCase();
				
				while(!queryStack.lastElement().equals(")")) {
					tempStack.push(queryStack.pop());
				}
				
				queryStack.pop(); // remove parenthesis
				queryStack.push("("+String.join(" "+operator+" ", tempStack)+")");
			}
		}
		
		return queryStack.pop();
	}
	
	private void checkWord(String s) {
		if (word != "")
			if (booleans.contains(word))
				prelimStack.push(word);
			else
				tempStack.push(word);
		word = "";
	}
}