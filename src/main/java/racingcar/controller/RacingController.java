package racingcar.controller;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.RacingRequestDto;
import racingcar.dto.RacingResponseDto;
import racingcar.model.car.Car;
import racingcar.model.racing.Racing;
import racingcar.model.result.RoundResult;
import racingcar.util.CarNameParser;
import racingcar.util.RaceRoundParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;

    /**
     * RacingController를 생성합니다.
     *
     * @param inputView  사용자 입력을 처리하는 뷰
     * @param outputView 결과 출력을 처리하는 뷰
     */
    public RacingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    /**
     * 자동차 경주 게임을 실행합니다.
     */
    public void run() {
        RacingRequestDto racingRequest = inputView.getRacingRequest();
        Racing racing = initRacing(racingRequest);

        List<RoundResult> roundResults = runRacing(racing);
        List<String> winners = getWinnerNames(racing);

        outputView.printRacingResponse(new RacingResponseDto(roundResults, winners));
    }

    /**
     * 사용자 입력을 바탕으로 Racing 객체를 초기화합니다.
     *
     * @param racingRequest 사용자의 경주 요청 정보
     * @return 초기화된 Racing 객체
     */
    private Racing initRacing(RacingRequestDto racingRequest) {
        List<String> carNames = CarNameParser.parseCarName(racingRequest.rawCarNames());
        List<Car> cars = carNames.stream()
                .map(Car::new)
                .toList();
        int roundsToRace = RaceRoundParser.parseRaceRound(racingRequest.rawRoundsToRace());
        return Racing.from(cars, roundsToRace);
    }

    /**
     * 경주를 실행하고 각 라운드의 결과를 수집합니다.
     *
     * @param racing 실행할 경주 객체
     * @return 각 라운드의 결과 리스트
     */
    private List<RoundResult> runRacing(Racing racing) {
        List<RoundResult> roundResults = new ArrayList<>();
        while (racing.hasNextRound()) {
            racing.executeRound();
            roundResults.add(createRoundResult(racing));
        }
        return roundResults;
    }

    /**
     * 우승자 이름들을 반환합니다.
     *
     * @param racing 경주 객체
     * @return 우승자 이름들의 리스트
     */
    private List<String> getWinnerNames(Racing racing) {
        return racing.getWinners().stream()
                .map(Car::getName)
                .toList();
    }

    /**
     * 라운드 결과를 생성합니다.
     *
     * @param racing 경주 객체
     * @return 생성된 라운드 결과
     */
    private RoundResult createRoundResult(Racing racing) {
        return racing.createCurrentRoundResult();
    }
}