package br.com.locadora.exception;

import java.sql.SQLException;

public class RuntimeException extends Exception{

	public RuntimeException(String string, Exception e) {
		super(string, e);
	}


}
