package bridge.domain;

public enum MovingResult {
    UP_SUCCESS(Moving.U, true),
    UP_FAIL(Moving.U, false),
    DOWN_SUCCESS(Moving.D, true),
    DOWN_FAIL(Moving.D, false);
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