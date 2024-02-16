package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;

public class GetManufacturersPanel extends MainPanel {
	public GetManufacturersPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextAreaPanel manufacturers = new LabelWithTextAreaPanel("All manufacturers:");
		manufacturers.setText(controller.getManufacturers());
		this.add(manufacturers);
	}
}
