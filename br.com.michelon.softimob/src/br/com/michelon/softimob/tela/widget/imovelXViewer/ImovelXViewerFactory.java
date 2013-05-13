package br.com.michelon.softimob.tela.widget.imovelXViewer;

import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn.SortDataType;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.swt.SWT;

public class ImovelXViewerFactory extends XViewerFactory {

	public ImovelXViewerFactory(String namespace) {
		super(namespace);
	}

	public ImovelXViewerFactory() {
		super(COLUMN_NAMESPACE);
		registerColumns(codigo, metragem, angariador, tipo, proprietario, tipo, status);
	}

	private static String COLUMN_NAMESPACE = "xviewer.test";
	public static XViewerColumn codigo = new XViewerColumn(COLUMN_NAMESPACE+ ".codigo", "Código", 350, SWT.LEFT, true, SortDataType.String,false, null);
	public static XViewerColumn metragem = new XViewerColumn(COLUMN_NAMESPACE + ".metragem", "Metragem", 250, SWT.LEFT, true, SortDataType.String, false, null);
	public static XViewerColumn angariador = new XViewerColumn(COLUMN_NAMESPACE + ".angariador", "Angariador", 250, SWT.LEFT, true, SortDataType.String, false, null);
	public static XViewerColumn tipo = new XViewerColumn(COLUMN_NAMESPACE + ".tipo", "Tipo", 250, SWT.LEFT, true, SortDataType.String, false, null);
	public static XViewerColumn proprietario = new XViewerColumn(COLUMN_NAMESPACE + ".proprietario", "Poprietário", 250, SWT.LEFT, true, SortDataType.String, false, null);
	public static XViewerColumn status = new XViewerColumn(COLUMN_NAMESPACE + ".status", "Status", 250, SWT.LEFT, true, SortDataType.String, false, null);

	@Override
	public boolean isAdmin() {
		return true;
	}

	@Override
	public boolean isLoadedStatusLabelAvailable() {
		return false;
	}

	@Override
	public boolean isFilterUiAvailable() {
		return false;
	}

	@Override
	public boolean isSearchUiAvailable() {
		return false;
	}

}
