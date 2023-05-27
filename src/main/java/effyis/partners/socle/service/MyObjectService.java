package effyis.partners.socle.service;

import effyis.partners.socle.dto.MyObjectDto;
import effyis.partners.socle.entity.MyObject;

import java.util.List;

public interface MyObjectService {

    List<MyObjectDto> getAllObjects();
    MyObjectDto saveObject(MyObjectDto myObject);
    MyObjectDto saveObject();

}
