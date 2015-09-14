package ca.carleton.poker.entity.card;

/**
 * Represents a playing card.
 *
 * Created by Mike on 14/09/2015.
 */
public class Card {

    public Rank rank;

    public Suit suit;

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
}
