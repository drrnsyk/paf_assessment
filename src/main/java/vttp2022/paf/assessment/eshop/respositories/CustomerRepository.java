package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;

@Repository
public class CustomerRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public Optional<Customer> findCustomerByName(String name) {
		
		// TODO: Task 3 
		final List<Customer> listOfCustomers = new LinkedList<>();
        // perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_SELECT_CUSTOMER_BY_NAME, name);

        while (rs.next()) {
            listOfCustomers.add(Customer.create(rs));
        }

		if (listOfCustomers.size() < 1) {
			return Optional.empty();
		} else {
			return Optional.of(listOfCustomers.get(0));
		}
	}
}
