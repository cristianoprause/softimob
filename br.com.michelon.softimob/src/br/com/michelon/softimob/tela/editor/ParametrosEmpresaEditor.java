package br.com.michelon.softimob.tela.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wb.swt.ImageRepository;
import org.eclipse.wb.swt.SWTResourceManager;

import br.com.michelon.softimob.aplicacao.editorInput.GenericEditorInput;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper;
import br.com.michelon.softimob.aplicacao.helper.listElementDialog.ListElementDialogHelper.TipoDialog;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ParametrosEmpresaService;
import br.com.michelon.softimob.modelo.CheckList;
import br.com.michelon.softimob.modelo.Funcionario;
import br.com.michelon.softimob.modelo.ModeloContrato;
import br.com.michelon.softimob.modelo.OrigemConta;
import br.com.michelon.softimob.modelo.ParametrosEmpresa;
import br.com.michelon.softimob.modelo.PlanoConta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.CNPJTextField;

public class ParametrosEmpresaEditor extends GenericEditor<ParametrosEmpresa>{
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ParametrosEmpresaEditor";
	
	private ParametrosEmpresaService service = new ParametrosEmpresaService();

	private Text txtCnpj;
	private Text txtRazaoSocial;
	private Text txtContratoVenda;
	private Text txtContratoAluguel;
	private Text txtFuncionarioResponsavel;
	private Text txtPrazoFinalizacaoReforma;
	private Text txtDiaRecebAluguel;
	private Text txtDiaRepasseAluguel;
	private Text txtCreci;
	private Text txtCheckListVenda;
	private Text txtCheckListAluguel;
	private Text txtCheckListVistoria;
	private Text txtContratoPrestacaoServico;
	private Text txtContaVenda;
	private Text txtContraPartidaVenda;
	private Text txtContaAluguel;
	private Text txtContraPartidaAluguel;
	private Text txtContaContratoPrestacaoServico;
	private Text txtContaReforma;
	private Text txtContaComissao;
	private Text txtContaDescontoRecebido;
	private Text txtJurosPagos;
	private Text txtContaDescontoConcedido;
	private Text txtContaJurosRecebido;
	
	public ParametrosEmpresaEditor() {
		super(ParametrosEmpresa.class);
	}

	@Override
	public void afterCreatePartControl(Composite parent) {
		Label lblRazoSocial = new Label(parent, SWT.NONE);
		lblRazoSocial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRazoSocial.setText("Razão Social");
		
		txtRazaoSocial = new Text(parent, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 241;
		txtRazaoSocial.setLayoutData(gd_text_1);
		
		Label lblCnpj = new Label(parent, SWT.NONE);
		lblCnpj.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCnpj.setText("CNPJ");
		
		CNPJTextField textField = new CNPJTextField(parent);
		txtCnpj = textField.getControl();
		GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 112;
		txtCnpj.setLayoutData(gd_text);
		
		Label lblCreci = new Label(parent, SWT.NONE);
		lblCreci.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCreci.setText("CRECI");
		
		txtCreci = new Text(parent, SWT.BORDER);
		GridData gd_text_8 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_text_8.widthHint = 112;
		txtCreci.setLayoutData(gd_text_8);
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmVenda = new CTabItem(tabFolder, SWT.NONE);
		tbtmVenda.setText("Venda");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmVenda.setControl(composite);
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		
		Label lblModeloDeContrato = new Label(composite, SWT.NONE);
		lblModeloDeContrato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato.setText("Modelo de Contrato");
		
		txtContratoVenda = new Text(composite, SWT.BORDER);
		txtContratoVenda.setEditable(false);
		txtContratoVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(composite, SWT.NONE);
		button.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, button, value, "contratoVenda");
		
		Button btnt = new Button(composite, SWT.NONE);
		btnt.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnt.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblChecklist = new Label(composite, SWT.NONE);
		lblChecklist.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist.setText("Check List");
		
		txtCheckListVenda = new Text(composite, SWT.BORDER);
		txtCheckListVenda.setEditable(false);
		txtCheckListVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_11 = new Button(composite, SWT.NONE);
		button_11.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CHECK_LIST, button_11, value, "checkListVenda");
		
		Button button_17 = new Button(composite, SWT.NONE);
		button_17.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblConta = new Label(composite, SWT.NONE);
		lblConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta.setText("Conta");
		
