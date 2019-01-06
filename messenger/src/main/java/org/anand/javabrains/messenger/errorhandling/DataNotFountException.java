package org.anand.javabrains.messenger.errorhandling;

public class DataNotFountException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotFountException(String exception){
		super(exception);
	}

}
