package com.f1dot5.data.repository;

import com.f1dot5.data.CartArticle;
import com.f1dot5.data.SalesInvoice;
import com.f1dot5.data.CustomerDelivery;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        saveSalesInvoice(customerDeliveryId, customerDelivery.getCustomer(), customerDelivery.getSalesInvoice());

        return customerDelivery;
    }

    private long saveSalesInvoice(Long customerDeliveryId, String customer, SalesInvoice salesInvoice) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Sales_Invoice "
                                + "(customer_delivery, currency, total_price, created_at) "
                                + "values (?,?,?,?)",
                        Type.LONG, Types.VARCHAR, Types.FLOAT, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        salesInvoice.setCreatedAt(new Date());
        salesInvoice.setCustomerDelivery(customerDeliveryId);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                salesInvoice.getCustomerDelivery(),
                                salesInvoice.getCurrency(),
                                salesInvoice.getTotalPrice(),
                                salesInvoice.getCreatedAt())
                );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long salesInvoiceId = keyHolder.getKey().longValue();

        List<CartArticle> cartArticles = salesInvoice.getCartArticles();
        for (CartArticle cartArticle : cartArticles) {
            saveCartArticle(salesInvoiceId, customer, cartArticle);
        }

        return salesInvoiceId;
    }

    private long saveCartArticle(Long salesInvoiceId, String customer, CartArticle cartArticle) {
        cartArticle.setCreatedAt(new Date());
        cartArticle.setCustomer(customer);
        cartArticle.setSalesInvoice(salesInvoiceId);
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Cart_Article "
                                + "(article, sales_invoice, quantity, created_at, customer) "
                                + "values (?, ?, ?, ?, ?)",
                        Type.LONG, Type.LONG, Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                cartArticle.getArticle(),
                                cartArticle.getSalesInvoice(),
                                cartArticle.getQuantity(),
                                cartArticle.getCreatedAt(),
                                cartArticle.getCustomer()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long cartArticleId = keyHolder.getKey().longValue();

        return cartArticleId;
    }
}
