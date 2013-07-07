package br.com.michelon.softimob.tela.widget.xViewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.XViewerStyledTextLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import br.com.michelon.softimob.aplicacao.helper.ReflectionHelper;

public class GenericXViewerLabelProvider extends XViewerStyledTextLabelProvider{

	private XViewer xViewer;
	
	public GenericXViewerLabelProvider(XViewer xViewer) {
		super(xViewer);
		
		this.xViewer = xViewer;
	}

	@Override
	public Color getBackground(Object arg0, XViewerColumn arg1, int arg2) throws Exception {
		return xViewer.getTree().getBackground();
	}

	@Override
	public Image getColumnImage(Object arg0, XViewerColumn arg1, int arg2) throws Exception {
		GenericXViewerColumn col = ((GenericXViewer<?>)xViewer).getGenericXViewerColumn(arg1.getId());
		
		XViewerColumnProperties xViewerColumnProperties = col.getProperties().get(arg0.getClass());
		Image image = xViewerColumnProperties == null ? null : xViewerColumnProperties.getImage();
		
		return image;
	}

	@Override
	public Font getFont(Object arg0, XViewerColumn arg1, int arg2) throws Exception {
		return xViewer.getTree().getFont();
	}

	@Override
	public Color getForeground(Object arg0, XViewerColumn arg1, int arg2) throws Exception {
		return xViewer.getTree().getForeground();
	}

	@Override
	public StyledString getStyledText(Object arg0, XViewerColumn arg1, int arg2) throws Exception {
		GenericXViewerColumn col = ((GenericXViewer<?>)xViewer).getGenericXViewerColumn(arg1.getId());
		
		XViewerColumnProperties xViewerColumnProperties = col.getProperties().get(arg0.getClass());
		String property = xViewerColumnProperties == null ? null : xViewerColumnProperties.getProperty();
		
		Object obj = property == null ? StringUtils.EMPTY : ReflectionHelper.getAtribute(arg0, property);
			
		return new StyledString(FormatterHelper.formatObject(obj), null);
	}
	
}
