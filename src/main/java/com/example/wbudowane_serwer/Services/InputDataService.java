package com.example.wbudowane_serwer.Services;

import com.example.wbudowane_serwer.Entities.DataFromScanner;
import com.example.wbudowane_serwer.Repos.DataFromScanerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InputDataService {
    private final DataFromScanerRepository dataFromScanerRepository;

    public DataFromScanner addDataFromScanner(DataFromScanner toSave){
        return dataFromScanerRepository.save(toSave);
    }

    public List<DataFromScanner> getAllDataFromScanner(){
        return dataFromScanerRepository.getAllBy();
    }
}
