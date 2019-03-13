package com.todoitproject.dto.ETask;
/**
*  @author TheBigThree
* @version 1.0.1
*
*/
import com.todoitproject.persistence.entity.EProject;

public class DtoUpdateProjet {
	
	private EProject eProject;

	public EProject geteProject() {
		return eProject;
	}

	public void seteProject(EProject eProject) {
		this.eProject = eProject;
	}
	
	

}
