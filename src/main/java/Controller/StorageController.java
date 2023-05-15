package Controller;

import Service.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/storage")

public class StorageController {

    private final StorageServiceImpl storageService;

    public StorageController(@Qualifier("storageService") StorageServiceImpl storageService) {
        this.storageService= storageService;
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllStrings(){
            return new ResponseEntity<>(storageService.getList(), HttpStatus.I_AM_A_TEAPOT);
    }

    @PostMapping(value ="/add")
    public ResponseEntity<?> addToList(@RequestParam String str){
        storageService.addToList(str);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete/{str}")
    public ResponseEntity<Boolean> deleteFromList(@PathVariable String str){
        return new ResponseEntity<>(storageService.deleteStringFromList(str), HttpStatus.OK);
    }
    @GetMapping(value = "/get/{str}")
    public ResponseEntity<Boolean> isExist(@PathVariable String str){
        return  new ResponseEntity<>(storageService.isExistInList(str), HttpStatus.OK);
    }
    @GetMapping(value = "/cats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCats(){
        return new ResponseEntity<>(storageService.getCats(),HttpStatus.OK);
    }
}
