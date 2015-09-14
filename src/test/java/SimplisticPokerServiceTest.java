import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.service.SimplisticPokerService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link ca.carleton.poker.service.SimplisticPokerService}
 *
 * Created by Mike on 14/09/2015.
 */
public class SimplisticPokerServiceTest {

    private SimplisticPokerService sut;

    @Before
    public void setUp() {
        this.sut = new SimplisticPokerService();
    }

    @Test
    public void canOrderHands() {
        // Royal flush
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.KING, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.TEN, Suit.SPADES));

        // Random cards
        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.SEVEN, Suit.SPADES));
        hand2.addCard(new Card(Rank.ONE, Suit.SPADES));
        hand2.addCard(new Card(Rank.SIX, Suit.SPADES));
        hand2.addCard(new Card(Rank.NINE, Suit.SPADES));
        hand2.addCard(new Card(Rank.TEN, Suit.SPADES));

        final List<PokerHand> sortedHand = this.sut.sortAndRankHands(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand1));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand2));
        assertThat(sortedHand.get(1).getFinalRank(), is(2));
    }

    @Test
    public void canOrderTwoEqualHands() {
        // Royal flush
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.KING, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.TEN, Suit.SPADES));

        // Royal flush
        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand2.addCard(new Card(Rank.KING, Suit.SPADES));
        hand2.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand2.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand2.addCard(new Card(Rank.TEN, Suit.SPADES));

        final List<PokerHand> sortedHand = this.sut.sortAndRankHands(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand1));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand2));
        assertThat(sortedHand.get(1).getFinalRank(), is(1));
    }

    @Test
    public void canOrderHandWithHighCardWinner() {
        // Straight flush - fives high
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ONE, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.THREE, Suit.SPADES));
        hand1.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        // Straight flush - eights high
        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand2.addCard(new Card(Rank.FIVE, Suit.SPADES));
        hand2.addCard(new Card(Rank.SIX, Suit.SPADES));
        hand2.addCard(new Card(Rank.SEVEN, Suit.SPADES));
        hand2.addCard(new Card(Rank.EIGHT, Suit.SPADES));

        final List<PokerHand> sortedHand = this.sut.sortAndRankHands(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand2));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand1));
        assertThat(sortedHand.get(1).getFinalRank(), is(2));
    }

}
