package racingcar.util;

import java.util.Arrays;
import java.util.List;
import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;

public class CarNameParser {

    private static final String NAME_SEPARATOR = ",";

    /**
     * 쉼표로 구분된 자동차 이름 문자열을 파싱합니다.
     *
     * @param rawCarNames 파싱할 자동차 이름 문자열 (쉼표로 구분)
     * @return 파싱된 자동차 이름들의 리스트
     * @throws RacingCarException 입력이 null이거나 공백인 경우
     */
    public static List<String> parseCarName(String rawCarNames) {
        if (rawCarNames == null || rawCarNames.isBlank()) {
            throw new RacingCarException(ErrorMessage.EMPTY_CAR_NAMES);
        }

        return Arrays.stream(rawCarNames.split(NAME_SEPARATOR))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .toList();
    }
}