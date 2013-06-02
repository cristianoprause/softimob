package br.com.michelon.softimob.aplicacao.helper.listElementDialog;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;
import br.com.michelon.softimob.aplicacao.helper.ShellHelper;
import br.com.michelon.softimob.aplicacao.service.ClienteService;
import br.com.michelon.softimob.aplicacao.service.FuncionarioService;
import br.com.michelon.softimob.aplicacao.service.PlanoContaService;
import br.com.michelon.softimob.aplicacao.service.TipoComodoService;
import br.com.michelon.softimob.aplicacao.service.TipoImovelService;

public class ListElementDialogHelper<T> {
	
	public static void addListElementDialogToText(final TipoDialog tipoDialog, Text text, final WritableValue value, final String property){
		addListElementDialogToText(tipoDialog, text, value, property, null);
	}
	
	public static void addListElementDialogToText(final TipoDialog tipoDialog, Text text, final WritableValue value, final String property, final OkListElementDialogListener listener){
		text.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ALT){
					openDialogAndSetValue(tipoDialog, value, property, listener);
				}
			}
		});
	}
	
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final WritableValue value, final String property){
		addSelectionListDialogToButton(tipoDialog, button, value, property, null);
	}
	
	public static void addSelectionListDialogToButton(final TipoDialog tipoDialog, Button button, final WritableValue value, final String property, final OkListElementDialogListener listener){
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openDialogAndSetValue(tipoDialog, value, property, listener);
			}
		});
	}
	
	private static void openDialogAndSetValue(TipoDialog tipoDialog, final WritableValue value, final String property, final OkListElementDialogListener listener) {

		tipoDialog.openDialogAndExecuteListeners(new OkListElementDialogListener() {
			@Override
			public void ok(Object obj) {
				try{
					Object copy = value.getValue();
					value.setValue(null);
					
					ReflectionHelper.setAtribute(copy, property, obj);
					
					value.setValue(copy);
					
					listener.ok(obj);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

	public void openDialogAndExecuteListenerIfOkPressed(TipoDialog tipoDialog, Button btn, OkListElementDialogListener okListener){
		btn.addSelectionListener(new SelectionAdapter() {
			
			
			
		});
	}
	
	public enum TipoDialog{
		
		FUNCIONARIO("Funcionários", "Selecione um funcionário."),
		CLIENTE("Clientes", "Selecione um cliente."), 
		COMODO("Cômodos", "Selecione um cômodo."),
		TIPO_IMOVEL("Tipo de imóvel", "Selecione um tipo de imóvel."),
		IMOVEL("Imóveis", "Selecione um imóvel."), 
		PLANOCONTA("Plano de Contas", "Selecione uma conta."), 
		COMISSIONADO("Comissionados", "Selecione um cliente ou funcionário.");
		
		private final String title;
		private final String message;
	
		private TipoDialog(String title, String message) {
			this.title = title;
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
		
		public String getTitle() {
			return title;
		}
		
		public void openDialogAndExecuteListeners(OkListElementDialogListener listener){
			openDialogAndExecuteListeners(Arrays.asList(listener));
		}
		
		public void openDialogAndExecuteListeners(List<OkListElementDialogListener> listeners){
			ElementListSelectionDialog dialog = openDialog();
			
			if(dialog.open() == IDialogConstants.OK_ID){
				
				for(OkListElementDialogListener listener : listeners)
					listener.ok(dialog.getFirstResult());
			}
		}
		
		public String getDescription(Object obj){
			return obj.toString();
		}
		
		public ElementListSelectionDialog openDialog(){
			ElementListSelectionDialog dialog = new ElementListSelectionDialog(ShellHelper.getActiveShell(), new LabelProvider(){
				@Override
				public String getText(Object element) {
					return getDescription(element);
				}
			});
			
			dialog.setTitle(getTitle());
			dialog.setMessage(getMessage());
			dialog.setElements(getElements());
			
			return dialog;
		}
		
		public Object[] getElements(){
			if(equals(FUNCIONARIO)){
				return new FuncionarioService().findAll().toArray();
			} else if(equals(CLIENTE)){
				return new ClienteService().findAll().toArray();
			} else if(equals(PLANOCONTA)){
				return new PlanoContaService().findAll().toArray();
			} else if(equals(COMODO)){
				return new TipoComodoService().findAll().toArray();
			} else if(equals(TIPO_IMOVEL)){
				return new TipoImovelService().findAll().toArray();
			} else{
				return null;
			}
		}
	}
	
}