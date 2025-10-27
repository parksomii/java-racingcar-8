package racingcar.util;

import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;

public class RaceRoundParser {

    private static final int MIN_ROUND_COUNT = 1;
    private static final int MAX_ROUND_COUNT = 10000;

    /**
     * 문자열로 입력된 라운드 수를 파싱합니다.
     *
     * @param rawRoundsToRace 파싱할 라운드 수 문자열
     * @return 파싱된 라운드 수 (양수)
     * @throws RacingCarException 입력이 유효하지 않은 경우
     */
    public static int parseRaceRound(String rawRoundsToRace) {
        validateInput(rawRoundsToRace);
        String trimmedInput = rawRoundsToRace.trim();
        return parseInteger(trimmedInput);
    }

    /**
     * 입력값의 기본 유효성을 검증합니다.
     *
     * @param rawInput 원본 입력 문자열
     * @throws RacingCarException 입력이 null이거나 공백인 경우
     */
    private static void validateInput(String rawInput) {
        if (rawInput == null || rawInput.isBlank()) {
            throw new RacingCarException(ErrorMessage.EMPTY_INPUT);
        }
    }

    /**
     * 문자열을 정수로 파싱하고 유효성을 검증합니다.
     *
     * @param trimmedInput 공백이 제거된 입력 문자열
     * @return 파싱된 정수 값
     * @throws RacingCarException 파싱 실패 또는 범위 초과 시
     */
    private static int parseInteger(String trimmedInput) {
        try {
            int rounds = Integer.parseInt(trimmedInput);
            validateRoundCount(rounds);
            return rounds;
        } catch (NumberFormatException e) {
            handleNumberFormatException(trimmedInput);
            return 0;
        }
    }

    /**
     * 라운드 수가 허용된 범위 내에 있는지 검증합니다.
     *
     * @param rounds 검증할 라운드 수
     * @throws RacingCarException 라운드 수가 범위를 벗어난 경우
     */
    private static void validateRoundCount(int rounds) {
        if (rounds < MIN_ROUND_COUNT || rounds > MAX_ROUND_COUNT) {
            throw new RacingCarException(ErrorMessage.RACE_ROUND_OUT_OF_BOUND);
        }
    }

    /**
     * NumberFormatException 발생 시 정수 범위 초과인지 구분하여 처리합니다.
     *
     * @param input 파싱에 실패한 입력 문자열
     * @throws RacingCarException 정수 범위 초과 또는 숫자가 아닌 경우
     */
    private static void handleNumberFormatException(String input) {
        try {
            Long.parseLong(input);
            throw new RacingCarException(ErrorMessage.RACE_ROUND_OUT_OF_BOUND);
        } catch (NumberFormatException longException) {
            throw new RacingCarException(ErrorMessage.INVALID_NUMBER_FORMAT);
        }
    }
}