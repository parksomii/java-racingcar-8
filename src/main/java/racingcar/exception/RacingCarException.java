package racingcar.exception;

/**
 * 자동차 경주 게임에서 발생하는 커스텀 예외 클래스
 */
public class RacingCarException extends IllegalArgumentException {

    /**
     * ErrorMessage를 사용하여 예외를 생성합니다.
     *
     * @param errorMessage 에러 메시지 enum
     */
    public RacingCarException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
