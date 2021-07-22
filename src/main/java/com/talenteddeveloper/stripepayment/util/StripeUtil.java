package com.talenteddeveloper.stripepayment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.talenteddeveloper.stripepayment.model.CustomerData;

@Component
public class StripeUtil {

	@Value("${stripe.apikey}")
	String stripeKey;

	public CustomerData getCustomer(String id) throws StripeException {
		Stripe.apiKey = stripeKey;

		Customer customer = Customer.retrieve(id);
		CustomerData data = setCustomerData(customer);
		return data;
	}
	
	public CustomerData setCustomerData(Customer customer) {
		CustomerData customerData = new CustomerData();
		customerData.setCustomerId(customer.getId());
		customerData.setName(customer.getName());
		customerData.setEmail(customer.getEmail());
		
		return customerData;
	}
}
