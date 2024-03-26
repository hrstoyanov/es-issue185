package issue185.utilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

public interface CloseUtils {

    /**
     * Quietly close a {@link java.lang.AutoCloseable} dealing with nulls and exceptions.
     *
     * @param closeable to be closed.
     */
    static void quietClose(final AutoCloseable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (final Throwable ignore) {
        }
    }

    /**
     * Close all closeables in closeables. All exceptions and nulls will be ignored.
     *
     * @param closeables to be closed.
     */
    static void quietCloseAll(final Collection<? extends AutoCloseable> closeables) {
        if (closeables == null || closeables.isEmpty()) {
            return;
        }

        for (var closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (final Throwable ignore) {
                }
            }
        }
    }

    /**
     * Close all closeables in closeables. All exceptions and nulls will be ignored.
     *
     * @param closeables to be closed.
     */
    static void quietCloseAll(final AutoCloseable... closeables) {
        if (closeables == null) {
            return;
        }

        for (final AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (final Throwable ignore) {
                }
            }
        }
    }

    /**
     * Close a {@link java.lang.AutoCloseable} dealing with nulls and exceptions.
     * This version re-throws exceptions as runtime exceptions.
     *
     * @param closeable to be closed.
     */
    static void close(final AutoCloseable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (final Throwable ex) {
            ExceptionUtils.rethrowUnchecked(ex);
        }
    }

    /**
     * Close all provided closeables. If any of them throw then throw that exception.
     * If multiple closeables throw an exception when being closed, then throw an exception that contains
     * all of them as suppressed exceptions.
     *
     * @param closeables to be closed.
     */
    static void closeAll(final Collection<? extends AutoCloseable> closeables) {
        if (closeables == null || closeables.isEmpty()) {
            return;
        }

        Throwable error = null;
        for (var closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (final Throwable ex) {
                    if (error == null) {
                        error = ex;
                    } else {
                        error.addSuppressed(ex);
                    }
                }
            }
        }

        if (error != null) {
            ExceptionUtils.rethrowUnchecked(error);
        }
    }

    /**
     * Close all provided closeables. If any of them throw then throw that exception.
     * If multiple closeables throw an exception when being closed, then throw an exception that contains
     * all of them as suppressed exceptions.
     *
     * @param closeables to be closed.
     */
    static void closeAll(final AutoCloseable... closeables) {
        if (closeables == null || closeables.length == 0) {
            return;
        }
        closeAll(Arrays.asList(closeables));
    }

    /**
     * Close a {@link java.lang.AutoCloseable} delegating exceptions to the {@link Consumer}.
     *
     * @param errorHandler to delegate exceptions to.
     * @param closeable    to be closed.
     */
    static void close(Consumer<Throwable> errorHandler, final AutoCloseable closeable) {
        try {
            if (null != closeable) {
                closeable.close();
            }
        } catch (final Throwable ex) {
            errorHandler.accept(ex);
        }
    }

    /**
     * Close all closeables and delegate exceptions to the {@link Consumer}.
     *
     * @param errorHandler to delegate exceptions to.
     * @param closeables   to be closed.
     */
    static void closeAll(Consumer<Throwable> errorHandler, final Collection<? extends AutoCloseable> closeables) {
        if (closeables == null || closeables.isEmpty()) {
            return;
        }

        for (var closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (final Throwable ex) {
                    errorHandler.accept(ex);
                }
            }
        }
    }

    /**
     * Close all closeables and delegate exceptions to the {@link Consumer}.
     *
     * @param errorHandler to delegate exceptions to.
     * @param closeables   to be closed.
     */
    static void closeAll(Consumer<Throwable> errorHandler,final AutoCloseable... closeables) {
        if (closeables == null) {
            return;
        }
        for (var closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (final Throwable ex) {
                    errorHandler.accept(ex);
                }
            }
        }
    }
}

