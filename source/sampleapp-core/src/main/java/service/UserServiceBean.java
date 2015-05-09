package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.entity.Role;
import model.entity.Role.UserRoleName;
import model.entity.User;
import model.security.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UserServiceBean implements UserServiceLocal {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserServiceBean.class);
	@PersistenceContext
	private EntityManager manager;

	@EJB
	private UserTransformationBean transformer;

	@Override
	public List<UserDTO> getAllUsers() {
		try {
			final String jpq = "select distinct u from User u join fetch u.role";
			final TypedQuery<User> query = manager.createQuery(jpq, User.class);
			final List<User> users = query.getResultList();

			return transformer.toDTO(users);
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage(), ex);
			return Collections.<UserDTO> emptyList();
		}
	}

	@Override
	public List<UserRoleName> getAllRoles() {
		try {
			final String jpq = "from Role r";
			final TypedQuery<Role> query = manager.createQuery(jpq, Role.class);
			final List<Role> roles = query.getResultList();
			return getRoleNames(roles);
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage(), ex);
			return Collections.<UserRoleName> emptyList();
		}
	}

	private List<UserRoleName> getRoleNames(List<Role> roles) {
		final List<UserRoleName> names = new ArrayList<UserRoleName>(
				roles.size());
		roles.forEach(r -> {
			names.add(r.getName());
		});
		return names;
	}

}
