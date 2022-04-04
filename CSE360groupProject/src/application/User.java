package application;

public abstract class User {

	private String userName;
	
	private String phoneNumber;
	
	public User(String userName, String phoneNumber)
	{
		setUserName(userName);
		setPhoneNumber(phoneNumber);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getFormattedNumber()
	{
		String newString = "";
		int count = 0;
		
		for(int i = 0; i < phoneNumber.length(); i++)
		{
			if(((i + 1) % 3 == 0) && count != 2)
			{
				newString += phoneNumber.charAt(i) + "-";
				count++;
			}
			else
			{
				newString += phoneNumber.charAt(i);
			}
		}
		
		return newString;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
