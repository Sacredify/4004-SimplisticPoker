package ca.carleton.poker;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;

/**
 * Main class.
 * Created by Mike on 14/09/2015.
 */
public class Launcher {

    public static void main(final String[] args) {

        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ONE, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.THREE, Suit.SPADES));
        hand1.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));
        hand1.setPokerRank(new PokerRank(HandRank.STRAIGHT, Rank.FIVE));

        System.out.println(hand1);
    }
}
