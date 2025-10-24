package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RaceRoundParserTest {

    @Test
    @DisplayName("정상적인 라운드 수를 파싱할 수 있다")
    void parseValidRoundNumber() {
        // given
        String roundNumber = "5";

        // when
        int parsedRound = RaceRoundParser.parseRaceRound(roundNumber);

        // then
        assertThat(parsedRound).isEqualTo(5);
    }

    @Test
    @DisplayName("공백이 포함된 라운드 수를 파싱할 수 있다")
    void parseRoundNumberWithSpaces() {
        // given
        String roundNumber = "  10  ";

        // when
        int parsedRound = RaceRoundParser.parseRaceRound(roundNumber);

        // then
        assertThat(parsedRound).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "abc", "5.5", "0", "-1"})
    @DisplayName("유효하지 않은 입력일 때 예외가 발생한다")
    void parseInvalidInput(String invalidInput) {
        // when & then
        assertThatThrownBy(() -> RaceRoundParser.parseRaceRound(invalidInput))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("null 입력 시 예외가 발생한다")
    void parseNullInput() {
        // when & then
        assertThatThrownBy(() -> RaceRoundParser.parseRaceRound(null))
                .isInstanceOf(RacingCarException.class);
    }
}