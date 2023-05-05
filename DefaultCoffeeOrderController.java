/**
 * 
 */
package com.promineotech.coffee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.coffee.entity.Order;
import com.promineotech.coffee.entity.OrderRequest;
import com.promineotech.coffee.service.CoffeeOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCoffeeOrderController implements CoffeeOrderController {

  @Autowired
  private CoffeeOrderService coffeeOrderService;
  
  @Override
  public Order fetchOrder(OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return coffeeOrderService.createOrder(orderRequest);
  }

}
