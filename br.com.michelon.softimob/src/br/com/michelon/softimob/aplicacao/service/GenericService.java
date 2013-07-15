package br.com.michelon.softimob.aplicacao.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;
import br.com.michelon.softimob.persistencia.SpringUtils;

public class GenericService<T> {

	private CrudRepository<T, Serializable> crudRepository;
	private T model;
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public GenericService(Class clazz){
		if(SpringUtils.getContext() != null)
			crudRepository = SpringUtils.getContext().getBean(clazz);
	}
	
	protected CrudRepository<T, ?> getRepository() {
		return crudRepository;
	}
	
	public void salvar(T registro) throws Exception{
		crudRepository.save(registro);
	}
	
	public void salvar(List<T> registros) throws Exception{
		crudRepository.save(registros);
	}
	
	public void removerAtivarOuDesativar(T registro) throws Exception{
		List<Field> fields = ReflectionHelper.getAtributoAtivoDesativado(registro);
				
		if(fields.isEmpty()){
			delete(registro);
		} else {
			ativarDesativar(registro, fields);
		}
	}

	private void ativarDesativar(T registro, List<Field> fields) throws Exception {
		for(Field f : fields){
			Boolean b = (Boolean) ReflectionHelper.getAtribute(registro, f.getName());
			ReflectionHelper.setAtribute(registro, f.getName(), !b, Boolean.class);
		}

		salvar(registro);
	}

	private void delete(T registro) {
		crudRepository.delete(registro);
	}
	
	public List<T> findAll(){
		return (List<T>) crudRepository.findAll();
	}

	public T findOne(Serializable id){
		return crudRepository.findOne(id);
	}
	
	public void setModel(T object) {
		this.model = object;
	}
	
	public T getModel() {
		return model;
	}
	
}
