package vttp2022.paf.assessment.eshop.models;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// DO NOT CHANGE THIS CLASS
public class Order {

	private String orderId;
	private String deliveryId;
	private String name;
	private String address;
	private String email;
	private String status;
	private Date orderDate = new Date();
	private List<LineItem> lineItems = new LinkedList<>();

	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return this.address; }
	public void setAddress(String address) { this.address = address; }

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }

	public String getStatus() { return this.status; }
	public void setStatus(String status) { this.status = status; }

	public Date getOrderDate() { return this.orderDate; }
	public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

	public Customer getCustomer() { 
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setEmail(email);
		return customer;
	}
	public void setCustomer(Customer customer) {
		name = customer.getName();
		address = customer.getAddress();
		email = customer.getEmail();
	}

	public List<LineItem> getLineItems() { return this.lineItems; }
	public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
	public void addLineItem(LineItem lineItem) { this.lineItems.add(lineItem); }

	
	// helper function, to read sql row set
	public static Order create(SqlRowSet rs) {
        Order order = new Order();
		order.setDeliveryId(rs.getString("delivery_id"));
		order.setStatus(rs.getString("status"));
        return order;
    }
	
	
	// helper function, to read json string and create order model
	public static Order create(String jsonStr) throws Exception {
        JsonReader reader = Json.createReader(
                new ByteArrayInputStream(jsonStr.getBytes()));
        return create(reader.readObject());
    }

	// helper function, to read json object and create order model
    private static Order create(JsonObject readObject) {
        Order order = new Order();
		order.setOrderId(UUID.randomUUID().toString().substring(0, 8));
		order.setName(readObject.getString("name"));
		// order.setAddress(readObject.getString("address"));
		// order.setEmail(readObject.getString("email"));
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.now();
		order.setOrderDate(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		
		List<LineItem> list = new LinkedList<>();
		JsonArray jsonArr = readObject.getJsonArray("lineItems");
		for (int i = 0; i < jsonArr.size(); i++) {
			LineItem lineItem = new LineItem();
			lineItem.setItem(jsonArr.getJsonObject(i).getString("item"));
			lineItem.setQuantity(jsonArr.getJsonObject(i).getInt("quantity"));
			list.add(lineItem);
		}
		
		order.setLineItems(list);
		
        return order;
    }
	
}

