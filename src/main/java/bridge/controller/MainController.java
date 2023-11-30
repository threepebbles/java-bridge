package bridge.controller;

import bridge.domain.BridgeRandomNumberGenerator;
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
    }

    private List<String> createBridge() {
        int bridgeSize = inputView.readBridgeSize();
        return bridgeService.createBridge(bridgeSize, new BridgeRandomNumberGenerator());
    }
}