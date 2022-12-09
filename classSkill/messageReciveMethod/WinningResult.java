package classSkill.messageReciveMethod;

import classSkill.firstClassCollection.Lotto;
import classSkill.messageReciveMethod.serve.Rank;
import classSkill.messageReciveMethod.serve.WinningLotto;

import java.util.Map;
import java.util.TreeMap;

/**
 * 메세지 수신 메서드란? getter를 이용해 외부 객체에서 행위를 하지 않고,
 * 책임이 각 객체에 원만하게 분배되도록 해당 객체에서 메세지를 수신해 수행하도록 하는 방법
 *
 * - 구현시 어떤 규칙을 지켜야 하는가?
 * 외부 객체를 get해서 로직을 만들기 전에 해당 로직이 외부 객체에 캡슐화 되는게 옳지 않은지 고려해야한다
 *
 * - 아래 일급 컬랙션 객체를 예시로 확인하자
 */
public class WinningResult {

    private static final double ROUND_UNIT = 100.0;
    private final Map<Rank,Integer> winningResult;

    /**
     * TODO : 초기화 블럭을 활용해 통용되는 기초 작업을 '생성자 로드 시점'에 수행하도록 함, 코드 중복 감소 효과
     * static {} : 클래스 로드 시점 1회 - 모든 객체에 공동 관리 되는 맴버에 초기 로직을 적용할 때 사용
     * {} : 생성자 호출 마다 1회 - 각 객체에 별도 관리 되는 맴버에 초기 로직을 적용할 때 사용
     *
     * 이 경우, 인스턴스 마다 winningResult는 별도 관리 되지만, 초기 세팅이 필요하기 때문에 {} 사용
     */
    {
        //TODO: HashMap은 키의 해시 값을 기반으로 요소를 정렬하기 때문에, 삽입 순서로 정렬되지 않는다 (때문에, key 객체를 정렬 기준으로 삼아, 순서를 유지하는 TreeMap 사용)
        winningResult = new TreeMap<>();

        winningResult.put(Rank.FIRST,0);
        winningResult.put(Rank.SECOND,0);
        winningResult.put(Rank.THIRD,0);
        winningResult.put(Rank.FORTH,0);
        winningResult.put(Rank.FIFTH,0);
    }

    //TODO : 메세지 수신 메서드 적극 활용한 로직 예시
    //TODO : 매개변수에 VO를 활용할 것
    public void generateWinningResult(Lotto lotto, WinningLotto winningLotto){

        //TODO : Lotto에 요소를 비교하는 로직을 캡슐화
        int matchedCountInLotto = lotto.getSameNumberCount(winningLotto.getWinningLotto());
        boolean isBonusMatch = lotto.isContain(winningLotto.getBonusNumber());

        //TODO : Rank에 해당 결과의 순위가 무엇인지 찿는 로직을 캡슐화
        Rank thisRank = Rank.findRank(matchedCountInLotto,isBonusMatch);

        winningResult.put(thisRank,winningResult.get(thisRank)+1);
    }

    public double getRatio(){
        int totalPrize = 0;

        for(Rank rank : winningResult.keySet()){
            //TODO : Rank에 해당 순위의 상금을 캡슐화
            totalPrize += (rank.getPrize() * getWinningCount(rank));
        }

        return roundToSecondDigit(totalPrize) / Lotto.lottoFee; //해당 상수는 여기서만 쓰이지만, Lotto에 정의되는게 올바르다
    }

    //TODO : 의미가 미미한 로직은 별도 메서드로 포장
    private int getWinningCount(Rank rank){
        return winningResult.get(rank);
    }

    private double roundToSecondDigit(double number){
        return Math.round(number*ROUND_UNIT) / ROUND_UNIT;
    }



}
