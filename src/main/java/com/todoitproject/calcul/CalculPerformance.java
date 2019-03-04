package com.todoitproject.calcul;  
import java.util.List;  
import com.todoitproject.persistence.entity.ETask;  
public class CalculPerformance {              
	public double indicePerfo ; // taux de performance
	public double taskFinished;         
	public double taskTotal;                  
	public double calculPerfoIndice(List<ETask> eTasks ) {             
		// recup methode thomas pour lister taches                       
		this.indicePerfo=0;              
		this.taskFinished=0;              
		this.taskTotal=0;                           
		eTasks.forEach(task -> {                 
			if(task.isEtat()) {                      
				this.taskFinished += task.getPriorite(); }
			
				this.taskTotal += task.getPriorite();
				});                          
		if( this.taskTotal != 0) {                 
			this.indicePerfo = this.taskFinished/this.taskTotal;                  
			} else {
				this.indicePerfo = 1;                 
				}                                       
		return this.indicePerfo;          
		             
	
	}
}