/*
	Name: Creighton Young
	Date: 9/24/2020
	Assigment: Make some pipes appear
*/

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;



public class Tube extends Sprite
{
	int x;
	int y;
	int width;
	int height;
	int scrollPos;
	Model model;
	boolean onCurrentTube=false;
	static BufferedImage tube_image=null;

	Tube(int xPos, int yPos)
	{
		x = xPos;
		y = yPos;
		width=55;
		height=400;
		scrollPos=0;
		super.type="tube";
		if (tube_image==null)
		{
			tube_image=View.loadImage("tube.png");
		}
	}

	Json tubeMarshal()
	{
		Json ob = Json.newObject();
		ob.add("x",x);
		ob.add("y",y);
		return ob;
	}

	public Tube(Json ob)
	{
		x=(int)ob.getLong("x");
		y=(int)ob.getLong("y");
		width=55;
		height=400;
		super.type="tube";
		tube_image=View.loadImage("tube.png");
	}

	void update()
	{

	}

	void drawYourself(Graphics g)
	{

		g.drawImage(tube_image, x-super.scrollPos, y, null);
	}

	void Collision(Sprite sprite)
	{
		if (sprite.type.equals("mario"))
		{
			Mario mario=(Mario) sprite;
			if (mario.x+mario.width+2>x && (mario.x+mario.width+2)<(x+width+60)) //the two helps us with clipping into the pipe
			{
				if (mario.y>y)
				{
					model.rightHittingTube=true;
				}
				else if (y-mario.y<=50 && mario.x+mario.width>x && mario.frameCounter==0)
				{
					mario.onTube=true;
					mario.y=y-95;
					mario.onGround=true;
					onCurrentTube=true;
				}
			}
			else if (x+2<mario.x && x+width+2>mario.x) //this two also helps us with clipping
			{
				if (mario.y>y)
				{

					model.leftHittingTube=true;
				}
			}
			if (mario.onTube && (mario.x+35<x || mario.x>x+width) && onCurrentTube) //the 40 is an arbitrary that makes it look cleaner when mario should fall off the tube
			{
				mario.onTube=false;
			}
			sprite=mario;
		}
		else if (sprite.type.equals("goomba"))
		{
			Goomba goomba=(Goomba) sprite;
			if (goomba.x+goomba.width>x && (goomba.x+goomba.width+2)<(x+width+60))
			{
				goomba.moveAmount=-5;
			}
			else if(x+2<goomba.x && x+width+2>goomba.x)
			{
				goomba.moveAmount=5;
			}
		}
	}


}
