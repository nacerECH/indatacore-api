package effyis.partners.socle.service.implService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import effyis.partners.socle.entity.MyObject;
import effyis.partners.socle.repository.MyObjectRepository;
import effyis.partners.socle.utils.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    @Autowired
    MyObjectRepository myObjectRepository ;

    public void save(MultipartFile file) {
        try {
            List<MyObject> tutorials = CSVHelper.csvToObjects(file.getInputStream());
            myObjectRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    public ByteArrayInputStream load() {
        List<MyObject> myObjectList = myObjectRepository.findAll();
        ByteArrayInputStream in = CSVHelper.objectsToCSV(myObjectList);
        return in;
    }

}