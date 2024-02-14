package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Souvenir;

import javax.swing.*;
import java.util.Collection;

public class RemoveSouvenirPanel extends MainPanel {
	private Souvenir selectedSouvenir;

	public RemoveSouvenirPanel(SouvenirController controller) {
		super(controller);

		JLabel souvenirsLabel = new JLabel("All souvenirs:");
		JList<Souvenir> souvenirs = new JList<>();
		souvenirs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Collection<Souvenir> souvenirsList = controller.getSouvenirs();
		souvenirs.setListData(souvenirsList.toArray(new Souvenir[0]));
		JScrollPane souvenirsPane = new JScrollPane(souvenirs);
		this.add(souvenirsLabel);
		this.add(souvenirsPane);

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
