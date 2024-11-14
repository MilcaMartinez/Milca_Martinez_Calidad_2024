package com.fca.calidad.servicio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.modelo.User;

class UserServiceTest {
    private UserService servicio;
    private IDAOUser dao;
    private HashMap<Integer, User> baseDatos;

    @BeforeEach
    void setup() {
        dao = mock(IDAOUser.class);
        servicio = new UserService(dao);
        baseDatos = new HashMap<>();
    }

    @Test
    void updatetest() {
        // Inicialización
        User usuarioViejo = new User("Usuario", "Correo", "contrasenia");
        usuarioViejo.setId(1);
        
        baseDatos.put(usuarioViejo.getId(), usuarioViejo);
        
        User usuarioNuevo = new User("nuevoUsuario", "correo", "nuevaContrasenia");
        usuarioNuevo.setId(1);

        // Mocking
        when(dao.findById(1)).thenReturn(usuarioViejo);
        
        // Se crea el mock para updateUser con un Answer
        when(dao.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User arg = (User) invocation.getArguments()[0];
                baseDatos.replace(arg.getId(), arg);
                return baseDatos.get(arg.getId());  // Retorna el usuario actualizado
            }
        });

        // Ejecución
        User result = servicio.updateUser(usuarioNuevo);

        // Verificación
        assertNotNull(result);
        assertEquals("nuevoUsuario", result.getName());
        assertEquals("nuevaContrasenia", result.getPassword());
    }

    @Test
    void deleteUserTest() {
        // Inicialización
        User usuarioEliminar = new User("usuario1", "correo", "contrasenia");
        usuarioEliminar.setId(1);
        
        // Agregar el usuario a la base de datos mock
        baseDatos.put(1, usuarioEliminar);
        
        // Mocking
        when(dao.deleteById(1)).thenAnswer(new Answer<Boolean>() {
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                Integer id = (Integer) invocation.getArguments()[0];
                baseDatos.remove(id);  // Elimina el usuario de baseDatos
                return true;  // Retorna que la eliminación fue exitosa
            }
        });
        
        // Ejecutar el método a probar
        boolean result = servicio.deleteUser(1);
        
        // Verificación: El resultado debe ser true y el usuario debe haber sido eliminado
        assertTrue(result, "El resultado de la eliminación debe ser true");
        assertFalse(baseDatos.containsKey(1), "El usuario con ID 1 debe haber sido eliminado de la base de datos");
    }
    @Test
    void createUserTest() {
    	 when(dao.findUserByEmail("nuevo@email.com")).thenReturn(null);
    	 
    	 when (dao.save(any(User.class))).thenAnswer(new Answer<Integer>() {
    		 @Override
             public Integer answer(InvocationOnMock invocation) throws Throwable {
                 User newUser = (User) invocation.getArguments()[0];
                 int newId = baseDatos.size() + 1;
                 newUser.setId(newId);
                 baseDatos.put(newId, newUser);
                 return newId;
             }
         });
    	 }
    

    
    @Test
    void findByIdTest() {
        // Inicialización
        User usuarioBuscar = new User("usaurio1", "correol", "contrasenia");
        usuarioBuscar.setId(1);
        
        // Agregar el usuario a la base de datos mock
        baseDatos.put(1, usuarioBuscar);
        
        // Configurar el comportamiento del mock para findById
        when(dao.findById(1)).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                Integer id = (Integer) invocation.getArguments()[0];
                return baseDatos.get(id);  // Retorna el usuario si existe en baseDatos
            }
        });
        
        // Ejecutar el método a probar
        User result = servicio.findUserById(1);
        
        // Verificar que el usuario encontrado es el correcto
        assertNotNull(result, "El usuario debería existir en la base de datos");
        assertEquals("usuario1", result.getName(), "El nombre del usuario debe coincidir");
        assertEquals("correo", result.getEmail(), "El email del usuario debe coincidir");
        assertEquals("contrasenia", result.getPassword(), "La contraseña del usuario debe coincidir");
        
        // Verificar que si buscamos un usuario con un ID no existente, retorne null
        when(dao.findById(2)).thenReturn(null);
        User resultNoExistente = servicio.findUserById(2);
        assertNull(resultNoExistente, "El usuario con ID 2 no debería existir");
    }
    @Test
    void findAllUsersTest() {
        baseDatos.put(1, new User("usuario1", "correo1", "contrasenia1"));
        baseDatos.put(2, new User("usuario2", "correo", "contrasenia2"));

        when(dao.findAll()).thenAnswer(new Answer<List<User>>() {
            @Override
            public List<User> answer(InvocationOnMock invocation) {
                return new ArrayList<>(baseDatos.values());
            }
        });

        List<User> users = servicio.findAllUsers();
        assertNotNull(users);
        assertThat(users.size(), is(2));
        assertThat(users.get(0).getName(), is("usuario1"));
        assertThat(users.get(0).getEmail(), is("correo1"));
        assertThat(users.get(0).getPassword(), is("contrasenia1"));
        assertThat(users.get(1).getName(), is("usuario2"));
        assertThat(users.get(1).getEmail(), is("correo2"));
        assertThat(users.get(1).getPassword(), is("contrasenia2"));
    }

	private Object is(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	private void assertThat(Object string, Object object) {
		// TODO Auto-generated method stub
		
	}

	private Object is(String striig) {
		// TODO Auto-generated method stub
		return null;
	}
}




