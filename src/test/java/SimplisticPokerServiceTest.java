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
    public void canOrderTwoEqualHands() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.KING, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.TEN, Suit.SPADES));

        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand2.addCard(new Card(Rank.KING, Suit.SPADES));
        hand2.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand2.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand2.addCard(new Card(Rank.TEN, Suit.SPADES));

        final List<PokerHand> sortedHand = this.sut.sortAndRankHands(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand1));
        assertThat(sortedHand.get(0).ranking, is(1));
        assertThat(sortedHand.get(1), is(hand2));
        assertThat(sortedHand.get(1).ranking, is(1));
    }
}
