package lolmatchticketing.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> itemList = new ArrayList<>();

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public ArrayList<CartItem> getItemList() {
        return itemList;
    }

    public int getNumItems() {
        return itemList.size();
    }

    public String getItemInfo(int index) {
        return itemList.get(index).toString();
    }

    public void addItem(Match match) {
        CartItem item = getCartItem(match);
        if (item == null) {
            itemList.add(new CartItem(match));
        } else {
            item.addQuantity(1);
        }
    }
    
    private CartItem getCartItem(Match match) {
        for (CartItem item : itemList) {
            if (item.getMatch() == match) return item;
        }
        return null;
    }
    
    private CartItem getCartItem(int matchId) {
        for (CartItem item : itemList) {
            if (item.matchId == matchId) return item;
        }
        return null;
    }

    public void resetCart() {
        itemList.clear();
    }

    public boolean isValidItem(int matchId) {
        for (CartItem item : itemList) {
            if (item.matchId == matchId) return true;
        }
        return false;
    }

    public void deleteItem(int matchId) {
        CartItem item = getCartItem(matchId);
        itemList.remove(item);
    }

    public void updateQuantity(int matchId, int quantity) {
        if (quantity == 0)
            deleteItem(matchId);
        else {
            CartItem item = getCartItem(matchId);
            item.setQuantity(quantity);
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem item : itemList) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
