package slotegrator.project;

public enum PathEnum {
    TOKEN("/v2/oauth2/token"),
    PLAYERS("/v2/players");

    private final String path;

    PathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
