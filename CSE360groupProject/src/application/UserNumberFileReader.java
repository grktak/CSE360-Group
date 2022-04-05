package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class UserNumberFileReader {

	public static HashMap<String, String> userToPhoneNumbers = new HashMap<String, String>();
	
	@SuppressWarnings("unused")
	private static boolean fileIsEmpty;
	
	public static void ReadUserNumbersFile()
	{
		File myObj = new File("UserNumbers.txt");
		try {
			try (Scanner myReader = new Scanner(myObj)) {
				
				if(!myReader.hasNext())
				{
					fileIsEmpty = true;
				}		
				else
				{
					fileIsEmpty = false;
				}
				
				while(myReader.hasNextLine())
				{
					String data = myReader.nextLine();
					
					String[] parts = data.split(" ");
						
					userToPhoneNumbers.put(parts[0], parts[1]); //causes array index out of bounds error when more then 4 error
						
					System.out.println(parts[0] + " : " +  parts[1]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occured when trying to read from the user name and phone numbers file.");
			e.printStackTrace();
		}
	}
	
	public static void WriteNewUserAndNumber(String user, String num)
	{		
		File myObj = new File("UserNumbers.txt");
		try {
			if(myObj.createNewFile())
			{
				System.out.println("File created: " + myObj.getName());
			}
			else
			{
				System.out.println("File 'UserNumbers.txt' exists");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileWriter myWriter = new FileWriter("UserNumbers.txt", true);
			
			if(fileIsEmpty)
			{
				myWriter.write(user + " " + num);
				fileIsEmpty = false;
			}else
			{
				myWriter.write("\n" + user + " " + num);
			}
		
			myWriter.close();
			System.out.println("Wrote new user and number to file.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void updatePhoneNumberInFile(String newNum, String oldNum)
	{
		 	File myObj = new File("UserNumbers.txt");
	        String oldContent = "";
	        BufferedReader reader = null;	         
	        FileWriter writer = null;
	         
	        try
	        {
	            reader = new BufferedReader(new FileReader(myObj));	             
	            //Reading all lines of myObj and storing them in old content	             
	            String line = reader.readLine();
	            while (line != null) 
	            {
	                oldContent = oldContent + line + System.lineSeparator();
	                line = reader.readLine();
	            }
	            //Replacing the phone numbers 
	            String newContent = oldContent.replaceAll(oldNum, newNum);
	            //ReWriting to file w/ new phone number
	            writer = new FileWriter(myObj);
	            writer.write(newContent);
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            try
	            { 
	                reader.close();
	                writer.close();
	            } 
	            catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }	
	}
	
}
