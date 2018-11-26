package controller;

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
	
	public LevelLoader()
	{
	}
	
	private void readLevel(String fileName)
	{
		curLevel = null;
		String name;
		int width, height, balance, numTurns;
		String[] plants;
		JsonObject turns;
		try
		{	
			JsonObject jsonObject = new JsonParser().parse(readFile(folder+fileName, Charset.defaultCharset())).getAsJsonObject();
			name = jsonObject.get("name").getAsString();
			width = jsonObject.get("width").getAsInt();
			height = jsonObject.get("height").getAsInt();
			balance = jsonObject.get("balance").getAsInt();
			numTurns = jsonObject.get("numturns").getAsInt();
			
			JsonArray arrJson = jsonObject.getAsJsonArray("plants");
			plants = new String[arrJson.size()];
			for(int i = 0; i < arrJson.size(); i++)
			    plants[i] = arrJson.get(i).getAsString();
			turns = jsonObject.get("turns").getAsJsonObject();

			curLevel = new Level(name, width, height, balance, plants, turns, numTurns);

		} catch (IOException e)
		{
			System.err.println("Invalid filepath");
			e.printStackTrace();
		}
		
	}
	public Level getLevel(String filePath)
	{
		readLevel(filePath);
		if(curLevel instanceof Level) return curLevel;
		else throw new IllegalArgumentException();
		
	}

	
	private static String readFile(String path, Charset encoding) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
}
