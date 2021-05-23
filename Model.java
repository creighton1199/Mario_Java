/*
	Name: Creighton Young
	Date: 9/24/2020
	Assigment: Make some pipes appear
*/

import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.Iterator;


class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	static boolean leftHittingTube;
	static boolean rightHittingTube;
	int currentTube;
	Tube tube;
	Mario mario2;
	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(50,350);
		mario2=new Mario(50,350);
		sprites.add(new Mario(50,350));
	}

	public void update()
	{
		rightHittingTube=false;
		leftHittingTube=false;
		mario2.update();  //for some reason i could not just update my mario thourgh the use of sprites.get(0).update() so i made a mario and then upadted my sprite through the mario.update()
		sprites.set(0,mario2);

		for (int x=0;x<sprites.size();x++)
		{
			if (!sprites.get(x).equals("goomba"))
			{
				sprites.get(x).update();
			}
			if (sprites.get(x).type.equals("tube"))
			{
				Tube temp=(Tube)sprites.get(x);
				temp.Collision(mario2);
				for (int y=0;y<sprites.size();y++)
				{
					if (sprites.get(y).type.equals("goomba"))
					{
						Goomba gTemp=(Goomba)sprites.get(y);
						temp.Collision(gTemp);
					}
				}
			}
			else if (sprites.get(x).type.equals("fireball"))
			{
				Fireball fireBall=(Fireball)sprites.get(x);
				//System.out.println(fireBall.bounceCount);
				if (fireBall.x>600)
				{
					sprites.remove(x);
					x--;
				}
				for (int y=0;y<sprites.size();y++)
				{
					if (sprites.get(y).type.equals("goomba"))
					{
						Goomba gTemp=(Goomba)sprites.get(y);
						System.out.println("Fireball x: "+fireBall.x);
						if (gTemp.fireballCollision(fireBall) )
						{
							gTemp.frameCounter++;
							sprites.remove(x);
							x--;
						}
					}
				}
				fireBall.update();
			}

			for (int w=0;w<sprites.size();w++)
			{
				if (sprites.get(w).type.equals("goomba"))
				{
					Goomba gTemp=(Goomba)sprites.get(w);
					if (gTemp.frameCounter>40)
					{
						sprites.remove(w);
						w--;
					}
				}
			}

		}



	}



	public void makeTube(int xPos, int yPos)
    {
		///System.out.println(xPos + " " + yPos);
		if (noTubeThere(xPos,yPos))
		{
			Tube t = new Tube(xPos, yPos);
			sprites.add(t);
		}
	}

	public boolean noTubeThere(int xPos, int yPos) 	//pipe is 55 pixels wide so this will stop overlapping
	{

		return true;
	}

	Json marshal()
	{
		Json ob=Json.newObject();
		Json tmpList=Json.newList();
		ob.add("tubes",tmpList);
		for (int w=0;w<sprites.size();w++)
		{
			Tube t=(Tube)sprites.get(w);
			tmpList.add(t.tubeMarshal());
		}
		return ob;
	}

	void unmarshal(Json ob)
	{
		//sprites = new ArrayList<Sprite>();
		Json tmpList = ob.get("tubes");
		for (int w=0;w<tmpList.size();w++)
		{
			sprites.add(new Tube(tmpList.get(w)));
		}
		tmpList=ob.get("goombas");
		for (int w=0;w<tmpList.size();w++)
		{
			sprites.add(new Goomba(tmpList.get(w)));
		}



	}

}
