package ca.carleton.poker.entity.rank;

import ca.carleton.poker.entity.card.Rank;

import java.util.List;

/**
 * A rank for a hand.
 *
 * Created by Mike on 14/09/2015.
 */
public class PokerRank implements Comparable<PokerRank> {

    private final HandRank handRank;

    private final List<Rank> highCards;

    public PokerRank(final HandRank handRank, final List<Rank> highCards) {
        this.handRank = handRank;
        this.highCards = highCards;
    }

    public HandRank getHandRank() {
        return this.handRank;
    }

    public List<Rank> getHighCards() {
        return this.highCards;
    }

    @Override
    public String toString() {
        return String.format("%s (high card(s): %s)", this.handRank, this.highCards);
    }

    @Override
    public int compareTo(final PokerRank rhs) {
        final int handRankResult = this.handRank.compareTo(rhs.getHandRank());
        if (handRankResult == 0) {
            for (final Rank currentRank : this.highCards) {
                for (final Rank otherRank : rhs.getHighCards()) {
                    final int currentHighCardRank = currentRank.compareTo(otherRank);
                    if (currentHighCardRank != 0) {
                        return currentHighCardRank;
                    }
                }
            }
            return 0;
        } else {
            return handRankResult;
        }
    }
}
