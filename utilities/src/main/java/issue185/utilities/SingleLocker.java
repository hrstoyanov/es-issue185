package issue185.utilities;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class SingleLocker implements Locker {

    private transient volatile ReentrantReadWriteLock lock;

    public SingleLocker() {}

    private ReentrantReadWriteLock ensureLock() {
        ReentrantReadWriteLock lock = this.lock;
        if (lock == null) {
            synchronized (this) {
                if ((lock = this.lock) == null) {
                    lock = this.lock = new ReentrantReadWriteLock();
                }
            }
        }
        return lock;
    }

//    public final <T> T readWithException(Callable<T> readOperation) throws Exception {
//        return execute(lock().readLock(),readOperation);
//    }

    public final <T> T read(Supplier<T> readOperation){
        return execute(ensureLock().readLock(),readOperation);
    }

    public final void read(Runnable readOperation){
         execute(ensureLock().readLock(),readOperation);
    }

//    public final <T> T writeWithException(Callable<T> writeOperation) throws Exception {
//        return execute(lock().writeLock(),writeOperation);
//    }

    public final <T> T write(Supplier<T> writeOperation){
        return execute(ensureLock().writeLock(),writeOperation);
    }

    public final void write(Runnable writeOperation){
        execute(ensureLock().writeLock(),writeOperation);
    }

    static <T> T execute(Lock theLock, Callable<T> operation) throws Exception {
        theLock.lock();
        try {
            return operation.call();
        } finally {
            theLock.unlock();
        }
    }

    static <T> T execute(Lock theLock, Supplier<T> operation) {
        theLock.lock();
        try {
            return operation.get();
        } finally {
            theLock.unlock();
        }
    }

    static void execute(Lock theLock, Runnable operation) {
        theLock.lock();
        try {
            operation.run();
        } finally {
            theLock.unlock();
        }
    }

    @Override
    public <T> T read(Object key, Supplier<T> readOperation) {
        return read(readOperation);
    }

    @Override
    public void read(Object key, Runnable readOperation) {
         read(readOperation);
    }

    @Override
    public <T> T write(Object key, Supplier<T> writeOperation) {
        return write(writeOperation);
    }

    @Override
    public void write(Object key, Runnable writeOperation) {
        write(writeOperation);
    }

    @Override
    public void clear() {
        lock.readLock().unlock();
        lock.writeLock().unlock();
    }
}
