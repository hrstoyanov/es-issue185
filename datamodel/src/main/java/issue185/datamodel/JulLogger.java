package issue185.datamodel;

import org.eclipse.serializer.entity.Entity;
import org.eclipse.serializer.entity.EntityLogger;

import java.util.logging.Logger;

public class JulLogger implements EntityLogger {
    public JulLogger() {
        super();
    }

    @Override
    public void entityCreated(final Entity identity, final Entity data) {
        Logger.getLogger(identity.getClass().getName()).info(() -> STR."Entity created: \{data}");
    }

    @Override
    public void afterUpdate(final Entity identity, final Entity data, final boolean successful) {
        Logger.getLogger(identity.getClass().getName()).info(() -> STR."Entity updated: \{data}");
    }
}
