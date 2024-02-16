package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Manufacturer;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;

import java.util.Collection;

public class GetSouvenirsByManufacturerPanel extends MainPanel {
	public GetSouvenirsByManufacturerPanel(SouvenirController controller) {
		super(controller);

		LabelWithListPanel<Manufacturer> manufacturers = new LabelWithListPanel<>("Select manufacturer:",
				controller.getManufacturers().stream().toList());
		this.add(manufacturers);

		LabelWithTextAreaPanel souvenirs = new LabelWithTextAreaPanel("All souvenirs by manufacturer:");
		souvenirs.setText(controller.getSouvenirs());
		this.add(souvenirs);

		manufacturers.addListSelectionListener(e -> {
			Collection<Souvenir> souvenirsList = controller.getSouvenirsByManufacturer(manufacturers.getSelectedValue());
			souvenirs.setText(souvenirsList);
		});
	}
}
