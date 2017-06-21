package screenpack;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
public class ScreenCap extends JFrame
{
	private int a = 0,b=0;
	public int x1,x2,y1,y2;
	private boolean exit = false;
	public int click = 0;
	JTextArea textField1;
	JFrame newFrame;
	JFrame mainFrame;
	private int mouseX, mouseY;
	Robot roboto = new Robot();
	BufferedImage imag = null;
	BufferedImage[] editIm = new BufferedImage[201];
	
	public ScreenCap() throws AWTException{
        
		final JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        final r panel2 = new r();
        textField1 = new JTextArea(1,3);
		textField1.setText("200");
		//textField1.setDocument(new JTextFieldLimit(10));
        JButton startButton = new JButton("Start The Gif");//The JButton name.
        
		panel.add(startButton);//Add the button to the JFrame.
		ListenKeys lk = new ListenKeys();
		ListenMouse lm = new ListenMouse();
		panel.add(textField1);
		panel.addMouseListener(lm);
		panel.addKeyListener(lk);
		frame.setTitle("Gif Maker");
        frame.setSize(300, 100);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("You clicked the button");
                panel2.removeAll();
                
                frame.setState(ICONIFIED);
                imag = roboto.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                frame.setState(NORMAL);
               /* try {
					imag = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\sreenShot.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/

                newFrame = new JFrame();
                newFrame.addMouseListener(new ListenMouse());
                newFrame.setUndecorated(true);
                panel2.add(new JLabel(new ImageIcon(imag)));
                newFrame.add(panel2);
                newFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                newFrame.setCursor (Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                newFrame.setVisible(true);
                newFrame.setFocusable(true);
            
            }
        });     
        
	
	}
	
	
	public static void main(String[] args) throws Exception
	{
		//Robot robot = new Robot();
		Rectangle rect = new Rectangle();
		r h = new r();
		ScreenCap screen = new ScreenCap();
		
		while(!screen.exit){
			System.out.println("3");
            if(h.isExit()){
            	rect = h.getRect();
            	screen.closeNewFrame();
            	h.setExit(false);
            	screen.TakeSnap(rect);
            }
		}		
		
	}
	
	public void TakeSnap(Rectangle rect /*GifSequenceWriter writer*/) throws AWTException, IOException{
		Robot robot = new Robot();
		BufferedImage[] ImageArray = new BufferedImage[201];
		//ImageOutputStream output = new FileImageOutputStream(new File("C:\\Users\\thiago\\Desktop\\Out2.gif"));
		//GifSequenceWriter writer= new GifSequenceWriter(output,1, 100, true);	
		for (int i = 0; i <= 200; i++) {
			BufferedImage nIm = robot.createScreenCapture(rect);
			ImageArray[i] = nIm;
			//ImageIO.write(nIm, "GIF", new File("C:\\Users\\USER\\Desktop\\sreenShot.gif"));
			/*try {
				writer.writeToSequence(nIm);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		ControleDeslizante cont = new ControleDeslizante(ImageArray,rect.width,rect.height);
		//writer.close();
		//output.close();
	}
	
	public void closeNewFrame(){
		newFrame.dispose();
	}

	
	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
	private class ListenKeys implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:
	        	textField1.append("Move upp");
	            System.out.println("Move up");
	            break;
	 
	        case KeyEvent.VK_ESCAPE:
	        	setExit(true);
				break;

	        }
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ListenMouse implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			setMouseX(MouseInfo.getPointerInfo().getLocation().x);
			setMouseY(MouseInfo.getPointerInfo().getLocation().y);
			System.out.println(arg0.getButton());
			textField1.append("Window in Normal State\n");
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			setX1(MouseInfo.getPointerInfo().getLocation().x);
			setY1(MouseInfo.getPointerInfo().getLocation().y);
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			setX2(MouseInfo.getPointerInfo().getLocation().x);
			setY2(MouseInfo.getPointerInfo().getLocation().y);
			repaint();
			
		}
		
	}
	

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	

}