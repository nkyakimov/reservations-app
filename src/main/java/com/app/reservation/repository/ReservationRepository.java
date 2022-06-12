package com.app.reservation.repository;

import java.time.OffsetDateTime;
import java.util.List;

import com.app.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r " +
            "where (:name is null or r.name = :name) " +
            "and (:time is null or r.time = :time) " +
            "and (:people is null or r.numberOfPeople = :people)")
    List<Reservation> searchReservations(@Param("name") String name,
            @Param("time") OffsetDateTime time,
            @Param("people") Long people);

}
