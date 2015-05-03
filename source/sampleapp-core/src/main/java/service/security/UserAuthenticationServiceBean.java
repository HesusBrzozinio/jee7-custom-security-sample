package service.security;

import javax.ejb.Stateless;

import model.security.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserAuthenticationServiceBean implements
		UserAuthenticationServiceLocal {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserAuthenticationServiceBean.class);

	@Override
	public UserDTO find(final String username, final String password) {

		LOG.info("fetching user: {}", username);
		if ("admin".equals(username) && "admin".equals(password)) {
			final UserDTO user = new UserDTO();
			user.setUsername(username);
			return user;
		}
		return null;
	}

}
