/**
 * 
 */
package com.promineotech.coffee.dao;

import java.util.List;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;

public interface CoffeeSalesDao {

  List<Size> fetchCoffee(CoffeeSize size, String flavor);

}
