package com.zup.zupProgress.repositories;

import com.zup.zupProgress.model.InstrutorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrutorRepository extends JpaRepository<InstrutorModel,Long> {
}
