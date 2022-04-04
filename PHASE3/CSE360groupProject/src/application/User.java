package application;

public class User {

	private String userName;
	
	private int phoneNumber;
	
	public User(String userName, int phoneNumber)
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

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
