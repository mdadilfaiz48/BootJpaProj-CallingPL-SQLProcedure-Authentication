package com.adil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Service
public class LoginMgmtServiceImpl implements ILoginMgmtService {
	
	@Autowired
	private EntityManager manager;
	
	/*CREATE PROCEDURE p_authentication(
		    IN uname VARCHAR(20),
		    IN pwd VARCHAR(50),
		    OUT result VARCHAR(50)
		)
		BEGIN
		    DECLARE cnt INT DEFAULT 0;

		    SELECT COUNT(*)
		    INTO cnt
		    FROM login_info
		    WHERE username = uname
		      AND password = pwd;

		    IF cnt > 0 THEN
		        SET result = 'Login Successful';
		    ELSE
		        SET result = 'Invalid Username or Password';
		    END IF;
		END;*/


	@Override
	public String doLogin(String username, String password) {
		
		// create stored procedure query object
		StoredProcedureQuery query = manager.createStoredProcedureQuery("p_authentication");
		// register the parameter
		 query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		 query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		 query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
		// set param values
		 query.setParameter(1, username);
		 query.setParameter(2, password);
		 // call PL/SQL Procedure
		 String message = (String) query.getOutputParameterValue(3);
		
		return message;
	}

}
