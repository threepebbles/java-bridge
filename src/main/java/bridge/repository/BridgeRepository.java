package bridge.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BridgeRepository {
    private static List<String> bridge = new ArrayList<>();

    public static List<String> getBridge() {
        return Collections.unmodifiableList(bridge);
    }

    public static void updateBridge(List<String> bridge) {
        BridgeRepository.bridge = new ArrayList<>(bridge);
    }
}