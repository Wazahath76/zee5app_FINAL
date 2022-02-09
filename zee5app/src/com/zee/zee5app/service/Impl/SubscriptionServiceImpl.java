package com.zee.zee5app.service.Impl;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.service.SubscriptionService;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.repository.Impl.SubscriptionRepositoryImpl;

public class SubscriptionServiceImpl implements SubscriptionService {

	private SubscriptionRepository repository = SubscriptionRepositoryImpl.getInstance();
	private static SubscriptionService service;	
	
	public static SubscriptionService getInstance() {
		if(service == null)
			service = new SubscriptionServiceImpl();
		return service;
	}
	
    private SubscriptionServiceImpl() {
		
	}

	@Override
	public String addSubscription(Subscription subscription) throws InvalidAmountException {
		// TODO Auto-generated method stub
		return this.repository.addSubscription(subscription);
	}

	@Override
	public String deleteSubscription(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.deleteSubscription(id);
	}

	@Override
	public String modifySubscription(String id, Subscription subscription) {
		// TODO Auto-generated method stub
		return this.repository.modifySubscription(id, subscription);
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return this.repository.getSubscriptionById(id);
	}

	@Override
	public List<Subscription> getAllSubscription() {
		// TODO Auto-generated method stub
		return this.repository.getAllSubscription();
	}
	
//	@Override
//	public String addSubscription(Subscription subscription) {
//		// TODO Auto-generated method stub
//		return this.repository.addSubscription(subscription);
//	}
//
//	@Override
//	public Subscription getSubscriptionById(String id) {
//		// TODO Auto-generated method stub
//		return this.repository.getSubscriptionById(id);
//	}
//
//	@Override
//	public Subscription[] getAllSubscriptions() {
//		// TODO Auto-generated method stub
//		return repository.getAllSubscription();
//	}

}
