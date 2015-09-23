package ca.carleton.poker.client;

import ca.carleton.poker.entity.PokerHand;
import ca.carleton.poker.service.SimplisticPokerService;
import ca.carleton.poker.service.input.InputService;
import ca.carleton.poker.service.input.impl.InputServiceFactory;
import ca.carleton.poker.service.rank.PokerRankService;

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

        while (true) {
            try {
                pokerService.clearCaches();
                final List<PokerHand> hands = new ArrayList<>(5);
                out.print("Enter number of players for this round (Q to quit) >>> ");
                final String input = in.getInput();
                if (input.equalsIgnoreCase("Q")) {
                    System.exit(0);
                }
                final int numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                    out.println("Number of players must be between 2 and 4!");
                    continue;
                }
                out.println("\nBegin entering hand data (format: playerId RankSuit RankSuit RankSuit RankSuit RankSuit)");
                for (int i = 1; i <= numberOfPlayers; i++) {
                    out.print("Enter data for player " + i + " >>>");
                    final String playerInput = in.getInput();
                    final PokerHand playerHand = pokerService.makeHand(playerInput);
                    hands.add(playerHand);
                }
                pokerService.sortAndSetFinalRankings(hands);
                out.println("==== BEGIN RESULTS ====");
                hands.forEach(out::println);
                out.println("==== END RESULTS ====\n");
            } catch (final IllegalArgumentException exception) {
                exception.printStackTrace();
            }
        }

    }
}
