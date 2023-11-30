package bridge.domain;

public enum Moving {
    U("U"),
    D("D");
    private final String direction;

    Moving(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}