package matchingGame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck 
{
	ArrayList<Card> cards = new ArrayList<Card>();
	int length;
	
	public Deck(int length)
	{
		this.length = length;		
		createDeck(length);
	}

	private void createDeck(int length) 
	{
		//create deck
		int cardNum = 1;
		for (int i = 0; i < length; i++)
		{
			if (i % 2 == 0 && i != 0)
				cardNum++;
			Card card = new Card("card" + cardNum + ".jpg", cardNum);
			cards.add(card);
		}
		
		//shuffle deck
		Collections.shuffle(cards);
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public Card getCard(int i)
	{
		return cards.get(i);
	}
	
	
	
}


