package racingcar.exception;

public enum ErrorMessage {
    EMPTY_CAR_NAMES("[ERROR] 자동차 이름 목록이 비어있습니다."),
    EMPTY_CAR_NAME("[ERROR] 자동차 이름은 빈 값일 수 없습니다."),
    CAR_NAME_TOO_LONG("[ERROR] 자동차 이름은 5자 이하만 가능합니다."),
    DUPLICATED_CAR_NAME("[ERROR] 동일한 자동차 이름이 존재합니다."),
    INVALID_NUMBER_FORMAT("[ERROR] 시도 횟수는 숫자여야 합니다."),
    RACE_ROUND_OUT_OF_BOUND("[ERROR] 시도 횟수는 양수여야 합니다."),
    INTEGER_RANGE_EXCEEDED("[ERROR] 시도 횟수가 정수 범위를 초과했습니다."),
    EMPTY_INPUT("[ERROR] 입력값이 비어있습니다."),
    BLANK_INPUT("[ERROR] 공백만 입력할 수 없습니다.");

    private final String message;

    /**
     * ErrorMessage를 생성합니다.
     *
     * @param message 에러 메시지 내용
     */
    ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * 에러 메시지를 반환합니다.
     *
     * @return 에러 메시지 문자열
     */
    public String getMessage() {
        return this.message;
    }
}