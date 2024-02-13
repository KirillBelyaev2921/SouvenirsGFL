package ua.bieliaiev.souvenier.model;

import java.io.*;

public class SouvenirFileHandler {
	private static final String FINE_NAME = "resources/souvenirs.dat";
	private final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FINE_NAME));
	private final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FINE_NAME));

	public SouvenirFileHandler() throws IOException {
	}

	public void saveSouvenirs(SouvenirStorage souvenirs) throws IOException {
		objectOutputStream.writeObject(souvenirs);
	}

	public SouvenirStorage readSouvenirs() throws IOException, ClassNotFoundException {
		return (SouvenirStorage) objectInputStream.readObject();
	}
}
