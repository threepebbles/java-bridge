package bridge.service;

import bridge.domain.BridgeMaker;
import bridge.domain.BridgeNumberGenerator;
import bridge.repository.BridgeRepository;
import java.util.List;

public class BridgeService {
    public static List<String> createBridge(int size, BridgeNumberGenerator bridgeNumberGenerator) {
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
        List<String> bridge = bridgeMaker.makeBridge(size);
        BridgeRepository.updateBridge(bridge);
        return getBridge();
    }

    public static List<String> getBridge() {
        return BridgeRepository.getBridge();
    }
}