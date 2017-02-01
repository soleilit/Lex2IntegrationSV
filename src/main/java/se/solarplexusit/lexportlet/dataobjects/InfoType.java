package se.solarplexusit.lexportlet.dataobjects;

public enum InfoType {
    MEETING("Meeting"), CASE("Case"), DOCUMENT("Document");
    private String id;

    InfoType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
