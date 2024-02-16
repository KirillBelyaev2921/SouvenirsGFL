package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithTextAreaPanel;

public class GetSouvenirsGroupedByYearPanel extends MainPanel {
	public GetSouvenirsGroupedByYearPanel(SouvenirController controller) {
		super(controller);

		LabelWithTextAreaPanel souvenirs = new LabelWithTextAreaPanel("All souvenirs by year:");
		souvenirs.setText(controller.getSouvenirsGroupingByYears());
		this.add(souvenirs);

	}
}
