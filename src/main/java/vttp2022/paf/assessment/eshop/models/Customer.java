package vttp2022.paf.assessment.eshop.models;

import java.io.ByteArrayInputStream;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

// DO NOT CHANGE THIS CLASS
public class Customer {

	private String name;
	private String address;
	private String email;

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return this.address; }
	public void setAddress(String address) { this.address = address; }

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }

	public static Customer create(SqlRowSet rs) {
        Customer customer = new Customer();
        customer.setName(rs.getString("name"));
        return customer;
    }

	public static Customer create(String jsonStr) throws Exception {
        JsonReader reader = Json.createReader(
                new ByteArrayInputStream(jsonStr.getBytes()));
        return create(reader.readObject());
    }

	// helper function, to read json object and create order model
    private static Customer create(JsonObject readObject) {
        final Customer customer = new Customer();
		customer.setName(readObject.getString("name"));
		// customer.setAddress(readObject.getString("address"));
		// customer.setEmail(readObject.getString("email"));
        return customer;
    }
}
