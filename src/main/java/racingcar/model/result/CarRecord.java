package racingcar.model.result;

/**
 * 자동차의 기록을 나타내는 record
 *
 * @param carName       자동차의 이름
 * @param movedDistance 자동차의 이동 거리
 */
public record CarRecord(
        String carName,
        int movedDistance
) {
}