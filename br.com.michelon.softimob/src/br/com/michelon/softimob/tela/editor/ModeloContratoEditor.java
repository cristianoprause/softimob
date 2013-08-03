package br.com.michelon.softimob.tela.editor;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ImageRepository;

import br.com.michelon.softimob.aplicacao.helper.DialogHelper;
import br.com.michelon.softimob.aplicacao.service.GenericService;
import br.com.michelon.softimob.aplicacao.service.ModeloContratoService;
import br.com.michelon.softimob.modelo.Arquivo;
import br.com.michelon.softimob.modelo.ModeloContrato;

public class ModeloContratoEditor extends GenericEditor<ModeloContrato> {
	
	public static final String ID = "br.com.michelon.softimob.tela.editor.ModeloContratoEditor";
	
	private ModeloContratoService service = new ModeloContratoService();
	
	private Text text;
	private Text text_1;

	public ModeloContratoEditor() {
		super(ModeloContrato.class);
	}
	
	@Override
	public GenericService<ModeloContrato> getService() {
		return service;
	}
	
	@Override
	public void afterCreatePartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(3, false);
		gl_parent.verticalSpacing = 10;
		parent.setLayout(gl_parent);
		
		Label lblDescrio = new Label(parent, SWT.NONE);
		lblDescrio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrio.setText("Descrição");
		
		text = new Text(parent, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text.widthHint = 260;
		text.setLayoutData(gd_text);
		new Label(parent, SWT.NONE);
		
		Label lblModelo = new Label(parent, SWT.NONE);
		lblModelo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModelo.setText("Modelo");
		
		text_1 = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnSelecionar = new Button(parent, SWT.NONE);
		btnSelecionar.setImage(ImageRepository.SEARCH_16.getImage());
		btnSelecionar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Arquivo> files = DialogHelper.openDocDialog();
				
				if(files != null && files.size() > 0){
					Arquivo file = files.get(0);
					getCurrentObject().setArquivo(file);
					
					Object valueCopy = value.getValue();
					value.setValue(null);
					value.setValue(valueCopy);
				}
			}
		});
	}
	
	protected DataBindingContext initBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTextObserveWidget = WidgetProperties.text(SWT.Modify).observe(text);
		IObservableValue valueNomeObserveDetailValue = PojoProperties.value(ModeloContrato.class, "nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextTextObserveWidget, valueNomeObserveDetailValue, null, null);
		//
		IObservableValue observeTextText_1ObserveWidget = WidgetProperties.text(SWT.Modify).observe(text_1);
		IObservableValue valueArquivonomeObserveDetailValue = PojoProperties.value(ModeloContrato.class, "arquivo.nome", String.class).observeDetail(value);
		bindingContext.bindValue(observeTextText_1ObserveWidget, valueArquivonomeObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
