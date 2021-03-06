package br.com.michelon.softimob.tela.view;

import java.io.IOException;
import java.util.List;

import org.eclipse.nebula.widgets.gallery.DefaultGalleryItemRenderer;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.nebula.widgets.gallery.NoGroupRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import br.com.michelon.softimob.aplicacao.service.ArquivoService;
import br.com.michelon.softimob.modelo.Arquivo;

public class PhotosView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.PhotosView"; //$NON-NLS-1$
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private GalleryItem galleryItem;
	private ArquivoService arquivoService = new ArquivoService();

	public PhotosView() {
	}

	public void setFotos(List<Arquivo> fotos) {
		for (Arquivo foto : fotos) {
			
			if(foto.getArquivo() == null)
				continue;
			
			try {
				GalleryItem galleryItem_1 = new GalleryItem(galleryItem, SWT.NONE);
				galleryItem_1.setText(foto.getNome());
				galleryItem_1.setImage(arquivoService.getImage(foto.getArquivo().getArquivo()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		galleryItem.setExpanded(true);
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));

		Composite composite = formToolkit.createComposite(parent, SWT.NONE);
		formToolkit.paintBordersFor(composite);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.marginWidth = 60;
		composite.setLayout(gl_composite);

		final Canvas canvas = new Canvas(composite, SWT.NONE);
		canvas.setLayout(new GridLayout(1, false));
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		formToolkit.adapt(canvas);
		formToolkit.paintBordersFor(canvas);

		Gallery gallery = new Gallery(parent, SWT.BORDER);
		gallery.setGroupRenderer(new NoGroupRenderer());
		gallery.setItemRenderer(new DefaultGalleryItemRenderer());

		galleryItem = new GalleryItem(gallery, SWT.NONE);
		
		gallery.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Gallery gal = (Gallery) e.widget;

				if (gal.getSelectionCount() < 1)
					return;

				GalleryItem gi = gal.getSelection()[0];
				Image img = gi.getImage();

				GC gc = new GC(canvas);
				gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 0, 0, canvas.getClientArea().width, canvas.getClientArea().height);
			}

		});

		initializeToolBar();
		initializeMenu();
	}


	private void initializeToolBar() {
	}

	private void initializeMenu() {
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
