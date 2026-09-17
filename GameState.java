// in GameState.java
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Deck deck;
    private final Player player;
    private final Enemy enemy;
    private final List<Card> discard = new ArrayList<>();

    public GameState(Deck deck, Player player, Enemy enemy) {
        this.deck = deck;
        this.player = player;
        this.enemy = enemy;
    }

    public Deck getDeck() { return deck; }
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }

    public void addToDiscard(Card c) {
        if (c != null) discard.add(c);
    }

    public List<Card> getDiscard() {
        return discard;
    }
}