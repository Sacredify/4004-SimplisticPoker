import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.card.Card;
import ca.carleton.poker.entity.card.Rank;
import ca.carleton.poker.entity.card.Suit;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.entity.rank.PokerRank;
import ca.carleton.poker.service.PokerRankService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link ca.carleton.poker.service.PokerRankService}
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
        assertThat(hand1.getPokerRank().getHighCard(), is(Rank.ACE));
    }
}
