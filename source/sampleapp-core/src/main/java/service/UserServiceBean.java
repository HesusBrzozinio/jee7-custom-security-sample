package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Override
	public List<UserDTO> getAllUsers() {
		try {
			final String jpq = "select distinct u from User u join fetch u.role";
			final TypedQuery<User> query = manager.createQuery(jpq, User.class);
			final List<User> users = query.getResultList();

			return transform(users);
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage(), ex);
			return Collections.<UserDTO> emptyList();
		}
	}

	/**
	 * Transform list of entities to list of DTO.
	 */
	private List<UserDTO> transform(final List<User> users) {
		final List<UserDTO> usersDto = new ArrayList<UserDTO>(users.size());
		users.forEach(u -> {
			usersDto.add(transform(u));
		});
		return usersDto;
	}

	/**
	 * Transform entity to DTO
	 */
	private UserDTO transform(final User user) {
		final UserDTO usr = new UserDTO();
		usr.setId(user.getId());
		usr.setUsername(user.getName());
		usr.setState(user.getState());
		usr.setRoles(transform(user.getRoles()));
		return usr;
	}

	private Set<UserRoleName> transform(Set<Role> roles) {
		final Set<UserRoleName> rls = new HashSet<Role.UserRoleName>(
				roles.size());
		roles.forEach(r -> {
			rls.add(r.getName());
		});
		return rls;
	}
}
