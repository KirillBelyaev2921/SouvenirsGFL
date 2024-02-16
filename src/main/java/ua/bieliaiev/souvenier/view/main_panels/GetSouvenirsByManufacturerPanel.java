package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;

import javax.swing.*;
import java.util.Collection;

public class GetSouvenirsByManufacturerPanel extends MainPanel {
	public GetSouvenirsByManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("Select manufacturer:",
				controller.getManufacturers().stream().toList());
		this.add(manufacturers);

		JLabel souvenirsLabel = new JLabel("All souvenirs by manufacturer:");
		JList<Souvenir> souvenirs = new JList<>();
		souvenirs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane souvenirsPane = new JScrollPane(souvenirs);
		this.add(souvenirsLabel);
		this.add(souvenirsPane);

		manufacturers.addListSelectionListener(e -> {
			Collection<Souvenir> souvenirsList = controller.getSouvenirsByManufacturer(manufacturers.getSelectedValue());
			souvenirs.setListData(souvenirsList.toArray(new Souvenir[0]));
		});
	}
}
