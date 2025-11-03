package lotto.generatelotto;

public enum LottoRank {
    MISS("낙첨", 0, 0),
    FIFTH("3개 일치 (5,000원)", 3, 5_000),
    FOURTH("4개 일치 (50,000원)", 4, 50_000),
    THIRD("5개 일치 (1,500,000원)", 5, 1_500_000),
    SECOND("5개 일치, 보너스 볼 일치 (30,000,000원)", 5, 30_000_000),
    FIRST("6개 일치 (2,000,000,000원)", 6, 2_000_000_000);

    private final String description;
    private final int matchCount;
    private final long prize;

    LottoRank(String description, int matchCount, long prize) {
        this.description = description;
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public String getDescription() { return description; }
    public long getPrize() { return prize; }
    public int getMatchCount() { return matchCount; }

    // matchCount와 hasBonusNumber로 LottoRank 찾기
    public static LottoRank valueOf(int matchCount, boolean hasBonusNumber) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && hasBonusNumber) {
            return SECOND;
        }
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return MISS;
    }
}
