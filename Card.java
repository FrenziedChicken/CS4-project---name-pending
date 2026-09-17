// Card.java
public final class Card {
    public enum CardType { ATTACK, DEFENCE }

    private final CardType type;
    private final int value;      // numeric magnitude (e.g., damage or block)
    private final String name;    // human-readable name (never null)
    private final int drawCount;  // number of cards to draw when this card is played

    // Backwards-compatible constructor (no explicit name, no draws)
    public Card(CardType type, int value) {
        this(type, value, defaultNameFor(type, value), 0);
    }

    // Constructor with name but no draws
    public Card(CardType type, int value, String name) {
        this(type, value, name, 0);
    }

    // Full constructor including draw count
    public Card(CardType type, int value, String name, int drawCount) {
        if (type == null) throw new IllegalArgumentException("type must not be null");
        if (name == null) throw new IllegalArgumentException("name must not be null");
        if (drawCount < 0) throw new IllegalArgumentException("drawCount must be >= 0");
        this.type = type;
        this.value = value;
        this.name = name;
        this.drawCount = drawCount;
    }

    public CardType getType() { return type; }
    public int getValue() { return value; }
    public String getName() { return name; }
    public int getDrawCount() { return drawCount; }

    @Override
    public String toString() {
        if (drawCount > 0) {
            return String.format("%s(%s %d, draw=%d)", name, type, value, drawCount);
        }
        return String.format("%s(%s %d)", name, type, value);
    }

    private static String defaultNameFor(CardType type, int value) {
        switch (type) {
            case ATTACK: return "Strike";
            case DEFENCE: return "Defend";
            default: return type.toString() + "-" + value;
        }
    }
}