package com.zee.zee5app.repository.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidAmountException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.SubscriptionRepository;


@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private SubscriptionRepositoryImpl() throws IOException {
		
	}
	
	private static SubscriptionRepository repository;
	public static SubscriptionRepository getInstance() throws IOException {
		if(repository==null)
			repository = new SubscriptionRepositoryImpl();
		
		return repository;
	
	}

	
	@Override
	public String addSubscription(Subscription subscription) throws InvalidAmountException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertStatement = "insert into subscription"
				+"(id, dateofpurchase, amount, paymentmode, expirydate, status, type, autorenewal, regId)"
                +"values(?,?,?,?,?,?,?,?,?)";
		
        connection = dbUtils.getConnection();
        
        try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, subscription.getId());
			preparedStatement.setString(2, subscription.getDateOfPurchase());
			preparedStatement.setFloat(3, subscription.getAmount());
			preparedStatement.setString(4, subscription.getPaymentMode());
			preparedStatement.setString(5, subscription.getExpiryDate());
			preparedStatement.setString(6, subscription.getStatus());
			preparedStatement.setString(7, subscription.getType());
			preparedStatement.setString(8, subscription.getAutoRenewal());
			preparedStatement.setString(9, subscription.getRegId());
			
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "subscription added successfully";
			}
			else {
				connection.rollback();
				return "fail25";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail25";
			}
			
			e.printStackTrace();
			return "fail25";
		}
        finally {
        	dbUtils.closeConnection(connection);
        }
	}

	@Override
	public String deleteSubscription(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteStatement = "delete from subscription where id = ?";
		
        connection = dbUtils.getConnection();
        
        try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "subscription deleted successfully";
			}
			else {
				connection.rollback();
				return "fail27";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail27";
			}
			e.printStackTrace();
			return "fail27";
		}
        finally {
        	dbUtils.closeConnection(connection);
        }
	}

	@Override
	public String modifySubscription(String id, Subscription subscription) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateStatement = "UPDATE subscription SET amount = ?, paymentmode=? where id = ?";
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setFloat(1, subscription.getAmount());
			preparedStatement.setString(2, subscription.getPaymentMode());
			preparedStatement.setString(3, id);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "subscription updated successfully";
			}
			else {
				connection.rollback();
				return "fail28";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "fail28";
			}
			e.printStackTrace();
			return "fail28";
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidAmountException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String selectStatement = "select * from subscription where id=?";
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getString("id"));
				subscription.setDateOfPurchase(resultSet.getString("dateofpurchase"));
				subscription.setExpiryDate(resultSet.getString("expirydate"));
				subscription.setAmount(resultSet.getFloat("amount"));
				subscription.setPaymentMode(resultSet.getString("paymentmode"));
				subscription.setStatus(resultSet.getString("status"));
				subscription.setType(resultSet.getString("type"));
				subscription.setAutoRenewal(resultSet.getString("autorenewal"));
				subscription.setRegId(resultSet.getString("regId"));
				
				return Optional.of(subscription);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<Subscription>> getAllSubscription() throws InvalidIdLengthException, InvalidAmountException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Subscription> arraylist = new ArrayList<>();
		String selectStatement = "select * from subscription";
		
		
		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Subscription subscription = new Subscription();
				subscription.setId(resultSet.getString("id"));
				subscription.setDateOfPurchase(resultSet.getString("dateofpurchase"));
				subscription.setExpiryDate(resultSet.getString("expirydate"));
				subscription.setAmount(resultSet.getFloat("amount"));
				subscription.setPaymentMode(resultSet.getString("paymentmode"));
				subscription.setStatus(resultSet.getString("status"));
				subscription.setType(resultSet.getString("type"));
				subscription.setAutoRenewal(resultSet.getString("autorenewal"));
				subscription.setRegId(resultSet.getString("regId"));
				arraylist.add(subscription);
				//System.out.println(arraylist.size());
				
				
			}
			return Optional.ofNullable(arraylist);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return Optional.empty();
	}
   
	
	
	
	
//	private List<Subscription> set = new ArrayList<>();
//	
//    private SubscriptionRepositoryImpl() {
//		
//	}
//	
//	private static SubscriptionRepository subscriptionRepository;
//	public static SubscriptionRepository getInstance() {
//		if(subscriptionRepository==null)
//			subscriptionRepository = new SubscriptionRepositoryImpl();
//		
//		return subscriptionRepository;
//	
//	}
//	@Override
//	public String addSubscription(Subscription subscription) throws InvalidAmountException {
//		// TODO Auto-generated method stub
//		boolean result = this.set.add(subscription);
//		return null;
//		
//	}
//	
//	@Override
//	public String deleteSubscription(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Optional<Subscription> optional = this.getSubscriptionById(id);
//		if (optional.isPresent()){
//			boolean result = set.remove(optional.get());
//		if(result) {
//			return null;
//		}
//		else
//			return("fail8");
//	}
//	return "fail8";
//	}
//	
//	@Override
//	public String modifySubscription(String id, Subscription subscription) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	@Override
//	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException {
//		// TODO Auto-generated method stub
//		Subscription subscription2 = null;
//		for (Subscription subscription : set) {
//			if(subscription.getId().equals(id)) {
//				subscription2 = subscription;
//			    break;
//		}
//			
//		}
//	 return Optional.of(Optional.ofNullable(subscription2).orElseThrow(()-> new IdNotFoundException("subscription id not found")));
//				
//	}
//	@Override
//	public List<Subscription> getAllSubscription() {
//		// TODO Auto-generated method stub
//        List<Subscription> arrlist = new ArrayList(set);
//        Collections.sort(arrlist);
//        return arrlist;
//	}
//	
  
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
