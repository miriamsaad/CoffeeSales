/**
 * 
 */
package com.promineotech.coffee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;
import com.promineotech.coffee.service.CoffeeSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultCoffeeSalesController implements CoffeeSalesController {

  @Autowired
  private CoffeeSalesService coffeeSalesService;
  
  @Override
  public List<Size> fetchCoffee(CoffeeSize size, String flavor) {
    log.debug("size={}, flavor={}", size, flavor);
    return coffeeSalesService.fetchCoffee(size, flavor);
  }

}
