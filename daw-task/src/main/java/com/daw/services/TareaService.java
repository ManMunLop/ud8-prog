package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.entities.enums.Estado;
import com.daw.repositories.TareaRepository;
import com.daw.services.excepciones.TareaException;
import com.daw.services.excepciones.TareaNotFoundException;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;
	
	public List<TareaEntity> findAll() {
		return tareaRepository.findAll();
	}
	
	public TareaEntity findById(long idTarea) {
		if(this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException("No se encuentra la tarea con id: " + idTarea);
		}
		return this.tareaRepository.findById(idTarea).get();
		
	}
	
	public void create (TareaEntity t) {
		if(t.getFechaCreacion() != null) {
			throw new TareaException("No se puede modificar la fecha de creacion de una tarea");
		}
		if(t.getEstado() !=null) {
			throw new TareaException("No se puede modificar el estado de una tarea");
		}
		
		t.setId(0);
		t.setFechaCreacion(null);
		t.setEstado(Estado.PENDIENTE);
	}
}                                                                                                                      