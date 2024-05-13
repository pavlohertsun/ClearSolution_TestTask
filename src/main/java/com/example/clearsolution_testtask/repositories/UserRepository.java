package com.example.clearsolution_testtask.repositories;

import com.example.clearsolution_testtask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.birthDate BETWEEN :startDate AND :endDate")
    List<User> findAllInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
