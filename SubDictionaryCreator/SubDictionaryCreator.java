import java.io.*;
import java.util.*;

/**
 * 
 * COMP249 Assignment # 4 Due Date April 15,2022
 * 
 * @author HARINDER PARTAP SINGH AND 40076200
 * @author Prabhmehar Singh Kharbanda 40210198
 */
public class SubDictionaryCreator {
	static PrintWriter writer = null;
	static Scanner reader = null;
	static Scanner input = new Scanner(System.in);

	static String fileName = null;
	static ArrayList<String> list = null;
	static ArrayList<Integer> asciList = null;
	static ArrayList<String> writerList = null;

	/**
	 * 
	 * This is the main engine of the program
	 */
	public static void main(String[] args) {
		String line = "Enter the file name to create the Sub-Dictionary:";

		createAsciList();

		boolean flag = false;
		while (!flag) {
			System.out.println(line);
			fileName = input.next();
			try {
				reader = new Scanner(new FileInputStream(fileName));
				writer = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
				createDictionary();
				reader = new Scanner(new FileInputStream("SubDictionary.txt"));
				readFile();
				flag = true;
			} catch (FileNotFoundException e) {
				line = "Unable to read the file. Make sure that the file entered is readable.\nEnter again the file name:";
			}
		}
	}

	/**
	 * This method stores all the information in the arraylist.
	 * After creating an arraylist, output file is created.
	 * 
	 */
	public static void createDictionary() {

		list = new ArrayList<>();
		while (reader.hasNext()) {
			list.add(reader.next());
		}
	
		verifyPunctuation(list);
		writeToDictionary();

	}

	/**
	 * This writes the entries of arraylist to the output file.
	 */
	public static void writeToDictionary() {
		int ascii = 0;
		writer.println(
				"The document produced this sub-dictionary, which includes " + writerList.size() + " entries.");
		for (String words : writerList) {
			if (ascii != words.charAt(0)) {
				ascii = words.charAt(0);
				writer.println("\n" + ((char) ascii) + "\n==");
				writer.println(words);
			} else {
				writer.println(words);
			}

		}

		writer.close();

	}

	/**
	 * Reads the generated dictionary file.
	 */
	public static void readFile() {

		while (reader.hasNextLine()) {
			System.out.println(reader.nextLine());

		}
	}

	/**
	 * Verifies if there is any punctuation or any words that includes numbers.
	 * If the words in the list includes punctuation then the word is splited from that point.
	 * @param list The list to read. 
	 */
	public static void verifyPunctuation(ArrayList<String> list) {
		writerList = new ArrayList<String>();

		for (String word : list) {
			boolean foundNum = false;
			boolean foundPunc = false;

			word = word.toUpperCase();
			int length = word.length();

			if (length == 1) {
				if ((word.equals("A") || word.equals("I")) && !writerList.contains(word)) {
					writerList.add(word);
				}
				continue;
			}

			if (word.contains(",") || word.contains(".") || word.contains(":") || word.contains(";")
					|| word.contains("!") || word.contains("?")) {
				word = word.substring(0, length - 1);
				foundPunc = true;
			}

			for (int j = 0; j < word.length(); j++) {
				if(word.equals("MC\u00B2")) {
					
				    break;
				}
				
				if (word.charAt(j) == 8217) {
					word = word.split("’")[0];
					break;
				}
				if (word.charAt(j) == 39) {
					word = word.split("'")[0];
					break;
				}
				if(word.charAt(j)<0 || word.charAt(j)>127) {
					foundNum=true;
				}
				for (int k = 0; k < asciList.size(); k++) {
					if (word.charAt(j) == asciList.get(k)) {
						foundNum = true;
					}
				}
			}

			if (!foundNum && !writerList.contains(word)) {
				writerList.add(word);
			}
		}

		sortArray(writerList);

	}

	/**
	 * This sorts the array from A-Z
	 * 
	 * @param list The name of the list to be sorted.
	 */
	public static void sortArray(ArrayList<String> list) {
		String string1 = null;
		String string2 = null;

		for (int i = 0; i < list.size(); i++)

			for (int j = 0; j < list.size() - 1; j++) {
				string1 = list.get(j);
				string2 = list.get(j + 1);
				if (string1.compareTo(string2) > 0) {

					list.set(j, string2);
					list.set(j + 1, string1);

				}
			}
	}

	/**
	 * This creates the list of ascii values of all the numbers and '=' sign.
	 */
	public static void createAsciList() {
		asciList = new ArrayList<>();
		for (int i = 48; i <= 57; i++) {
			asciList.add(i);
		}
		asciList.add(61);

	}

}
