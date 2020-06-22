package GAME;

import java.util.ArrayList;

import CArds.Cards;
import GAME.players;
public class Store {

private ArrayList< Cards> BuyCard=new ArrayList<>();

public void setBuyCard(ArrayList< Cards> a) {
	BuyCard=a;
}
public void setCardToBuyCard(Cards s) {
	BuyCard.add(s);
}
public ArrayList<Cards> getBuyCard() {
	return BuyCard;
}
public void RemoveCardFromBuyCard(Cards s) {
	BuyCard.remove(s);
}


public  Cards GetCard(Cards s) {
	BuyCard.remove(s);
	return s;
	}

}