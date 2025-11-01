package lotto.lotto;

public enum Rank {
    FIFTH("3개 일치 (5,000원)", 5, 5_000),
    FOURTH("4개 일치 (50,000원)",4, 50_000),
    THIRD("5개 일치 (1,500,000원)",3, 1_500_000),
    SECOND("5개 일치, 보너스 볼 일치 (30,000,000원)",2, 30_000_000),
    FIRST("6개 일치 (2,000,000,000원)",1, 20_000_000_000L);

    private final String info;
    private final int number;
    private final long prizeMoney;

    Rank(String info, int number, long prizeMoney) {
        this.info = info;
        this.number = number;
        this.prizeMoney = prizeMoney;
    }

    public String getInfo() {
        return info;
    }

    public int getNumber() {
        return number;
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
