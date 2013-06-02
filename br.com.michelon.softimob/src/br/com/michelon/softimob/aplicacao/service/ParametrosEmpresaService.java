package br.com.michelon.softimob.aplicacao.service;

import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.persistencia.ParametrosEmpresaDAO;


public class ParametrosEmpresaService extends GenericService<ParametrosEmpresa>{

	public ParametrosEmpresaService() {
		super(ParametrosEmpresaDAO.class);
	}
	
}
