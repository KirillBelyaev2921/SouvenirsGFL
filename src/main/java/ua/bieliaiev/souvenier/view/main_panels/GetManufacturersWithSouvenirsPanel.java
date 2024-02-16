package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;

public class GetManufacturersWithSouvenirsPanel extends MainPanel {
	public GetManufacturersWithSouvenirsPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextAreaPanel manufacturers = new LabelWithTextAreaPanel("All manufacturers with souvenirs:");
		manufacturers.setText(controller.getManufacturersWithSouvenirs());
		this.add(manufacturers);
	}
}
