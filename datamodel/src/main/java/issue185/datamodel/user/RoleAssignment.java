package issue185.datamodel.user;

import issue185.datamodel.EntityFactory;
import issue185.datamodel.WithAudit;
import issue185.datamodel.WithId;
import issue185.datamodel.user._RoleAssignment.RoleAssignmentCreator;
import org.eclipse.serializer.entity.Entity;

import java.util.UUID;

public interface RoleAssignment extends WithId<UUID>, WithAudit, Entity {

    static RoleAssignmentCreator creator() {
        return EntityFactory.addLayers(RoleAssignmentCreator.New());
    }

    User user();
    Role role();

}
