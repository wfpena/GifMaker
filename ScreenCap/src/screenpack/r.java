package screenpack;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

class r extends JPanel
{
      public int x1,x2,y1,y2;
      public static double SWITCH;
      public static Rectangle rec = new Rectangle();
      static boolean exit = false;
      
      public r()
      {
          //setBackground(Color.WHITE);
    	  //setOpaque(false);
          addMouseListener(new MouseAdapter()
          {
               public void mousePressed(MouseEvent m)
               {
               x1=MouseInfo.getPointerInfo().getLocation().x;
               y1=MouseInfo.getPointerInfo().getLocation().y;
               repaint();
               }
               public void mouseReleased(MouseEvent m)
               {
               x2=m.getX();
               y2=m.getY();
               rec.setBounds(x1, y1, x2-x1, y2-y1);
               setExit(true);
               repaint();
               }
          });
          addMouseMotionListener(new MouseMotionAdapter()
          {
              public void mouseDragged(MouseEvent m)
              {
                 x2=m.getX();
                 y2=m.getY();
                 repaint();
              }
          });
      }
      public void paintComponent(Graphics g)
      { 
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;
          g2d.setRenderingHint(
              RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
          g2d.setComposite(AlphaComposite.getInstance(
              AlphaComposite.SRC_OVER, 0.3f));
          
          //g2d.setColor(Color.yellow);
          g.fillRect(x1, y1, x2-x1, y2-y1);
         
      }
      
      public Rectangle getRect(){
    	  return rec;
      }
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}

  }

  