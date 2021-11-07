package com.example.restservice;

/**
 * @author DucTruong
 *This class generate the initial request object which is then use to validate each providers and accountNumber
 */
public class Validate{

	private final String accountNumber;
	private final String[] providers;

	public Validate(String accountnum, String[] providerArray) {
		this.accountNumber = accountnum;
		this.providers = providerArray;
	}
	
	public Validate(String accountnum) {
		this.accountNumber = accountnum;
		this.providers = new String[0];
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String[] getProviders() {
		return providers;
	}
	
	public int getProvidersLength() {
		if(providers==null) {
			return 0;
		}
		else {
			return providers.length;
		}
	}
}