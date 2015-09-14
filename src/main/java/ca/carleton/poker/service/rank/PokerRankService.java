package ca.carleton.poker.service.rank;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;

import java.util.*;
import java.util.function.Consumer;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * Service to determine poker ranks for a hand.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public class PokerRankService {

    /**
     * Consumer chain that will check and (if valid, set) the PokerRank field of the hand,
     * which contains info on what their hand is (two high, etc.), as well as the high card.
     *
     * This chain calls each method in turn, starting from highest-value hands (so we don't accidentally
     * de-value a hand because we called 3 of a kind before 4 of a kind).
     *
     * Cool? Maybe. Efficient? Not necessarily. But hey, its an assignment.
     */
    private final Consumer<PokerHand> handCheck = applyRoyalFlush
            .andThen(applyStraightFlush)
            .andThen(applyFourOfAKind)
            .andThen(applyFullHouse)
            .andThen(applyFlush)
            .andThen(applyStraight)
            .andThen(applyThreeOfAKind)
            .andThen(applyTwoPair)
            .andThen(applyOnePair)
            .andThen(applyHighCard);

    /**
     * Rank a given poker hand. This method sets the pokerRank field in the hand.
     *
     * @param pokerHand the hand.
     * @return the same hand passed in.
     */
    public PokerHand rankHand(final PokerHand pokerHand) {
        this.handCheck.accept(pokerHand);
        return pokerHand;
    }

    private static final Consumer<PokerHand> applyRoyalFlush = pokerHand -> {
        if (pokerHand.getPokerRank() != null) {
            return;
        }

        final Map<Rank, Integer> frequency = getRankFrequency(pokerHand.getCards());
        final boolean hasRanks = containsRanks(frequency, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE);
        final boolean isAllSameSuit = isAllSameSuit(pokerHand.getCards());

        if (hasRanks && isAllSameSuit) {
            pokerHand.setPokerRank(new PokerRank(HandRank.ROYAL_FLUSH, getHighCard(pokerHand.getCards())));
        }
    };

    private static final Consumer<PokerHand> applyStraightFlush = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyFourOfAKind = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyFullHouse = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyFlush = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyStraight = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyThreeOfAKind = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyTwoPair = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyOnePair = pokerHand -> {
    };
    private static final Consumer<PokerHand> applyHighCard = pokerHand -> {
    };

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
