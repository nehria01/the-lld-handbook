package ATMSystem;

public class Card {
    private String cardNumber;
    private String cardPin;

    public Card(String cardNumber, String cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin =  cardPin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }
}
