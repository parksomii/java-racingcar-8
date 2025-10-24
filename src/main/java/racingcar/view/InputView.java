package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.RacingRequestDto;

public class InputView {
    private static final String CAR_NAMES_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private final ErrorView errorView;

    /**
     * InputView를 생성합니다.
     * 
     * @param errorView 에러 처리를 담당하는 뷰
     */
    public InputView(ErrorView errorView) {
        this.errorView = errorView;
    }

    /**
     * 사용자로부터 경주 요청 정보를 입력받습니다.
     * 
     * @return 사용자가 입력한 경주 요청 정보
     */
    public RacingRequestDto getRacingRequest() {
        String carNames = getCarNames();
        String roundsToRace = getRoundsToRace();
        return new RacingRequestDto(carNames, roundsToRace);
    }

    /**
     * 사용자로부터 자동차 이름을 입력받습니다.
     * 
     * @return 입력받은 자동차 이름 문자열
     */
    private String getCarNames() {
        System.out.println(CAR_NAMES_INPUT_MESSAGE);
        String carNames = Console.readLine();
        errorView.validateInput(carNames);
        return carNames;
    }

    /**
     * 사용자로부터 시도 횟수를 입력받습니다.
     * 
     * @return 입력받은 시도 횟수 문자열
     */
    private String getRoundsToRace() {
        System.out.println(ROUND_INPUT_MESSAGE);
        String roundsToRace = Console.readLine();
        errorView.validateInput(roundsToRace);
        return roundsToRace;
    }
}