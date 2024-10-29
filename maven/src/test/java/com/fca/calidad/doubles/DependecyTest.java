package com.fca.calidad.doubles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DependecyTest {
	
	private Dependecy dependency;
	private SubDependecy sub;
	
	@BeforeEach
	void setup() {
		sub = mock(SubDependecy.class);
		dependency = new Dependecy(sub);
	}
	

	@Test
	void test() {
		//inicializacion
		when(sub.getClassName()).thenReturn("Hola, mock!");
		
		//Ejercicio
		String resultado = sub.getClassName();
		System.out.println(resultado);
		
		//verificacion
		assertEquals("Hola, mock!", resultado);
		//CHECAR EL DE LAS IMAGENES Y CAMBIAR CODIGO 
	}

}