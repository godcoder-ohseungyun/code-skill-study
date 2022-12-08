package classSkill.firstClassCollection;


import classSkill.valueObject.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * firstClassCollection(일급 컬랙션) 이란, 컬랙션 자료구조를 포장하는 객체이다
 *
 * -이점
 * Collection의 불변성을 보장
 * 상태와 행위를 한 곳에서 관리
 * 검증 로직 같은 비즈니스 로직 추가 가능
 * 컬랙션의 의미를 부여
 *
 * -아래 예시 코드를 보자
 */
public class Lotto {

    //TODO : final 불변
    private final Set<LottoNumber> lottoNumbers; //자료 구조를 활용해 불필요 검증 로직 제거 : 중복 검사 로직이 필요 없도록 자료구조 만으로 구현

    //초기화 블럭 : 클래스 로드 시점에 공통 적으로 필요한 초기 세팅이 존재하는 경우
    static {
        //ex Map에 기본 키 들을 미리 할당해 둔다던가
        //이 예시에선 쓸일 없음
    }

    //생성자 접근 제어
    private Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    //객체 생성은 정적 팩토리 메서드 활용해 의미 전달 ex) from, of, etc
    public static Lotto createAuto() {
        Set<Integer> numbers = createAutoNumbers();

        //컬랙션 형태 변경에 스트림 활용
        Set<LottoNumber> lottoNumbers = numbers
                .stream()
                .map(n -> LottoNumber.from(n))
                .collect(Collectors.toSet());

        return new Lotto(lottoNumbers);
    }

    private static Set<Integer> createAutoNumbers() {
        return Set.of(1, 2, 3, 4, 5, 6); //예시용 임시 로직임, 랜덤으로 동작한다고 생각하면 됨
    }

    //메세지를 수신하는 메서드
    //로직 중요함
    public int getSameNumberCount(Lotto otherLotto) {

        //ArrayList는 List 하위 클래스를 주입받아 생성할 수 있음
        List<LottoNumber> duplicates = new ArrayList<>(lottoNumbers);

        System.out.println("t: " + duplicates);

        //두 컬랙션간 교집합을 찾음
        duplicates.retainAll(otherLotto.lottoNumbers);

        System.out.println("t: " + duplicates);

        return duplicates.size(); //교집합 리스트의 크기가 중복 카운트임
    }

    //필요한 경우 toString 재정의 필요
    @Override
    public String toString() {
        return lottoNumbers.toString();
    }
}
