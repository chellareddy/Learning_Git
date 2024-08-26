package com.eurochange.springboot.web.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {

	/* @Size(min = 1, max = 15) */
    @NotNull
    private String status;

	/* @Size(min = 1, max = 30) */
    @NotNull
    private String userName;
}
