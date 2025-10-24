package racingcar.view;

import java.util.List;
import racingcar.dto.RacingResponseDto;
import racingcar.model.result.CarRecord;
import racingcar.model.result.RoundResult;

public class OutputView {
    private static final String ROUND_RESULT_START_MESSAGE = "실행 결과";
    private static final String NEW_LINE = "\n";
    private static final String NAME_DISTANCE_SEPARATOR = " : ";
    private static final String DISTANCE_GRAPH_CHAR = "-";
    private static final String WINNER_MESSAGE_PREFIX = "최종 우승자 : ";
    private static final String WINNER_SEPARATOR = ", ";

    /**
     * 출력용 문자열 빌더
     */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * 경주 응답 정보를 출력합니다.
     *
     * @param racingResponseDto 출력할 경주 응답 정보
     */
    public void printRacingResponse(RacingResponseDto racingResponseDto) {
        clearStringBuilder();

        appendAllRoundResults(racingResponseDto.roundResults());
        appendWinners(racingResponseDto.winners());

        System.out.println(stringBuilder);
        clearStringBuilder();
    }

    /**
     * 모든 라운드 결과를 문자열에 추가합니다.
     *
     * @param roundResults 추가할 라운드 결과들
     */
    private void appendAllRoundResults(List<RoundResult> roundResults) {
        stringBuilder.append(NEW_LINE)
                .append(ROUND_RESULT_START_MESSAGE)
                .append(NEW_LINE);
        for (RoundResult roundResult : roundResults) {
            appendRoundResult(roundResult);
        }
    }

    /**
     * 한 라운드의 결과를 문자열에 추가합니다.
     *
     * @param roundResult 추가할 라운드 결과
     */
    private void appendRoundResult(RoundResult roundResult) {
        for (CarRecord record : roundResult.getRoundResult()) {
            stringBuilder
                    .append(record.carName())
                    .append(NAME_DISTANCE_SEPARATOR)
                    .append(buildDistanceGraph(record.movedDistance()))
                    .append(NEW_LINE);
        }
        stringBuilder.append(NEW_LINE);
    }

    /**
     * 우승자 정보를 문자열에 추가합니다.
     *
     * @param winners 우승자 이름들
     */
    private void appendWinners(List<String> winners) {
        stringBuilder.append(WINNER_MESSAGE_PREFIX)
                .append(joinWinners(winners));
    }

    /**
     * 우승자 이름들을 쉼표로 구분하여 연결합니다.
     *
     * @param winners 우승자 이름들
     * @return 쉼표로 구분된 우승자 이름 문자열
     */
    private String joinWinners(List<String> winners) {
        return String.join(WINNER_SEPARATOR, winners);
    }

    /**
     * 이동 거리를 '-' 문자로 시각화합니다.
     *
     * @param distance 시각화할 이동 거리
     * @return '-' 문자로 구성된 거리 그래프
     */
    private String buildDistanceGraph(int distance) {
        return DISTANCE_GRAPH_CHAR.repeat(distance);
    }

    /**
     * StringBuilder를 초기화합니다.
     */
    private void clearStringBuilder() {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.setLength(0);
        }
    }
}