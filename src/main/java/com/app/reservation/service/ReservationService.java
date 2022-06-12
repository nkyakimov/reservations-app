package com.app.reservation.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.app.reservation.configuration.DateDeserializer;
import com.app.reservation.domain.Reservation;
import com.app.reservation.domain.User;
import com.app.reservation.exception.NotFoundException;
import com.app.reservation.mapper.ReservationMapper;
import com.app.reservation.repository.ReservationRepository;
import com.app.reservation.web.rest.resource.ReservationResource;
import com.app.reservation.web.rest.resource.ReservationSearchResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationResource create(ReservationResource reservationResource, User user) {
        Reservation reservation = reservationMapper.toEntity(reservationResource);
        reservation.setUser(user);

        return reservationMapper.toResource(reservationRepository.save(reservation));
    }

    public ReservationResource update(Long id, ReservationResource reservationResource, User user) {
        return reservationRepository.findById(id)
                .filter(reservation -> canViewReservation(reservation, user))
                .map(reservation -> reservationMapper.update(reservation, reservationResource))
                .map(reservationRepository::save)
                .map(reservationMapper::toResource)
                .orElseThrow(NotFoundException::new);
    }

    public ReservationResource delete(Long id, User user) {
        Reservation reservation = reservationRepository.findById(id)
                .filter(r -> canViewReservation(r, user))
                .orElseThrow(NotFoundException::new);

        reservationRepository.delete(reservation);
        return reservationMapper.toResource(reservation);
    }

    public List<ReservationResource> get(ReservationSearchResource searchResource, User user) {
        return reservationRepository.searchReservations(searchResource.getName(),
                        DateDeserializer.deserialize(searchResource.getTime()),
                        searchResource.getNumberOfPeople())
                .stream()
                .filter(reservation -> canViewReservation(reservation, user))
                .map(reservationMapper::toResource)
                .collect(Collectors.toList());
    }

    private boolean canViewReservation(Reservation reservation, User user) {
        return Objects.equals(reservation.getUser().getId(), user.getId());
    }

}
