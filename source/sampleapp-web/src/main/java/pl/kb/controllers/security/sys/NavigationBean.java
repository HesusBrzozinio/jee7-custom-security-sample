package pl.kb.controllers.security.sys;

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

	public String redirectToWelcome() {
		return "/html/sys/secured/adminOnly.xhtml?faces-redirect=true";
	}

	public String toWelcome() {
		return "/html/sys/secured/adminOnly.xhtml";
	}

}
