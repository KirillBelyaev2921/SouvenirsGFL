package ua.bieliaiev.souvenier.view;

import ua.bieliaiev.souvenier.controller.SouvenirController;

import javax.swing.*;

public abstract class MainPanel extends JPanel {
	protected final SouvenirController controller;

	public MainPanel(SouvenirController controller) {
		this.controller = controller;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	}
}
