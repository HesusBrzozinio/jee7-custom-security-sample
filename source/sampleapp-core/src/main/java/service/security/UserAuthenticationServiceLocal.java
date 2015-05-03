package service.security;

import javax.ejb.Local;

import model.security.UserDTO;

@Local
public interface UserAuthenticationServiceLocal {

	UserDTO find(final String username, final String password);
}
