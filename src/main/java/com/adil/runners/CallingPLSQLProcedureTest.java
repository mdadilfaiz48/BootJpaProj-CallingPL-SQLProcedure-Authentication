package com.adil.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.adil.service.ILoginMgmtService;

@Component
public class CallingPLSQLProcedureTest implements CommandLineRunner {
    @Autowired
	private ILoginMgmtService service;
	
	@Override
	public void run(String... args) throws Exception {
		try {
		// invoke the method
		String msg = service.doLogin("raja", "rani1");
		System.out.println(msg);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
