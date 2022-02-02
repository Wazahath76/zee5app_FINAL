package com.zee.zee5app.repository.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;

@Repository
public class LoginRepositoryImpl implements LoginRepository {
	
//	DBUtils dbUtils = DBUtils.getInstance();
//    private LoginRepositoryImpl() throws IOException{
//		
//	}
//    
//    private static LoginRepository repository;
//	public static LoginRepository getInstance() throws IOException {
//		if(repository ==null)
//			//but we refer using interface only i.e.
//			//repository = new UserRepository()
//			// we can only access interface methods
//			
//			// this will use functionalities of the interface and both class only
//			repository = new LoginRepositoryImpl();
//		return repository;
//	}
	@Autowired
	DataSource dataSource;
	@Autowired
	private LoginRepository loginrepository;
	@Autowired
	private PasswordUtils passwordUtils;
	
	public LoginRepositoryImpl() throws IOException {
		
	}

	@Override
	public String addCredentials(Login login) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		
		String insertStatement = "insert into login"
				+"(userName, password, regId, role)"
				+"values(?,?,?,?)";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, login.getUserName());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setString(3, login.getRegId());
			preparedStatement.setString(4, login.getRole().toString());
			
			int result = preparedStatement.executeUpdate();
			//connection.commit();
			if(result>0) {
				connection.commit();
				return "success";
	}
			else {
				connection.rollback();
				return "fail12";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return "fail12";
	}

	@Override
	public String deleteCredentials(String userName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		String deleteStatement = "delete from login where username=?";
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(deleteStatement);
			preparedStatement.setString(1, userName);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "login record deleted";
			}
			else {
				connection.rollback();
				return "fail16";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return "fail16";
	}


	@Override
	public String changePassword(String userName, String password) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		String updateStatement = "UPDATE login SET password =? where userName = ?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			String salt = passwordUtils.getSalt(30);
			String encryptedPassword = passwordUtils.generateSecurePassword(password, salt);
			preparedStatement.setString(1, encryptedPassword);
			preparedStatement.setString(2, userName);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "login password changed successfully";
			}
			else {
				connection.rollback();
				return "fail17";
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	
		return "fail17";
		
	}

	@Override
	public String changeRole(String userName, ROLE role) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement;
		String updateStatement = "UPDATE login SET role =? where userName = ?";
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		try {
			preparedStatement = connection.prepareStatement(updateStatement);
			preparedStatement.setString(1, role.toString());
			preparedStatement.setString(2, userName);
			
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return "login role changed successfully";
	}
			else {
				connection.rollback();
				return "fail13";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				try {
					connection.rollback();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return "fail13";
	}

}
