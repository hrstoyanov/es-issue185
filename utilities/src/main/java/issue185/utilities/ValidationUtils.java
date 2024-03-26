package issue185.utilities;

import java.net.URI;
import java.util.Collection;
import java.util.function.Supplier;

public interface ValidationUtils {


    static boolean isEmptyOrNull(String s) {
        return s == null || s.isBlank();
    }

    static boolean isNotEmptyOrNull(String s) {
        return !isEmptyOrNull(s);
    }

    static String requireNonEmptyAndNonNull(String s, Supplier<String> message) {
        if (isEmptyOrNull(s)) throw new NullPointerException(getMessage(message));
        return s;
    }

    static String requireNonEmptyAndNonNull(String s, String message) {
        if (isEmptyOrNull(s)) throw new NullPointerException(message);
        return s;
    }

    static String requireNonEmptyAndNonNull(String s) {
        return requireNonEmptyAndNonNull(s, (String)null);
    }


    static long requirePositive(long l, Supplier<String> message) {
        if (l <= 0) throw new IllegalArgumentException(STR."Illegal positive value:\{l} \{getMessage(message)}");
        return l;
    }

    static long requirePositive(long l, String message) {
        if (l <= 0) throw new IllegalArgumentException(STR."Illegal positive value:\{l} \{message}");
        return l;
    }

    static long requirePositive(long l) {
        return requirePositive(l, (String)null);
    }

    static long requirePositiveOrZero(long l, Supplier<String> message) {
        if (l < 0) throw new IllegalArgumentException(STR."Illegal positive value:\{l} \{getMessage(message)}");
        return l;
    }

    static long requirePositiveOrZero(long l, String message) {
        if (l < 0) throw new IllegalArgumentException(STR."Illegal positive value:\{l} \{message}");
        return l;
    }

    static long requirePositiveOrZero(long l) {
        return requirePositiveOrZero(l, (String)null);
    }

    static String validateURI(String uri) {
        requireNonEmptyAndNonNull(uri);
        URI.create(uri);
        return uri;
    }

    static String validateURI(String uri, Supplier<String> message) {
        try {
            requireNonEmptyAndNonNull(uri, getMessage(message));
            new URI(uri);
            return uri;
        } catch (Exception e) {
            throw new IllegalArgumentException(STR."Invalid uri: \{uri}  \{getMessage(message)}");
        }
    }

    static String validateURI(String uri, String message) {
        try {
            requireNonEmptyAndNonNull(uri, message);
            new URI(uri);
            return uri;
        } catch (Exception e) {
            throw new IllegalArgumentException(STR."Invalid uri: \{uri}  \{message}");
        }
    }

    static <T> boolean isEmptyOrNull(T[] array) {
        return array == null || array.length == 0;
    }
    static boolean isEmptyOrNull(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    static <T> T[] requireNonEmpty(T[] c, Supplier<String> message) {
       if(isEmptyOrNull(c))
           throw new IllegalArgumentException(STR."Empty or null \{getMessage(message)}");
       return c;
    }

    static <T> T[] requireNonEmpty(T[] c, String message) {
        if(isEmptyOrNull(c))
            throw new IllegalArgumentException(STR."Empty or null \{(message)}");
        return c;
    }


    static <T> Collection<T> requireNonEmpty(Collection<T> c) {
        return requireNonEmpty(c, (String)null);
    }

    static <T> Collection<T> requireNonEmpty(Collection<T> c, Supplier<String> message) {
        if (c == null || c.isEmpty())
            throw new IllegalArgumentException(STR."Empty or null \{getMessage(message)}");
        return c;
    }

    static <T> Collection<T> requireNonEmpty(Collection<T> c, String message) {
        if (c == null || c.isEmpty())
            throw new IllegalArgumentException(STR."Empty or null \{message}");
        return c;
    }


    static String getMessage(Supplier<String> message) {
        return message != null ? message.get() : null;
    }

}
