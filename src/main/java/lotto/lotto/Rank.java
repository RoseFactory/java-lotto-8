package lotto.lotto;

public enum Rank {
    FIRST(1, 20_000_000_000L),
    SECOND(2, 30_000_000),
    THIRD(3, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(5, 5_000);

    private final int number;
    private final long prizeMoney;

    Rank(int number, long prizeMoney) {
        this.number = number;
        this.prizeMoney = prizeMoney;
    }

    public int getNumber() {
        return number;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
