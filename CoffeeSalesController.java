/**
 * 
 */
package com.promineotech.coffee.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/coffee")
@OpenAPIDefinition(info = @Info(title = "Coffee Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local Server.")})

public interface CoffeeSalesController {
  // @formatter:off
  @Operation(
  summary = "Returns a list of Coffee",
  description = "Returns a list of coffee given an optional size and flavor.",
  responses = {
      @ApiResponse(
          responseCode = "200", 
          description = "A list of Coffee is returned.", 
          content = @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Size.class))),
      @ApiResponse(
          responseCode = "400", 
          description = "The request parameters are invalid.", 
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "404", 
          description = "No Coffees were found with the input criteria.", 
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "500", 
          description = "An unplanned error occured.", 
          content = @Content(mediaType = "application/json"))
  },
  parameters = {
      @Parameter(
          name = "size", 
          allowEmptyValue = false, 
          required = false, 
          description = "The size (i.e., 'SMALL')"),
      @Parameter(
          name = "flavor", 
          allowEmptyValue = false, 
          required = false, 
          description = "The flavor (i.e., 'VANILLA')")
      
  }
  )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Size> fetchCoffee(
      @RequestParam(required = false) CoffeeSize size,
      @RequestParam(required = false) String flavor);
  // @formatter:on
}
