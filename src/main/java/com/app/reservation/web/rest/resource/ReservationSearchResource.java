package com.app.reservation.web.rest.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationSearchResource {

    private String name;
    private String time;
    private Long numberOfPeople;

}
