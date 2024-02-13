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
				// TODO save the souvenirs
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

		toolbar.add(addManufacturerButton);

		JButton getSouvenirsButton = new JButton("Get Souvenirs");
		getSouvenirsButton.addActionListener(e -> setPanel(new GetSouvenirsPanel(controller)));
		toolbar.add(getSouvenirsButton);

		JButton getManufacturersButton = new JButton("Get Manufacturers");
		toolbar.add(getManufacturersButton);


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
