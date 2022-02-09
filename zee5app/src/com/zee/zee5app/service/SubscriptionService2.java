package com.zee.zee5app.service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.repository.SubscriptionRepository2;

public class SubscriptionService2 {
	private SubscriptionRepository2 repository = SubscriptionRepository2.getInstance();
	
	private static SubscriptionService2 service = null;
	
	public static SubscriptionService2 getInstance() {
		if (service ==null)
			service = new SubscriptionService2();
		return service;
	}
	
	public String addSubscription(Subscription subscription) {
		return this.repository.addSubscription(subscription);
	}
	
	public Subscription getSubscriptionById(String id) {
		return this.repository.getSubscriptionById(id);
	}
	
	public Subscription[] getAllSubscriptions() {
		return repository.getAllSubscription();
	}
	

}
