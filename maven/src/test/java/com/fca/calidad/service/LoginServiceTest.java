package com.fca.calidad.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;
import com.fca.calidad.servicio.LoginService;

	class LoginServiceTest{
		
		LoginService service;
		IDAOUser dao;
		
		

	@Test
	void logintest() {
		//Inicializacion
		dao = mock (IDAOUser.class);
		service = new LoginService (dao);
User usuario = new User("Nombre1","email@email.com", "password");
		
		when (dao.findByUserName("Nombre1")).thenReturn(usuario);
		
		//ejercicio
		boolean result =service.login("Nombre1", "123456");
		
		//verific
		asserThat(result,is(true));
		
						
	}



	private void asserThat(boolean result, Object object) {
		// TODO Auto-generated method stub
		
	}



	private Object is(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}
	}

