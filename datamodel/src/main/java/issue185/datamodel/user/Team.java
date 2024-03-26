package issue185.datamodel.user;


import issue185.datamodel.EntityFactory;
import issue185.datamodel.WithAudit;
import issue185.datamodel.WithId;
import issue185.datamodel.WithName;
import issue185.datamodel.business.Business;
import issue185.datamodel.user._Team.TeamCreator;
import org.eclipse.serializer.entity.Entity;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static issue185.utilities.CollectionUtils.ensureStream;
import static issue185.utilities.CollectionUtils.toStream;

public interface Team extends WithId<UUID>, WithName, WithAudit, Entity {

    static TeamCreator creator() {
        return EntityFactory.addLayers(TeamCreator.New());
    }

    default Stream<User> usersStream(){
        return toStream(roleAssignments(), RoleAssignment::user, true);
    }

    default Stream<Role> rolesStream(){
        return toStream(roleAssignments(), RoleAssignment::role, true);
    }

    default Stream<RoleAssignment> roleAssignmentsStream(){
       return  ensureStream(roleAssignments());
    }

    Set<RoleAssignment> roleAssignments();

    Business business();

}
