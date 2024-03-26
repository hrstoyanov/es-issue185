package issue185.utilities;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface CollectionUtils {

     static <T, C extends Collection<T>> Stream<T> ensureStream(final C collection) {
        return collection == null || collection.isEmpty()?
                 Stream.empty()
                :collection.stream();
    }

     static <T, C extends Collection<T>> Stream<T> ensureParallelStream(final C collection) {
        return collection == null ? Stream.empty() : collection.parallelStream();
    }

    static <E,T> Stream<T> toStream(Collection<E> source, Function<E,T> mapper, boolean distinct){
        return distinct?
                ensureStream(source).map(mapper).distinct()
                :ensureStream(source).map(mapper);
    }

     static <K, V extends Comparable<V>, M extends Map<K, V>> K maxKey(final M map) {
        final Map.Entry<K, V> max = map.entrySet()
                .parallelStream()
                .max((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .orElse(null);
        return max != null
                ? max.getKey()
                : null;
    }

    static <C extends Collection<E>,E> C add(C c,E e){
         c.add(e);
         return c;
    }

}

