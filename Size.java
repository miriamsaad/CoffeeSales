/**
 * 
 */
package com.promineotech.coffee.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Size {
  private Long sizePK;
  private CoffeeSize sizeId;
  private BigDecimal basePrice;
}
