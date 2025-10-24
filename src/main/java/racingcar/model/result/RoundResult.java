package racingcar.model.result;

import java.util.List;
import racingcar.model.car.Car;

public class RoundResult {

    private final List<CarRecord> roundResult;

    /**
     * RoundResult를 생성
     *
     * @param roundResult 라운드 결과 리스트
     */
    private RoundResult(List<CarRecord> roundResult) {
        this.roundResult = roundResult;
    }

    /**
     * Car 리스트로부터 RoundResult를 생성
     *
     * @param cars 자동차 리스트
     * @return 생성된 RoundResult 객체
     */
    public static RoundResult from(List<Car> cars) {
        List<CarRecord> carRecords = cars.stream()
                .map(car -> new CarRecord(car.getName(), car.getMovedDistance()))
                .toList();
        return new RoundResult(carRecords);
    }

    /**
     * 라운드 결과를 반환
     *
     * @return 자동차 기록들의 리스트
     */
    public List<CarRecord> getRoundResult() {
        return roundResult;
    }
}