package main;

public class MapTile {
    private String type;

    public MapTile() {
        type = "Default";
    }

    public final String getType() {
        return type;
    }

    public final void setType(final String type) {
        this.type = type;
    }
}
