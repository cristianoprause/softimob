package br.com.michelon.softimob.tela.editor;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.modelo.Cliente;
import br.com.michelon.softimob.tela.widget.CEPTextField;
import br.com.michelon.softimob.tela.widget.CNPJTextField;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

public class ClientePJEditor extends GenericEditor<Cliente> {

	private ClienteService service = new ClienteService();
	private Text text_9;
	private Text text_10;
	private Text text_8;
	private Text text_12;
	private Text text_14;
	private Text text_15;
	private Text text_16;
	private Text text_13;
	private Text text_17;
	private Text text_18;
	private Text text_19;

	public ClientePJEditor() {
		super(Cliente.class);
	}

	@Override
	public GenericService<Cliente> getService() {
		return service;
	}

	@Override
	public void afterCreatePartControl(Composite parent1) {
		GridLayout gl2_parent = new GridLayout(1, false);
		gl2_parent.verticalSpacing = 10;
		parent1.setLayout(gl2_parent);
		parent1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1,
				1));
		
				Composite composite_1 = new Composite(parent1, SWT.NONE);
				composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
				GridLayout gl_composite_1 = new GridLayout(4, false);
				gl_composite_1.verticalSpacing = 10;
				composite_1.setLayout(gl_composite_1);
				
						Label lblRazoSocial = new Label(composite_1, SWT.NONE);
						lblRazoSocial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
								false, 1, 1));
						lblRazoSocial.setText("Razão Social");
						
								text_12 = new Text(composite_1, SWT.BORDER);
								text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
										1, 1));
								new Label(composite_1, SWT.NONE);
								new Label(composite_1, SWT.NONE);
								
										Label lblDataDeAbertura = new Label(composite_1, SWT.NONE);
										lblDataDeAbertura.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
												false, false, 1, 1));
										lblDataDeAbertura.setText("Data de Abertura");
										
												text_18 = new Text(composite_1, SWT.BORDER);
												text_18.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
														1, 1));
												new Label(composite_1, SWT.NONE);
												new Label(composite_1, SWT.NONE);
												
														Label lblCnpj = new Label(composite_1, SWT.NONE);
														lblCnpj.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
																1, 1));
														lblCnpj.setText("CNPJ");
														
																CNPJTextField textField_2 = new CNPJTextField(composite_1);
																text_14 = textField_2.getControl();
																text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																		1, 1));
																
																		Label lblInscEstadual = new Label(composite_1, SWT.NONE);
																		lblInscEstadual.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
																				false, false, 1, 1));
																		lblInscEstadual.setText("Insc Estadual");
																		
																				text_15 = new Text(composite_1, SWT.BORDER);
																				text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																						1, 1));
																				
																						Label lblTelefone = new Label(composite_1, SWT.NONE);
																						lblTelefone.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																								false, 1, 1));
																						lblTelefone.setText("Telefone");
																						
																								PhoneTextField phoneTextField_2 = new PhoneTextField(composite_1);
																								text_16 = phoneTextField_2.getControl();
																								text_16.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																										1, 1));
																								
																										Label lblCelular_1 = new Label(composite_1, SWT.NONE);
																										lblCelular_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																												false, 1, 1));
																										lblCelular_1.setText("Celular");
																										
																												PhoneTextField phoneTextField_3 = new PhoneTextField(composite_1);
																												text_13 = phoneTextField_3.getControl();
																												text_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																														1, 1));
																												
																														Label lblEmail_1 = new Label(composite_1, SWT.NONE);
																														lblEmail_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
																																false, 1, 1));
																														lblEmail_1.setText("E-mail");
																														
																																text_17 = new Text(composite_1, SWT.BORDER);
																																text_17.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																																		1, 1));
																																new Label(composite_1, SWT.NONE);
																																new Label(composite_1, SWT.NONE);
																																
																																		Label lblScio = new Label(composite_1, SWT.NONE);
																																		lblScio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
																																				1, 1));
																																		lblScio.setText("Sócio");
																																		
																																				text_19 = new Text(composite_1, SWT.BORDER);
																																				text_19.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
																																						2, 1));
																																				
																																						Button button = new Button(composite_1, SWT.NONE);
																																						button.setText("...");

		Group grpEndereo = new Group(parent1, SWT.NONE);
		grpEndereo.setText("Endereço");
		GridLayout gl_grpEndereo = new GridLayout(4, false);
		gl_grpEndereo.verticalSpacing = 10;
		grpEndereo.setLayout(gl_grpEndereo);
		grpEndereo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,
				4, 1));

		Label lblCep_1 = new Label(grpEndereo, SWT.NONE);
		lblCep_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCep_1.setText("CEP");

		CEPTextField textField_1 = new CEPTextField(grpEndereo);
		text_8 = textField_1.getControl();
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);

		Label lblUf = new Label(grpEndereo, SWT.NONE);
		lblUf.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblUf.setText("UF");

		ComboViewer comboViewer_1 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_1 = comboViewer_1.getCombo();
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblCidade = new Label(grpEndereo, SWT.NONE);
		lblCidade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblCidade.setText("Cidade");

		ComboViewer comboViewer_2 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_2 = comboViewer_2.getCombo();
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblBairro = new Label(grpEndereo, SWT.NONE);
		lblBairro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblBairro.setText("Bairro");

		ComboViewer comboViewer_3 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_3 = comboViewer_3.getCombo();
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblRua = new Label(grpEndereo, SWT.NONE);
		lblRua.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblRua.setText("Rua");

		ComboViewer comboViewer_4 = new ComboViewer(grpEndereo, SWT.READ_ONLY);
		Combo combo_4 = comboViewer_4.getCombo();
		combo_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblNmero = new Label(grpEndereo, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNmero.setText("Número");

		text_9 = new Text(grpEndereo, SWT.BORDER);
		text_9.setText("");
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);

		Label lblComplemento = new Label(grpEndereo, SWT.NONE);
		lblComplemento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblComplemento.setText("Complemento");

		text_10 = new Text(grpEndereo, SWT.BORDER);
		text_10.setText("");
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(grpEndereo, SWT.NONE);
		new Label(grpEndereo, SWT.NONE);
	}

}