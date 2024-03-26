package issue185.datamodel;

import org.eclipse.serializer.entity.Entity;
import org.eclipse.serializer.entity.EntityVersionCleaner;
import org.eclipse.serializer.entity.EntityVersionContext;


public interface EntityFactory {
    JulLogger logger = new JulLogger();
    EntityVersionContext<Integer> versionContext =   EntityVersionContext.AutoIncrementingInt(EntityVersionCleaner.AmountPreserving(10));

//    EntityVersionCleaner<Integer> cleaner = EntityVersionCleaner.AmountPreserving(10);

    static <E extends Entity, C extends Entity.Creator<E, C>> C addLayers(final C creator) {
//        return creator.addLayer(logger).addLayer(EntityVersionContext.AutoIncrementingInt(cleaner));
        return creator.addLayer(logger).addLayer(versionContext);
    }
}
