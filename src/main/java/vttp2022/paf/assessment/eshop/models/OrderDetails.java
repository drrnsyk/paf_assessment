package vttp2022.paf.assessment.eshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class OrderDetails {
    private String name;
    private Integer dispatched;
    private Integer pending;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDispatched() {
        return dispatched;
    }
    public void setDispatched(Integer dispatched) {
        this.dispatched = dispatched;
    }
    public Integer getPending() {
        return pending;
    }
    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public static OrderDetails create(SqlRowSet rs) {
        OrderDetails od = new OrderDetails();
        od.setName(rs.getString("name"));
        od.setDispatched(rs.getInt("dispatched"));
        od.setPending(rs.getInt("pending"));
        return od;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("name", getName())
                .add("dispatched", getDispatched())
                .add("pending", getPending())
                .build();
    }
}