		txtContaVenda = new Text(composite, SWT.BORDER);
		txtContaVenda.setEditable(false);
		txtContaVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_6 = new Button(composite, SWT.NONE);
		button_6.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_6, value, "contaVenda");
		
		Button button_18 = new Button(composite, SWT.NONE);
		button_18.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblContraPartida = new Label(composite, SWT.NONE);
		lblContraPartida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContraPartida.setText("Contra - Partida");
		
		txtContraPartidaVenda = new Text(composite, SWT.BORDER);
		txtContraPartidaVenda.setEditable(false);
		txtContraPartidaVenda.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_7 = new Button(composite, SWT.NONE);
		button_7.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_7, value, "contraPartidaVenda");
		
		Button button_19 = new Button(composite, SWT.NONE);
		button_19.setImage(ImageRepository.REMOVE_16.getImage());
		
		CTabItem tbtmAlguel = new CTabItem(tabFolder, SWT.NONE);
		tbtmAlguel.setText("Aluguel");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmAlguel.setControl(composite_1);
		GridLayout gl_composite_1 = new GridLayout(4, false);
		gl_composite_1.verticalSpacing = 10;
		composite_1.setLayout(gl_composite_1);
		
		Label lblDataDeRecebimento_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRecebimento_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRecebimento_1.setText("Dia de Recebimento");
		
		txtDiaRecebAluguel = new Text(composite_1, SWT.BORDER);
		txtDiaRecebAluguel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblDataDeRepasse_1 = new Label(composite_1, SWT.NONE);
		lblDataDeRepasse_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDataDeRepasse_1.setText("Dia de Repasse");
		
		txtDiaRepasseAluguel = new Text(composite_1, SWT.BORDER);
		txtDiaRepasseAluguel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Label lblModeloDeContrato_1 = new Label(composite_1, SWT.NONE);
		lblModeloDeContrato_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_1.setText("Modelo de Contrato");
		
		txtContratoAluguel = new Text(composite_1, SWT.BORDER);
		txtContratoAluguel.setEditable(false);
		txtContratoAluguel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnContratoAluguel = new Button(composite_1, SWT.NONE);
		btnContratoAluguel.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, btnContratoAluguel, value, "contratoAluguel");
		
		Button button_20 = new Button(composite_1, SWT.NONE);
		button_20.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_20.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblChecklist_1 = new Label(composite_1, SWT.NONE);
		lblChecklist_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_1.setText("Check List");
		
		txtCheckListAluguel = new Text(composite_1, SWT.BORDER);
		txtCheckListAluguel.setEditable(false);
		txtCheckListAluguel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CHECK_LIST, button_1, value, "checkListAluguel");
		
		Button button_21 = new Button(composite_1, SWT.NONE);
		button_21.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblConta_1 = new Label(composite_1, SWT.NONE);
		lblConta_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblConta_1.setText("Conta");
		
		txtContaAluguel = new Text(composite_1, SWT.BORDER);
		txtContaAluguel.setEditable(false);
		txtContaAluguel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_8 = new Button(composite_1, SWT.NONE);
		button_8.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_8, value, "contaAluguel");
		
		Button button_22 = new Button(composite_1, SWT.NONE);
		button_22.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblContraPartida_1 = new Label(composite_1, SWT.NONE);
		lblContraPartida_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContraPartida_1.setText("Contra - Partida");
		
		txtContraPartidaAluguel = new Text(composite_1, SWT.BORDER);
		txtContraPartidaAluguel.setEditable(false);
		txtContraPartidaAluguel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_9 = new Button(composite_1, SWT.NONE);
		button_9.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_9, value, "contraPartidaAluguel");
		
		Button button_23 = new Button(composite_1, SWT.NONE);
		button_23.setImage(ImageRepository.REMOVE_16.getImage());
		
		CTabItem tbtmPrestaoDeServio = new CTabItem(tabFolder, SWT.NONE);
		tbtmPrestaoDeServio.setText("Prestação de Serviço");
		
		Composite composite_6 = new Composite(tabFolder, SWT.NONE);
		tbtmPrestaoDeServio.setControl(composite_6);
		composite_6.setLayout(new GridLayout(4, false));
		
		Label lblModeloDeContrato_2 = new Label(composite_6, SWT.NONE);
		lblModeloDeContrato_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModeloDeContrato_2.setText("Modelo de Contrato");
		
		txtContratoPrestacaoServico = new Text(composite_6, SWT.BORDER);
		txtContratoPrestacaoServico.setEditable(false);
		txtContratoPrestacaoServico.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_3 = new Button(composite_6, SWT.NONE);
		button_3.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.MODELO_CONTRATO, button_3, value, "contratoPrestacaoServico");
		
		Button button_24 = new Button(composite_6, SWT.NONE);
		button_24.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblTipoDeConta_1 = new Label(composite_6, SWT.NONE);
		lblTipoDeConta_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDeConta_1.setText("Tipo de Conta");
		
		txtContaContratoPrestacaoServico = new Text(composite_6, SWT.BORDER);
		txtContaContratoPrestacaoServico.setEditable(false);
		txtContaContratoPrestacaoServico.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_4 = new Button(composite_6, SWT.NONE);
		button_4.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.ORIGEM_CONTA, button_4, value, "tipoContaPrestacaoServico");
		
		Button button_25 = new Button(composite_6, SWT.NONE);
		button_25.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_25.setImage(ImageRepository.REMOVE_16.getImage());
		
		CTabItem tbtmVistoria = new CTabItem(tabFolder, SWT.NONE);
		tbtmVistoria.setText("Vistoria");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmVistoria.setControl(composite_5);
		composite_5.setLayout(new GridLayout(4, false));
		
		Label lblChecklist_2 = new Label(composite_5, SWT.NONE);
		lblChecklist_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblChecklist_2.setText("Check List");
		
		txtCheckListVistoria = new Text(composite_5, SWT.BORDER);
		txtCheckListVistoria.setEditable(false);
		txtCheckListVistoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_2 = new Button(composite_5, SWT.NONE);
		button_2.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.CHECK_LIST, button_2, value, "checkListVistoria");
		
		Button button_26 = new Button(composite_5, SWT.NONE);
		button_26.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_26.setImage(ImageRepository.REMOVE_16.getImage());
		
		CTabItem tbtmReformas = new CTabItem(tabFolder, SWT.NONE);
		tbtmReformas.setText("Reformas");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmReformas.setControl(composite_3);
		composite_3.setLayout(new GridLayout(4, false));
		
		Label lblPrazoPFinalizao = new Label(composite_3, SWT.NONE);
		lblPrazoPFinalizao.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrazoPFinalizao.setText("Prazo p/ Finalização");
		
		txtPrazoFinalizacaoReforma = new Text(composite_3, SWT.BORDER);
		txtPrazoFinalizacaoReforma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDias = new Label(composite_3, SWT.NONE);
		lblDias.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.ITALIC));
		lblDias.setText("Dias");
		new Label(composite_3, SWT.NONE);
		
		Label lblResponsvel = new Label(composite_3, SWT.NONE);
		lblResponsvel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResponsvel.setText("Funcionário Responsável");
		
		txtFuncionarioResponsavel = new Text(composite_3, SWT.BORDER);
		txtFuncionarioResponsavel.setEditable(false);
		txtFuncionarioResponsavel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_10 = new Button(composite_3, SWT.NONE);
		button_10.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.FUNCIONARIO, button_10, value, "funcionarioResponsavelReforma");
		
		Button button_27 = new Button(composite_3, SWT.NONE);
		button_27.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_27.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblTipoDaConta = new Label(composite_3, SWT.NONE);
		lblTipoDaConta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDaConta.setText("Tipo da Conta");
		
		txtContaReforma = new Text(composite_3, SWT.BORDER);
		txtContaReforma.setEditable(false);
		txtContaReforma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_5 = new Button(composite_3, SWT.NONE);
		button_5.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.ORIGEM_CONTA, button_5, value, "tipoContaReforma");
		
		Button button_28 = new Button(composite_3, SWT.NONE);
		button_28.setImage(ImageRepository.REMOVE_16.getImage());
		
		CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		tabItem.setText("Comissão");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_4);
		composite_4.setLayout(new GridLayout(4, false));
		
		Label lblTipoDeConta = new Label(composite_4, SWT.NONE);
		lblTipoDeConta.setText("Tipo de Conta");
		
		txtContaComissao = new Text(composite_4, SWT.BORDER);
		txtContaComissao.setEditable(false);
		txtContaComissao.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_12 = new Button(composite_4, SWT.NONE);
		button_12.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.ORIGEM_CONTA, button_12, value, "tipoContaComissao");
		
		Button button_29 = new Button(composite_4, SWT.NONE);
		button_29.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_29.setImage(ImageRepository.REMOVE_16.getImage());
		
		tabFolder.setSelection(0);
		
		CTabItem tbtmContas = new CTabItem(tabFolder, SWT.NONE);
		tbtmContas.setText("Contas");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmContas.setControl(composite_2);
		composite_2.setLayout(new GridLayout(4, false));
		
		Label lblDescontoRecebido = new Label(composite_2, SWT.NONE);
		lblDescontoRecebido.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescontoRecebido.setText("Desconto Obtido");
		
		txtContaDescontoRecebido = new Text(composite_2, SWT.BORDER);
		txtContaDescontoRecebido.setEditable(false);
		txtContaDescontoRecebido.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_13 = new Button(composite_2, SWT.NONE);
		button_13.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_13, value, "contaDescontoRecebido");
		
		Button button_30 = new Button(composite_2, SWT.NONE);
		button_30.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		button_30.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblJurosPago = new Label(composite_2, SWT.NONE);
		lblJurosPago.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJurosPago.setText("Juros Pago");
		
		txtJurosPagos = new Text(composite_2, SWT.BORDER);
		txtJurosPagos.setEditable(false);
		txtJurosPagos.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_14 = new Button(composite_2, SWT.NONE);
		button_14.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_14, value, "contaJurosPagos");
		
		Button button_31 = new Button(composite_2, SWT.NONE);
		button_31.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblDescontoConcedido = new Label(composite_2, SWT.NONE);
		lblDescontoConcedido.setText("Desconto Concedido");
		
		txtContaDescontoConcedido = new Text(composite_2, SWT.BORDER);
		txtContaDescontoConcedido.setEditable(false);
		txtContaDescontoConcedido.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_15 = new Button(composite_2, SWT.NONE);
		button_15.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_15, value, "contaDescontoConcedido");
		
		Button button_32 = new Button(composite_2, SWT.NONE);
		button_32.setImage(ImageRepository.REMOVE_16.getImage());
		
		Label lblJurosRecebido = new Label(composite_2, SWT.NONE);
		lblJurosRecebido.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJurosRecebido.setText("Juros Recebido");
		
		txtContaJurosRecebido = new Text(composite_2, SWT.BORDER);
		txtContaJurosRecebido.setEditable(false);
		txtContaJurosRecebido.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button_16 = new Button(composite_2, SWT.NONE);
		button_16.setImage(ImageRepository.SEARCH_16.getImage());
		ListElementDialogHelper.addSelectionListDialogToButton(TipoDialog.PLANOCONTA, button_16, value, "contaJurosRecebido");
		
		Button button_33 = new Button(composite_2, SWT.NONE);
		button_33.setImage(ImageRepository.REMOVE_16.getImage());
	}
	
	@Override
	public void saveCurrentObject(GenericService<ParametrosEmpresa> service) {
		if(salvar(getService(), value)){
			IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditor(activeEditor, false);
		}
	}
	
	@Override
	public GenericService<ParametrosEmpresa> getService() {
		return service;
	}

	@Override
	protected ParametrosEmpresa getValorInicial(GenericEditorInput<ParametrosEmpresa> editorInput) {
		return ParametrosEmpresa.getInstance();
	}
	
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtContratoVendaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContratoVenda);
		IObservableValue valueContratoVendaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contratoVenda", ModeloContrato.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContratoVendaObserveWidget, valueContratoVendaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtCheckListVendaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtCheckListVenda);
		IObservableValue valueCheckListVendaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "checkListVenda", CheckList.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCheckListVendaObserveWidget, valueCheckListVendaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtCheckListVistoriaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtCheckListVistoria);
		IObservableValue valueCheckListVistoriaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "checkListVistoria", CheckList.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCheckListVistoriaObserveWidget, valueCheckListVistoriaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContaVendaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaVenda);
		IObservableValue valueContaVendaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaVenda", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaVendaObserveWidget, valueContaVendaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContraPartidaVendaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContraPartidaVenda);
		IObservableValue valueContraPartidaVendaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contraPartidaVenda", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContraPartidaVendaObserveWidget, valueContraPartidaVendaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtDiaRecebAluguelObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDiaRecebAluguel);
		IObservableValue valueDiaRecebAluguelObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "diaRecebAluguel", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtDiaRecebAluguelObserveWidget, valueDiaRecebAluguelObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtDiaRepasseAluguelObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtDiaRepasseAluguel);
		IObservableValue valueDiaRepasseAluguelObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "diaRepasseAluguel", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtDiaRepasseAluguelObserveWidget, valueDiaRepasseAluguelObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContratoAluguelObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContratoAluguel);
		IObservableValue valueContratoAluguelnomeObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contratoAluguel.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContratoAluguelObserveWidget, valueContratoAluguelnomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtCheckListAluguelObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtCheckListAluguel);
		IObservableValue valueCheckListAluguelnomeObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "checkListAluguel.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCheckListAluguelObserveWidget, valueCheckListAluguelnomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContaAluguelObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaAluguel);
		IObservableValue valueContaAluguelObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaAluguel", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaAluguelObserveWidget, valueContaAluguelObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContraPartidaAluguelObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContraPartidaAluguel);
		IObservableValue valueContraPartidaAluguelObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contraPartidaAluguel", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContraPartidaAluguelObserveWidget, valueContraPartidaAluguelObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContratoPrestacaoServicoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContratoPrestacaoServico);
		IObservableValue valueContratoPrestacaoServicoObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contratoPrestacaoServico", ModeloContrato.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContratoPrestacaoServicoObserveWidget, valueContratoPrestacaoServicoObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtPrazoFinalizacaoReformaObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtPrazoFinalizacaoReforma);
		IObservableValue valueDiasFinalizacaoReformaObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "diasFinalizacaoReforma", Integer.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtPrazoFinalizacaoReformaObserveWidget, valueDiasFinalizacaoReformaObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtFuncionarioResponsavelObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtFuncionarioResponsavel);
		IObservableValue valueFuncionarioResponsavelObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "funcionarioResponsavelReforma", Funcionario.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtFuncionarioResponsavelObserveWidget, valueFuncionarioResponsavelObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtRazaoSocialObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtRazaoSocial);
		IObservableValue valueCreciObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "razaoSocial", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtRazaoSocialObserveWidget, valueCreciObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtCnpjObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCnpj);
		IObservableValue valueCnpjObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "cnpj", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCnpjObserveWidget, valueCnpjObserveDetailValue, UVSHelper.uvsExtractNumbers(), null);
		//
		IObservableValue observeTextTxtCreciObserveWidget = WidgetProperties.text(SWT.Modify).observe(txtCreci);
		IObservableValue valueCreciObserveDetailValue_1 = PojoProperties.value(ParametrosEmpresa.class, "creci", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtCreciObserveWidget, valueCreciObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextTxtContaComissaoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaComissao);
		IObservableValue value2OrigemObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "tipoContaComissao", OrigemConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaComissaoObserveWidget, value2OrigemObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtContaReformaObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaReforma);
		IObservableValue value2OrigemObserveDetailValue_1 = PojoProperties.value(ParametrosEmpresa.class, "tipoContaReforma", OrigemConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaReformaObserveWidget, value2OrigemObserveDetailValue_1, null, null);
		//
		IObservableValue observeTextTxtContaContratoPrestacaoServicoObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaContratoPrestacaoServico);
		IObservableValue value2OrigemObserveDetailValue_2 = PojoProperties.value(ParametrosEmpresa.class, "tipoContaPrestacaoServico", OrigemConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTxtContaContratoPrestacaoServicoObserveWidget, value2OrigemObserveDetailValue_2, null, null);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaDescontoRecebido);
		IObservableValue valueContaDescontoRecebidoObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaDescontoRecebido", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContaDescontoRecebidoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtJurosPagos);
		IObservableValue valueContaJurosPagosObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaJurosPagos", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueContaJurosPagosObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_2ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaDescontoConcedido);
		IObservableValue valueContaDescontoConcedidoObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaDescontoConcedido", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_2ObserveWidget, valueContaDescontoConcedidoObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_3ObserveWidget = WidgetProperties.text(SWT.NONE).observe(txtContaJurosRecebido);
		IObservableValue valueContaJurosObtidosObserveDetailValue = PojoProperties.value(ParametrosEmpresa.class, "contaJurosRecebido", PlanoConta.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_3ObserveWidget, valueContaJurosObtidosObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
