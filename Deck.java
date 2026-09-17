// Deck.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Deck backed by a mutable List<Card>.
 * - shuffle() uses Fisher–Yates with injected RandomSource
 * - dealOne() returns next card or null when deck empty
 * - reshuffleFromDiscard(List<Card>) consumes the discard pile, adds those cards to the deck,
 *   shuffles, and resets the dealing index.
 */
public class Deck {
    private final List<Card> cards;      // all cards in deck order (0..size-1)
    private int nextIndex;               // index of next card to deal
    private final RandomSource rnd;      // injected RNG

    /**
     * Create a default deck using the given RandomSource.
     * This constructor mirrors your earlier deck composition: 26 Strikes and 26 Defends.
     */
    public Deck(RandomSource rnd) {
        this.rnd = Objects.requireNonNull(rnd, "rnd");
        this.cards = new ArrayList<>();
        final int total = 52;
        final int half = total / 2;
        for (int i = 0; i < half; i++) {
            cards.add(new Card(Card.CardType.ATTACK, 1, "Strike"));
        }
        for (int i = half; i < total; i++) {
            cards.add(new Card(Card.CardType.DEFENCE, 2, "Defend"));
        }
        this.nextIndex = 0;
    }

    /**
     * Fisher–Yates shuffle (in-place) using injected RandomSource.
     * Resets dealing index to 0.
     */
    public void shuffle() {
        // Fisher–Yates from end down to 1
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1); // 0..i inclusive
            Collections.swap(cards, i, j);
        }
        nextIndex = 0;
    }

    /**
     * Deal one card, or null if deck is empty.
     */
    public Card dealOne() {
        if (nextIndex >= cards.size()) return null;
        return cards.get(nextIndex++);
    }

    /**
     * How many cards remain to be dealt.
     */
    public int cardsRemaining() {
        return cards.size() - nextIndex;
    }

    /**
     * Peek next card without advancing.
     */
    public Card peekNext() {
        if (nextIndex >= cards.size()) return null;
        return cards.get(nextIndex);
    }

    /**
     * Reset dealing index to start dealing from the top again (without reshuffling).
     */
    public void resetDealIndex() {
        this.nextIndex = 0;
    }

    /**
     * Add all cards from discard back into the deck, shuffle, and reset dealing index.
     * This method *clears* the provided discard list (it is consumed).
     */
    public void reshuffleFromDiscard(List<Card> discard) {
        if (discard == null || discard.isEmpty()) return;
        // Append discarded cards to the remaining undealt cards
        List<Card> remaining = new ArrayList<>();
        // Add remaining undealt cards (from nextIndex .. end)
        for (int i = nextIndex; i < cards.size(); i++) {
            remaining.add(cards.get(i));
        }
        // Add discard pile
        remaining.addAll(discard);
        // Clear the discard pile (caller likely holds this list)
        discard.clear();

        // Replace internal cards list with the new combined list
        cards.clear();
        cards.addAll(remaining);

        // Reset and shuffle
        nextIndex = 0;
        shuffle();
    }

    /**
     * For debugging: return an immutable copy of the current undealt list (from nextIndex..end).
     */
    public List<Card> undealtSnapshot() {
        return Collections.unmodifiableList(new ArrayList<>(cards.subList(nextIndex, cards.size())));
    }
}