package bridge.service;

import bridge.domain.Moving;
import bridge.domain.MovingResult;
import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final List<String> bridge;
    private List<MovingResult> result = new ArrayList<>();
    private int playerPosition;
    private int retryCount;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        playerPosition = 0;
        retryCount = 0;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(Moving moving) {
        boolean isSuccess = bridge.get(playerPosition)
                .equals(moving.getDirection());
        MovingResult movingResult = MovingResult.valueOf(moving, isSuccess);
        result.add(movingResult);
        playerPosition++;
        return isSuccess;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        result = new ArrayList<>();
        playerPosition = 0;
        retryCount++;
    }

    public boolean isEnd() {
        return playerPosition == bridge.size();
    }

    public List<MovingResult> getResult() {
        return result;
    }

    public int getRetryCount() {
        return retryCount;
    }
}