package com.app.reservation.web.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResource {

    @NotBlank
    @Size(max = 50, min = 6)
    private String username;
    @Size(max = 80, min = 6)
    private String password;

}
