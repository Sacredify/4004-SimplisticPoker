package ca.carleton.poker.entity;

import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.rank.PokerRank;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.toList;

/**
 * Represents a hand a poker player may have.
 *
 * Created by Mike on 14/09/2015.
 */
public class PokerHand {

    // The player ID this hand is assigned to.
    private int playerId;

    // The cards of the hand.
    private List<Card> cards;

    // The poker rank (aka the hand, straight flush, etc).
    private PokerRank pokerRank;

    // The final finalRank of the poker hand (per-round).
    private int finalRank;

    public void addCard(final Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>(5);
        }
        if (this.cards.size() == 5) {
            throw new IllegalStateException("hands may have up to 5 cards.");
        }
        this.cards.add(card);
    }

    public List<Rank> getCardRanksForHighCard() {
        final List<Card> copyOf = new ArrayList<>(this.cards);
        sort(copyOf, Card.Comparators.BY_RANK);
        return copyOf.stream().map(Card::getRank).collect(toList());
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(final int playerId) {
        this.playerId = playerId;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(final List<Card> cards) {
        this.cards = cards;
    }

    public PokerRank getPokerRank() {
        return this.pokerRank;
    }

    public void setPokerRank(final PokerRank pokerRank) {
        this.pokerRank = pokerRank;
    }

    public int getFinalRank() {
        return this.finalRank;
    }

    public void setFinalRank(final int finalRank) {
        this.finalRank = finalRank;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("Hand for player %d:\n", this.playerId));
        for (final Card card : this.cards) {
            builder.append(String.format("\t%s\n", card));
        }
        builder.append(String.format("Final player rank: %d [%s]", this.finalRank, this.pokerRank));
        return builder.toString();
    }
}
