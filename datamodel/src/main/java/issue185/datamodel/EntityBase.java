package issue185.datamodel;

import org.eclipse.serializer.entity.Entity;

import java.util.Map;
import java.util.UUID;

public interface EntityBase extends WithId<UUID>, WithExternalId, WithName, WithAudit, Entity {
    Map<String,Object> additionalAttributes();
}
