package bridge.view;

import bridge.domain.Moving;
import bridge.domain.MovingResult;
import java.util.List;
import java.util.StringJoiner;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String BRIEDGE_DELIMETER = " | ";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<MovingResult> result) {
        System.out.println(drawUpMap(result));
        System.out.println(drawDownMap(result));
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<MovingResult> results, boolean isSuccess, int tryCount) {
        System.out.println("최종 게임 결과");
        printMap(results);
        System.out.printf(LINE_SEPARATOR + "게임 성공 여부: %s", createSuccessMessage(isSuccess));
        System.out.printf(LINE_SEPARATOR + "총 시도한 횟수: %d", tryCount);
    }

    private String createSuccessMessage(boolean isSuccess) {
        if (isSuccess) {
            return "성공";
        }
        return "실패";
    }

    private String drawUpMap(List<MovingResult> result) {
        StringJoiner stringJoiner = new StringJoiner(BRIEDGE_DELIMETER);
        result.stream()
                .map(movingResult -> movingResultToString(movingResult, Moving.UP))
                .forEach(stringJoiner::add);
        return String.format("[ %s ]", stringJoiner);
    }

    private String drawDownMap(List<MovingResult> result) {
        StringJoiner stringJoiner = new StringJoiner(BRIEDGE_DELIMETER);
        result.stream()
                .map(movingResult -> movingResultToString(movingResult, Moving.DOWN))
                .forEach(stringJoiner::add);
        return String.format("[ %s ]", stringJoiner);
    }

    private String movingResultToString(MovingResult movingResult, Moving moving) {
        if (movingResult.isSuccess() && moving.equals(movingResult.getMoving())) {
            return "O";
        }
        return "X";
    }
}