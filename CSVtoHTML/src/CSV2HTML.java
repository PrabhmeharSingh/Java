
/*
 *	Assignment 3 
 *	Written By: Prabhmehar Singh Kharbanda 40210198
 *	This Program reads and processes CSV files and 
 *	creates corresponding HTML tables if possible 
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Name and ID - Prabhmehar Singh Kharbanda 40210198<br>
 * COMP 249<br>
 * Assignment 3<br>
 * Due March 25 2022<br>
 * Class to implement the CVS to HTML tool
 * @author prabh
 */
public class CSV2HTML {

	/**
	 * Core engine for processing the input files and creating the output files
	 * @param file_name File Name
	 * @param fin Scanner object for csv file input
	 * @param fout PrintWriter Object for html file output
	 * @param linesRead Number of lines already read ( if re-entering the method )
	 * @param attributeLine Attribute line of the input file ( if re-entering the method )
	 * @throws CSVAttributeMissing thrown if there is a missing attribute in the input file
	 * @throws CSVDataMissing thrown if there is a missing data field in the input file
	 */
	public static void convertCSVtoHTML(String file_name, Scanner fin, PrintWriter fout, int linesRead,
			String attributeLine) throws CSVAttributeMissing, CSVDataMissing {
		String title = null;
		String attLine = null;
		String line = null;
		int numAttributes = 1;
		int ctr = 0;
		boolean hasNote = false;
		if (linesRead == 0) {
			title = fin.nextLine();
			title = title.substring(0, title.indexOf(','));
			attLine = fin.nextLine();
			ctr = 2;
		} else {
			ctr = linesRead;
			attLine = attributeLine;
		}
		line = attLine;
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == ',')
				numAttributes++;
		}
		String attributes[] = new String[numAttributes];
		String[] tableContents = new String[numAttributes];

		for (int i = 0; i < numAttributes; i++) {
			if (i < numAttributes - 1)
				attributes[i] = line.substring(0, line.indexOf(','));
			else
				attributes[i] = line;
			line = line.substring(line.indexOf(',') + 1);
			if (attributes[i].isBlank()) {
				fin.close();
				fout.close();
				throw new CSVAttributeMissing(
						"ERROR: In file " + file_name + " Missing attribute.File is not converted to HTML.");
			}
		}
		if (ctr == 2) {
			fout.println("<!DOCTYPE html>\n" + "<style>\n"
					+ "table{font-family:arial, sans-serif;border-collapse:collapse;}\n"
					+ "td,th{border:1px solid #000000;text-align: center;padding:8px;}\n"
					+ "tr:nth-child(even){background-color: #dddddd;}\n" + "span{font-size:  small}\n" + "</style>\n"
					+ "<body>\n" + "<table>\n" + "<caption>" + title + "</caption>\n" + "<tr>");
			for (int i = 0; i < numAttributes; i++)
				fout.println("<th>" + attributes[i] + "</th>");
			fout.println("</tr");
		}
		while (fin.hasNextLine()) {
			line = fin.nextLine();
			ctr++;
			if (line.substring(0, 5).equals("Note:") && !fin.hasNextLine()) {
				hasNote = true;
				fout.println("</table>\n<span>" + line.substring(0, line.indexOf(',')) + "</span>");
				break;
			}

			for (int i = 0; i < numAttributes; i++) {
				if (i < numAttributes - 1)
					tableContents[i] = line.substring(0, line.indexOf(','));
				else
					tableContents[i] = line;
				line = line.substring(line.indexOf(',') + 1);
				if (tableContents[i].isBlank()) {
					throw new CSVDataMissing("WARNNING: In file  " + file_name + " line " + ctr
							+ " is not converted to HTML: missing data:" + attributes[i], ctr, attLine);
				}
			}
			fout.println("<tr>");
			for (int i = 0; i < numAttributes; i++)
				fout.println("<td>" + tableContents[i] + "</td>");
			fout.println("</tr>");

		}
		fout.println(((!hasNote) ? ("</table>\n") : ("")) + "</body>\n</html>");
		fin.close();
		fout.close();
	}

	/**
	 * main method
	 * @param args nothing
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File outputDirectory = new File("OutputFiles");
		Scanner fin1 = null, fin2 = null;
		PrintWriter fout1 = null, fout2 = null, ferr = null;
		Scanner cin = new Scanner(System.in);
		BufferedReader bin = null;
		String oName = null, l = null;
		System.out.println("Welcome\nThis program is created by Prabhmehar Singh Kharbanda");;
		try {
			fin1 = new Scanner(new FileInputStream("covidStatistics.csv"));
			fin2 = new Scanner(new FileInputStream("doctorList.csv"));
			outputDirectory.mkdir();
			fout1 = new PrintWriter(new FileOutputStream("OutputFiles/covidStatistics.html"));
			fout2 = new PrintWriter(new FileOutputStream("OutputFiles/doctorList.html"));
			ferr = new PrintWriter(new FileOutputStream("OutputFiles/Exceptions.log", true));

		} catch (FileNotFoundException e) {
			String file_name = null;
			if (fin1 == null || fin2 == null) {
				if (fin1 == null) {
					file_name = "covidStatistics";
				} else {
					file_name = "doctorList";
					fin1.close();
				}
				System.out.println("Could not open file " + file_name + ".csv for reading.\n"
						+ "Please check that the file exists and is readable. This program will terminate after closing any opened files.");
			} else {
				fin1.close();
				fin2.close();
				if (!outputDirectory.isDirectory()) {
					System.out.println("Directory was not created. Ending Program.");
					System.exit(0);
				} else if (fout1 == null)
					file_name = "covidStatistics.html";
				else if (fout2 == null) {
					file_name = "doctorList.html";
					fout1.close();
				} else {
					file_name = "Exceptions.log";
					fout1.close();
					fout2.close();
				}
				System.out.println(file_name + " could not be opened/created.");
				File[] fileArray = outputDirectory.listFiles();
				for (int i = 0; i < fileArray.length; i++)
					fileArray[i].delete();
			}
			System.exit(0);

		}
		tryingToConvert("covidStatistics.csv", fin1, fout1, ferr);
		tryingToConvert("doctorList.csv", fin2, fout2, ferr);
		ferr.close();
		System.out.println("Output Files Created are \n1.)covidStatistics.html\n2.)doctorList.html\n3.)Exceptions.log");

		for (int i = 0; i < 2; i++) {
			System.out.println(((i == 0) ? ("First") : ("Second (FINAL)")) + " Attempt");
			System.out.println("Enter the name of the output file you want to display");
			oName = cin.next();
			try {
				if (!(oName.equals("covidStatistics.html") || oName.equals("doctorList.html")
						|| oName.equals("Exceptions.log")))
					throw new FileNotFoundException("This name does not correspond to one of the created output files");
				bin = new BufferedReader(new FileReader("OutputFiles/" + oName));
				System.out.println("Displaying File " + oName);
				while (true) {
					l = bin.readLine();
					if (l == null)
						break;
					System.out.println(l);
				}
				bin.close();
				break;
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				if (i == 1) {
					System.out.println("Second Attempt Failed terminating program");
					cin.close();
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println("Error Reading File. Terminating");
				break;
			}
		}
		System.out.println("Thank you for using this program.");
		cin.close();

	}

	/**
	 * Method for calling the converCSVtoHTML method and handling the CSVDataMissing and CSVAttributeMissing Exception.
	 * @param fName File Name (To be passed to the convertCSVtoHTML method)
	 * @param in Scanner object for csv file input (To be passed to the convertCSVtoHTML method)
	 * @param out PrintWriter object for html file output (To be passed to the convertCSVtoHTML method
	 * @param error PrintWriter object for log file output (To write errors)
	 */
	public static void tryingToConvert(String fName, Scanner in, PrintWriter out, PrintWriter error) {
		int lines = 0;
		String atLine = null;
		while (true) {
			try {
				convertCSVtoHTML(fName, in, out, lines, atLine);
				break;
			} catch (CSVAttributeMissing e) {
				System.out.println(e.getMessage());
				error.println(e.getMessage());
				break;
			} catch (CSVDataMissing e) {
				System.out.println(e.getMessage());
				error.println(e.getMessage());
				lines = e.getNumLines();
				atLine = e.getAttributeLine();
			}
		}
	}

}

