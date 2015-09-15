package ca.carleton.poker.entity.card;

import java.util.Comparator;

/**
 * Represents a playing card.
 *
 * Created by Mike on 14/09/2015.
 */
public class Card {

    private final Rank rank;

    private final Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", this.rank, this.suit);
    }

    public static enum Comparators implements Comparator<Card> {
        BY_SUIT {
            @Override
            public int compare(final Card o1, final Card o2) {
                return o1.getSuit().compareTo(o2.getSuit());
            }
        },
        BY_RANK {
            @Override
            public int compare(final Card o1, final Card o2) {
                // Slight caveat - we *could* change the rank enum to go descending order but this is easier.
                return -1 * o1.getRank().compareTo(o2.getRank());
            }
        }
    }
}
