package jy;


public enum Type {

    CREATE("create","1"),
    SEARCH("search","2"),
    UPDATE("update","3"),
    DELETE("delete","4");

    private String typeKey;
    private String typeValue;

    private Type(String typeValue , String typeKey) {
        this.typeValue = typeValue;
        this.typeKey = typeKey;
    }

    public String getTypeValue() {
        return this.typeValue;
    }

}
