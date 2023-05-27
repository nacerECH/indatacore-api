package effyis.partners.socle.controller;


import effyis.partners.socle.dto.MyObjectDto;
import effyis.partners.socle.service.MyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/indatacore/api/objects")
public class MyObjectContoller {

    @Autowired
    MyObjectService myObjectService;

    @GetMapping()
    public List<MyObjectDto> getAllObjects(){
        return myObjectService.getAllObjects() ;
    }

    @PostMapping()
    public MyObjectDto saveObject(@Valid @RequestBody MyObjectDto myObjectDto){
       return myObjectService.saveObject(myObjectDto);
    }

    @PostMapping("/random")
    public MyObjectDto saveRandomObject(){
        return myObjectService.saveObject();
    }

}
