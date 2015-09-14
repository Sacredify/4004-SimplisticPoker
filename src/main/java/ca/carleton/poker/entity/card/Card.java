package ca.carleton.poker.entity.card;

/**
 * Represents a playing card.
 *
 * Created by Mike on 14/09/2015.
 */
public class Card {

    private Rank rank;

    private Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(final Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public void setSuit(final Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }
}
