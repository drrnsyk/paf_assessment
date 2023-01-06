package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    
    public static final String SQL_SELECT_CUSTOMER_BY_NAME = "select name from customers where name = ?";

    public static String SQL_INSERT_ORDER = "insert into orders(order_id, delivery_id, status, order_date) values (?, ?, ?, ?)";

    public static final String SQL_SEARCH_ORDER_BY_ORDER_ID = "select order_id, delivery_id, status, order_date from orders where order_id = ?";

    public static final String SQL_UPDATE_ORDER_BY_ORDER_ID = "update orders set delivery_id = ?, status = ? , order_date = ? where order_id = ?";
    
    public static String SQL_INSERT_LINE_ITEM = "insert into line_item(item, quantity, order_id) values (?, ?, ?)";

}
