package application;

public class CreditCard {

	private int cardNumber;
	private String cardName;
	private int cardExpiration;
	private int cardCVC;
	private String billingAddress;
	
	public CreditCard(int cardNumber, String cardName, int cardExpiration, int cardCVC, String billingAddress)
	{
		setCardNumber(cardNumber);
		setCardName(cardName);
		setCardExpiration(cardExpiration);
		setCardCVC(cardCVC);
		setBillingAddress(billingAddress);
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(int cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public int getCardCVC() {
		return cardCVC;
	}

	public void setCardCVC(int cardCVC) {
		this.cardCVC = cardCVC;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
}
