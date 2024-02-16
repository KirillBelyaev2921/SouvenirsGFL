package ua.bieliaiev.souvenier.view.main_panels;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.Souvenir;
import ua.bieliaiev.souvenier.view.MainPanel;

import javax.swing.*;
import java.util.Collection;

public class GetSouvenirsPanel extends MainPanel {
	public GetSouvenirsPanel(SouvenirController controller) {
		super(controller);

		JLabel souvenirsLabel = new JLabel("All souvenirs:");
		JList<Souvenir> souvenirs = new JList<>();
		Collection<Souvenir> souvenirsList = controller.getSouvenirs();
		souvenirs.setListData(souvenirsList.toArray(new Souvenir[0]));
		JScrollPane souvenirsPane = new JScrollPane(souvenirs);
		this.add(souvenirsLabel);
		this.add(souvenirsPane);
	}
}
