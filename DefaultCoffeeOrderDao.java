/**
 * 
 */
package com.promineotech.coffee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.coffee.entity.CoffeeSize;
import com.promineotech.coffee.entity.Customer;
import com.promineotech.coffee.entity.Flavor;
import com.promineotech.coffee.entity.Size;
import com.promineotech.coffee.entity.Order;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Component
public class DefaultCoffeeOrderDao implements CoffeeOrderDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Order saveOrder(Customer customer, Flavor flavor, Size size,
      BigDecimal price) {
    
   SqlParams params = generateInsertSql(customer, flavor, size, price);
   
   KeyHolder keyHolder = new GeneratedKeyHolder();
   jdbcTemplate.update(params.sql, params.source, keyHolder);
   
   Long orderPK = keyHolder.getKey().longValue();
   
   // @formatter:off
   return Order.builder()
       .orderPK(orderPK)
       .customer(customer)
       .flavor(flavor)
       .size(size)
       .price(price)
       .build();
       }
  //@formatter: on
  
 
  private SqlParams generateInsertSql(Customer customer, Flavor flavor, Size size,
      BigDecimal price) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders ("
        + "customer_fk, flavor_fk, size_fk, price"
        + ") VALUES ("
        + ":customer_fk, :flavor_fk, :size_fk, :price"
        + ")";
    // @formatter:on
    
  SqlParams params = new SqlParams();
    
    params.sql = sql;
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("color_fk", flavor.getFlavorPK());
    params.source.addValue("size_pk", size.getSizePK());
    params.source.addValue("price", price);
    
    return params;
  }

  
  @Override
  public Optional<Customer> fetchCustomer(String customerId) {
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);
   
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }
  
  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Customer.builder()
          .customerId(rs.getString("customer_id"))
          .customerPK(rs.getLong("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .build();
      // @formatter:on

    }
  }
  
  @Override
  public Optional<Flavor> fetchFlavor(String flavorId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM flavors " 
        + "WHERE flavor_id = :flavor_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("flavor_id", flavorId);

    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new FlavorResultSetExtractor()));
  }
  
  class FlavorResultSetExtractor implements ResultSetExtractor<Flavor> {
    @Override
    public Flavor extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Flavor.builder()
          .flavor(rs.getString("flavor"))
          .flavorId(rs.getString("flavor_id"))
          .flavorPK(rs.getLong("flavor_pk"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on
    }
  }
  
  @Override
  public Optional<Size> fetchSize(CoffeeSize sizeId) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM sizes " 
        + "WHERE size_id = :size_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("size_id", sizeId);

    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new SizeResultSetExtractor()));
  }
  
  class SizeResultSetExtractor implements ResultSetExtractor<Size> {
    @Override
    public Size extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Size.builder()
          .sizePK(rs.getLong("size_id"))
          .basePrice(rs.getBigDecimal("price"))
          .build();
      // @formatter:on
    }
  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

}
