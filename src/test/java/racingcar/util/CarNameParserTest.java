package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.RacingCarException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarNameParserTest {

    @ParameterizedTest
    @CsvSource({
        "'pobi,woni,jun', 'pobi,woni,jun'",
        "' pobi , woni , jun ', 'pobi,woni,jun'",
        "'pobi,,woni, ,jun', 'pobi,woni,jun'"
    })
    @DisplayName("자동차 이름을 파싱할 수 있다")
    void parseCarNames(String input, String expectedNames) {
        // when
        List<String> parsedNames = CarNameParser.parseCarName(input);

        // then
        List<String> expected = List.of(expectedNames.split(","));
        assertThat(parsedNames).containsExactlyElementsOf(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("유효하지 않은 입력일 때 예외가 발생한다")
    void parseInvalidInput(String invalidInput) {
        // when & then
        assertThatThrownBy(() -> CarNameParser.parseCarName(invalidInput))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("null 입력 시 예외가 발생한다")
    void parseNullInput() {
        // when & then
        assertThatThrownBy(() -> CarNameParser.parseCarName(null))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("쉼표만 있는 문자열은 빈 리스트를 반환한다")
    void parseOnlyCommaInput() {
        // given
        String onlyCommaInput = ",,,";

        // when
        List<String> parsedNames = CarNameParser.parseCarName(onlyCommaInput);

        // then
        assertThat(parsedNames).isEmpty();
    }
}