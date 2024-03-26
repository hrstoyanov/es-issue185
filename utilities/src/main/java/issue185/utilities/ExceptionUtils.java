package issue185.utilities;



public interface ExceptionUtils {

    /**
     * Rethrow an {@link java.lang.Throwable} preserving the stack trace but making it unchecked.
     *
     * @param ex to be rethrown and unchecked.
     */
    static void rethrowUnchecked(final Throwable ex) {
        rethrow(ex);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void rethrow(final Throwable t) throws T {
        throw (T) t;
    }

}
