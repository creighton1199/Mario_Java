import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;

public class Goomba extends Sprite
{
    int x;
    int y;
    int width;
    int height;
    int moveAmount=5;
    int currentImage=0;
    int frameCounter=0;
    boolean imHit=false;
    static BufferedImage [] goomba_images=null;

    Goomba (int x, int y)
    {
        this.x=x;
        this.y=y;
        if (goomba_images==null)
        {
            goomba_images=new BufferedImage[2];
        }
        goomba_images[0]=View.loadImage("goomba.png");
        goomba_images[1]=View.loadImage("goomba_fire.png");
    }

    public Goomba(Json ob)
	{
		x=(int)ob.getLong("x");
		y=(int)ob.getLong("y");
		width=100;
		height=200;
		super.type="goomba";
        if (goomba_images==null)
        {
            goomba_images=new BufferedImage[2];
        }
        goomba_images[0]=View.loadImage("goomba.png");
        goomba_images[1]=View.loadImage("goomba_fire.png");
	}

    void drawYourself(Graphics g)
    {
        g.drawImage(goomba_images[currentImage],x-super.scrollPos,y,null);
        x+=moveAmount;

    }

    void update()
    {
        if (imHit)
        {
            frameCounter++;
        }
    }

    boolean fireballCollision(Sprite sprite)
    {
        Fireball fireball= (Fireball) sprite;
        if (fireball.x+fireball.width+2>x && (fireball.x+fireball.width+2)<(x+width+60))
        {
            System.out.println("Now im right here");
            currentImage=1;
            imHit=true;
            frameCounter++;
            return true;
        }

        return false;
    }

}
