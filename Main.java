// Main.java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Deterministic RNG for repeatable runs during development
        RandomSource rnd = new FixedRandomSource(42);

        // Create deck, shuffle, and game entities
        Deck deck = new Deck(rnd);
        deck.shuffle();

        Player player = new Player(30);
        Enemy enemy = new Enemy(20);
        GameState state = new GameState(deck, player, enemy);

        // Initial draw to fill the hand
        player.getHand().drawFrom(deck);

        Scanner sc = new Scanner(System.in);
        System.out.println("=== CLI Card Prototype ===");
        System.out.println("Commands: play N   draw   peek   status   quit");

        // Show initial hand
        printHand(player);

        mainLoop:
        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                printHand(player);
                continue;
            }

            String[] parts = line.split("\\s+");
            String cmd = parts[0].toLowerCase();

            switch (cmd) {
                case "quit":
                    System.out.println("Goodbye.");
                    break mainLoop;

                case "status":
                    System.out.printf("Player HP: %d  Block: %d%n", player.getHp(), player.getBlock());
                    System.out.printf("Enemy  HP: %d  Block: %d%n", enemy.getHp(), enemy.getBlock());
                    System.out.printf("Deck remaining: %d  Discard size: %d%n",
                            deck.cardsRemaining(), state.getDiscard().size());
                    printHand(player);
                    continue;

                case "peek":
                    Card next = deck.peekNext();
                    System.out.println("Next card in deck: " + (next == null ? "null" : next));
                    printHand(player);
                    continue;

                case "draw":
                    // Manual draw of one card (useful for testing)
                    drawOneWithReshuffleIfNeeded(player, state);
                    printHand(player);
                    continue;

                case "play":
                    if (parts.length < 2) {
                        System.out.println("Usage: play N");
                        printHand(player);
                        continue;
                    }
                    int idx;
                    try {
                        idx = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid index.");
                        printHand(player);
                        continue;
                    }

                    Card played = player.getHand().play(idx);
                    if (played == null) {
                        System.out.println("No card at that index.");
                        printHand(player);
                        continue;
                    }

                    // Card removed from hand already
                    System.out.println("You played: " + played);

                    // Apply card effect
                    if (played.getType() == Card.CardType.ATTACK) {
                        enemy.takeDamage(played.getValue());
                        System.out.println("Dealt " + played.getValue() + " damage to enemy.");
                    } else {
                        player.addBlock(played.getValue());
                        System.out.println("Gained " + played.getValue() + " block.");
                    }

                    // Move played card to discard pile
                    state.addToDiscard(played);

                    // Draw any card-draw effects granted by the card
                    int draw = played.getDrawCount();
                    int actuallyDrawn = 0;
                    for (int i = 0; i < draw; i++) {
                        boolean drawn = drawOneWithReshuffleIfNeeded(player, state);
                        if (!drawn) break;
                        actuallyDrawn++;
                    }
                    if (draw > 0) System.out.println("Drew " + actuallyDrawn + " card(s).");

                    // Simple enemy reaction
                    if (!enemy.isDead()) {
                        int dmg = enemy.decideAttack();
                        System.out.println("Enemy attacks for " + dmg);
                        player.takeDamage(dmg);
                    }

                    // Show brief status and check win/lose
                    System.out.printf("Player HP: %d  Block: %d  | Enemy HP: %d  Block: %d%n",
                            player.getHp(), player.getBlock(), enemy.getHp(), enemy.getBlock());

                    if (player.isDead()) {
                        System.out.println("You died. Game over.");
                        break mainLoop;
                    } else if (enemy.isDead()) {
                        System.out.println("Enemy died. You win!");
                        break mainLoop;
                    }

                    // Show hand after play (played card removed)
                    printHand(player);
                    continue;

                default:
                    System.out.println("Unknown command. Try: play N, draw, peek, status, quit");
                    printHand(player);
            }
        }

        sc.close();
    }

    // Print player's hand neatly
    private static void printHand(Player player) {
        System.out.println("Hand (" + player.getHand().size() + "):");
        System.out.println(player.getHand());
    }

    // Draw one card; if deck empty and discard non-empty, reshuffle discard into deck first.
    // Returns true if a card was drawn.
    private static boolean drawOneWithReshuffleIfNeeded(Player player, GameState state) {
        Deck deck = state.getDeck();

        // If deck empty but discard has cards, reshuffle discard into deck
        if (deck.cardsRemaining() == 0 && !state.getDiscard().isEmpty()) {
            deck.reshuffleFromDiscard(state.getDiscard());
            System.out.println("Reshuffled discard into deck.");
        }

        // Attempt to draw one card
        boolean drawn = player.getHand().drawOne(deck);

        if (!drawn && deck.cardsRemaining() == 0 && state.getDiscard().isEmpty()) {
            System.out.println("No cards left to draw.");
        }

        return drawn;
    }
}