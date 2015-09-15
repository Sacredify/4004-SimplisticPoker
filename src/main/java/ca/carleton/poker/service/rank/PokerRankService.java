package ca.carleton.poker.service.rank;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.toList;
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
     * <p>
     * This chain calls each method in turn, starting from highest-value hands (so we don't accidentally
     * de-value a hand because we called 3 of a kind before 4 of a kind).
     * <p>
     * Cool? Maybe. Efficient? Not necessarily. But hey, its an assignment.
     */
    private final Consumer<PokerHand> handCheck = checkRoyalFlush
            .andThen(checkStraightFlush)
            .andThen(checkFourOfAKind)
            .andThen(checkFullHouse)
            .andThen(checkFlush)
            .andThen(checkStraight)
            .andThen(checkThreeOfAKind)
            .andThen(checkTwoPair)
            .andThen(checkOnePair)
            .andThen(checkHighCard);

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

    private static final Consumer<PokerHand> checkRoyalFlush = pokerHand -> {
        if (pokerHand.getPokerRank() != null) {
            return;
        }
        final boolean hasRanks = containsRanks(pokerHand.getCards(),
                Rank.TEN,
                Rank.JACK,
                Rank.QUEEN,
                Rank.KING,
                Rank.ACE);
        final boolean isAllSameSuit = isAllSameSuit(pokerHand.getCards());
        if (hasRanks && isAllSameSuit) {
            pokerHand.setPokerRank(new PokerRank(HandRank.ROYAL_FLUSH, getHighCard(pokerHand.getCards())));
        }
    };

    private static final Consumer<PokerHand> checkStraightFlush = pokerHand -> {
        if (pokerHand.getPokerRank() != null) {
            return;
        }
        final boolean isAllSameSuit = isAllSameSuit(pokerHand.getCards());
        if (isAllSameSuit && hasStraight(pokerHand.getCards())) {
            pokerHand.setPokerRank(new PokerRank(HandRank.STRAIGHT_FLUSH, getHighCard(pokerHand.getCards())));
        }
    };

    private static final Consumer<PokerHand> checkFourOfAKind = pokerHand -> {
    };

    private static final Consumer<PokerHand> checkFullHouse = pokerHand -> {
    };

    private static final Consumer<PokerHand> checkFlush = pokerHand -> {
        if (pokerHand.getPokerRank() != null) {
            return;
        }
        if (isAllSameSuit(pokerHand.getCards())) {
            pokerHand.setPokerRank(new PokerRank(HandRank.FLUSH, getHighCard(pokerHand.getCards())));
        }
    };

    private static final Consumer<PokerHand> checkStraight = pokerHand -> {
        if (pokerHand.getPokerRank() != null) {
            return;
        }
        if (hasStraight(pokerHand.getCards())) {
            pokerHand.setPokerRank(new PokerRank(HandRank.STRAIGHT, getHighCard(pokerHand.getCards())));
        }
    };

    private static final Consumer<PokerHand> checkThreeOfAKind = pokerHand -> {
    };

    private static final Consumer<PokerHand> checkTwoPair = pokerHand -> {
    };

    private static final Consumer<PokerHand> checkOnePair = pokerHand -> {
    };

    private static final Consumer<PokerHand> checkHighCard = pokerHand -> {
    };

    /**
     * Helper code to get the high card (for tiebreakers or single card) of a list of cards.
     *
     * @param cards the cards.
     * @return the high rank.
     */
    private static Rank getHighCard(final List<Card> cards) {
        final List<Card> copyOf = copyOf(cards);
        sort(copyOf, Card.Comparators.BY_RANK);
        final List<Rank> cardRanks = copyOf.stream().map(Card::getRank).collect(toList());
        return cardRanks.get(0);
    }

    /**
     * Helper code to see if the frequency contains the given ranks.
     */
    private static boolean containsRanks(final List<Card> cards, final Rank... ranks) {
        if (isEmpty(ranks)) {
            return false;
        }
        final List<Rank> cardRanks = cards.stream().map(Card::getRank).collect(toList());
        for (final Rank rank : ranks) {
            if (!cardRanks.contains(rank)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method - create a copy of the list to use with sorting.
     */
    private static <T> List<T> copyOf(final List<T> input) {
        return new ArrayList<>(input);
    }

    /**
     * Helper code to see if the hand is all the same suit.
     */
    private static boolean isAllSameSuit(final List<Card> cards) {
        final List<Card> copyOf = copyOf(cards);
        sort(copyOf, Card.Comparators.BY_SUIT);
        return copyOf.get(0).getSuit() == copyOf.get(copyOf.size() - 1).getSuit();
    }

    private static boolean hasStraight(final List<Card> cards) {
        final List<Card> copyOf = copyOf(cards);
        Collections.sort(copyOf, Card.Comparators.BY_RANK);
        boolean hasStraight = true;
        if (copyOf.get(0).getRank() == Rank.ACE) {
            // Need to check ace,2,3,4,5 and ten,jack,queen,king,ace
            final boolean hasLowerAce =
                    copyOf.get(1).getRank() == Rank.FIVE
                            && copyOf.get(2).getRank() == Rank.FOUR
                            && copyOf.get(3).getRank() == Rank.THREE
                            && copyOf.get(4).getRank() == Rank.TWO;
            final boolean hasHigherAce =
                    copyOf.get(1).getRank() == Rank.KING
                            && copyOf.get(2).getRank() == Rank.QUEEN
                            && copyOf.get(3).getRank() == Rank.JACK
                            && copyOf.get(4).getRank() == Rank.TEN;
            hasStraight = hasLowerAce || hasHigherAce;
        } else {
            for (int i = 1; i < 5; i++) {
                if (copyOf.get(i).getRank().compareTo(copyOf.get(i - 1).getRank()) * -1 != 1) {
                    hasStraight = false;
                    break;
                }
            }
        }
        return hasStraight;
    }
}
