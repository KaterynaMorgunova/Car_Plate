package com.f1dot5.data.repository;

import com.f1dot5.data.CartArticle;
import com.f1dot5.data.CartOrder;
import com.f1dot5.data.Customer;
import com.f1dot5.data.CustomerDelivery;
import org.springframework.asm.Type;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerDeliveryRepository implements CustomerDeliveryRepository {
    private JdbcOperations jdbcOperations;

    public JdbcCustomerDeliveryRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public CustomerDelivery save(CustomerDelivery customerDelivery) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Customer_Delivery "
                                + "(delivery_name, delivery_street, delivery_city, "
                                + "delivery_state, delivery_zip, cc_number, "
                                + "cc_expiration, cc_cvv, created_at, customer) "
                                + "values (?,?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP,
                        Types.VARCHAR
                );
        pscf.setReturnGeneratedKeys(true);

        customerDelivery.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                customerDelivery.getDeliveryName(),
                                customerDelivery.getDeliveryStreet(),
                                customerDelivery.getDeliveryCity(),
                                customerDelivery.getDeliveryState(),
                                customerDelivery.getDeliveryZip(),
                                customerDelivery.getCcNumber(),
                                customerDelivery.getCcExpiration(),
                                customerDelivery.getCcCVV(),
                                customerDelivery.getCreatedAt(),
                                customerDelivery.getCustomer())
                        );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long customerDeliveryId = keyHolder.getKey().longValue();
        customerDelivery.setId(customerDeliveryId);

        saveCartOrder(customerDeliveryId, customerDelivery.getCustomer(), customerDelivery.getCartOrder());

        return customerDelivery;
    }

    private long saveCartOrder(Long customerDeliveryId, String customer, CartOrder cartOrder) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Cart_Order "
                                + "(customer_delivery, currency, total_price, created_at) "
                                + "values (?,?,?,?)",
                        Type.LONG, Types.VARCHAR, Types.FLOAT, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        cartOrder.setCreatedAt(new Date());
        cartOrder.setCustomerDelivery(customerDeliveryId);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                cartOrder.getCustomerDelivery(),
                                cartOrder.getCurrency(),
                                cartOrder.getTotalPrice(),
                                cartOrder.getCreatedAt())
                );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long cartOrderId = keyHolder.getKey().longValue();

        List<CartArticle> cartArticles = cartOrder.getCartArticles();
        for (CartArticle cartArticle : cartArticles) {
            saveCartArticle(cartOrderId, customer, cartArticle);
        }

        return cartOrderId;
    }

    private long saveCartArticle(Long cartOrderId, String customer, CartArticle cartArticle) {
        cartArticle.setCreatedAt(new Date());
        cartArticle.setCustomer(customer);
        cartArticle.setCartOrder(cartOrderId);
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Cart_Article "
                                + "(article, cart_order, quantity, created_at, customer) "
                                + "values (?, ?, ?, ?, ?)",
                        Type.LONG, Type.LONG, Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                cartArticle.getArticle(),
                                cartArticle.getCartOrder(),
                                cartArticle.getQuantity(),
                                cartArticle.getCreatedAt(),
                                cartArticle.getCustomer()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long cartArticleId = keyHolder.getKey().longValue();

        return cartArticleId;
    }
/*
    @Override
    public Optional<TacoOrder> findById(Long id) {
        try {
            TacoOrder order = jdbcOperations.queryForObject(
                    "select id, delivery_name, delivery_street, delivery_city, "
                            + "delivery_state, delivery_zip, cc_number, cc_expiration, "
                            + "cc_cvv, placed_at from Taco_Order where id=?",
                    (row, rowNum) -> {
                        TacoOrder tacoOrder = new TacoOrder();
                        tacoOrder.setId(row.getLong("id"));
                        tacoOrder.setDeliveryName(row.getString("delivery_name"));
                        tacoOrder.setDeliveryStreet(row.getString("delivery_street"));
                        tacoOrder.setDeliveryCity(row.getString("delivery_city"));
                        tacoOrder.setDeliveryState(row.getString("delivery_state"));
                        tacoOrder.setDeliveryZip(row.getString("delivery_zip"));
                        tacoOrder.setCcNumber(row.getString("cc_number"));
                        tacoOrder.setCcExpiration(row.getString("cc_expiration"));
                        tacoOrder.setCcCVV(row.getString("cc_cvv"));
                        tacoOrder.setPlacedAt(new Date(row.getTimestamp("placed_at").getTime()));
                        tacoOrder.setTacos(findTacosByOrderId(row.getLong("id")));
                        return tacoOrder;
                    }, id);
            return Optional.of(order);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    private List<Taco> findTacosByOrderId(long orderId) {
        return jdbcOperations.query(
                "select id, name, created_at from Taco "
                        + "where taco_order=? order by taco_order_key",
                (row, rowNum) -> {
                    Taco taco = new Taco();
                    taco.setId(row.getLong("id"));
                    taco.setName(row.getString("name"));
                    taco.setCreatedAt(new Date(row.getTimestamp("created_at").getTime()));
                    taco.setIngredients(findIngredientsByTacoId(row.getLong("id")));
                    return taco;
                },
                orderId);
    }

    private List<IngredientRef> findIngredientsByTacoId(long tacoId) {
        return jdbcOperations.query(
                "select ingredient from Ingredient_Ref "
                        + "where taco = ? order by taco_key",
                (row, rowNum) -> {
                    return new IngredientRef(row.getString("ingredient"));
                },
                tacoId);
    }
 */
}
