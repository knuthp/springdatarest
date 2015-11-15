package com.knuthp.springdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@SpringBootApplication
public class SpringdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdataApplication.class, args);
	}

	@Bean
	public ResourceProcessor<Resource<Customer>> customerProcessor() {
		return new ResourceProcessor<Resource<Customer>>() {

			@Override
			public Resource<Customer> process(Resource<Customer> resource) {
				resource.add(ControllerLinkBuilder.linkTo(HelloController.class).withRel("processes"));
				return resource;
			}
		};
	}

	@Bean
	public CommandLineRunner initCustomers(CustomerRepository customerRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... arg0) throws Exception {
				Customer customer = new Customer();
				customer.setFirstName("Knut Helge");
				customer.setLastName("Pedersen");
				Membership membership = new Membership();
				membership.setName("AKortet");
				membership.setDescription("Aftenposten subscribers membership");
				Benefit aKortetTryvann = new Benefit();
				aKortetTryvann.setName("Tryvann");
				aKortetTryvann.setPercentOff("50% when buying two or more tickets");
				membership.addBenefit(aKortetTryvann);
				customer.addMembership("AKortet", membership);
				Membership dnt = new Membership();
				dnt.setName("DNT");
				dnt.setDescription("Den Norske Turistforeningen medlem");
				Benefit dntAntonSport = new Benefit();
				dntAntonSport.setName("AntonSport");
				dntAntonSport.setPercentOff("20% everything");
				dnt.addBenefit(dntAntonSport);
				customer.addMembership("DNT", dnt);
				customerRepository.save(customer);
			}
		};
	}
}
