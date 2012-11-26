package be.esi.projet11.gestionprojet.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author etienne
 */
@ManagedBean
@SessionScoped
public class UserSessionController
{
	private String inputNom;
	private String inputPassword;
	private boolean identificationEchouee;

	@PostConstruct
	public void init()
	{
		this.setIdentificationEchouee(false);
	}
	
	public String getInputNom()
	{
		return this.inputNom;
	}

	public void setInputNom(String inputNom)
	{
		this.inputNom = inputNom;
	}

	public String getInputPassword()
	{
		return this.inputPassword;
	}

	public void setInputPassword(String inputPassword)
	{
		this.inputPassword = inputPassword;
	}

	public boolean isIdentificationEchouee()
	{
		return identificationEchouee;
	}

	public void setIdentificationEchouee(boolean identificationEchouee)
	{
		this.identificationEchouee = identificationEchouee;
	}
	
	public String identifier()
	{
		this.setIdentificationEchouee(true);
		return null;
	}
}
