package tile;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import main.GamePanel;
import main.UtilityTool;

public class Map {
	GamePanel gamePanel;
	public int width;
	public int height;
	public int data[][];
	public Tile[] tiles;
	
	public Map(String path, GamePanel _gamePanel) {
		gamePanel = _gamePanel;
		try {
			//LOAD JSON
			JSONObject map = new JSONObject(Files.readString(Path.of(path)));
			JSONArray mapJsonArray = map.getJSONArray("layers").getJSONObject(0).getJSONArray("data");
			
			width = map.getInt("width");
			height = map.getInt("height");
			
			//LOAD MAP
			data = new int[width][height];
			
			int k = 0;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					data[j][i] = mapJsonArray.getInt(k);
					k++;
				}
			}
			
			//LOAD TILES
			String tilesetPath = map.getJSONArray("tilesets").getJSONObject(0).getString("source");
			tilesetPath = "res/tilesets/" + tilesetPath.substring(tilesetPath.lastIndexOf("/")+1);
			JSONObject tileset = new JSONObject(Files.readString(Path.of(tilesetPath)));
			JSONArray tileJsonArray = tileset.getJSONArray("tiles");
			
			tiles = new Tile[tileset.getInt("tilecount")+1];
			
			for (int i = 0; i < tileJsonArray.length(); i++) {
				JSONObject object = tileJsonArray.getJSONObject(i);
				
				int index = object.getInt("id")+1;
				String imageName = object.getString("image");
				imageName = imageName.substring(imageName.lastIndexOf("/")+1);
				Boolean hasCollision = object.getJSONArray("properties").getJSONObject(0).getBoolean("value");
				setup(index, imageName, hasCollision);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setup(int index, String imageName, boolean hasCollision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tiles[index] = new Tile();
			tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName));
			tiles[index].image = uTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
			tiles[index].collision = hasCollision;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
