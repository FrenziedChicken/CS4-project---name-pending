// FixedRandomSource.java
import java.util.Random;

public class FixedRandomSource implements RandomSource {
    private final Random rnd;
    public FixedRandomSource(long seed) { this.rnd = new Random(seed); }
    @Override public int nextInt(int bound) { return rnd.nextInt(bound); }
}