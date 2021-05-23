import java.awt.Graphics;

public abstract class Sprite
{
    int x, y;
    int width, height;
    int scrollPos=0;
    String type;


    abstract void update();

    abstract void drawYourself(Graphics g);

}
