package ca.carleton.poker.entity.card;

import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;

/**
 * Represents a playing card.
 *
 * Created by Mike on 14/09/2015.
 */
public class Card {

    public Rank rank;

    public Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
}
