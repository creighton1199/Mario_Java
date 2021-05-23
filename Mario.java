/*
	Name: Creighton Young
	Date: 9/24/2020
	Assigment: Make a bunch of pipes appear
*/

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;


public class Mario extends Sprite
{

    int x;
    int y;
    double vert_vel; //mario's vertical velocity
    int frameCounter;
    int height;
    int width;
    boolean onGround;
    boolean onTube;
    static BufferedImage [] mario_images=null;
    int marioImage=0;
	boolean rightKeyPress=false;
	boolean leftKeyPress=false;


    Mario(int x, int y)
    {
        this.x=x;
        this.y=y;
        frameCounter=0;
        width=60;
        height=95;
        vert_vel=0;
        super.type="mario";
        if (mario_images==null)
        {
            mario_images=new BufferedImage[5];
        }
        mario_images[0] = View.loadImage("mario1.png");
        mario_images[1] = View.loadImage("mario2.png");
        mario_images[2] = View.loadImage("mario3.png");
        mario_images[3] = View.loadImage("mario4.png");
        mario_images[4] = View.loadImage("mario5.png");
    }
    void update()
    {

        //mario jumping
        if (frameCounter>0 && frameCounter<10)
        {
            vert_vel-=2.5;
            y+=vert_vel;
            frameCounter++;
            onTube=false;
            onGround=false;
        }
        else if(y >= 350)
		{
			vert_vel = 0.0;
			y = 350; // snap back to the ground
            frameCounter=0;
            onTube=false;
            onGround=true;
		}
        else if (onTube)
        {
            vert_vel=0;
            onGround=true;
        }
        else
        {
            vert_vel += .9;
		    y += vert_vel;
        }
    }

    void drawYourself(Graphics g)
    {
        g.drawImage(mario_images[marioImage],50,y,null);

        if (rightKeyPress)
        {
            if (marioImage==4)
                marioImage=0;
            else
                marioImage++;
        }
        else if (leftKeyPress)
        {
            if (marioImage==0)
                marioImage=4;
            else
                marioImage--;
        }
    }

}
