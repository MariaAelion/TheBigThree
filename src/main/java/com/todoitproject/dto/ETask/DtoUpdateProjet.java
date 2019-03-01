package com.todoitproject.dto.ETask;

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
