package controller;

/**
 * The LevelLoader class, used to load default levels from JSON and create Level objects
 * @author Simon Krol
 * @version Nov 25, 2018
 */
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Level;

public class LevelLoader
{

	private static final String folder = "res/levels/";
	private Level curLevel;

	/**
	 * Create a levelloader
	 */
	public LevelLoader()
	{
	}

	/**
	 * Read the JSON information from the given file and create a level from it
	 * @param fileName The JSON file being read from
	 */
	private void readLevel(String fileName)
	{
		curLevel = null;
		String name;
		int width, height, balance, numTurns, passiveGen;
		String[] plants;
		JsonObject turns;
		try
		{
			JsonObject jsonObject = new JsonParser().parse(readFile(folder + fileName, Charset.defaultCharset()))
					.getAsJsonObject();
			name = jsonObject.get("name").getAsString();
			width = jsonObject.get("width").getAsInt();
			height = jsonObject.get("height").getAsInt();
			balance = jsonObject.get("balance").getAsInt();
			numTurns = jsonObject.get("numturns").getAsInt();
			passiveGen = jsonObject.get("passiveGen").getAsInt();

			JsonArray arrJson = jsonObject.getAsJsonArray("plants");
			plants = new String[arrJson.size()];
			for (int i = 0; i < arrJson.size(); i++)
				plants[i] = arrJson.get(i).getAsString();
			turns = jsonObject.get("turns").getAsJsonObject();

			curLevel = new Level(name, width, height, balance, plants, turns, numTurns, passiveGen);

		} catch (IOException e)
		{
			System.err.println("Invalid filepath");
			e.printStackTrace();
		}

	}

	/**
	 * Get a level object for the given filePath
	 * @param filePath The path to the levelData
	 * @return The level representing the filePath
	 */
	public Level getLevel(String filePath)
	{
		readLevel(filePath);
		if (curLevel instanceof Level)
			return curLevel;
		else
			throw new IllegalArgumentException();

	}

	/**
	 * Read a file and return it as a String
	 * @param path The path to the file being read
	 * @param encoding How the file is encoded
	 * @return The file, as a String
	 * @throws IOException If file doesnt exist
	 */
	private static String readFile(String path, Charset encoding) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
