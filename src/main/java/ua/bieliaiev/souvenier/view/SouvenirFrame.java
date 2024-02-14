package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;

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
