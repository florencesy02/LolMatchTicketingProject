package lolmatchticketing.model;

public class CartItem {
    Match match;
    int matchId;
    int quantity;
    
    public CartItem(Match match) {
        this.match = match;
        this.matchId = match.getMatchId();
        this.quantity = 1;
    }
    
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return match.getMatchId() + ", " + match.getName() + ", " + quantity + "장, " + getPrice() + "원";
    }

    public int getPrice() {
        return quantity * match.getTicketPrice();
    }
}
