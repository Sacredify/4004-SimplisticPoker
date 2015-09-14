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
    public int playerId;

    // The cards of the hand.
    public List<Card> cards;

    // The final ranking of the poker hand (per-round).
    public int ranking;

    public void addCard(final Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>(5);
        }
        if (this.cards.size() == 5) {
            throw new IllegalStateException("hands may have up to 5 cards.");
        }
        this.cards.add(card);
    }
}
