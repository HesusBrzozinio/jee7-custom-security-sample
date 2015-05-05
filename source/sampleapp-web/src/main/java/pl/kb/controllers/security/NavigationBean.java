package pl.kb.controllers.security;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name ="navigationBean")
@ApplicationScoped
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String redirectToLogin() {
		return "/html/sys/login.xhtml?faces-redirect=true";
	}

	public String toLogin() {
		return "/html/sys/login.xhtml";
	}

	public String redirectToInfo() {
		return "/info.xhtml?faces-redirect=true";
	}

	public String toInfo() {
		return "/info.xhtml";
	}

	public String redirectToUsersManagement() {
		return "/html/sys/secured/users.xhtml?faces-redirect=true";
	}

	public String toUsersManagement() {
		return "/html/sys/secured/users.xhtml";
	}

}
