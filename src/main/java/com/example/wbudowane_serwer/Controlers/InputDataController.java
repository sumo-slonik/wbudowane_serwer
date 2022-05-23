package com.example.wbudowane_serwer.Controlers;

import com.example.wbudowane_serwer.Entities.ActualPlaces;
import com.example.wbudowane_serwer.Entities.DataFromScanner;
import com.example.wbudowane_serwer.Entities.Employ;
import com.example.wbudowane_serwer.Repos.DataFromScanerReposytory;
import com.example.wbudowane_serwer.Services.EmployService;
import com.example.wbudowane_serwer.Services.InputDataService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class InputDataController {
    private final EmployService employService;
    private final InputDataService inputDataService;

    @PostMapping("employ/save")
    public ResponseEntity<Employ> addEmploy(@RequestBody Employ toSave){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/employ/save").toUriString());
        Employ result = employService.saveEmploy(toSave);
        return ResponseEntity.created(uri).body(result);
    }

    @PostMapping("addNewRecord")
    public ResponseEntity<DataFromScanner> addNewRecord(@RequestBody DataFromScanner toSave){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("addNewRecord/").toUriString());
        toSave.setInputDate(System.currentTimeMillis());
        DataFromScanner result = inputDataService.addDataFromScanner(toSave);
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("employ/actualPlace")
    public ResponseEntity<List<ActualPlaces>> getActualPlaces(){
        List<Employ> employs = employService.getAllEmploys();
        HashMap<String,Employ> employHashMap = new HashMap<>();
        List<DataFromScanner> dataFromScanners = inputDataService.getAllDataFromScanner();
//        key: employ card number| value: list<room id>
        HashMap<String,List<String>> recordsPerEmploy = new HashMap<>();
        dataFromScanners.stream()
            .sorted((data1, data2) -> data1.getInputDate().compareTo(data2.getInputDate()))
            .forEach((data)->{
                String cardId = data.getCardId();
                String roomID =  data.getRoomId();
                if (recordsPerEmploy.get(cardId).size() < 2){
                    recordsPerEmploy.get(cardId).add(roomID);
                }});

        recordsPerEmploy.keySet().stream(){

        }

    }
}
