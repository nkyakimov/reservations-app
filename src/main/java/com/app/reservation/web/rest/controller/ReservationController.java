package com.app.reservation.web.rest.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.app.reservation.domain.User;
import com.app.reservation.service.ReservationService;
import com.app.reservation.web.rest.resource.ReservationResource;
import com.app.reservation.web.rest.resource.ReservationSearchResource;
import com.app.reservation.web.rest.validation.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationResource> getReservations(ReservationSearchResource searchResource,
            @AuthenticationPrincipal User user) {
        return reservationService.get(searchResource, user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResource createReservation(
            @RequestBody @Validated(Validation.Create.class) ReservationResource reservationResource,
            @AuthenticationPrincipal User user) {
        return reservationService.create(reservationResource, user);
    }

    @PutMapping("/{id}")
    public ReservationResource updateReservation(
            @PathVariable Long id,
            @RequestBody @Validated(Validation.Update.class) ReservationResource reservationResource,
            @AuthenticationPrincipal User user) {
        return reservationService.update(id, reservationResource, user);
    }

    @DeleteMapping("/{id}")
    public ReservationResource deleteReservation(
            @PathVariable Long id,
            @AuthenticationPrincipal User user) {
        return reservationService.delete(id, user);
    }

}
