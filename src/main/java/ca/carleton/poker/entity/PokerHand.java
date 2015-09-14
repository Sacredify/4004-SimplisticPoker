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

    public List<Card> cards;

    public void addCard(final Card card) {
        if (this.cards == null) {
            this.cards = new ArrayList<>(5);
        }
        this.cards.add(card);
    }
}
