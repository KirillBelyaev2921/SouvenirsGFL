package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;
import ua.bieliaiev.souvenier.view.compact.LabelWithListPanel;

import javax.swing.*;

public class RemoveSouvenirPanel extends MainPanel {
	private Souvenir selectedSouvenir;

	public RemoveSouvenirPanel(SouvenirController controller) {
		super(controller);
		
		LabelWithListPanel<Souvenir> souvenirs = new LabelWithListPanel<>("All souvenirs:",
				controller.getSouvenirs().stream().toList());
		this.add(souvenirs);

		JButton removeSouvenir = new JButton("Remove souvenir");
		removeSouvenir.addActionListener(e -> {
			if (controller.removeSouvenir(selectedSouvenir)) {
				removeSouvenir.setText("Removed!");
				removeSouvenir.setEnabled(false);
			} else {
				removeSouvenir.setText("Wrong data!");
			}
		});
		this.add(removeSouvenir);

		souvenirs.addListSelectionListener(e -> selectedSouvenir = souvenirs.getSelectedValue());
	}
}
