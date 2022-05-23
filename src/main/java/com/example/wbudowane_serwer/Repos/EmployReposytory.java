package com.example.wbudowane_serwer.Repos;

import com.example.wbudowane_serwer.Entities.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployReposytory extends JpaRepository<Employ,Long> {
    List<Employ> getAllBy();
}
