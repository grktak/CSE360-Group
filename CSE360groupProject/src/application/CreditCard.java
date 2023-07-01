package application;

public class CreditCard {

	private String cardNumber;
	private String cardExpiration;
	private String cardCVC;
	
	public CreditCard(String cardNumber, String cardExpiration, String cardCVC)
	{
		setCardNumber(cardNumber);
		setCardExpiration(cardExpiration);
		setCardCVC(cardCVC);
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	
}
