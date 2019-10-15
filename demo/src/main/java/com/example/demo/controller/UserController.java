package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRest;
import com.example.demo.model.UpdateUserRestModel;
import com.example.demo.model.UserRestModel;
import com.example.demo.ws.userservice.UserService;


@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {
	
	// Mapa de datos
	private Map<String, UserRest> users = new HashMap<String, UserRest>();
	
	@Autowired
	UserService userService;

	
	/**
	 * Método GET para obtener datos en base al emailId ingresado
	 * @param page
	 * @param limit
	 * @return 
	 * @return 
	 */
	@GetMapping(path = "/{emailId}",
			produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	},
	consumes = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserRest> getUsers(@PathVariable String emailId){
		/*String firstName = null;
		int firstNameLength = firstName.length();*/
		//if(true) throw new UserServiceException("Ha ocurrido un error D:"); // Se invoca la excepción UserServiceException
		UserRest storedUserDetails = users.get(emailId); // Obtenemos los datos en base al emailId ingresado en URL
		return new ResponseEntity<UserRest>(storedUserDetails, HttpStatus.OK); // Retornamos los datos
	}
	
	
	/**
	 * Método GET para obtener datos del mapa
	 * @param page
	 * @param limit
	 * @return 
	 * @return 
	 */
	@GetMapping(produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	},
	consumes = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<Map<String, UserRest>> getUsers(){
		return new ResponseEntity<Map<String, UserRest>>(users, HttpStatus.OK); // Retornamos el Map completo
	}
	
	
	
	/**
	 * @param userId
	 * @return
	 */
	@GetMapping(path = "/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId){
		UserRest returnValue = new UserRest();
		returnValue.setEmail("rcruz@gmail.com");
		returnValue.setFirstname("Ricardo");
		returnValue.setLastName("Cruz");
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Método POST para actualizar datos en el mapa UserRestModel
	 * @param emailId
	 * @param userDetails
	 * @return
	 */
	@PostMapping(path = "/{emailId}",
			produces = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	},
	consumes = {
			MediaType.APPLICATION_XML_VALUE, 
			MediaType.APPLICATION_JSON_VALUE
	})
	public ResponseEntity<UserRest> updateUser(@PathVariable String emailId, @Valid @RequestBody UpdateUserRestModel userDetails){
		// Obtiene el email que coincida con el que es enviado en el URL
		UserRest storedUserDetails = users.get(emailId);
		// Se actualizan los datos del mapa
		storedUserDetails.setFirstname(userDetails.getFirstname());
		storedUserDetails.setLastName(userDetails.getLastName());
		// Agregamos la actualización al mapa
		users.put(emailId, storedUserDetails);
		return new ResponseEntity<UserRest>(storedUserDetails, HttpStatus.OK);
	}
	
	/**
	 * Método PUT para insertar datos a mapa UserRestModel
	 * @param userRestModel
	 * @return ResponseEntity<UserRest>(HttpStatus);
	 */
	@PutMapping(
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
			},
			consumes = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRestModel userRestModel){
		UserRest returnValue = userService.createUser(userRestModel);
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	/**
	 * Método DELETE para eliminar elemento de mapa UserRestModel
	 * @param emailId
	 * @return
	 */
	@DeleteMapping(path = "/{emailId}",
			produces = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
			},
			consumes = {
					MediaType.APPLICATION_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE
			}
	)
	public ResponseEntity<String> deleteUser(@PathVariable String emailId){
		// Eliminamos el elemento que coincida con el email del request
		users.remove(emailId);
		// Obtenemos el tamaño del mapa y lo devolvemos en un mensaje
		String msg = "El tamaño del mapa es " + users.values().size();
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
}