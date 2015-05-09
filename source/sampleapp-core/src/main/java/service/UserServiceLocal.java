package service;

import java.util.List;

import javax.ejb.Local;

import model.entity.Role.UserRoleName;
import model.security.UserDTO;

@Local
public interface UserServiceLocal {

	/**
	 * Get all registered users.
	 */
	List<UserDTO> getAllUsers();

	List<UserRoleName> getAllRoles();
}
