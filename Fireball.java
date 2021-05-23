import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;

public class Fireball extends Sprite
{
    int x,y;
    int width, height;
    int bounceCount, frameCounter;
    double vert_vel;
    static BufferedImage fireBall_image=null;

    Fireball(int x, int y)
    {
        this.x=x;
        this.y=y;
        width=50;
        height=50;
        super.type="fireball";
        if (fireBall_image==null)
        {
            fireBall_image=View.loadImage("fireball.png");
        }
    }

    void update()
    {

        if (y>=390 || (frameCounter>1 && frameCounter<6))
        {
            frameCounter++;
            y=390;
            vert_vel-=2;
            y+=vert_vel;
        }
        else
        {
            frameCounter=0;
            vert_vel+=1;
            y+=vert_vel;
        }
        x+=3;
    }

    void drawYourself(Graphics g)
    {
        g.drawImage(fireBall_image,x,y,null);
    }

}
