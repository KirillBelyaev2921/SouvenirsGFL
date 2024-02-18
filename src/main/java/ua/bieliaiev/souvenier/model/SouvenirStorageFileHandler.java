package ua.bieliaiev.souvenier.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class that encapsulates all requests for the data file.
 * Program based on minimizing requests to files and only get data
 * at the start and save data at the end. All other operations with data
 * should be done with {@link SouvenirStorage}.
 */
public class SouvenirStorageFileHandler {
	private static final String FILE_NAME = "src/main/resources/souvenirs.dat";

	public SouvenirStorageFileHandler() throws IOException {
		Path path = Path.of(FILE_NAME);
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
	}

	public void saveSouvenirs(SouvenirStorage souvenirs) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			outputStream.writeObject(souvenirs);
		}
	}

	public SouvenirStorage readSouvenirs() throws IOException, ClassNotFoundException {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			return (SouvenirStorage) inputStream.readObject();
		} catch (EOFException e) {
			return new SouvenirStorage();
		}
	}
}
