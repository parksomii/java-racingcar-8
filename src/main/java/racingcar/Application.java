package racingcar;

import racingcar.controller.RacingController;
import racingcar.exception.RacingCarException;
import racingcar.view.ErrorView;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {

    /**
     * 자동차 경주 게임의 메인 메서드
     *
     * @param args 명령행 인수
     * @throws RacingCarException 게임 실행 중 발생하는 예외
     */
    public static void main(String[] args) {
        final ErrorView errorViewBean = new ErrorView();
        final InputView inputViewBean = new InputView(errorViewBean);
        final OutputView outputViewBean = new OutputView();

        final RacingController racingControllerBean = new RacingController(inputViewBean, outputViewBean);

        try {
            racingControllerBean.run();
        } catch (RacingCarException e) {
            errorViewBean.errorPage(e.getMessage());
            throw e;
        }
    }
}
