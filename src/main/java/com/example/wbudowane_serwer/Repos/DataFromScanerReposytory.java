package com.example.wbudowane_serwer.Repos;

import com.example.wbudowane_serwer.Entities.DataFromScanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataFromScanerReposytory extends JpaRepository<DataFromScanner, Long> {
    List<DataFromScanner> getAllBy();
}
