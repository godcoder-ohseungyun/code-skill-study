package librarySkill.stream;

import classSkill.firstClassCollection.Lotto;
import classSkill.messageReciveMethod.WinningResult;
import classSkill.messageReciveMethod.serve.WinningLotto;
import classSkill.valueObject.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 스트림을 활용하면 컨밴션을 준수하면 컬랙션을 쉽게 다룰 수 있다
 *
 * 다만, 일회성이며 유지보수가 어려운 경우가 있고, 남발하면 가독성을 오히려 해칠 수 있으니 단순한 경우에 활용하자
 */
public class ConvertCollection {

    public static void main(String[] args) {
        List<WinningLotto> winningLottos = new ArrayList<>();
        winningLottos.add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(30)));
        winningLottos.add(WinningLotto.of(Lotto.createAuto(), LottoNumber.from(20)));

        //원하는 컬랙션으로 변경하기 : collect와 Collectors 활용
        Map<LottoNumber,Lotto> map = winningLottos.stream()
                .collect(
                        Collectors.toMap(WinningLotto::getBonusNumber,WinningLotto::getWinningLotto)
                );

        System.out.println(map);

        //요소를 조작하여 새로운 리스트 만들기 : map으로 요소 조작 후,   collect와 Collectors 활용
        List<Lotto> winningLottos2 = winningLottos.stream()
                .map(WinningLotto::getWinningLotto) //요소 조작
                .collect(Collectors.toList());

        System.out.println(winningLottos2);
    }
}
