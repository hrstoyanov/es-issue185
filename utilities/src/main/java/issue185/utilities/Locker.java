package issue185.utilities;

import java.util.function.Supplier;

public interface Locker {
    <T> T read(Object key, Supplier<T> readOperation);

    void read(Object key, Runnable readOperation);

    <T> T write(Object key, Supplier<T> writeOperation);

    void write(Object key, Runnable writeOperation);

    void clear();
}
