package br.com.michelon.softimob.aplicacao.command;

import br.com.michelon.softimob.aplicacao.editorInput.ComodoEditorInput;
import br.com.michelon.softimob.tela.editor.TipoComodoEditor;

public class CmdComodo extends GenericAbstractHandler {

	public CmdComodo() {
		this.editorInput = new ComodoEditorInput();
		this.id = TipoComodoEditor.ID;
	}
	
}
