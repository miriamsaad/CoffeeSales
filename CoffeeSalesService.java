/**
 * 
 */
package com.promineotech.coffee.service;

import java.util.List;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;

public interface CoffeeSalesService {
  /**
   * @param size
   * @return
   */
  List<Size> fetchCoffee(CoffeeSize size, String flavor);

}
