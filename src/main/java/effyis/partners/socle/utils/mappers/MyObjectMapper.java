package effyis.partners.socle.utils.mappers;


import effyis.partners.socle.dto.MyObjectDto;
import effyis.partners.socle.entity.MyObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MyObjectMapper {


    public MyObjectDto toObjectDto(MyObject myObject){
        MyObjectDto myObjectDto = new MyObjectDto();
        BeanUtils.copyProperties(myObject, myObjectDto);
        return  myObjectDto;
    }

    public MyObject toObjectEntity(MyObjectDto myObjectDto){

        MyObject myObject = new MyObject();
        BeanUtils.copyProperties(myObjectDto,myObject);
        return  myObject;
    }
}
