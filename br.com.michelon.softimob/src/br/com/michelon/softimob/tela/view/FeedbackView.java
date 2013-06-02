package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.Images;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.ImovelEditorInput;
import br.com.michelon.softimob.aplicacao.service.FeedbackService;
import br.com.michelon.softimob.modelo.Feedback;
import br.com.michelon.softimob.tela.editor.ImovelEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

public class FeedbackView extends GenericView<Feedback>{

	private List<ColumnProperties> atributos;
	private FeedbackService service = new FeedbackService();
	
	public FeedbackView() {
		super(false);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Data", "data", 10));
		atributos.add(new ColumnProperties("Cliente", "cliente.nome", 30));
		atributos.add(new ColumnProperties("Funcionário", "funcionario.nome", 30));
		atributos.add(new ColumnProperties("Feedback", "feedback", 30));
	}
	
	@Override
	protected void excluir(List<Feedback> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Feedbacks";
	}

	@Override
	protected Image getImage() {
		return Images.FEEDBACK_32_2.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Feedback t) {
		return new ImovelEditorInput();
	}

	@Override
	protected Object getModelOfEditorInput(Feedback element) {
		return element.getImovel();
	}
	
	@Override
	protected String getEditorId(Feedback t) {
		return ImovelEditor.ID;
	}

	@Override
	protected List<Feedback> getInput() {
		return service.findAll();
	}

	@Override
	protected List<Action> createMoreActions() {
		return null;
	}
	
}
