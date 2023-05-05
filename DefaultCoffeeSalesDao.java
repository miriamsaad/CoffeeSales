/**
 * 
 */
package com.promineotech.coffee.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class DefaultCoffeeSalesDao implements CoffeeSalesDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Size> fetchCoffee(CoffeeSize size, String flavor) {
    log.debug("DAO: size={}, flavor={}", size, flavor);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM sizes "
        + "WHERE size_id = :size_id";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("size_id", size);
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      
      @Override
      public Size mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Size.builder()
            .basePrice(new BigDecimal(rs.getString("price")))
            .sizeId(CoffeeSize.valueOf(rs.getString("size_id")))
            .sizePK(rs.getLong("size_pk"))
            .build();
        // @formatter:on
      }
    });
  }

}
