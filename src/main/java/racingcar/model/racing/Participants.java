package racingcar.model.racing;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;
import racingcar.model.car.Car;
import racingcar.model.result.RoundResult;

public class Participants {
    private final List<Car> participants;

    /**
     * Participants 객체를 생성합니다.
     *
     * @param participants 참가할 자동차들의 리스트
     * @throws RacingCarException 참가자 목록이 비어있거나 중복 이름이 있는 경우
     */
    public Participants(List<Car> participants) {
        validateParticipants(participants);
        this.participants = List.copyOf(participants);
    }

    /**
     * 모든 참가자 자동차를 이동시킵니다.
     */
    public void moveCars() {
        participants.forEach(Car::move);
    }


    /**
     * 특정 이동 거리를 가진 자동차들을 찾습니다.
     *
     * @param distance 찾을 이동 거리
     * @return 해당 거리를 가진 자동차들의 리스트
     */
    public List<Car> findCarsWithMovedDistance(int distance) {
        return participants.stream()
                .filter(car -> car.getMovedDistance() == distance)
                .toList();
    }

    /**
     * 참가자들 중 최대 이동 거리를 반환합니다.
     *
     * @return 최대 이동 거리 (참가자가 없으면 0)
     */
    public int getMaxMovedDistance() {
        return participants.stream()
                .mapToInt(Car::getMovedDistance)
                .max()
                .orElse(0);
    }

    /**
     * 참가자들의 라운드 결과를 생성합니다.
     *
     * @return 참가자들의 라운드 결과
     */
    public RoundResult createRoundResult() {
        return RoundResult.from(participants);
    }

    /**
     * 참가자 목록의 유효성을 검증합니다.
     *
     * @param participants 검증할 참가자 목록
     * @throws RacingCarException 참가자 목록이 비어있거나 중복 이름이 있는 경우
     */
    private void validateParticipants(List<Car> participants) {
        if (participants == null || participants.isEmpty()) {
            throw new RacingCarException(ErrorMessage.EMPTY_CAR_NAMES);
        }
        Set<String> distinctCarNames = participants.stream()
                .map(Car::getName)
                .collect(Collectors.toSet());
        if (participants.size() != distinctCarNames.size()) {
            throw new RacingCarException(ErrorMessage.DUPLICATED_CAR_NAME);
        }
    }
}