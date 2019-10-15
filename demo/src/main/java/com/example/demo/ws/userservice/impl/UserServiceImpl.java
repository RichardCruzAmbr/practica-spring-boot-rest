package com.example.demo.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRest;
import com.example.demo.model.UserRestModel;
import com.example.demo.ws.shared.Utils;
import com.example.demo.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private Map<String, UserRest> users = new HashMap<String, UserRest>();
	Utils utils;
	
	public UserServiceImpl(){}
	@Autowired
	public UserServiceImpl(Utils utils){
		this.utils = utils;
	}
	
	@Override
	public UserRest createUser(UserRestModel userRestModel) {
		// Copiamos la información del model a un dto
		UserRest userRest = new UserRest();
		userRest.setEmail(userRestModel.getEmail());
		userRest.setFirstname(userRestModel.getFirstname());
		userRest.setLastName(userRestModel.getLastName());
		// Agregamos al mapa el nuevo dto
		users.put(userRest.getEmail(), userRest);
		//String respuesta = "El tamaño del objeto es " + users.values().size();
		return userRest;
	}

}
