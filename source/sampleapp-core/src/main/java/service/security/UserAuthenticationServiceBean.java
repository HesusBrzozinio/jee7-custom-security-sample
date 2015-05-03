package service.security;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.entity.User;
import model.security.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserAuthenticationServiceBean implements
		UserAuthenticationServiceLocal {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserAuthenticationServiceBean.class);

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDTO find(final String username, final String password) {

		LOG.info("fetching user: {}", username);
		final User usr = manager.find(User.class, 1);

		if (usr != null) {
			final UserDTO user = new UserDTO();
			user.setUsername(usr.getName());
			return user;
		}
		return null;
	}

}
