package racingcar.model.racing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.car.Car;
import racingcar.model.result.RoundResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingTest {

    @Test
    @DisplayName("정상적인 Racing을 생성할 수 있다")
    void createRacingWithValidParameters() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        int totalRounds = 5;

        // when
        Racing racing = Racing.from(cars, totalRounds);

        // then
        assertThat(racing.hasNextRound()).isTrue();
    }


    @Test
    @DisplayName("라운드를 실행할 수 있다")
    void executeRound() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Racing racing = Racing.from(cars, 3);

        // when
        racing.executeRound();

        // then
        assertThat(racing.hasNextRound()).isTrue();
    }

    @Test
    @DisplayName("모든 라운드가 끝나면 hasNextRound가 false를 반환한다")
    void hasNextRoundReturnsFalseWhenAllRoundsCompleted() {
        // given
        List<Car> cars = List.of(new Car("pobi"));
        Racing racing = Racing.from(cars, 1);

        // when
        racing.executeRound();

        // then
        assertThat(racing.hasNextRound()).isFalse();
    }

    @Test
    @DisplayName("우승자를 결정할 수 있다")
    void getWinners() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        car1.move();
        car1.move();
        car2.move();
        
        List<Car> cars = List.of(car1, car2);
        Racing racing = Racing.from(cars, 1);

        // when
        List<Car> winners = racing.getWinners();

        // then
        assertThat(winners).isNotEmpty();
    }

    @Test
    @DisplayName("현재 라운드 결과를 생성할 수 있다")
    void createCurrentRoundResult() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Racing racing = Racing.from(cars, 1);

        // when
        RoundResult roundResult = racing.createCurrentRoundResult();

        // then
        assertThat(roundResult.getRoundResult()).hasSize(2);
    }
}