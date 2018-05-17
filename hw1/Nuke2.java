/* Nuke2.java */

import java.io.*;

/**  A class that reads a string from the keyboard and prints the same string, with its
	second character removed.
 */

class Nuke2 {

	public static void main(String[] arg) throws Exception {

		BufferedReader keyboard;
	    String inputLine;

	    keyboard = new BufferedReader(new InputStreamReader(System.in));

	    System.out.print("Please enter the string: ");
	    System.out.flush();        /* Make sure the line is printed immediately. */
	    inputLine = keyboard.readLine();
	    if (inputLine == null || inputLine.length() < 2) {
	    	System.out.println(inputLine);
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append(inputLine.charAt(0));
	    sb.append(inputLine.substring(2));

	    System.out.println(sb.toString());
	}

}