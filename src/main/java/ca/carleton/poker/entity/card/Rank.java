package ca.carleton.poker.entity.card;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * Rankings of cards.
 *
 * Created by Mike on 14/09/2015.
 */
public enum Rank {

    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public boolean isLessThan(final Rank rank) {
        return this.ordinal() < rank.ordinal();
    }

    public boolean isGreaterThan(final Rank rank) {
        return this.ordinal() > rank.ordinal();
    }

    @Override
    public String toString() {
        return capitalize(this.name().toLowerCase());
    }
}
