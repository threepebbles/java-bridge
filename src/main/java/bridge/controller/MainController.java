package bridge.controller;

import bridge.domain.BridgeRandomNumberGenerator;
import bridge.domain.Moving;
import bridge.error.ErrorHandler;
import bridge.service.BridgeGame;
import bridge.service.BridgeService;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private BridgeService bridgeService;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> bridge = createBridge();
        startBridgeGame(bridge);
    }

    private List<String> createBridge() {
        int bridgeSize = inputView.readBridgeSize();
        return bridgeService.createBridge(bridgeSize, new BridgeRandomNumberGenerator());
    }

    private void startBridgeGame(List<String> bridge) {
        BridgeGame bridgeGame = new BridgeGame(bridge);
        while (bridgeGame.isEnd()) {
            Moving moving = requestMoving();
            if (!bridgeGame.move(moving.getDirection())) {
                // outputView.printResult(bridgeGame.getResult());
                bridgeGame.retry();
            }
        }
    }

    private Moving requestMoving() {
        return (Moving) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String moving = inputView.readMoving();
            return Moving.valueOf(moving);
        });
    }
}