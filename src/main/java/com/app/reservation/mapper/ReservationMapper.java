package com.app.reservation.mapper;

import java.util.Optional;

import com.app.reservation.domain.Reservation;
import com.app.reservation.web.rest.resource.ReservationResource;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toEntity(ReservationResource reservationResource) {
        return Reservation.builder()
                .name(reservationResource.getName())
                .numberOfPeople(reservationResource.getNumberOfPeople())
                .time(reservationResource.getTime())
                .build();
    }

    public ReservationResource toResource(Reservation reservation) {
        return ReservationResource.builder()
                .id(reservation.getId())
                .name(reservation.getName())
                .numberOfPeople(reservation.getNumberOfPeople())
                .time(reservation.getTime())
                .build();
    }

    public Reservation update(Reservation reservation, ReservationResource reservationResource) {
        Optional.ofNullable(reservationResource.getTime()).ifPresent(reservation::setTime);
        Optional.ofNullable(reservationResource.getName()).ifPresent(reservation::setName);
        Optional.ofNullable(reservationResource.getNumberOfPeople()).ifPresent(reservation::setNumberOfPeople);

        return reservation;
    }

}
