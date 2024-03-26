package issue185.datamodel.business;

import issue185.datamodel.EntityFactory;
import issue185.datamodel.business._Hotel.HotelCreator;

public interface Hotel extends Business {
    static HotelCreator creator() {
        return EntityFactory.addLayers(HotelCreator.New());
    }

    int stars();

    int rooms();

    int beds();
}
