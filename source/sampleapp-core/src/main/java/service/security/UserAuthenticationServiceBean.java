package service.security;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public UserDTO authenticate(final String username, final String password) {

		LOG.info("fetching active user: {}", username);
		final String jpq = "from User u where u.name=:name and u.password=:password and u.state=:state";
		final TypedQuery<User> query = manager.createQuery(jpq, User.class);

		try {
			final User user = query.setParameter("name", username)
					.setParameter("password", password)
					.setParameter("state", User.UserState.ACTIVE)
					.getSingleResult();
			final UserDTO usr = new UserDTO();
			usr.setUsername(user.getName());
			return usr;
		} catch (final NoResultException ex) {
			LOG.warn("No active user", ex);
			return null;
		} catch (final NonUniqueResultException ex) {
			LOG.error("Data consistency error", ex);
			return null;
		} catch (final Exception ex) {
			LOG.error(ex.getLocalizedMessage(), ex);
			return null;
		}
	}

}
