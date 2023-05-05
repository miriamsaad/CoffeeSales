/**
 * 
 */
package com.promineotech.coffee.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long orderPK;
  private Customer customer;
  private Coffee coffee;
  private Flavor flavor;
  private Size size;
  private BigDecimal price;
  
  @JsonIgnore
  public Long getOrderPK() {
    return orderPK;
  }

}
