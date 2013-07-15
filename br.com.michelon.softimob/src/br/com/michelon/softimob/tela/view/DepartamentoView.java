package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image; 
import br.com.michelon.softimob.tela.widget.ColumnProperties;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.DepartamentoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.DepartamentoService;
import br.com.michelon.softimob.modelo.Departamento;
import br.com.michelon.softimob.tela.editor.DepartamentoEditor;
import com.google.common.collect.Lists;

public class DepartamentoView extends GenericView<Departamento>{

	private List<ColumnProperties> atributos;
	private DepartamentoService service;
	
	public DepartamentoView() {
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 10));
		
		service = new DepartamentoService();
	}
	
	@Override
	protected String getTitleView() {
		return "Departamento";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.DEPARTAMENTO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Departamento t) {
		return new DepartamentoEditorInput();
	}

	@Override
	protected String getEditorId(Departamento t) {
		return DepartamentoEditor.ID;
	}

	@Override
	protected List<Departamento> getInput() {
		return service.findAll();
	}

	public DepartamentoService getService(Object obj) {
		return service;
	}

	public void setService(DepartamentoService service) {
		this.service = service;
	}

}
