import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.service.PokerRankService;
import ca.carleton.poker.service.SimplisticPokerService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link ca.carleton.poker.service.SimplisticPokerService}
 *
 * Created by Mike on 14/09/2015.
 */
public class SimplisticPokerServiceTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private SimplisticPokerService sut;

    @Before
    public void setUp() {
        this.sut = new SimplisticPokerService(new PokerRankService());
    }

    @Test
    public void canMakePokerHand() {
        final String input = "AceSpades KingSpades QueenSpades JackSpades TenSpades";
        final PokerHand hand = this.sut.makeHand(input);

        assertThat(hand, is(not(nullValue())));
        assertThat(hand.getCards().size(), is(5));

        assertThat(hand.getCards().get(0).getRank(), is(Rank.ACE));
        assertThat(hand.getCards().get(0).getSuit(), is(Suit.SPADES));

        assertThat(hand.getCards().get(1).getRank(), is(Rank.KING));
        assertThat(hand.getCards().get(1).getSuit(), is(Suit.SPADES));

        assertThat(hand.getCards().get(2).getRank(), is(Rank.QUEEN));
        assertThat(hand.getCards().get(2).getSuit(), is(Suit.SPADES));

        assertThat(hand.getCards().get(3).getRank(), is(Rank.JACK));
        assertThat(hand.getCards().get(3).getSuit(), is(Suit.SPADES));

        assertThat(hand.getCards().get(4).getRank(), is(Rank.TEN));
        assertThat(hand.getCards().get(4).getSuit(), is(Suit.SPADES));
    }

    @Test()
    public void canDetermineIllegalHand_one() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("invalid token AceCookies");

        final String input = "AceCookies KingSpades QueenSpades JackSpades TenSpades";
        final PokerHand hand = this.sut.makeHand(input);
    }

    @Test()
    public void canDetermineIllegalHand_two() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("input requires 5 space-delimited tokens");

        final String input = "AceSpadesKingSpadesQueenSpadesJackSpadesTenSpades";
        final PokerHand hand = this.sut.makeHand(input);
    }

    @Test
    public void canDetermineIllegalHand_three() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("input requires 5 space-delimited tokens");

        final String input = "KingSpades QueenSpades JackSpades TenSpades";
        final PokerHand hand = this.sut.makeHand(input);
    }

    @Test
    public void canDetermineIllegalHand_four() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("input may not be null");

        final String input = null;
        final PokerHand hand = this.sut.makeHand(input);
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

        final List<PokerHand> sortedHand = this.sut.sortAndSetFinalRankings(Arrays.asList(hand1, hand2));
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

        final List<PokerHand> sortedHand = this.sut.sortAndSetFinalRankings(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand1));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand2));
        assertThat(sortedHand.get(1).getFinalRank(), is(1));
    }

    @Test
    public void canOrderHandsOffHighCard() {
        // Three of a kind - King - Jack (second place)
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        hand1.addCard(new Card(Rank.KING, Suit.CLUBS));
        hand1.addCard(new Card(Rank.JACK, Suit.CLUBS));

        //  Three of a kind - King - Queen (highest possible)
        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand2.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMONDS));
        hand2.addCard(new Card(Rank.KING, Suit.CLUBS));
        hand2.addCard(new Card(Rank.QUEEN, Suit.CLUBS));

        final List<PokerHand> sortedHand = this.sut.sortAndSetFinalRankings(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand2));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand1));
        assertThat(sortedHand.get(1).getFinalRank(), is(2));
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

        final List<PokerHand> sortedHand = this.sut.sortAndSetFinalRankings(Arrays.asList(hand1, hand2));
        assertThat(sortedHand.get(0), is(hand2));
        assertThat(sortedHand.get(0).getFinalRank(), is(1));
        assertThat(sortedHand.get(1), is(hand1));
        assertThat(sortedHand.get(1).getFinalRank(), is(2));
    }

}
