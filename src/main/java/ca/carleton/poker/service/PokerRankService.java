package ca.carleton.poker.service;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;

import java.util.*;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Service to determine poker ranks for a hand.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public class PokerRankService {

    /**
     * Rank a given poker hand. This method sets the pokerRank field in the hand.
     *
     * @param pokerHand the hand.
     * @return the same hand passed in.
     */
    public PokerHand rankHand(final PokerHand pokerHand) {

        // Working our way down we're going to determine the hand from highest value to lowest.

        checkRoyalFlush(pokerHand);

        return pokerHand;
    }

    private static void checkRoyalFlush(final PokerHand hand) {

        // Prevent further modification downstream by lesser hands - this is quite implementation dependent.
        if (hand.getPokerRank() != null) {
            return;
        }

        final Map<Rank, Integer> frequency = getRankFrequency(hand.getCards());
        final boolean hasRanks = containsRanks(frequency, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE);
        final boolean isAllSameSuit = isAllSameSuit(hand.getCards());

        if (hasRanks && isAllSameSuit) {
            hand.setPokerRank(new PokerRank(HandRank.ROYAL_FLUSH, getHighCard(hand.getCards())));
        }
    }

    private static void checkStraightFlush(final PokerHand pokerHand) {

    }


    private static Map<Rank, Integer> getRankFrequency(final List<Card> cards) {
        final Map<Rank, Integer> frequency = new HashMap<>();
        cards.forEach(card -> {
            final Integer currentFrequency = frequency.get(card.getRank());
            frequency.put(card.getRank(), currentFrequency == null ? 1 : currentFrequency + 1);
        });
        return frequency;
    }

    /**
     * Helper code to get the high card (for tiebreakers or single card) of a list of cards.
     *
     * @param cards the cards.
     * @return the high rank.
     */
    private static Rank getHighCard(final List<Card> cards) {
        final Map<Rank, Integer> frequency = getRankFrequency(cards);
        final List<Rank> cardRanks = new ArrayList<>(frequency.keySet());
        Collections.sort(cardRanks);
        // We need to reverse because we're looking at descending order.
        Collections.reverse(cardRanks);
        return cardRanks.get(0);
    }

    /**
     * Helper code to see if the frequency contains the given ranks.
     */
    private static boolean containsRanks(final Map<Rank, Integer> frequency, final Rank... ranks) {
        if (isEmpty(ranks)) {
            return false;
        }
        for (final Rank rank : ranks) {
            if (!frequency.containsKey(rank)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper code to see if the hand is all the same suit.
     */
    private static boolean isAllSameSuit(final List<Card> cards) {
        outer:
        for (final Suit suit : Suit.values()) {
            for (final Card card : cards) {
                if (card.getSuit() != suit) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }
}
