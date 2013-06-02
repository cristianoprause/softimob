package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.ChamadoReformaService;
import br.com.michelon.softimob.modelo.ChamadoReforma;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class ChamadoReformaView extends GenericView<ChamadoReforma>{

	private List<ColumnProperties> atributos;
	private ChamadoReformaService service = new ChamadoReformaService();
	
	public ChamadoReformaView(){
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Número", "numero", 5));
		atributos.add(new ColumnProperties("Data do chamado", "dataChamado", 8));
		atributos.add(new ColumnProperties("Cliente", "cliente.nome", 25));
		atributos.add(new ColumnProperties("Observações", "observacoes", 40));
		atributos.add(new ColumnProperties("Status", "status", 20));
	}
	
	@Override
	protected void excluir(List<ChamadoReforma> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Chamados";
	}

	@Override
	protected Image getImage() {
		return Images.REFORMA_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(ChamadoReforma t) {
		return new ImovelEditorInput();
	}

	@Override
	protected String getEditorId() {
		return ImovelEditor.ID;
	}

	@Override
	protected Object getElementToEdit(ChamadoReforma object) {
		return object.getAluguel();
	}
	
	@Override
	protected List<ChamadoReforma> getInput() {
		return null;
	}
	
	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
