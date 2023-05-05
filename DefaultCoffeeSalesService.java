/**
 * 
 */
package com.promineotech.coffee.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.coffee.dao.CoffeeSalesDao;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultCoffeeSalesService implements CoffeeSalesService {

  @Autowired
  private CoffeeSalesDao coffeeSalesDao;
  
  @Override
  public List<Size> fetchCoffee(CoffeeSize size, String flavor) {
    log.info("The fetchCoffee method was called with size={} and flavor={}", size, flavor);
    return coffeeSalesDao.fetchCoffee(size, flavor);
  }

}
