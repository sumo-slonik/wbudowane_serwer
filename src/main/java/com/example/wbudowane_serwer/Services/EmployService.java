package com.example.wbudowane_serwer.Services;

import com.example.wbudowane_serwer.Entities.Employ;
import com.example.wbudowane_serwer.Repos.EmployReposytory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployService {

    private final EmployReposytory employReposytory;

    public Employ saveEmploy(Employ toSave){
        return employReposytory.save(toSave);
    }

    public List<Employ> getAllEmploys(){
        return employReposytory.getAllBy();
    }
}
