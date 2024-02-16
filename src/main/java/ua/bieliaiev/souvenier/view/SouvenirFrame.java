package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.view.main_panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SouvenirFrame {
	private final SouvenirController controller;
	private MainPanel mainPanel;
	private final JFrame frame;

	public SouvenirFrame(SouvenirController controller) {
		this.controller = controller;
		mainPanel = new GetSouvenirsPanel(this.controller);

		frame = new JFrame("Souvenirs");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.saveData();
				System.exit(0);
			}
		});

		frame.add(mainPanel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		frame.add(buttonsPanel, BorderLayout.SOUTH);

		// Create a Debug toolbar.
		JToolBar toolbar = new JToolBar("Toolbar");
		toolbar.setLayout(new GridLayout(4, 4));
		frame.add(toolbar, BorderLayout.NORTH);

		JButton addSouvenirButton = new JButton("Add Souvenir");
		addSouvenirButton.addActionListener(e -> setPanel(new AddSouvenirPanel(controller)));
		toolbar.add(addSouvenirButton);

		JButton addManufacturerButton = new JButton("Add Manufacturer");
		addManufacturerButton.addActionListener(e -> setPanel(new AddManufacturerPanel(controller)));
		toolbar.add(addManufacturerButton);

		JButton getSouvenirsButton = new JButton("Get Souvenirs");
		getSouvenirsButton.addActionListener(e -> setPanel(new GetSouvenirsPanel(controller)));
		toolbar.add(getSouvenirsButton);

		JButton getManufacturersButton = new JButton("Get Manufacturers");
		getManufacturersButton.addActionListener(e -> setPanel(new GetManufacturersPanel(controller)));
		toolbar.add(getManufacturersButton);

		JButton editSouvenirButton = new JButton("Edit Souvenir");
		editSouvenirButton.addActionListener(e -> setPanel(new EditSouvenirPanel(controller)));
		toolbar.add(editSouvenirButton);

		JButton editManufacturerButton = new JButton("Edit Manufacturer");
		editManufacturerButton.addActionListener(e -> setPanel(new EditManufacturerPanel(controller)));
		toolbar.add(editManufacturerButton);

		JButton removeSouvenirButton = new JButton("Remove Souvenir");
		removeSouvenirButton.addActionListener(e -> setPanel(new RemoveSouvenirPanel(controller)));
		toolbar.add(removeSouvenirButton);

		JButton removeManufacturerButton = new JButton("Remove Manufacturer");
		removeManufacturerButton.addActionListener(e -> setPanel(new RemoveManufacturerPanel(controller)));
		toolbar.add(removeManufacturerButton);

		JButton getSouvenirsByManufacturerButton = new JButton("Get souvenirs by manufacturer");
		getSouvenirsByManufacturerButton.addActionListener(e -> setPanel(new GetSouvenirsByManufacturerPanel(controller)));
		toolbar.add(getSouvenirsByManufacturerButton);

		JButton getManufacturersByPriceButton = new JButton("Get manufacturers by lower price");
		getManufacturersByPriceButton.addActionListener(e -> setPanel(new GetManufacturersByLowerPricePanel(controller)));
		toolbar.add(getManufacturersByPriceButton);

		JButton getManufacturersWithSouvenirsButton = new JButton("Get manufacturers with their souvenirs");
		getManufacturersWithSouvenirsButton.addActionListener(e -> setPanel(new GetManufacturersWithSouvenirsPanel(controller)));
		toolbar.add(getManufacturersWithSouvenirsButton);

		JButton getManufacturersByNameAndYearButton = new JButton("Get manufacturers by their souvenirs name and year");
		getManufacturersByNameAndYearButton.addActionListener(e -> setPanel(new GetManufacturersByNameAndYearPanel(controller, frame)));
		toolbar.add(getManufacturersByNameAndYearButton);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public synchronized void setPanel(MainPanel panel) {
		frame.remove(mainPanel);
		mainPanel = panel;
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.pack();
	}

}
