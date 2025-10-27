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
    @ValueSource(strings = {"", "   ", "abc", "5.5", "0", "-1", "10001"})
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

    @Test
    @DisplayName("정수 범위를 초과하는 입력 시 적절한 예외 메시지가 발생한다")
    void parseIntegerRangeExceeded() {
        // given
        String exceededInput = "9999999999999999";

        // when & then
        assertThatThrownBy(() -> RaceRoundParser.parseRaceRound(exceededInput))
                .isInstanceOf(RacingCarException.class)
                .hasMessage("[ERROR] 시도 횟수가 정수 범위를 초과했습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10000})
    @DisplayName("경계값 라운드 수를 파싱할 수 있다")
    void parseBoundaryRoundNumbers(int roundNumber) {
        // when
        int parsedRound = RaceRoundParser.parseRaceRound(String.valueOf(roundNumber));

        // then
        assertThat(parsedRound).isEqualTo(roundNumber);
    }
}