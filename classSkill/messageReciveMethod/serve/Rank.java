package classSkill.messageReciveMethod.serve;

public enum Rank {

    //TODO : enum은 상수 선언 순서로 정렬에서 우선 순위를 가진다
    FIRST(6,false,2_000_000_000),
    SECOND(5,true,30_000_000),
    THIRD(5,false,1_500_000),
    FORTH(4,false, 50_000),
    FIFTH(3,false, 5_000),
    NO_RANK(0,false, 0);

    private final int matchedCountInLotto;
    private final boolean isBonus;

    private final int prize;


    Rank(int matchedCountInLotto, boolean isBonus, int prize) {
        this.matchedCountInLotto = matchedCountInLotto;
        this.isBonus = isBonus;
        this.prize = prize;
    }

    public static Rank findRank(int matchedCountInLotto, boolean isBonus){
        Rank foundRank = NO_RANK;

        for(Rank rank : Rank.values()){
            if(rank.isMatched(matchedCountInLotto,isBonus)) foundRank = rank;
        }

        return foundRank;
    }

    private boolean isMatched(int matchedCountInLotto, boolean isBonus){
        if(this.matchedCountInLotto != matchedCountInLotto) return false;

        if(this == SECOND) return this.isBonus == isBonus;

        return true;
    }

    public int getPrize(){
        return this.prize;
    }
}
