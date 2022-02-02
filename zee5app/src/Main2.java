//package com.zee.zee5app;
//
//import com.zee.zee5app.dto.Register;
//import com.zee.zee5app.exception.InvalidIdLengthException;
//import com.zee.zee5app.exception.InvalidNameException;
//
//public class Main2 {
//	
//	public static void main(String[] args) {
//		
//		//we get different values of hash here coz  we have not overriden the hash method
//		// if we over ride the hash method, we get same value of hash
//		try {
//			Register register = new Register("ab0001","abhi","sharma","b32@gm","dfe22");
//			System.out.println(register);
//			System.out.println(register.toString());
//			System.out.println(register.hashCode());
//			
//			Register register2 = new Register("ab0001","abhi","sharma","b32@gm","dfe22");
//			System.out.println(register2);
//			System.out.println(register2.toString());
//			System.out.println(register2.hashCode());
//			
//		} catch (InvalidIdLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//
//}
