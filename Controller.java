/*
	Name: Creighton Young
	Date: 9/24/2020
	Assigment: Make some pipes appear
*/

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


class Controller implements ActionListener, MouseListener, KeyListener
{

	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyS;
	boolean keyL;
	boolean keySpace;

	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
		view.removeButton();
	}

	void setView(View v)
	{
		view = v;
	}


	public void mousePressed(MouseEvent e)
	{
		//model.makeTube(e.getX()+view.scrollPos, e.getY());
	}



	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
									keyRight = true;
									if(!model.rightHittingTube)
									{
										for (int x=0;x<model.sprites.size();x++)
										{
											Sprite temp=model.sprites.get(x);
											temp.scrollPos+=4;
										}
										model.mario2.x+=4;
									}

									model.mario2.rightKeyPress=true; break;
			case KeyEvent.VK_LEFT:
									keyLeft = true;
									if (!model.leftHittingTube)
									{
										for (int x=0;x<model.sprites.size();x++)
										{
											Sprite temp=model.sprites.get(x);
											temp.scrollPos-=4;
										}
										model.mario2.x-=4;
									}
									model.mario2.leftKeyPress=true;break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_S: keyS=true; model.marshal().save("map.json"); break;
			case KeyEvent.VK_L: keyL=true; Json j=Json.load("map.json");model.unmarshal(j);Json.load("map.json");break;
			case KeyEvent.VK_SPACE: keySpace=true; if (model.mario2.onGround){model.mario2.frameCounter++;}break;
			case KeyEvent.VK_CONTROL: model.sprites.add(new Fireball(50,model.mario2.y)); break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; model.mario2.rightKeyPress=false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; model.mario2.leftKeyPress=false;break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_S: keyS = false; break;
			case KeyEvent.VK_L: keyL =false;break;
			case KeyEvent.VK_SPACE: keySpace=false; model.mario2.frameCounter=0;break;
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}



	void update()
	{
	}

	void setModel(Model m)
	{
		model = m;
	}


}
