package issue185.datamodel;

public interface WithName {
    String name();

    default String description() {
        return "";
    }
}
