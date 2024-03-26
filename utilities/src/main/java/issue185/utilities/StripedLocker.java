package issue185.utilities;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class StripedLocker implements Locker {

    private final int stripes;
    private transient volatile ReferenceQueue<LockHolder> queue;
    private transient volatile ConcurrentHashMap<Integer, WeakReference<LockHolder>> locks;

    public StripedLocker(int stripes) {
        this.stripes = stripes;
    }

    public StripedLocker() {
        this(4);
    }

//    public final <T> T read(Object key, Callable<T> readOperation) throws Exception {
//        return execute(getLock(key).readLock(), readOperation);
//    }

    @Override
    public final <T> T read(Object key, Supplier<T> readOperation) {
        return execute(getLock(key).readLock(), readOperation);
    }

    @Override
    public void read(Object key, Runnable readOperation) {
        execute(getLock(key).readLock(), readOperation);
    }

//    public final <T> T write(Object key, Callable<T> writeOperation) throws Exception {
//        return execute(getLock(key).writeLock(), writeOperation);
//    }

    @Override
    public final <T> T write(Object key, Supplier<T> writeOperation) {
        return execute(getLock(key).writeLock(), writeOperation);
    }

    @Override
    public void write(Object key, Runnable writeOperation) {
        execute(getLock(key).writeLock(), writeOperation);
    }

    @Override
    synchronized public void clear(){
        ensureInitialized();
        locks.clear();
        while (queue.poll()!=null);
    }

    private ReentrantReadWriteLock getLock(Object keyObject) {
        requireNonNull(keyObject, "key can't be null");
        int key = keyObject.hashCode() % stripes;
        ensureInitialized();
        return requireNonNull(locks.compute(key, (_, currentReference) -> {
            if (currentReference == null) return new WeakReference<>(new LockHolder(key, new ReentrantReadWriteLock()), queue);
            var lockHolder = currentReference.get();
            if (lockHolder == null) return new WeakReference<>(new LockHolder(key, new ReentrantReadWriteLock()), queue);
            return currentReference;
        }).get()).lock();
    }

    private void ensureInitialized() {
        ReferenceQueue<LockHolder> queue = this.queue;
        if (queue == null) {
            synchronized (this) {
                //noinspection ReassignedVariable
                if ((queue = this.queue) == null) {
                    this.queue = new ReferenceQueue<>();
                    this.locks = new ConcurrentHashMap<>();
                }
            }
        }
    }

//    private <T> T execute(Lock theLock, Callable<T> operation) throws Exception {
//        var results = ReadWriteLocked.execute(theLock, operation);
//        purgeStaleLocks();
//        return results;
//    }

    private <T> T execute(Lock theLock, Supplier<T> operation) {
        var results = SingleLocker.execute(theLock, operation);
        purgeStaleLocks();
        return results;
    }

    private void execute(Lock theLock, Runnable operation) {
        SingleLocker.execute(theLock, operation);
        purgeStaleLocks();
    }

    @SuppressWarnings("unchecked")
    private void purgeStaleLocks() {
        Reference<LockHolder> reference;
        while ((reference = (Reference<LockHolder>) queue.poll()) != null) {
            //noinspection SynchronizeOnNonFinalField
            synchronized (queue) {
                locks.remove(requireNonNull(reference.get()).key());
            }
        }
    }

    private record LockHolder(int key, ReentrantReadWriteLock lock) {}

}
