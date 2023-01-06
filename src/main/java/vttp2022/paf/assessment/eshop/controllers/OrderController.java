package vttp2022.paf.assessment.eshop.controllers;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.Order;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	//TODO: Task 3
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrderRepository orderRepo;


	@PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postOrder(@RequestBody String json) {
	
		Customer customer = null;
		Order order = null;
		Order orderResult = null;
		
		JsonObject resp;
		try {
			customer = Customer.create(json);
			Optional<Customer> opt = customerRepo.findCustomerByName(customer.getName());
			if (opt.isEmpty()) {
				return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.contentType(MediaType.APPLICATION_JSON)
					.body("{\"error\": \"Customer " + customer.getName() + " not found\"}");
			} 

			order = Order.create(json);
			orderResult = orderRepo.insertOrder(order);
			resp = Json.createObjectBuilder()
					.add("order_Id", orderResult.getOrderId())
					.build();

			return ResponseEntity
					.status(HttpStatus.CREATED)
					.contentType(MediaType.APPLICATION_JSON)
					.body(resp.toString());

        } catch (Exception e) {
            e.printStackTrace();
            resp = Json.createObjectBuilder()
                    .add("error", e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(resp.toString());
        }	
	}


	// @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> postOrder(@RequestBody String json) {
    //     Order order = null;
    //     Order orderResult = null;
    //     JsonObject resp;
    //     try {
    //         order = Order.create(json);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         resp = Json.createObjectBuilder()
    //                 .add("error", e.getMessage())
    //                 .build();
    //         return ResponseEntity.badRequest().body(resp.toString());
    //     }

    //     orderResult = orderRepo.insertOrder(order);
    //     resp = Json.createObjectBuilder()
    //             .add("rsvpId", orderResult.getOrderId())
    //             .build();

    //     return ResponseEntity
    //             .status(HttpStatus.CREATED)
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .body(resp.toString());
    // }


	
}
