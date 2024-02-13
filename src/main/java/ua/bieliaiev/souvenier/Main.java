package ua.bieliaiev.souvenier;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.SouvenirService;
import ua.bieliaiev.souvenier.view.SouvenirFrame;

public class Main {
	public static void main(String[] args) {
		SouvenirService service = new SouvenirService();
		SouvenirController controller = new SouvenirController(service);
		SouvenirFrame view = new SouvenirFrame(controller);
	}
}
