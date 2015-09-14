package ca.carleton.poker.client;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.service.PokerRankService;
import ca.carleton.poker.service.SimplisticPokerService;
import ca.carleton.poker.service.input.InputService;
import ca.carleton.poker.service.input.impl.InputServiceFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
/**
 * Main class.
 * Created by Mike on 14/09/2015.
 */
public class Launcher {

    private static final SimplisticPokerService pokerService = new SimplisticPokerService(new PokerRankService());

    public static void main(final String[] args) {
        final InputService in = InputServiceFactory.getInputService();

        final List<PokerHand> hands = new ArrayList<>(5);

        // TODO split into methods, validation
        while (true) {
            out.print("Enter number of players for this round (Q to quit) >>> ");
            final String input = in.getInput();
            if (input.equalsIgnoreCase("Q")) {
                System.exit(0);
            }
            final int numberOfPlayers = Integer.parseInt(input);
            out.println("\nBegin score data (format: playerId RankSuit RankSuit RankSuit RankSuit RankSuit)");
            for (int i = 1; i <= numberOfPlayers; i++) {
                out.print("Enter score data for player " + i + " >>>");
                final String playerInput = in.getInput();
                final PokerHand playerHand = pokerService.makeHand(playerInput);
                hands.add(playerHand);
            }
            System.out.println(hands);
        }

    }
}