/**
 * Exception class for missing attributes
 * @author prabh
 *
 */
class CSVAttributeMissing extends Exception {
	/**
	 * default constructor
	 */
	public CSVAttributeMissing() {
		super("Error: Input row cannot be parsed due to missing information.");
	}
	
	/**
	 * parameterized constructor
	 * @param msg String message to define the type of exception ( to be sent to parent class Exception )
	 */
	public CSVAttributeMissing(String msg) {
		super(msg);
	}
}
/**
 * Exception class for missing data fields
 * @author prabh
 *
 */
class CSVDataMissing extends Exception {
	private int numLines;
	private String attributeLine;

	/**
	 * default constructor
	 */
	public CSVDataMissing() {
		super("Error: Input row cannot be parsed due to missing information.");
	}

	/**
	 * parameterized constructor
	 * @param msg String message to define the type of exception ( to be sent to parent class Exception )
	 * @param x Number of lines read before the Exception was thrown
	 * @param l Attribute line of the file for which the Exception was thrown
	 */
	public CSVDataMissing(String msg, int x, String l) {
		super(msg);
		this.attributeLine = l;
		this.numLines = x;
	}

	/**
	 * Accessor method for numLines
	 * @return The number of lines read before the Exception was thrown
	 */
	public int getNumLines() {
		return numLines;
	}

	/**
	 * Accessor method for attributeLine
	 * @return Attribute line of the file for which the Exception was thrown
	 */
	public String getAttributeLine() {
		return this.attributeLine;
	}
}