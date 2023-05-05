/**
 * 
 */
package com.promineotech.coffee.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.coffee.dao.CoffeeOrderDao;
import com.promineotech.coffee.entity.Customer;
import com.promineotech.coffee.entity.Flavor;
import com.promineotech.coffee.entity.Order;
import com.promineotech.coffee.entity.OrderRequest;
import com.promineotech.coffee.entity.Size;


@Service
public class DefaultCoffeeOrderService implements CoffeeOrderService {

  @Autowired
  private CoffeeOrderDao coffeeOrderDao;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    Customer customer = getCustomer(orderRequest);
    Flavor flavor = getFlavor(orderRequest);
    Size size = getSize(orderRequest);
     
    BigDecimal price = size.getBasePrice().add(flavor.getPrice());
    
     
    return coffeeOrderDao.saveOrder(customer,flavor, size, price);
   
  }

  private Customer getCustomer(OrderRequest orderRequest) {
    return coffeeOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID=" 
            + orderRequest.getCustomer() + " was not found"));
  }

  private Flavor getFlavor(OrderRequest orderRequest) {
    return coffeeOrderDao.fetchFlavor(orderRequest.getFlavor())
        .orElseThrow(() -> new NoSuchElementException("Flavor with ID=" 
            + orderRequest.getFlavor() + " was not found"));
  }

  private Size getSize(OrderRequest orderRequest) {
    return coffeeOrderDao.fetchSize(orderRequest.getSize())
        .orElseThrow(() -> new NoSuchElementException("Size with ID=" 
            + orderRequest.getSize() + " was not found"));
  }
  
}
