package issue185.utilities;

import java.util.Comparator;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public interface Range<T> extends Comparator<T> {

    Range<T> of(T start, T end);

    T start();

    T end();

    default Range<T> min(Range<T> r) {
        if (r == null || compare(start(), end()) < 0) return this;
        return r;
    }

    default Range<T> max(Range<T> r) {
        if (r == null || compare(start(), end()) > 0) return this;
        return r;
    }

    default boolean isPoint() {
        return start().equals(end());
    }

    default boolean contains(T t) {
        return t != null && compare(start(), t) <= 0 && compare(end(), t) >= 0;
    }

    default Optional<Range<T>> intersect(Range<T> r) {
        var s = compare(start(), r.start()) >= 0 ? start() : r.start();
        var e = compare(end(), r.end()) <= 0 ? end() : r.end();
        return compare(s, e) >= 0 ? Optional.of(of(s, e)) : Optional.empty();
    }

    default Range<T> union(Range<T> r) {
        var s = compare(start(), r.start()) <= 0 ? start() : r.start();
        var e = compare(end(), r.end()) >= 0 ? end() : r.end();
        return of(s, e);
    }

    default void validate() {
        requireNonNull(start(), "start date time cannot be null");
        requireNonNull(end(), "end date time cannot be null");
        if (compare(start(), end()) > 0)
            throw new IllegalArgumentException(STR."Start : \{start()} canot be less than end: \{end()}");
    }

    static <C extends Comparable<C>>  void validate(C start, C end){
        requireNonNull(start, "start date time cannot be null");
        requireNonNull(end, "end date time cannot be null");
        if (start.compareTo(end) > 0)
            throw new IllegalArgumentException(STR."Start : \{start} canot be less than end: \{end}");
    }

}
