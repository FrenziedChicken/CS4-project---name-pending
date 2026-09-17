// Hand.java
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int HAND_SIZE = 5;
    private final List<Card> cards = new ArrayList<>(HAND_SIZE);

    /** Fill up to HAND_SIZE. */
    public void drawFrom(Deck deck) {
        while (cards.size() < HAND_SIZE) {
            Card c = deck.dealOne();
            if (c == null) break;
            cards.add(c);
        }
    }

    /** Draw exactly one card if there's room. Returns true if drawn. */
    public boolean drawOne(Deck deck) {
        if (cards.size() >= HAND_SIZE) return false;
        Card c = deck.dealOne();
        if (c == null) return false;
        cards.add(c);
        return true;
    }

    // in Hand.java


    public Card play(int index) {
        if (index < 0 || index >= cards.size()) return null;
        return cards.remove(index);
    }

    public int size() { return cards.size(); }
    public boolean isEmpty() { return cards.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(i).append(": ").append(cards.get(i)).append('\n');
        }
        return sb.toString();
    }
}