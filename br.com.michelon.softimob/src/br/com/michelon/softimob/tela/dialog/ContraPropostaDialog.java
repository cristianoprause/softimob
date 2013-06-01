package br.com.michelon.softimob.tela.dialog;

import java.math.BigDecimal;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.nebula.jface.viewer.radiogroup.RadioGroupViewer;
import org.eclipse.nebula.widgets.radiogroup.RadioGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.com.michelon.softimob.modelo.Proposta;
import br.com.michelon.softimob.tela.binding.updateValueStrategy.UVSHelper;
import br.com.michelon.softimob.tela.widget.MoneyTextField;

public class ContraPropostaDialog extends TitleAreaDialog{
	private DataBindingContext m_bindingContext;
	
	private Text text;
	private Text text_1;
	private Proposta proposta;

	private WritableValue valueContraProposta = WritableValue.withValueType(Proposta.class);
	private RadioGroup radioGroup;
	private RadioGroupViewer radioGroupViewer;
	
	public ContraPropostaDialog(Shell parentShell, Proposta proposta) {
		super(parentShell);
		this.proposta = proposta;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label lblValor = new Label(composite, SWT.NONE);
		lblValor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValor.setText("Valor");
		
		MoneyTextField moneyTextField = new MoneyTextField(composite);
		text_1 = moneyTextField.getControl();
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 122;
		text_1.setLayoutData(gd_text_1);
		new Label(composite, SWT.NONE);
		
		radioGroupViewer = new RadioGroupViewer(composite, SWT.NONE);
		radioGroup = radioGroupViewer.getRadioGroup();
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		radioGroupViewer.setContentProvider(ArrayContentProvider.getInstance());
		radioGroupViewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				return "";
//				return ((Proposta)element).getTipoContraProposta() == Proposta.CONTRA_PROPOSTA_CLIENTE ? "Cliente" : "Proprietario";
			}
		});
		radioGroupViewer.setInput(new Integer[]{Proposta.CONTRA_PROPOSTA_CLIENTE, Proposta.CONTRA_PROPOSTA_PROPRIETARIO});
		
		Label lblObservaes = new Label(composite, SWT.NONE);
		lblObservaes.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblObservaes.setText("Observações");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.heightHint = 61;
		text.setLayoutData(gd_text);
		
		setTitleAndMessage();
		
		valueContraProposta.setValue(new Proposta(proposta));
		m_bindingContext = initDataBindings();
		
		return composite;
	}
	
	@Override
	protected void okPressed() {
		Proposta prop = (Proposta) valueContraProposta.getValue();
	}
	
	private void setTitleAndMessage() {
		setTitle("Contra proposta");
		setMessage("Informe os valores da contra-proposta");
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueContraPropostaValorObserveDetailValue = PojoProperties.value(Proposta.class, "valor", BigDecimal.class).observeDetail(valueContraProposta);
		Binding bindValue = bindingContext.bindValue(observeTextText_1ObserveWidget, valueContraPropostaValorObserveDetailValue, UVSHelper.uvsStringToBigDecimal(), UVSHelper.uvsBigDecimalToString());
		ControlDecorationSupport.create(bindValue, SWT.LEFT | SWT.TOP);
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueContraPropostaObservacoesObserveDetailValue = PojoProperties.value(Proposta.class, "observacoes", String.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeTextTextObserveWidget, valueContraPropostaObservacoesObserveDetailValue, null, null);
		//
		IObservableValue observeSingleSelectionRadioGroupViewer = ViewerProperties.singleSelection().observe(radioGroupViewer);
		IObservableValue valueChaveLocalizacaoObserveDetailValue = PojoProperties.value(Proposta.class, "tipoContraProposta", Integer.class).observeDetail(valueContraProposta);
		bindingContext.bindValue(observeSingleSelectionRadioGroupViewer, valueChaveLocalizacaoObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
