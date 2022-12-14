package librarySkill.stream;

import classSkill.firstClassCollection.Lotto;
import classSkill.messageReciveMethod.serve.WinningLotto;
import classSkill.valueObject.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * > 스트림을 활용하면 컬랙션 및 배열을 쉽게 다룰 수 있다
 * <p>
 * > 스트림 내용 구분
 * 스트림 생성하기 : 스트림 인스턴스 생성
 * 스트림 가공하기 : fillter, map 등 원하는 결과를 만드는 중간 과정
 * 스트람 결과 만들기 : 최종 결과를 만들어 내는 작업
 * <p>
 * > 스트림은 아래와 같이 체이닝을 통해 가공된다
 * 전체 -> 맵핑 -> 필터링 1 -> 필터링 2 -> 결과 만들기 -> 결과물
 */
public class ConvertCollection {

    public static void main(String[] args) {

        //TODO: 스트림 생성하기
        //배열 to 스트림
        String[] arr = {"가", "나", "다"};

        Arrays.stream(arr);
        Arrays.stream(arr, 0, 2); //특정 구간만 스트림화


        //컬랙션 to 스트림
        List<WinningLotto> winningLottos = new ArrayList<>();
        winningLottos.add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(30)));
        winningLottos.add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(20)));

        winningLottos.stream();

        //스트림 to 컬랙션
        List<WinningLotto> winningLottos2 = Stream.<WinningLotto>builder()
                .add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(20)))
                .add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(30)))
                .build()
                .toList();

        List<WinningLotto> winningLottos3 = Stream
                .generate(() -> WinningLotto.of(Lotto.createAuto(), LottoNumber.from(20)))
                .limit(5) //제한 크기 많큼 generate안에 값을 추가한다
                .toList();

        //TODO: 스트림 가공하기
        //filter : 람다식의 내용에 부합하는 요소만 남긴다
        List<String> ss = Arrays.stream(arr)
                .filter(string -> string.contains("가"))
                .toList();

        //map : 람다식의 내용으로 요소들을 변경한다
        List<String> sss = Arrays.stream(arr)
                .map(string -> string.concat("님"))
                .toList();

        //sorted : 람다식의 내용으로 요소를 정렬한다, 기본은 comparable 준수
        List<String> ssss = Arrays.stream(arr)
                .sorted((s1, s2) -> s2.length() - s1.length())
                .toList();


        //TODO: 결과 만들기
        //collect : 가장 자주 사용됨, Collectors를 활용해 다양한 결과를 생성함

        //case1 : *일급 컬랙션 toString 재정의에 아주 용이할 것으로 보임, 이러면 OutputView에서도 편리하게 출력 가능
        String r = Arrays.stream(arr)
                .map(string -> string.concat("님"))
                .collect(Collectors.joining(" , ", "[", "]")); //문자열 요소 사이, 문자열 앞, 문자열 뒤에 인자 삽입
        //[가님 , 나님 , 다님]

        //case2
        List<String> r2 = Arrays.stream(arr)
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(Collectors.toList()); //toList()와 같다
        /**
         * 이 밖에도 컬랙션 요소의 평균, 합 등의 연산도 가능하다
         * 검색 기능 활용할 것
         */

        //case3 : *일급 컬랙션에 result를 map에 담을때 유용할 듯
        Map<LottoNumber, Lotto> map = winningLottos.stream()
                .collect(
                        Collectors.toMap(WinningLotto::getBonusNumber, WinningLotto::getWinningLotto)
                );

    }
}
