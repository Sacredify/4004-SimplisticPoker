import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.service.rank.PokerRankService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link ca.carleton.poker.service.rank.PokerRankService}
 * <p>
 * Created by Mike on 14/09/2015.
 */
public class PokerRankServiceTest {

    private PokerRankService sut;

    @Before
    public void setUp() {
        this.sut = new PokerRankService();
    }

    @Test
    public void canGetHighCardsForHand() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.TEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.KING, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.ROYAL_FLUSH));
        final List<Rank> highCards = hand1.getCardRanksForHighCard();
        assertThat(highCards.get(0), is(Rank.ACE));
        assertThat(highCards.get(1), is(Rank.KING));
        assertThat(highCards.get(2), is(Rank.QUEEN));
        assertThat(highCards.get(3), is(Rank.JACK));
        assertThat(highCards.get(4), is(Rank.TEN));
    }

    @Test
    public void canRankRoyalFlush() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.KING, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.TEN, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.ROYAL_FLUSH));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.ACE));
    }

    @Test
    public void canRankStraightFlush() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.THREE, Suit.SPADES));
        hand1.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.STRAIGHT_FLUSH));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.ACE));
    }

    @Test
    public void canRankFourOfAKind() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand1.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand1.addCard(new Card(Rank.TWO, Suit.DIAMONDS));
        hand1.addCard(new Card(Rank.SEVEN, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.FOUR_OF_A_KIND));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.SEVEN));
    }

    @Test
    public void canRankFullHouse() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.HEARTS));
        hand1.addCard(new Card(Rank.JACK, Suit.DIAMONDS));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.FULL_HOUSE));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.JACK));
    }

    @Test
    public void canRankFullHousesHighCard() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.JACK, Suit.HEARTS));
        hand1.addCard(new Card(Rank.JACK, Suit.DIAMONDS));

        final PokerHand hand2 = new PokerHand();
        hand2.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand2.addCard(new Card(Rank.JACK, Suit.HEARTS));
        hand2.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand2.addCard(new Card(Rank.ACE, Suit.HEARTS));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMONDS));

        this.sut.rankHand(hand1);
        this.sut.rankHand(hand2);

        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.FULL_HOUSE));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.JACK));
        assertThat(hand1.getPokerRank().getHighCards().get(1), is(Rank.ACE));

        assertThat(hand2.getPokerRank(), is(not(nullValue())));
        assertThat(hand2.getPokerRank().getHandRank(), is(HandRank.FULL_HOUSE));
        assertThat(hand2.getPokerRank().getHighCards().get(0), is(Rank.ACE));
        assertThat(hand2.getPokerRank().getHighCards().get(1), is(Rank.JACK));
    }

    @Test
    public void canRankFlush() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.JACK, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.FOUR, Suit.SPADES));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));
        hand1.addCard(new Card(Rank.SEVEN, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.FLUSH));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.JACK));
    }

    @Test
    public void canRankStraight() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand1.addCard(new Card(Rank.THREE, Suit.CLUBS));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEARTS));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.STRAIGHT));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.ACE));
        assertThat(hand1.getPokerRank().getHighCards().get(1), is(Rank.FIVE));
        assertThat(hand1.getPokerRank().getHighCards().get(2), is(Rank.FOUR));
        assertThat(hand1.getPokerRank().getHighCards().get(3), is(Rank.THREE));
        assertThat(hand1.getPokerRank().getHighCards().get(4), is(Rank.TWO));

    }

    @Test
    public void canRankThreeOfAKind() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand1.addCard(new Card(Rank.TWO, Suit.CLUBS));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEARTS));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.THREE_OF_A_KIND));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.FIVE));
        assertThat(hand1.getPokerRank().getHighCards().get(1), is(Rank.FOUR));
    }

    @Test
    public void canRankTwoPair() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand1.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEARTS));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.TWO_PAIR));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.FIVE));
    }

    @Test
    public void canRankOnePair() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.TWO, Suit.SPADES));
        hand1.addCard(new Card(Rank.TWO, Suit.HEARTS));
        hand1.addCard(new Card(Rank.SEVEN, Suit.CLUBS));
        hand1.addCard(new Card(Rank.KING, Suit.HEARTS));
        hand1.addCard(new Card(Rank.FIVE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.ONE_PAIR));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.KING));
        assertThat(hand1.getPokerRank().getHighCards().get(1), is(Rank.SEVEN));
        assertThat(hand1.getPokerRank().getHighCards().get(2), is(Rank.FIVE));
    }

    @Test
    public void canRankHighCard() {
        final PokerHand hand1 = new PokerHand();
        hand1.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand1.addCard(new Card(Rank.QUEEN, Suit.HEARTS));
        hand1.addCard(new Card(Rank.KING, Suit.CLUBS));
        hand1.addCard(new Card(Rank.JACK, Suit.HEARTS));
        hand1.addCard(new Card(Rank.NINE, Suit.SPADES));

        this.sut.rankHand(hand1);
        assertThat(hand1.getPokerRank(), is(not(nullValue())));
        assertThat(hand1.getPokerRank().getHandRank(), is(HandRank.HIGH_CARD));
        assertThat(hand1.getPokerRank().getHighCards().get(0), is(Rank.ACE));
        assertThat(hand1.getPokerRank().getHighCards().get(1), is(Rank.KING));
        assertThat(hand1.getPokerRank().getHighCards().get(2), is(Rank.QUEEN));
        assertThat(hand1.getPokerRank().getHighCards().get(3), is(Rank.JACK));
        assertThat(hand1.getPokerRank().getHighCards().get(4), is(Rank.NINE));

    }
}
