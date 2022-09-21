package slotegrator.project.dictionaries;

public enum TimeoutEnum {
    TIME_OUT_IN_SECONDS_FOR_FIND_WEB_ELEMENT(10L);

    private final long time;

    TimeoutEnum(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
