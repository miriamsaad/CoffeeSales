/**
 * 
 */
package com.promineotech.coffee.dao;

import java.math.BigDecimal;
import java.util.Optional;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Customer;
import com.promineotech.coffee.entity.Flavor;
import com.promineotech.coffee.entity.Size;
import com.promineotech.coffee.entity.Order;


public interface CoffeeOrderDao {

  Optional<Customer> fetchCustomer(String customer);
  Optional<Flavor> fetchFlavor(String flavorId);
  Optional<Size> fetchSize(CoffeeSize size);


  Order saveOrder(Customer customer, Flavor flavor, Size size, BigDecimal price);


}
