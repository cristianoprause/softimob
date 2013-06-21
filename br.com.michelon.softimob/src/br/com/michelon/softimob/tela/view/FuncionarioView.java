package br.com.michelon.softimob.tela.view;

import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.editorInput.FuncionarioEditorInput;
import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.service.FuncionarioService;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.tela.editor.FuncionarioEditor;
import br.com.michelon.softimob.tela.widget.ColumnProperties;

import com.google.common.collect.Lists;

import de.ralfebert.rcputils.tables.format.Formatter;

public class FuncionarioView extends GenericView<Funcionario>{

	private List<ColumnProperties> atributos;
	private FuncionarioService service = new FuncionarioService();
	
	public FuncionarioView() {
		super(true);
		
		atributos = Lists.newArrayList();
		
		atributos.add(new ColumnProperties("Nome", "nome", 20));
		atributos.add(new ColumnProperties("Departamento", "departamento.nome", 12));
		atributos.add(new ColumnProperties("Data de Nascimento", "dataNascimento", 12, Formatter.forDate(FormatterHelper.getSimpleDateFormat())));
		atributos.add(new ColumnProperties("Telefone/Ramal", "telefone", 12));
		atributos.add(new ColumnProperties("Celular", "celular", 12));
		atributos.add(new ColumnProperties("E-mail", "email", 12));
		atributos.add(new ColumnProperties("Data de Admissão", "dataAdmissao", 12, Formatter.forDate(FormatterHelper.getSimpleDateFormat())));
		
	}
	
	@Override
	protected void excluir(List<Funcionario> objetos) {
	}

	@Override
	protected String getTitleView() {
		return "Funcionários";
	}

	@Override
	protected Image getImage() {
		return ImageRepository.FUNCIONARIO_32.getImage();
	}

	@Override
	public List<ColumnProperties> getColumns() {
		return atributos;
	}

	@Override
	protected GenericEditorInput<?> getIEditorInput(Funcionario t) {
		return new FuncionarioEditorInput();
	}

	@Override
	protected String getEditorId(Funcionario t) {
		return FuncionarioEditor.ID;
	}

	@Override
	protected List<Funcionario> getInput() {
		return service.findAll();
	}

}
