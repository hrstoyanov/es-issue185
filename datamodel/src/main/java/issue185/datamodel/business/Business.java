package issue185.datamodel.business;

import issue185.datamodel.*;
import issue185.datamodel.business._Business.BusinessCreator;

public interface Business extends EntityBase, WithAddress, WithElectronicAddress {
    static BusinessCreator creator() {
        return EntityFactory.addLayers(BusinessCreator.New());
    }

    String description();

    String rawAddress();

    ContactPerson contactPerson();

}
