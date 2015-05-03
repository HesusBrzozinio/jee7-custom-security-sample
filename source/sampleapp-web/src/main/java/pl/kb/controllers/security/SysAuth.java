package pl.kb.controllers.security;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.security.UserDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.security.UserAuthenticationServiceLocal;

@ManagedBean(name = "sysAuth")
@SessionScoped
public class SysAuth implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SysAuth.class);
	private UserDTO user;
	private String username;
	private String password;

	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	@EJB
	private UserAuthenticationServiceLocal userService;

	public String doLogin() {
		LOG.debug("doLogin invoked");
		user = userService.find(username, password);
		password = null;
		if (user != null) {
			return navigationBean.redirectToWelcome();
		}

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Login failed. Try again.", "ERROR MSG");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return navigationBean.toLogin();
	}

	public String doLogout() {
		user = null;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Logout success!", "INFO MSG");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return navigationBean.toLogin();
	}

	public boolean isUserInRole(final String role) {
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public boolean isLoggedIn() {
		return user != null;
	}

}
