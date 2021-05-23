/*
	Name: Creighton Young
	Date: 9/11/2020
	Assigment: Make a turtle dance and make a button disappear
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	JButton b1;
	BufferedImage tube_image;
	Model model;
	int scrollPos=0;
	BufferedImage[] mario_images;
	BufferedImage[] goomba_images;
	int marioImage=0;

	View(Controller c, Model m)
	{
		model = m;



		c.setView(this);




		mario_images = new BufferedImage[5];

	}
	void removeButton()
	{
		this.remove(b1);
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{

		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(0,204,0));

		for (int x=0;x<model.sprites.size();x++)
		{
			model.sprites.get(x).drawYourself(g);
		}
		g.fillRect(0, 445, this.getWidth(),this.getHeight());


	}

	void setModel(Model m)
	{
		model = m;
	}

	static BufferedImage loadImage(String imageFile)
	{
		BufferedImage loadedImage=null;
		try
		{
			loadedImage=ImageIO.read(new File(imageFile));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return loadedImage;
	}
}
