package ca.carleton.poker.entity.rank;

import ca.carleton.poker.entity.card.Rank;

/**
 * A rank for a hand.
 *
 * Created by Mike on 14/09/2015.
 */
public class PokerRank implements Comparable<PokerRank> {

    private final HandRank handRank;

    private final Rank highCard;

    public PokerRank(final HandRank handRank, final Rank highCard) {
        this.handRank = handRank;
        this.highCard = highCard;
    }

    public HandRank getHandRank() {
        return this.handRank;
    }

    public Rank getHighCard() {
        return this.highCard;
    }

    @Override
    public int compareTo(final PokerRank rhs) {
        final int handRankResult = this.handRank.compareTo(rhs.getHandRank());
        if (handRankResult == 0) {
            return this.highCard.compareTo(rhs.getHighCard());
        } else {
            return handRankResult;
        }
    }
}