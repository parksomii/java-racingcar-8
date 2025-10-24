package racingcar.model.racing;

import java.util.List;
import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;
import racingcar.model.car.Car;

public class Racing {
    private static final int MAX_RACE_ROUND = 10000;
    private static final int MIN_RACE_ROUND = 1;

    private final Participants participants;
    private final int totalRounds;
    private int currentRound;

    /**
     * Racing 객체를 생성합니다.
     *
     * @param participants 경주 참가자들
     * @param totalRounds  총 라운드 수
     * @throws RacingCarException 라운드 수가 유효하지 않은 경우
     */
    private Racing(Participants participants, int totalRounds) {
        validateRaceRoundInBound(totalRounds);
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
     * 경주 참가자들을 반환합니다.
     *
     * @return 경주 참가자들
     */
    public Participants getParticipants() {
        return participants;
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
     * Racing 객체를 생성하는 Factory 메서드입니다.
     *
     * @param cars        경주에 참가할 자동차들
     * @param totalRounds 총 라운드 수
     * @return 생성된 Racing 객체
     * @throws RacingCarException 라운드 수가 유효하지 않은 경우
     */
    public static Racing from(List<Car> cars, int totalRounds) {
        Participants participants = new Participants(cars);
        return new Racing(participants, totalRounds);
    }

    /**
     * 라운드 수가 유효한 범위인지 검증합니다.
     *
     * @param totalRounds 검증할 라운드 수
     * @throws RacingCarException 라운드 수가 1~10000 범위를 벗어나는 경우
     */
    private void validateRaceRoundInBound(int totalRounds) {
        if (totalRounds < MIN_RACE_ROUND || totalRounds > MAX_RACE_ROUND) {
            throw new RacingCarException(ErrorMessage.RACE_ROUND_OUT_OF_BOUND);
        }
    }
}