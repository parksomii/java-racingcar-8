package racingcar.dto;

import java.util.List;
import racingcar.model.result.RoundResult;

/**
 * 자동차 경주 응답 정보를 담는 DTO
 *
 * @param roundResults 각 라운드의 결과 리스트
 * @param winners      우승자 이름들의 리스트
 */
public record RacingResponseDto(
        List<RoundResult> roundResults,
        List<String> winners
) {
}