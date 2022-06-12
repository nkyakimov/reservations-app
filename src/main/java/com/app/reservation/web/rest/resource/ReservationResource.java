package com.app.reservation.web.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

import com.app.reservation.configuration.DateDeserializer;
import com.app.reservation.configuration.DateSerializer;
import com.app.reservation.web.rest.validation.Validation;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResource {

    @Null(groups = {Validation.Create.class, Validation.Update.class})
    private Long id;

    @NotBlank(groups = {Validation.Create.class})
    @Size(max = 250, groups = {Validation.Create.class, Validation.Update.class})
    private String name;

    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
    @NotNull(groups = {Validation.Create.class})
    private OffsetDateTime time;

    @NotNull(groups = {Validation.Create.class})
    @Positive(groups = {Validation.Create.class, Validation.Update.class})
    private Long numberOfPeople;

}
