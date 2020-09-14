/**
 * 
 */
package com.form3.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.swagger.model.Account;
import io.swagger.model.AccountAttributes;
import io.swagger.model.AccountCreation;
import io.swagger.model.AccountCreationResponse;
import io.swagger.model.AccountDetailsListResponse;

/**
 * @author
 *
 */
class ClientsApiTest {

	private final ClientsApi api = new ClientsApi();

	/**
	 * Test method for {@link com.form3.client.ClientsApi#createAccount()}.
	 */
	@Test
	void testCreateAccountNull() {
		AccountCreation accountCreationRequest = null;
		AccountCreationResponse response = api.createAccount(accountCreationRequest);
		assertNull(response);
	}

	/**
	 * Test method for {@link com.form3.client.ClientsApi#createAccount()}.
	 */
	@Test
	void testCreateAccountNotNull() {
		AccountCreation accountCreationRequest = new AccountCreation();
		Account account = new Account();
		AccountAttributes accountAttributes = new AccountAttributes();
		accountAttributes.setBankIdCode("GBDSC");
		accountAttributes.setBaseCurrency("GBP");
		accountAttributes.setCountry("GB");
		accountAttributes.setCustomerId("345");
		accountAttributes.setBankId("123456");
		accountAttributes.setBic("987654321");
		account.setId(UUID.fromString("101"));
		account.setOrganisationId(UUID.fromString("123"));
		accountCreationRequest.setData(account);
		
		AccountCreationResponse response = api.createAccount(accountCreationRequest);
		
		assertNotNull(response);
		Account createdAccount = response.getData();
		assertEquals(createdAccount.getOrganisationId(), UUID.fromString("987"));
		AccountAttributes attributes = createdAccount.getAttributes();
		assertNotNull(attributes);
		assertEquals(attributes.getBankIdCode(), "GBDSC");
		assertEquals(attributes.getCountry(), "GB");
		assertEquals(attributes.getBankId(), "123456");
		assertEquals(attributes.getBic(), "987654321");
	}

	/**
	 * Test method for {@link com.form3.client.ClientsApi#getAccounts()}.
	 */
	@Test
	void testGetAccounts() {

		String pageNumber = "2"; // String | Which page to select
		Integer pageSize = 5; // Integer | Number of items to select
		List<UUID> filterOrganisationId = Arrays.asList(UUID.fromString("123")); 
		List<String> filterBankIdCode = Arrays.asList("GBDSC"); 
		List<String> filterCountry = Arrays.asList("GB");
		List<String> filterCustomerId = Arrays.asList("345"); 
		List<String> filterBankId = Arrays.asList(); 		
		List<String> filterAccountNumber = Arrays.asList(); 		
		List<String> filterIban = Arrays.asList(); 		
		
		AccountDetailsListResponse response = api.getAccounts(pageNumber, pageSize, filterOrganisationId,
				filterBankIdCode, filterBankId, filterAccountNumber, filterCountry, filterCustomerId, filterIban);
		
		List<Account> data = response.getData();
		assertEquals(data.size(), 1);
		Account account = data.iterator().next();
		assertEquals(account.getId(), UUID.fromString("101"));
		assertEquals(account.getOrganisationId(), UUID.fromString("987"));
		AccountAttributes attributes = account.getAttributes();		
		assertEquals(attributes.getBankIdCode(), "GBDSC");
		assertEquals(attributes.getCountry(), "GB");
		assertEquals(attributes.getBankId(), "123456");
		assertEquals(attributes.getBic(), "987654321");
	}

	/**
	 * Test method for {@link com.form3.client.ClientsApi#deleteAccountByID()}.
	 */
	@Test
	void testDeleteAccountByID() {
		UUID id =  UUID.fromString("101");
		Integer version = null;
		api.deleteAccountByID(id, version);
	}

}
