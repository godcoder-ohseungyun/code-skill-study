package classSkill.messageReciveMethod.serve;

import classSkill.firstClassCollection.Lotto;
import classSkill.messageReciveMethod.serve.WinningLotto;
import classSkill.valueObject.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 테스트 코드
 *
 * 1. 테스트 하려는 도메인의 정상 기능 및 생성을 테스트 한다
 * 2. 테스트 하려는 도메인의 예외 기능 및 생성을 테스트 한다
 */
class WinningLottoTest {

    private WinningLotto winningLotto;

    //TODO: 보통 의존성이나 데이터를 미리 세팅할 때 활용한다
    @BeforeEach
    public void init(){
        WinningLotto.of(Lotto.createAuto(), LottoNumber.from(40));
    }

    //TODO: 정상 기능 검사
    @DisplayName(value = "디스플레이 네임은 이렇게 바꿉니다.")
    @Test
    public void createWinningLottoWithLottoAndBonus(){

        //TODO: 보통은 생성 인스턴스 타입으로 검사한다
        assertThat(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(30)))
                .isInstanceOf(WinningLotto.class);

        //TODO: VO는 Equals, HashCode 재정의 후 동일성으로 검사한다
        assertThat(LottoNumber.from(20)).isEqualTo(LottoNumber.from(20));

    }

    //TODO: 예외 검사
    @DisplayName(value = "디스플레이 네임은 이렇게 바꿉니다.")
    @Test
    public void createFailWinningLottoWithLottoAndBonus(){

        //TODO: 예외의 경우 이렇게 테스트한다
        //TODO: 메세지 포함 테스트를 위해 해당 상수는 protected를 이용한다
        assertThatThrownBy(()->WinningLotto.of(Lotto.createAuto(), LottoNumber.from(2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WinningLotto.BONUS_NUMBER_DUPLICATE_ERROR_MSG);


    }

    //TODO: 단순 파라미터 검사
    @DisplayName(value = "디스플레이 네임은 이렇게 바꿉니다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 1})
    public void testUsingParam(int number){
        assertThat(number).isEqualTo(1);
    }

    //TODO: 복합 파라미터 검사
    @DisplayName(value = "디스플레이 네임은 이렇게 바꿉니다.")
    @ParameterizedTest
    @MethodSource("provideForTestUsingComplexParam")
    public void testUsingComplexParam(List<Integer> list, int number){
        assertThat(list.size()).isEqualTo(5);
    }

    //TODO: provider 작성 , static이어야 한다
    private static Stream<Arguments> provideForTestUsingComplexParam(){
        return Stream.of(
                Arguments.arguments(List.of(1,2,3,4,5),66)
                , Arguments.arguments(List.of(1,2,3,4,5),77)
                , Arguments.arguments(List.of(1,2,3,4,5),44)
        );
    }






}