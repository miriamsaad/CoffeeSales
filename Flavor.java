/**
 * 
 */
package com.promineotech.coffee.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Flavor {
  private Long flavorPK;
  private String flavorId;
  private String flavor;
  private BigDecimal price;
}
