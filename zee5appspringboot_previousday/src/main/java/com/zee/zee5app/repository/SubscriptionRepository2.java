package com.zee.zee5app.repository;
import com.zee.zee5app.dto.Subscription;

public class SubscriptionRepository2 {
	
	private Subscription[] subscriptions = new Subscription[10];
	private static int count = -1;
	
	private static SubscriptionRepository2 subscriptionRepository;
	public static SubscriptionRepository2 getInstance() {
		if(subscriptionRepository==null)
			subscriptionRepository = new SubscriptionRepository2();
		
		return subscriptionRepository;
	
	}

	public String addSubscription(Subscription subscription) {
		if(count == subscriptions.length-1) {
			Subscription temp[] = new Subscription[subscriptions.length*4];
		    System.arraycopy(subscription, 0, temp, 0, subscriptions.length);
		    subscriptions = temp;
		    subscriptions[++count] = subscription;
		    
		    return "success1";
		}
		subscriptions[++count] = subscription;
	    return "success1";
		
	}
	
	public String deleteSubscription(String id) {
		return null;
	}
	
	public String modifySubscription(String id, Subscription subscription) {
		return null;
	}
	
	public Subscription getSubscriptionById(String id) {
		for (Subscription subscription : subscriptions) {
			if(subscription!=null && subscription.getId().equals(id))
				return subscription;		
		}
		return null;
	}
	
	public Subscription[] getAllSubscription() {
		return subscriptions;
	}
	
	
}
