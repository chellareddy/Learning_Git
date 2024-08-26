package com.eurochange.springboot.web.swagger;


import com.eurochange.springboot.web.model.EchoModel;
import com.eurochange.springboot.web.model.ErrorModel;
import com.eurochange.springboot.web.model.ResponseModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "XTRIS Authentication Service", description = "Service to authenticate users with existing XTRIS credentials.")
public interface EchoControllerSwagger {

    @Operation(operationId = "EchoController", description = "Takes a username and password and returns a status indicating whether the details are correct.", summary = "Authenticate a user with XTRIS credentials ")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseModel.class))),
    	    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorModel.class))),

    	})
	/*
	 * @ApiResponse(responseCode = "200", content = @Content(mediaType =
	 * "application/json", schema = @Schema(implementation = EchoModel.class)))
	 */
    public ResponseModel echoModel(@RequestBody @Valid EchoModel model);
}
