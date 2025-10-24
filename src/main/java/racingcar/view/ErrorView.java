package racingcar.view;

import racingcar.exception.ErrorMessage;
import racingcar.exception.RacingCarException;

public class ErrorView {

    /**
     * 에러 메시지를 출력합니다.
     *
     * @param message 출력할 에러 메시지
     */
    public void errorPage(String message) {
        System.out.println(message);
    }

    /**
     * 입력값의 기본적인 유효성을 검증합니다.
     *
     * @param input 검증할 입력값
     * @throws RacingCarException 입력값이 유효하지 않은 경우
     */
    public void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            showError(ErrorMessage.EMPTY_INPUT);
            throw new RacingCarException(ErrorMessage.EMPTY_INPUT);
        }
        if (input.isBlank()) {
            showError(ErrorMessage.BLANK_INPUT);
            throw new RacingCarException(ErrorMessage.BLANK_INPUT);
        }
    }

    /**
     * 에러 메시지를 콘솔에 출력합니다.
     *
     * @param errorMessage 출력할 에러 메시지 enum
     */
    private void showError(ErrorMessage errorMessage) {
        System.out.println(errorMessage.getMessage());
    }
}
