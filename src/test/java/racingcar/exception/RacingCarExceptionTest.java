package racingcar.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RacingCarExceptionTest {

    @Test
    @DisplayName("ErrorMessage로 RacingCarException을 생성할 수 있다")
    void createExceptionWithErrorMessage() {
        // given
        ErrorMessage errorMessage = ErrorMessage.EMPTY_CAR_NAME;

        // when
        RacingCarException exception = new RacingCarException(errorMessage);

        // then
        assertThat(exception.getMessage()).isEqualTo("[ERROR] 자동차 이름은 빈 값일 수 없습니다.");
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }
}