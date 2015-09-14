package ca.carleton.poker.entity.card;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * Suits of cards.
 * <p/>
 * Created by Mike on 14/09/2015.
 */
public enum Suit {
    SPADES,
    CLUBS,
    HEARTS,
    DIAMONDS;

    @Override
    public String toString() {
        return capitalize(this.name().toLowerCase());
    }
}
