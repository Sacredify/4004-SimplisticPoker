package ca.carleton.poker.service;

import ca.carleton.poker.entity.PokerHand;

import java.util.List;

/**
 * Methods for the capture and determination of poker hand ranks.
 * <p>
 * Created by Mike on 14/09/2015.
 */
public final class SimplisticPokerService {

    private final PokerRankService pokerRankService;

    /**
     * Constructor dependency injection...why do I not have spring?
     *
     * @param pokerRankService the poker rank service.
     */
    public SimplisticPokerService(final PokerRankService pokerRankService) {
        this.pokerRankService = pokerRankService;
    }

    /**
     * Create a new poker hand from the given string input.
     * <p>
     * This method creates cards in the order they were input.
     *
     * @param input the input.
     * @return the String
     * @throws java.lang.IllegalArgumentException if an invalid string is passed in.
     */
    public PokerHand makeHand(final String input) throws IllegalArgumentException {
        return null;
    }

    /**
     * Sorts the given list of hands into their final ranks, in descending order.
     * This method also sets the finalRanking int field in the case of ties, etc.
     *
     * @param hands the given hands.
     * @return the sorted hands with their rank set.
     */
    public List<PokerHand> sortAndSetFinalRankings(final List<PokerHand> hands) {
        return null;
    }

}
