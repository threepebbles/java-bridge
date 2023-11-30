package bridge.controller;

import bridge.domain.BridgeRandomNumberGenerator;
import bridge.error.ErrorHandler;
import bridge.service.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private BridgeGame bridgeGame;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> bridge = createBridge();
        startBridgeGame();
    }

    private List<String> createBridge() {
        int bridgeSize = inputView.readBridgeSize();
        return BridgeService.createBridge(bridgeSize, new BridgeRandomNumberGenerator());
    }

    private void startBridgeGame() {
        bridgeGame = new BridgeGame(inputView, outputView);
        bridgeGame.startGame();
        while (!bridgeGame.isEnd()) {
            boolean isRetry = requestGameCommand();
            if (!isRetry) {
                break;
            }
            bridgeGame.retry();
        }
        outputView.printResult(bridgeGame.getResult(), bridgeGame.isEnd(), bridgeGame.getRetryCount());
    }

    private Boolean requestGameCommand() {
        return (Boolean) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String command = inputView.readGameCommand();
            if (command.equals("R")) {
                return true;
            } else if (command.equals("Q")) {
                return false;
            }
            throw new IllegalArgumentException("올바르지 않은 재시작 여부 커맨드입니다.");
        });
    }
}