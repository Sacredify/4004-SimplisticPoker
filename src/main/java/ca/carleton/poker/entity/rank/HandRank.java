package ca.carleton.poker.entity.rank;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * The rank for a a hand (straight, etc.)
 * <p>
 * Created by Mike on 14/09/2015.
 */
public enum HandRank {

    HIGH_CARD,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    STRAIGHT,
    FLUSH,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    STRAIGHT_FLUSH,
    ROYAL_FLUSH;

    @Override
    public String toString() {
        return capitalize(this.name().toLowerCase().replaceAll("_", " "));
    }
}
