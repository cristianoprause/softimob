package br.com.michelon.softimob.tela.view;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import com.google.common.collect.Maps;

import br.com.michelon.softimob.aplicacao.editorInput.AluguelEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.modelo.Aluguel;
import br.com.michelon.softimob.tela.editor.AluguelEditor;

public class AluguelView extends GenericView<Aluguel>{

	private Map<String, String> atributos;
	
	public AluguelView() {
		atributos = Maps.newLinkedHashMap();
	}
	
	@Override
	protected void excluir(List<Aluguel> objetos) {
	}

	@Override
	protected String getName() {
		return "Aluguéis";
	}

	@Override
	protected Image getImage() {
		return Images.ALUGUEL_32.getImage();
	}

	@Override
	public Map<String, String> getAttributes() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput() {
		return new AluguelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return AluguelEditor.ID;
	}

	@Override
	protected List<Aluguel> getInput() {
		return null;
	}

}
