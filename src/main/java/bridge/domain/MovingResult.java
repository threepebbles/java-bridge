package bridge.domain;

public enum MovingResult {
    UP_SUCCESS(Moving.UP, true),
    UP_FAIL(Moving.UP, false),
    DOWN_SUCCESS(Moving.DOWN, true),
    DOWN_FAIL(Moving.DOWN, false);
    private final Moving moving;
    private final boolean isSuccess;

    MovingResult(Moving moving, boolean isSuccess) {
        this.moving = moving;
        this.isSuccess = isSuccess;
    }

    public static MovingResult valueOf(Moving moving, boolean isSuccess) {
        for (MovingResult movingResult : values()) {
            if (movingResult.isSame(moving, isSuccess)) {
                return movingResult;
            }
        }
        return null;
    }

    public boolean isSame(Moving moving, boolean isSuccess) {
        return this.moving == moving && this.isSuccess == isSuccess;
    }

    public Moving getMoving() {
        return moving;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}