package com.backend.quizzz.repository;

import com.backend.quizzz.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query(value = "SELECT * FROM result r ORDER BY r.score DESC LIMIT :limit", nativeQuery = true)
    List<Result> findTopResultsByGivenCount(@Param("limit") int count);
}