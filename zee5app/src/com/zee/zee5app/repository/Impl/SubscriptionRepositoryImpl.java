package com.zee.zee5app.repository.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.repository.SubscriptionRepository;


public class SubscriptionRepositoryImpl implements SubscriptionRepository {

	private List<Subscription> set = new ArrayList<>();
	
    private SubscriptionRepositoryImpl() {
		
	}
	
	private static SubscriptionRepository subscriptionRepository;
	public static SubscriptionRepository getInstance() {
		if(subscriptionRepository==null)
			subscriptionRepository = new SubscriptionRepositoryImpl();
		
		return subscriptionRepository;
	
	}
	@Override
	public String addSubscription(Subscription subscription) throws InvalidAmountException {
		// TODO Auto-generated method stub
		boolean result = this.set.add(subscription);
		return null;
		
	}
	
	@Override
	public String deleteSubscription(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Optional<Subscription> optional = this.getSubscriptionById(id);
		if (optional.isPresent()){
			boolean result = set.remove(optional.get());
		if(result) {
			return null;
		}
		else
			return("fail8");
	}
	return "fail8";
	}
	
	@Override
	public String modifySubscription(String id, Subscription subscription) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Subscription subscription2 = null;
		for (Subscription subscription : set) {
			if(subscription.getId().equals(id)) {
				subscription2 = subscription;
			    break;
		}
			
		}
	 return Optional.of(Optional.ofNullable(subscription2).orElseThrow(()-> new IdNotFoundException("subscription id not found")));
				
	}
	@Override
	public List<Subscription> getAllSubscription() {
		// TODO Auto-generated method stub
        List<Subscription> arrlist = new ArrayList(set);
        Collections.sort(arrlist);
        return arrlist;
	}
	
  
//	@Override
//	public String addSubscription(Subscription subscription) {
//		// TODO Auto-generated method stub
//		if(count == subscriptions.length-1) {
//			Subscription temp[] = new Subscription[subscriptions.length*4];
//		    System.arraycopy(subscription, 0, temp, 0, subscriptions.length);
//		    subscriptions = temp;
//		    subscriptions[++count] = subscription;
//		    
//		    return "success1";
//		}
//		subscriptions[++count] = subscription;
//	    return "success1";
//	}
//
//	@Override
//	public String deleteSubscription(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String modifySubscription(String id, Subscription subscription) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Subscription getSubscriptionById(String id) {
//		// TODO Auto-generated method stub
//		for (Subscription subscription : subscriptions) {
//			if(subscription!=null && subscription.getId().equals(id))
//				return subscription;		
//		}
//		return null;
//	}
//
//	@Override
//	public Subscription[] getAllSubscription() {
//		// TODO Auto-generated method stub
//		return subscriptions;
//	}

}
