package racingcar.model.racing;

import java.util.List;
import racingcar.model.car.Car;
import racingcar.model.result.RoundResult;

public class Racing {

    private final Participants participants;
    private final int totalRounds;
    private int currentRound;

    /**
     * Racing 객체를 생성합니다.
     *
     * @param participants 경주 참가자들
     * @param totalRounds  총 라운드 수 (이미 검증된 값)
     */
    private Racing(Participants participants, int totalRounds) {
        this.participants = participants;
        this.totalRounds = totalRounds;
        this.currentRound = 0;
    }

    /**
     * 한 라운드를 실행합니다.
     */
    public void executeRound() {
        participants.moveCars();
        currentRound++;
    }


    /**
     * 다음 라운드가 있는지 확인합니다.
     *
     * @return 다음 라운드가 있으면 true, 없으면 false
     */
    public boolean hasNextRound() {
        return (currentRound < totalRounds);
    }

    /**
     * 경주의 우승자들을 결정합니다.
     *
     * @return 우승자 자동차들의 리스트
     */
    public List<Car> getWinners() {
        int maxDistance = participants.getMaxMovedDistance();
        return participants.findCarsWithMovedDistance(maxDistance);
    }

    /**
     * 현재 라운드의 결과를 생성합니다.
     *
     * @return 현재 라운드의 결과
     */
    public RoundResult createCurrentRoundResult() {
        return participants.createRoundResult();
    }

    /**
     * Racing 객체를 생성하는 Factory 메서드입니다.
     *
     * @param cars        경주에 참가할 자동차들
     * @param totalRounds 총 라운드 수
     * @return 생성된 Racing 객체
     */
    public static Racing from(List<Car> cars, int totalRounds) {
        Participants participants = new Participants(cars);
        return new Racing(participants, totalRounds);
    }

}