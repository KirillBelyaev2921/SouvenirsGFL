package ua.bieliaiev.souvenier;

import ua.bieliaiev.souvenier.controller.SouvenirController;
import ua.bieliaiev.souvenier.model.SouvenirStorageFileHandler;
import ua.bieliaiev.souvenier.model.SouvenirService;
import ua.bieliaiev.souvenier.view.SouvenirFrame;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			SouvenirStorageFileHandler fileHandler = new SouvenirStorageFileHandler();
			SouvenirService service = new SouvenirService(fileHandler);
			SouvenirController controller = new SouvenirController(service);

			new SouvenirFrame(controller);
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
