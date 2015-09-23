package ca.carleton.poker.entity.rank;

import ca.carleton.poker.entity.card.Rank;

import java.util.List;

/**
 * A rank for a hand.
 * <p>
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
            for (int i = 0; i < this.highCards.size(); i++) {
                final Rank thisHighCard = this.highCards.get(i);
                final Rank otherHighCard = rhs.getHighCards().get(i);
                final int currentHighCardRank = thisHighCard.compareTo(otherHighCard);
                if (currentHighCardRank != 0) {
                    return currentHighCardRank;
                }
            }
            return 0;
        } else {
            return handRankResult;
        }
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PokerRank)) {
            return false;
        }
        final PokerRank rhs = (PokerRank) other;

        if (this.handRank == rhs.getHandRank()) {
            if (this.highCards.size() == rhs.getHighCards().size()) {
                for (int i = 0; i < this.highCards.size(); i++) {
                    final Rank thisHighCard = this.highCards.get(i);
                    final Rank otherHighCard = rhs.getHighCards().get(i);
                    if (thisHighCard != otherHighCard) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
