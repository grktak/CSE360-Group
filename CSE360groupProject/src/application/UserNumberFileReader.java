package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class UserNumberFileReader {

	public static HashMap<String, String> userToPhoneNumbers = new HashMap<String, String>();
	
	public static void ReadFile()
	{
		File myObj = new File("UserNumbers.txt");
		try {
			try (Scanner myReader = new Scanner(myObj)) {
				while(myReader.hasNextLine())
				{
					String data = myReader.nextLine();
					
					String[] parts = data.split(" ");
						
					userToPhoneNumbers.put(parts[0], parts[1]);
						
					System.out.println(parts[0] + " : " +  parts[1]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occured when trying to read from the user name and phone numbers file.");
			e.printStackTrace();
		}
	}
	
}
