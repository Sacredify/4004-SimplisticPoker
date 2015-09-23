import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.entity.rank.HandRank;
import ca.carleton.poker.service.SimplisticPokerService;
import ca.carleton.poker.service.rank.PokerRankService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests that cover the minimum amount required as listed by the instructor.
 * <p>
 * Some of these may be duplicates but might make it easier for the marking TA.
 * <p>
 * Created by Mike on 9/23/2015.
 */
public class MinimalCoverTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private SimplisticPokerService sut;

    @Before
    public void setUp() {
        this.sut = new SimplisticPokerService(new PokerRankService());
        this.sut.clearCaches();
    }

    @Test
    public void testDuplicateCards() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("token AceSpades is invalid (possible duplicate card, etc.)");

        final String hand1 = "1 AceSpades KingHearts QueenDiamonds TwoSpades ThreeHearts";
        final String hand2 = "2 AceSpades ThreeDiamonds FourDiamonds FiveDiamonds TenSpades";

        this.sut.makeHand(hand1);
        this.sut.makeHand(hand2);
    }

    @Test
    public void testMinimumFiveCards() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("input requires 6 space-delimited tokens");

        final String hand1 = "1 AceSpades KingHearts QueenDiamonds TwoSpades";

        this.sut.makeHand(hand1);
    }

    @Test
    public void testRanksInOrder() {
        final String hand1 = "2 AceDiamonds AceHearts TwoHearts ThreeHearts SevenDiamonds";
        final String hand2 = "1 AceSpades KingHearts QueenDiamonds JackHearts TenDiamonds";

        final PokerHand pokerHand1 = this.sut.makeHand(hand1);
        final PokerHand pokerHand2 = this.sut.makeHand(hand2);
        final List<PokerHand> ranks = this.sut.sortAndSetFinalRankings(asList(pokerHand1, pokerHand2));

        assertThat(ranks.get(0), is(pokerHand2));
        assertThat(ranks.get(1), is(pokerHand1));
        assertThat(pokerHand2.getFinalRank(), is(1));
        assertThat(pokerHand1.getFinalRank(), is(2));
        assertThat(pokerHand2.getPokerRank().getHandRank(), is(HandRank.STRAIGHT));
        assertThat(pokerHand1.getPokerRank().getHandRank(), is(HandRank.ONE_PAIR));
    }

    @Test
    public void testDuplicateRanks() {
        final String hand1 = "1 AceSpades KingSpades QueenSpades JackSpades TenSpades";
        final String hand2 = "2 AceHearts KingHearts QueenHearts JackHearts TenHearts";

        final PokerHand pokerHand1 = this.sut.makeHand(hand1);
        final PokerHand pokerHand2 = this.sut.makeHand(hand2);
        final List<PokerHand> ranks = this.sut.sortAndSetFinalRankings(asList(pokerHand1, pokerHand2));

        assertThat(ranks.get(0), is(pokerHand1));
        assertThat(ranks.get(1), is(pokerHand2));
        assertThat(pokerHand2.getFinalRank(), is(1));
        assertThat(pokerHand1.getFinalRank(), is(1));
        assertThat(pokerHand2.getPokerRank().getHandRank(), is(HandRank.ROYAL_FLUSH));
        assertThat(pokerHand1.getPokerRank().getHandRank(), is(HandRank.ROYAL_FLUSH));
    }

    @Test
    public void testInvalidCards() {
        this.expectedException.expect(IllegalArgumentException.class);
        this.expectedException.expectMessage("invalid token ThreeShovels");

        final String hand1 = "1 ThreeShovels KingSpades QueenSpades JackSpades TenSpades";
        this.sut.makeHand(hand1);
    }
}
