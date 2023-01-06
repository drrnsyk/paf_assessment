package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static final String SQL_SELECT_CUSTOMER_BY_NAME = "select name from customers where name = ?";

    public static String SQL_INSERT_ORDER = "insert into orders(order_id, order_date) values (?, ?)";

    public static final String SQL_SEARCH_ORDER_BY_ORDER_ID = "select order_id, delivery_id, status from orders where order_id = ?";

    public static final String SQL_UPDATE_ORDER_BY_ORDER_ID = "update orders set delivery_id = ?, status = ? , order_date = ? where order_id = ?";
    
    public static String SQL_INSERT_LINE_ITEM = "insert into line_item(item, quantity, order_id) values (?, ?, ?)";

    public static final String SQL_GET_NUMBER_DISPATCHED_PENDING = """
            SELECT
                order_id,
                name,
                o.customer_id as customer_id,
                sum(od.quantity * od.unit_price) as total_price,
                sum(od.quantity * od.unit_price * od.discount) as discount,
                sum(od.quantity * od.unit_price) - sum(od.quantity * od.unit_price * od.discount) as discounted_price,
                sum(od.quantity * p.standard_cost) as cost_price
            FROM
                orders o
                LEFT JOIN order_details od
                ON o.id = od.order_id
                LEFT JOIN products p
                ON od.product_id = p.id
                WHERE o.id = ?
                """;

}
