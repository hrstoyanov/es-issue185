package issue185.datamodel;

import issue185.datamodel.business.Hotel;
import issue185.datamodel.user.Role;
import issue185.datamodel.user.RoleAssignment;
import issue185.datamodel.user.Team;
import issue185.datamodel.user.User;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;

import static issue185.utilities.CollectionUtils.add;


public class TestTeamUserRole {

    @Test
    void testRoleAssignment() {

        var auditTrace1 = new WithAudit.Trace("me", Instant.now(), "192.168.0.0", "iPad");

        User user1 = User.creator()
                .id(UUID.randomUUID())
                .trace(auditTrace1)
                .firstName("Michael")
                .middleName("Lennon")
                .lastName("Presley")
                .create();

        Hotel hotel = Hotel.creator()
                .id(UUID.randomUUID())
                .trace(auditTrace1)
                .name("Hotel California")
                .beds(1)
                .stars(1)
                .rooms(2)
                .create();

        Team team1 = Team.creator()
                .id(UUID.randomUUID())
                .trace(auditTrace1)
                .name("team 1")
                .business(hotel)
                .roleAssignments(add(new HashSet<>(), RoleAssignment.creator()
                        .id(UUID.randomUUID())
                        .trace(auditTrace1)
                        .role(Role.OWNER)
                        .user(user1)
                        .create()))
                .create();

    }

}
