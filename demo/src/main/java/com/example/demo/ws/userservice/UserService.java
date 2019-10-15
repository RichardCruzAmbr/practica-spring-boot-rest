package com.example.demo.ws.userservice;

import com.example.demo.dto.UserRest;
import com.example.demo.model.UserRestModel;

public interface UserService {
	UserRest createUser(UserRestModel userRestModel);
}
