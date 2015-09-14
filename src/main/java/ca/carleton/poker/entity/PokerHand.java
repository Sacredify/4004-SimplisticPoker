package ca.carleton.poker.entity;

import ca.carleton.poker.entity.card.Card;

import java.util.ArrayList;
import java.util.List;

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

    // The final ranking of the poker hand (per-round).
    private int ranking;

    public void addCard(final Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>(5);
        }
        if (this.cards.size() == 5) {
            throw new IllegalStateException("hands may have up to 5 cards.");
        }
        this.cards.add(card);
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

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(final int ranking) {
        this.ranking = ranking;
    }
}
