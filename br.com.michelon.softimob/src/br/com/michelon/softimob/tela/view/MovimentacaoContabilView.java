package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.MovimentacaoContabilEditorInput;
import br.com.michelon.softimob.aplicacao.service.MovimentacaoContabilService;
import br.com.michelon.softimob.modelo.MovimentacaoContabil;
import br.com.michelon.softimob.tela.editor.MovimentacaoContabilEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class MovimentacaoContabilView extends GenericView<MovimentacaoContabil>{

	private List<ColumnProperties> atributos;
	private MovimentacaoContabilService service = new MovimentacaoContabilService();
	
	public MovimentacaoContabilView(){
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Código", "codigo"));
		atributos.add(new ColumnProperties("Data de Lançamento", "dataLancamento"));
		atributos.add(new ColumnProperties("Valor", "valor"));
	}
	
	@Override
	protected void excluir(List<MovimentacaoContabil> objetos) {
		// TODO Auto-generated method stub
	}

	@Override
	protected String getTitleView() {
		return "Movimentações Contábeis";
	}

	@Override
	protected Image getImage() {
		return Images.MOVIMENTACAO_CONTABIL_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(MovimentacaoContabil t) {
		return new MovimentacaoContabilEditorInput();
	}

	@Override
	protected String getEditorId(MovimentacaoContabil t) {
		return MovimentacaoContabilEditor.ID;
	}

	@Override
	protected List<MovimentacaoContabil> getInput() {
		return service.findAll();
	}
	
	

}
