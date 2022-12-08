package classSkill.valueObject;

import java.util.Objects;

/**
 * VO(Value Object)란 원시값을 포장하는 객체이다
 *
 * -이점
 * 해당 값에 대한 제약사항을 캡슐화할 수 있다
 * 특정 클래스의 원시값 맴버 변수를 VO로 패키징함으로써 해당 클래스가 거대해지는 것을 막을 수 있다
 *
 * -구현시 어떤 규칙을 지켜야 하는가?
 * equals & hash code 메서드를 재정의해야 한다
 * 오직 getter만을 가진다 (setter x)
 * 데이터 출력이 필요하다면 toString 재정의
 *
 * -아래 예시 코드를 보자
 */
public class LottoNumber {
    private static final Integer MAX_LOTTO_NUMBER = 45;
    private static final Integer MIN_LOTTO_NUMBER = 1;
    private static final String RANGE_ERROR_MSG = "1-45의 숫자여야 합니다";
    private final int number;

    //생성자 접근 제한
    private LottoNumber(int number) {
        validateTo45From1(number); //검증은 생성자에서 담당
        this.number = number;
    }

    private void validateTo45From1(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(RANGE_ERROR_MSG);
        }
    }

    //객체 생성은 정적 팩토리 메서드 활용해 의미 전달 ex) from, of, etc
    public static LottoNumber from(int number) {
        return new LottoNumber(number);
    }

    /**
     * TODO : No Setter, Only Getter
     */
    public int getNumber() {
        return number;
    }

    /**
     * TODO: equals & hash code 메서드를 재정의해야 한다
     * 동일성(==) 비교는 객체가 참조하고 있는 주솟값을 확인하기 때문에, 객체의 값을 비교하도록 equals 재정의 필요
     * 해시값을 사용하는 컬렉션 등에서 객체를 비교하는 용도로, 특정 값을 이용해 hashcode를 생성하도록 hashCode 재정의 필요
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        //final 중요
        final LottoNumber lottoNumber  = (LottoNumber) o;

        return (lottoNumber.number == this.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    /**
     * TODO: 데이터 출력이 필요하다면 toString 재정의
     */
    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
