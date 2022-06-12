package com.app.reservation.repository;

import java.util.Optional;

import com.app.reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

}
