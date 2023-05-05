/**
 * 
 */
package com.promineotech.coffee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import com.promineotech.coffee.entity.Order;
import com.promineotech.coffee.entity.OrderRequest;

@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Coffee Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local Server.")})

public interface CoffeeOrderController {
  
  // @formatter:off
  @Operation(
  summary = "Create an order for a coffee",
  description = "Returns the created coffee.",
  responses = {
      @ApiResponse(
          responseCode = "201", 
          description = "The created coffee is returned", 
          content = @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Order.class))),
      @ApiResponse(
          responseCode = "400", 
          description = "The request parameters are invalid.", 
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "404", 
          description = "A coffee component was not found with the input criteria.", 
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "500", 
          description = "An unplanned error occured.", 
          content = @Content(mediaType = "application/json"))
  },
  parameters = {
      @Parameter(
          name = "orderRequest", 
          allowEmptyValue = false, 
          required = true, 
          description = "The order as JSON")
      
  }
  )
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order fetchOrder(@RequestBody OrderRequest orderRequest);
     
}

  


