package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.models.OrderDetails;

@Repository
public class OrderRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	// TODO: Task 3

	public Order searchOrderByOrderId(String orderId) {
        // prevent inheritance
        final List<Order> orders = new LinkedList<>();
        // perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SEARCH_ORDER_BY_ORDER_ID, orderId);

        while (rs.next()) {
            orders.add(Order.create(rs));
        }
        return orders.get(0);
    }

	public boolean updateOrder(final Order order) {
        return jdbcTemplate.update(SQL_UPDATE_ORDER_BY_ORDER_ID,
                order.getDeliveryId(),
				order.getStatus(),
				order.getOrderId()
				) > 0;
    }

	// insert into orders table
	@Transactional
	public Order insertOrder(Order order) {
		//return jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), order.getDeliveryId(), order.getStatus(), order.getOrderDate()) > 0;

		KeyHolder keyholder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(conn -> {
                PreparedStatement ps = conn.prepareStatement(SQL_INSERT_ORDER,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getOrderId());
                ps.setString(2, order.getOrderDate().toString());
                return ps;
            }, keyholder);
            // BigInteger primaryKeyVal = (BigInteger) keyholder.getKey();
            // order.setOrderId(Integer.toString(primaryKeyVal.intValue()));
			addLineItems(order);

        } catch (DataIntegrityViolationException e) {
            Order existingOrder = searchOrderByOrderId(order.getOrderId());
			existingOrder.setDeliveryId(order.getDeliveryId());
			existingOrder.setStatus(order.getStatus());
			existingOrder.setOrderDate(order.getOrderDate());

            if (updateOrder(existingOrder))
                order.setOrderId(existingOrder.getOrderId());
        }

        return order;

	}

	// insert into line_item table
	@Transactional
	public void addLineItems(Order order) {
        addLineItems(order.getLineItems(), order.getOrderId());
    }

    public void addLineItems(List<LineItem> lineItems, String orderId) {
        List<Object[]> data = lineItems.stream()
                .map(li -> {
                    Object[] l = new Object[3];
                    l[0] = li.getItem();
                    l[1] = li.getQuantity();
                    l[2] = orderId;
                    return l;
                })
                .toList();

        // Batch update
        jdbcTemplate.batchUpdate(SQL_INSERT_LINE_ITEM, data);
    }

	public OrderDetails getNumberOfDispatchAndPending(String name) {
        // prevent inheritance
        final List<OrderDetails> ordDetails = new LinkedList<>();
        // perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_NUMBER_DISPATCHED_PENDING, name);

        while (rs.next()) {
            ordDetails.add(OrderDetails.create(rs));
        }
        return ordDetails.get(0);
    }

}
