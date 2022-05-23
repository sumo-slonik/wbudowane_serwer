package com.example.wbudowane_serwer.Services;

import com.example.wbudowane_serwer.Entities.DataFromScanner;
import com.example.wbudowane_serwer.Repos.DataFromScanerReposytory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InputDataService {
    private final DataFromScanerReposytory dataFromScanerReposytory;

    public DataFromScanner addDataFromScanner(DataFromScanner toSave){
        return dataFromScanerReposytory.save(toSave);
    }

    public List<DataFromScanner> getAllDataFromScanner(){
        return dataFromScanerReposytory.getAllBy();
    }
}
