package com.example.wbudowane_serwer.Controlers;

import com.example.wbudowane_serwer.Entities.CurrentLocation;
import com.example.wbudowane_serwer.Entities.DataFromScanner;
import com.example.wbudowane_serwer.Entities.Employee;
import com.example.wbudowane_serwer.Services.EmployeeService;
import com.example.wbudowane_serwer.Services.InputDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class InputDataController {
    private final EmployeeService employeeService;
    private final InputDataService inputDataService;

    @PostMapping("employee/save")
    public ResponseEntity<Employee> addEmploy(@RequestBody Employee toSave){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/employee/save").toUriString());
        Employee result = employeeService.saveEmployee(toSave);
        return ResponseEntity.created(uri).body(result);
    }

    @PostMapping("addNewRecord")
    public ResponseEntity<DataFromScanner> addNewRecord(@RequestBody DataFromScanner toSave){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/addNewRecord/").toUriString());
        toSave.setInputDate(System.currentTimeMillis());
        DataFromScanner result = inputDataService.addDataFromScanner(toSave);
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("employee")
    @CrossOrigin(origins = "http://localhost:3000/")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping("employee/currentLocation")
    public ResponseEntity<List<CurrentLocation>> getCurrentLocations() {
        List<Employee> employees = employeeService.getAllEmployees();
        HashMap<String, Employee> employeeHashMap = new HashMap<>();

        employees
            .forEach((employee) -> {
                employeeHashMap.put(employee.getCardId(), employee);
            });

        List<DataFromScanner> dataFromScanners = inputDataService.getAllDataFromScanner();
//        key: employ card number| value: list<room id>
        HashMap<String,List<String>> recordsPerEmployee = new HashMap<>();
        dataFromScanners.stream()
            .sorted((data1, data2) -> (-1)*data1.getInputDate().compareTo(data2.getInputDate()))
            .forEach((data)->{
                String cardId = data.getCardId();
                String roomID =  data.getRoomId();

                if (recordsPerEmployee.get(cardId) != null) {
                    recordsPerEmployee.get(cardId).add(roomID);
                }
                else {
                    recordsPerEmployee.put(cardId, new ArrayList<>());
                    recordsPerEmployee.get(cardId).add(roomID);
                }
            });

        List<CurrentLocation> currentLocations = new ArrayList<>();
        recordsPerEmployee.keySet().
            forEach((cardId) -> {

                if(recordsPerEmployee.get(cardId) != null) {

                    if(recordsPerEmployee.get(cardId).size() % 2 == 1)
                        currentLocations.add(new CurrentLocation(employeeHashMap.get(cardId), recordsPerEmployee.get(cardId).get(0)));
                }
            });

        return ResponseEntity.ok().body(currentLocations);
    }
}
