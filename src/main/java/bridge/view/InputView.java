package bridge.view;

import bridge.error.ErrorHandler;
import bridge.util.Validator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
    public static final String NOT_PROPER_BRIDGE_SIZE_MESSAGE = "다리 길이는 3부터 20 사이의 숫자여야 합니다.";
    public static final String NOT_PROPER_MOVING_MESSAGE = "U 또는 D를 입력해야 합니다.";
    public static final String NOT_PROPER_GAME_COMMAND_MESSAGE = "R 또는 Q를 입력해야 합니다.";

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        return (Integer) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println("다리의 길이를 입력해주세요.");
            String userInput = Console.readLine();
            validateBridgeSize(userInput);
            return userInput;
        });
    }

    private void validateBridgeSize(String userInput) {
        Validator.checkBlank(userInput, NOT_PROPER_BRIDGE_SIZE_MESSAGE);
        Validator.checkIsInteger(userInput, NOT_PROPER_BRIDGE_SIZE_MESSAGE);
        int size = Integer.parseInt(userInput);
        Validator.checkNumberInRange(size, 3, 20, NOT_PROPER_BRIDGE_SIZE_MESSAGE);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
            String userInput = Console.readLine();
            validateMoving(userInput);
            return userInput;
        });
    }

    private void validateMoving(String userInput) {
        Validator.checkBlank(userInput, NOT_PROPER_MOVING_MESSAGE);
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
            String userInput = Console.readLine();
            validateGameCommand(userInput);
            return userInput;
        });
    }

    private void validateGameCommand(String userInput) {
        Validator.checkBlank(userInput, NOT_PROPER_GAME_COMMAND_MESSAGE);
    }
}