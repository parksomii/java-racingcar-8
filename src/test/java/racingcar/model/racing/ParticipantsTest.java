package racingcar.model.racing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.exception.RacingCarException;
import racingcar.model.car.Car;
import racingcar.model.result.RoundResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("정상적인 Participants를 생성할 수 있다")
    void createParticipantsWithValidCars() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"), new Car("jun"));

        // when
        Participants participants = new Participants(cars);

        // then
        assertThat(participants.getMaxMovedDistance()).isEqualTo(0);
    }

    @Test
    @DisplayName("빈 자동차 목록으로 Participants를 생성할 때 예외가 발생한다")
    void createParticipantsWithEmptyList() {
        // given
        List<Car> emptyCars = List.of();

        // when & then
        assertThatThrownBy(() -> new Participants(emptyCars))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("중복된 자동차 이름이 있을 때 예외가 발생한다")
    void createParticipantsWithDuplicateNames() {
        // given
        List<Car> carsWithDuplicates = List.of(
                new Car("pobi"),
                new Car("pobi"),  // 중복
                new Car("jun")
        );

        // when & then
        assertThatThrownBy(() -> new Participants(carsWithDuplicates))
                .isInstanceOf(RacingCarException.class);
    }

    @Test
    @DisplayName("모든 자동차를 이동시킬 수 있다")
    void moveAllCars() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Participants participants = new Participants(cars);

        // when
        participants.moveCars();

        // then
        assertThat(participants.getMaxMovedDistance()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("라운드 결과를 생성할 수 있다")
    void createRoundResult() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        Participants participants = new Participants(cars);

        // when
        RoundResult roundResult = participants.createRoundResult();

        // then
        assertThat(roundResult.getRoundResult()).hasSize(2);
    }
}