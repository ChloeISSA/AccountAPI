package com.form3.client;

import java.util.List;
import java.util.UUID;

import io.swagger.api.ApiException;
import io.swagger.model.AccountsApi;
import io.swagger.model.AccountCreation;
import io.swagger.model.AccountCreationResponse;
import io.swagger.model.AccountDetailsListResponse;

public class ClientsApi {

	private static AccountsApi apiInstance = new AccountsApi();

	public static AccountsApi getDefaultAccountsApi() {
		return apiInstance;
	}

	public ClientsApi() {
		this(getDefaultAccountsApi());
	}

	public ClientsApi(AccountsApi accountsApi) {
		apiInstance = accountsApi;
	}

	public AccountsApi getAccountsApi() {
		return apiInstance;
	}

	public void setAccountsApi(AccountsApi accountsApi) {
		apiInstance = accountsApi;
	}

	// private String base_url = "https://api.staging-form3.tech/v1"; //TODO check
	// where it is going to be used and move to property file

	public AccountCreationResponse createAccount(AccountCreation accountCreationRequest) {
		AccountCreationResponse result = null;
		try {
			result = apiInstance.organisationAccountsPost(accountCreationRequest);
		} catch (ApiException e) {
			System.err.println("Exception when calling ClientsApi#createAccount");
			e.printStackTrace();
		}
		return result;
	}

	public AccountDetailsListResponse getAccounts(String pageNumber, Integer pageSize, List<UUID> filterOrganisationId,
			List<String> filterBankIdCode, List<String> filterBankId, List<String> filterAccountNumber,
			List<String> filterCountry, List<String> filterCustomerId, List<String> filterIban) {
		AccountDetailsListResponse result = null;
		try {
			result = apiInstance.organisationAccountsGet(pageNumber, pageSize, filterOrganisationId, filterBankIdCode,
					filterBankId, filterAccountNumber, filterCountry, filterCustomerId, filterIban);
			System.out.println(result);
		} catch (ApiException e) {
			System.err.println("Exception when calling ClientsApi#createAccount");
			e.printStackTrace();
		}
		return result;
	}

	public void deleteAccountByID(UUID id, Integer version) {
		try {
			apiInstance.organisationAccountsIdDelete(id, version);
		} catch (ApiException e) {
			System.err.println("Exception when calling ClientsApi#createAccount");
			e.printStackTrace();
		}
	}

}
