package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;

public class GetSouvenirsPanel extends MainPanel {
	public GetSouvenirsPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextAreaPanel souvenirs = new LabelWithTextAreaPanel("All souvenirs:");
		souvenirs.setText(controller.getSouvenirs());
		this.add(souvenirs);
	}
}
