package racingcar.model.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.car.Car;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RoundResultTest {

    @Test
    @DisplayName("Car 리스트로부터 RoundResult를 생성할 수 있다")
    void createRoundResultFromCarList() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        car1.move();
        car2.move();
        
        List<Car> cars = List.of(car1, car2);

        // when
        RoundResult roundResult = RoundResult.from(cars);

        // then
        assertThat(roundResult.getRoundResult()).hasSize(2);
        assertThat(roundResult.getRoundResult().get(0).carName()).isEqualTo("pobi");
        assertThat(roundResult.getRoundResult().get(1).carName()).isEqualTo("woni");
    }

    @Test
    @DisplayName("빈 Car 리스트로부터 RoundResult를 생성할 수 있다")
    void createRoundResultFromEmptyCarList() {
        // given
        List<Car> emptyCars = List.of();

        // when
        RoundResult roundResult = RoundResult.from(emptyCars);

        // then
        assertThat(roundResult.getRoundResult()).isEmpty();
    }
}