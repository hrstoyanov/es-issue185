package issue185.datamodel;

import java.util.StringJoiner;

public interface WithHumanName {
    String firstName();
    String middleName();
    String lastName();
    String prefix();
    String suffix();
    default String fullName(){
        return new StringJoiner(" ",prefix(),suffix())
                .add(firstName())
                .add(middleName())
                .add(lastName())
                .toString();
    }
}
