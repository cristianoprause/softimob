package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.EstadoEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.service.EstadoService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Estado;
import br.com.michelon.softimob.tela.editor.EstadoEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class EstadoView extends GenericView<Estado>{

	private List<ColumnProperties> atributos;
	private EstadoService service = new EstadoService();
	
	
	public EstadoView(){
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("UF", "uf", 10));
		atributos.add(new ColumnProperties("Nome", "nome", 20));
	}
	
	@Override
	protected String getTitleView() {
		return "Estados";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.ENDERECO.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Estado t) {
		return new EstadoEditorInput();
	}

	@Override
	protected String getEditorId(Estado t) {
		return EstadoEditor.ID;
	}

	@Override
	protected List<Estado> getInput() {
		return new EstadoService().findAll();
	}

	@Override
	protected GenericService<Estado> getService(Object obj) {
		return service;
	}
	
}
