// RandomSource.java
public interface RandomSource {
    /**
     * Return a uniformly distributed int in range [0, bound).
     */
    int nextInt(int bound);
}