package classSkill.messageReciveMethod.serve;

import classSkill.firstClassCollection.Lotto;
import classSkill.valueObject.LottoNumber;

/**
 * VO들을 포장하는 객체 생성
 */
public class WinningLotto {

    protected static final String BONUS_NUMBER_DUPLICATE_ERROR_MSG= "보너스 번호는 우승 번호와 중복 될 수 없습니다";

    //VO들
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    private WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto winningLotto, LottoNumber bonusNumber){
        validateDuplicate(winningLotto, bonusNumber);
        return new WinningLotto(winningLotto,bonusNumber);
    }

    private static void validateDuplicate(Lotto winningLotto, LottoNumber bonusNumber){
        if(winningLotto.isContain(bonusNumber)){
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR_MSG);
        }
    }

    public Lotto getWinningLotto(){
        return winningLotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
