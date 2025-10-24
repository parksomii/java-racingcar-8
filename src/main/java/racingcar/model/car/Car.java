package racingcar.model.car;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;

/**
 * 자동차를 나타내는 클래스
 * 0~9 사이의 랜덤 값이 4 이상일 때 1칸 전진합니다.
 */
public class Car {

    private static final int MAX_CAR_NAME_LENGTH = 5;
    private static final int MIN_RANDOM_NUMBER = 0;
    private static final int MAX_RANDOM_NUMBER = 9;
    private static final int MOVEMENT_THRESHOLD = 4;

    private final String name;
    private int movedDistance;

    /**
     * 자동차 객체를 생성합니다.
     *
     * @param name 자동차의 이름 (5자 이하)
     * @throws RacingCarException 이름이 유효하지 않은 경우
     */
    public Car(String name) {
        validateName(name);
        this.name = name.trim();
        this.movedDistance = 0;
    }

    /**
     * 자동차를 이동시킵니다.
     */
    public void move() {
        if (Randoms.pickNumberInRange(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER) >= MOVEMENT_THRESHOLD) {
            movedDistance += 1;
        }
    }

    /**
     * 자동차의 이름을 반환합니다.
     *
     * @return 자동차의 이름
     */
    public String getName() {
        return this.name;
    }

    /**
     * 자동차의 이동 거리를 반환합니다.
     *
     * @return 자동차의 이동 거리
     */
    public int getMovedDistance() {
        return this.movedDistance;
    }

    /**
     * 자동차 이름의 유효성을 검증합니다.
     *
     * @param carName 검증할 자동차 이름
     * @throws RacingCarException 이름이 유효하지 않은 경우
     */
    private static void validateName(String carName) {
        if (carName == null || carName.isBlank()) {
            throw new RacingCarException(ErrorMessage.EMPTY_CAR_NAME);
        }
        String trimmedName = carName.trim();
        if (trimmedName.length() > MAX_CAR_NAME_LENGTH) {
            throw new RacingCarException(ErrorMessage.CAR_NAME_TOO_LONG);
        }
    }
}
