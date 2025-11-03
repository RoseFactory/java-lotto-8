package lotto.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new LottoGenerator();

    @Test
    @DisplayName("1-45 사이의 중복이 없는 숫자 6개로 로또를 생성한다.")
    void should_generateNLottos_when_issueNLottos() {
        // given

        // when
        List<Lotto> lottos = lottoGenerator.issue(5);

        // then
        assertThat(lottos.size()).isEqualTo(5);
        for(Lotto lotto : lottos) {
            List<Integer> lottoNumbers = lotto.getNumbers();
            assertThat(lottoNumbers)
                .hasSize(6)
                .doesNotHaveDuplicates()
                .allMatch(n -> n >= 1 && n <= 45);
        }
    }
}