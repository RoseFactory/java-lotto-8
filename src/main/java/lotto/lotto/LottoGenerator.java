package lotto.lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoGenerator {

    public List<Lotto> issue(int n) {
        List<Lotto> lottos = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            List<Integer> uniqueNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Lotto lotto = new Lotto(uniqueNumbers);
            lottos.add(lotto);
        }

        return lottos;
    }
}
