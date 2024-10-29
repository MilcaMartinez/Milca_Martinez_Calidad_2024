package com.fca.calidad.doubles;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class DependecyTest {
   
   private Dependecy dependency;
   private SubDependecy sub;
   
   @BeforeEach
   void setup() {
       sub = mock(SubDependecy.class);
       dependency = mock(Dependecy.class);
       when(sub.getClassName()).thenReturn("Hola, mock"); // Mockeando el método
   }
   
   @Test
   void AnswerTest() {
       // inicializacion
       when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
           public Integer answer(InvocationOnMock invocation) throws Throwable {
               int arg = (Integer) invocation.getArguments()[0];
               return 20 * 2 + 10 + arg; // 40 + 10 + arg
           }
       });
       
       // Ejercicio
       int resultado = dependency.addTwo(5); // Resultado será 55
       assertThat(resultado, is(55)); // Verificando que el resultado es 55
       
       // Verificación
       String resultadoSub = sub.getClassName(); // Obteniendo el resultado del mock
       assertEquals("Hola, mock", resultadoSub); // Verificando el resultado del mock
   }
}
