package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.VistoriaService;
import br.com.michelon.softimob.modelo.Vistoria;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class VistoriaView extends GenericView<Vistoria>{

	private List<ColumnProperties> atributos;
	private VistoriaService service = new VistoriaService();
	
	public VistoriaView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Imovel", "imovel.numero", 10));
	}
	
	@Override
	protected void excluir(List<Vistoria> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Vistorias";
	}

	@Override
	protected Image getImage() {
		return Images.SEARCH_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Vistoria t) {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ImovelEditor.ID;
	}

	@Override
	protected Object getElementToEdit(Vistoria object) {
		return object.getImovel();
	}
	
	@Override
	protected List<Vistoria> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
