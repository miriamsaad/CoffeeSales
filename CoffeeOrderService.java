/**
 * 
 */
package com.promineotech.coffee.service;

import com.promineotech.coffee.entity.Order;
import com.promineotech.coffee.entity.OrderRequest;

public interface CoffeeOrderService {

  Order createOrder(OrderRequest orderRequest);

}
