package com.fca.calidad.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;
import com.fca.calidad.servicio.LoginService;

class LoginServiceTest {

    LoginService service;
    IDAOUser dao;

    @Test
    void logintest() {
        // Inicialización
        dao = mock(IDAOUser.class);
        service = new LoginService(dao);

        // Creamos un usuario de prueba
        User usuario = new User("Milca", "email@email.com", "Password");

        // Simulamos que el método findByUserName devuelve el usuario
        when(dao.findByUserName("Milca")).thenReturn(usuario);

        // Ejercicio: ejecutamos el login con el nombre de usuario "Milca" y contraseña incorrecta
        boolean result = service.login("Milca", "123456"); // Contraseña incorrecta

        // Verificación: comprobamos que el resultado es falso (login fallido)
        assertFalse(result, "El login debería fallar con una contraseña incorrecta");

        // Ejercicio: ejecutamos el login con el nombre de usuario "Milca" y la contraseña correcta
        result = service.login("Milca", "Password"); // Contraseña correcta

        // Verificación: comprobamos que el resultado es verdadero (login exitoso)
        assertTrue(result, "El login debería ser exitoso con las credenciales correctas");
    }
}

	

