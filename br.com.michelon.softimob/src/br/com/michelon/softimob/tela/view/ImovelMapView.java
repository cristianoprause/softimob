package br.com.michelon.softimob.tela.view;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import br.com.michelon.softimob.aplicacao.map.GMap;
import br.com.michelon.softimob.aplicacao.map.LatLng;
import br.com.michelon.softimob.aplicacao.map.MapAdapter;
import br.com.michelon.softimob.modelo.Endereco;

public class ImovelMapView extends ViewPart {

	public static final String ID = "br.com.michelon.softimob.tela.view.ImovelMapView"; //$NON-NLS-1$

	static final private String INIT_CENTER = "33.0,5.0";
	static final private int INIT_TYPE = GMap.TYPE_HYBRID;
	private GMap gmap = null;
	private Composite controls;
	private Text location;

	private Text addr;

	private Spinner zoom;
	
	public ImovelMapView() {
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		init(parent);
		//
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void init(Composite cp) {
		SashForm sash = new SashForm(cp, SWT.VERTICAL);
		createMap(sash);
		controls = new Composite(sash, SWT.BORDER);
//		createCenterControl(controls);
//		createZoomControl(controls);
//		createMapTypeControl(controls);
		createAddressControl(controls);
//		createMarkerControl(controls);
		sash.setWeights(new int[] { 8, 1 });
	}

	private void createMap(Composite parent) {
		gmap = new GMap(parent, SWT.NONE);

		gmap.setCenter(stringToLatLng(INIT_CENTER));

		gmap.setType(INIT_TYPE);
	}

	private void createCenterControl(Composite parent) {
		new Label(parent, SWT.None).setText("Localização:");
		location = new Text(parent, SWT.BORDER);
		location.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		location.setText(INIT_CENTER);
		location.setFont(new Font(parent.getDisplay(), "Arial", 9, SWT.NORMAL));
		location.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				gmap.setCenter(stringToLatLng(location.getText()));
			}
		});
		gmap.addMapListener(new MapAdapter() {
			@Override
			public void centerChanged() {
				location.setText(gmap.getCenter().toString());
			}
		});
	}

	private void createZoomControl(Composite parent) {
		new Label(parent, SWT.None).setText("Zoom:");
		zoom = new Spinner(parent, SWT.NORMAL);
		zoom.setMaximum(20);
		zoom.setMinimum(0);
		zoom.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				gmap.setZoom(Integer.parseInt(zoom.getText()));
			}
		});
		gmap.addMapListener(new MapAdapter() {
			@Override
			public void zoomChanged() {
				zoom.setSelection(gmap.getZoom());
			}
		});
	}

	private void createMapTypeControl(Composite parent) {
	}

	private void createAddressControl(Composite parent) {
		controls.setLayout(new GridLayout(3, false));
		Label label = new Label(parent, SWT.None);
		label.setText("Endereço");
		addr = new Text(parent, SWT.BORDER);
		addr.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		addr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				gmap.gotoAddress(addr.getText());
			}
		});
		addr.setFont(new Font(parent.getDisplay(), "Arial", 9, SWT.NORMAL));
		Button goToAddr = new Button(parent, SWT.PUSH);
		goToAddr.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		goToAddr.setText("Ir até");
		goToAddr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gmap.gotoAddress(addr.getText());
			}
		});
//		Button resolveAddr = new Button(parent, SWT.PUSH);
//		resolveAddr.setText("resolve");
//		resolveAddr.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				gmap.resolveAddress();
//			}
//		});
//		gmap.addMapListener(new MapAdapter() {
//			@Override
//			public void addressResolved() {
//				addr.setText(gmap.getAddress());
//			}
//		});
	}

	private void createMarkerControl(Composite parent) {
		final InputDialog markerDialog = new InputDialog(parent.getShell(), "Marker Name", "Enter Name", null, null);
		Button addMarker = new Button(parent, SWT.PUSH);
		addMarker.setText("add Marker");
		addMarker.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				markerDialog.open();
				String result = markerDialog.getValue();
				if (result != null && result.length() > 0) {
					gmap.addMarker(result);
				}
			}
		});
	}

	private LatLng stringToLatLng(String input) {
		LatLng result = null;
		if (input != null) {
			String temp[] = input.split(",");
			if (temp.length == 2) {
				try {
					double lat = Double.parseDouble(temp[0]);
					double lon = Double.parseDouble(temp[1]);
					result = new LatLng(lat, lon);
				} catch (NumberFormatException ex) {
				}
			}
		}
		return result;
	}

	public void stop() {
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
//		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
//		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	public void setEnderecoPadrao(Endereco endereco) {
		gmap.setEnderecoPadrao(endereco.toString());
		addr.setText(endereco.toString());
	}

}
