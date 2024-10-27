package com.fca.calidad.unitaria;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	
	

	

	@Test
	void suma2numerosPositivosTest() {
		//inicializacion
		double num1 = 2;
		double num2 = 4;
		double resEsperado= 6;
		Calculadora calculadora = new Calculadora();
		//Ejercicio, llamar al metodo que queremos acabar
		double resEjecucion= calculadora.suma(num1,num2);
		//Verificar
		assertThat(resEsperado, is(resEjecucion));
	}
	
	
	
	

}