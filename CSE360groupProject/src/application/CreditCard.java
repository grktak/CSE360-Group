package application;

public class CreditCard {

	private String cardNumber;
	private String cardName;
	private String cardExpiration;
	private String cardCVC;
	private String billingAddress;
	
	public CreditCard(String cardNumber, String cardName, String cardExpiration, String cardCVC, String billingAddress)
	{
		setCardNumber(cardNumber);
		setCardName(cardName);
		setCardExpiration(cardExpiration);
		setCardCVC(cardCVC);
		setBillingAddress(billingAddress);
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(String cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public String getCardCVC() {
		return cardCVC;
	}

	public void setCardCVC(String cardCVC) {
		this.cardCVC = cardCVC;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
}
