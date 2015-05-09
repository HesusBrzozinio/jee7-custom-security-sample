package pl.kb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.entity.Role.UserRoleName;
import model.entity.User.UserState;
import model.security.UserDTO;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.kb.utils.ExceptionHandler;
import service.UserServiceLocal;

@ManagedBean(name = "userManagement")
@SessionScoped
public class UserManagementController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory
			.getLogger(UserManagementController.class);
	private List<UserDTO> allUsers;
	private List<UserRoleName> allRoles;
	private UserDTO actualUser;
	private UserState actualUserState;
	private UserRoleName[] actualUserRoles;

	@EJB
	private UserServiceLocal userService;

	@PostConstruct
	private void init() {
		allUsers = userService.getAllUsers();
		allRoles = userService.getAllRoles();
	}

	public void editUserData() {
		LOG.info("{}", "edit invoked");
		RequestContext.getCurrentInstance().openDialog(
				"/html/sys/secured/editUserPanel");
	}

	public void closeEditPanel() {
		actualUser.setState(actualUserState);

		RequestContext.getCurrentInstance().closeDialog(null);
		LOG.info("{}", "edit panel closed");
	}

	public void onUserModified(final SelectEvent event) {
		LOG.info("{}", "onModified invoked");
	}

	public boolean inState(final UserDTO user, final String state) {
		try {
			final UserState stateName = UserState.valueOf(state);
			return user != null && user.getState() == stateName;
		} catch (final Exception ex) {
			ExceptionHandler.handleException(ex,
					"Brak stanu o nazwie " + state, LOG);
			return false;
		}
	}

	public String activate() {
		actualUser.setState(UserState.ACTIVE);
		return null;
	}

	public String deactivate() {
		actualUser.setState(UserState.BLOCKED);
		return null;
	}

	public List<UserDTO> getAllUsers() {
		return allUsers;
	}

	public void setActualUser(final UserDTO user) {
		this.actualUser = user;
		this.actualUserRoles = user.getRoles().toArray(
				new UserRoleName[user.getRoles().size()]);
		this.actualUserState = user.getState();
	}

	public UserDTO getActualUser() {
		return actualUser;
	}

	public UserState[] getAllStates() {
		return UserState.values();
	}

	public UserState getActualUserState() {
		return actualUserState;
	}

	public void setActualUserState(UserState actualUserState) {
		this.actualUserState = actualUserState;
	}

	public UserRoleName[] getActualUserRoles() {
		return actualUserRoles;
	}

	public void setActualUserRoles(UserRoleName[] actualUserRoles) {
		this.actualUserRoles = actualUserRoles;
	}

	public List<UserRoleName> getAllRoles() {
		return allRoles;
	}

}
