package ca.carleton.poker.client;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;
import ca.carleton.poker.service.PokerRankService;
import ca.carleton.poker.service.SimplisticPokerService;
import ca.carleton.poker.service.input.InputService;
import ca.carleton.poker.service.input.impl.InputServiceFactory;

import static java.lang.System.out;

/**
 * Main class.
 * Created by Mike on 14/09/2015.
 */
public class Launcher {

    private static final SimplisticPokerService pokerService = new SimplisticPokerService(new PokerRankService());

    public static void main(final String[] args) {

        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ONE, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.THREE, Suit.SPADES));
        hand1.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));
        hand1.setPokerRank(new PokerRank(HandRank.STRAIGHT, Rank.FIVE));

        out.println(hand1);
        out.print("Hand input >>> ");

        final InputService in = InputServiceFactory.getInputService();

        final String input = in.getInput();
        final PokerHand hand = pokerService.makeHand(input);
    }
}
