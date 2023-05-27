package effyis.partners.socle.service.implService;


import effyis.partners.socle.dto.MyObjectDto;
import effyis.partners.socle.repository.MyObjectRepository;
import effyis.partners.socle.service.MyObjectService;
import effyis.partners.socle.utils.mappers.MyObjectMapper;
import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyObjectImpService implements MyObjectService {
    @Autowired
    MyObjectRepository myObjectRepository;
    @Autowired
    MyObjectMapper myObjectMapper;
    @Override
    public List<MyObjectDto> getAllObjects() {
        return myObjectRepository.findAll().stream().map(obj->myObjectMapper.toObjectDto(obj)).collect(Collectors.toList());
    }
    @Override
    public MyObjectDto saveObject(MyObjectDto myObject) {
        return myObjectMapper.toObjectDto(myObjectRepository.save(myObjectMapper.toObjectEntity(myObject)));
    }
    @Override
    public MyObjectDto saveObject() {
        EasyRandom generator = new EasyRandom();
        MyObjectDto myObjectDto = generator.nextObject(MyObjectDto.class);
        return myObjectMapper.toObjectDto(myObjectRepository.save(myObjectMapper.toObjectEntity(myObjectDto)));
    }
}
