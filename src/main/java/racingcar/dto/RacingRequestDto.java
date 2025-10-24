package racingcar.dto;

/**
 * 자동차 경주 요청 정보를 담는 DTO
 *
 * @param rawCarNames     원시 자동차 이름 문자열 (쉼표로 구분)
 * @param rawRoundsToRace 원시 시도 횟수 문자열
 */
public record RacingRequestDto(
        String rawCarNames,
        String rawRoundsToRace
) {
}