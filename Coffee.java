/**
 * 
 */
package com.promineotech.coffee.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
  private Long sizePK;
  private CoffeeSize sizeId;
  private String flavor;
  private BigDecimal price;
  
@JsonIgnore
public Long getSizePK() {
  return sizePK;
}

}
