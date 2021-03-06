package com.acidpanther.javagameprogrammingcourse.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.acidpanther.javagameprogrammingcourse.entity.mob.Chaser;
import com.acidpanther.javagameprogrammingcourse.entity.mob.Dummy;

public class SpawnLevel extends Level{
	
	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
		
		//add(new Dummy(10, 8));
		add(new Chaser(10, 10));
	}
}
