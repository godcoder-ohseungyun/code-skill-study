package classSkill.dto;

import classSkill.firstClassCollection.Lotto;
import classSkill.valueObject.LottoNumber;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * dto란 데이터 변환 계층으로 컨트롤러에서 값을 사용자(mvc에선 view)에게 전달할 때 사용한다
 * 원본 객체를 받아 dto로 변환하는 메서드와 getter & setter를 가진다
 *
 * 데이터 변환 레이어를 분리함으로써 복잡한 시스템이 관리하기 용이해진다
 */
public class LottoDto {
    //final
    private final Set<Integer> lottoNumbers;

    //생성자 접근 제어
    private LottoDto(Set<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    //TODO : 정적 팩토리 메서드 활용, 원본을 입력 받아 DTO로 변환하는 작업
    public static LottoDto from(Lotto lotto) {

        Set<Integer> lottoNumbers = lotto.getNumbers()
                .stream()
                .map(LottoNumber::getNumber)
                .collect(Collectors.toSet());

        return new LottoDto(lottoNumbers);
    }

    //TODO : 컬랙션을 전달하는 경우 복사본 전달, 원본 회손 방지
    public List<Integer> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }
}
