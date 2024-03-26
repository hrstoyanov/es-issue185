package issue185.datamodel.user;

import issue185.datamodel.*;
import issue185.datamodel.user._User.UserCreator;
import org.eclipse.serializer.entity.Entity;

import java.util.List;
import java.util.UUID;

public interface User extends WithHumanName, WithId<UUID>, WithElectronicAddress, WithAudit, Entity {

    static UserCreator creator() {
        return EntityFactory.addLayers(UserCreator.New());
    }

    List<ElectronicAddress> otherElectronicAddresses();

    default ElectronicAddress primaryElectronicAddress(){
        return electronicAddress();
    }

    Object publicKey();

}
