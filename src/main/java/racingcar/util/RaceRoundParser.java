package racingcar.util;

import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;

public class RaceRoundParser {

    private static final int MIN_ROUND_COUNT = 1;

    /**
     * 문자열로 입력된 라운드 수를 파싱합니다.
     *
     * @param rawRoundsToRace 파싱할 라운드 수 문자열
     * @return 파싱된 라운드 수 (양수)
     * @throws RacingCarException 입력이 유효하지 않은 경우
     */
    public static int parseRaceRound(String rawRoundsToRace) {
        if (rawRoundsToRace == null || rawRoundsToRace.isBlank()) {
            throw new RacingCarException(ErrorMessage.EMPTY_INPUT);
        }

        try {
            int rounds = Integer.parseInt(rawRoundsToRace.trim());
            if (rounds < MIN_ROUND_COUNT) {
                throw new RacingCarException(ErrorMessage.RACE_ROUND_OUT_OF_BOUND);
            }
            return rounds;
        } catch (NumberFormatException e) {
            // 정수 범위 초과인지 확인
            try {
                Long.parseLong(rawRoundsToRace.trim());
                throw new RacingCarException(ErrorMessage.INTEGER_RANGE_EXCEEDED);
            } catch (NumberFormatException longException) {
                throw new RacingCarException(ErrorMessage.INVALID_NUMBER_FORMAT);
            }
        }
    }
}
