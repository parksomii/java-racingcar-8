package racingcar.model.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.exception.RacingCarException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("정상적인 자동차를 생성할 수 있다")
    void createCarWithValidName() {
        // given
        String validName = "pobi";

        // when
        Car car = new Car(validName);

        // then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getMovedDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이름 앞뒤 공백이 trim 처리된다")
    void createCarWithTrimmedName() {
        // given
        String nameWithSpaces = "  pobi  ";

        // when
        Car car = new Car(nameWithSpaces);

        // then
        assertThat(car.getName()).isEqualTo("pobi");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "abcdef"})
    @DisplayName("유효하지 않은 자동차 이름일 때 예외가 발생한다")
    void createCarWithInvalidName(String invalidName) {
        // when & then
        assertThatThrownBy(() -> new Car(invalidName))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("자동차가 이동할 수 있다")
    void moveCar() {
        // given
        Car car = new Car("pobi");

        // when
        car.move();

        // then
        assertThat(car.getMovedDistance()).isGreaterThanOrEqualTo(0);
    }
}