package screenpack;
import gifPack.GifSequenceWriter;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ControleDeslizante extends JFrame{
	public int min = 0;
	JRadioButton buttonBack,buttonFront;
  JTextField caixa;
  ImageIcon icon;
  JLabel label;
  //Container tela = getContentPane();
  JFrame tela;
  JPanel pan = new JPanel();
  BufferedImage dimg;
  JButton button1, button2, buttonSave;
  JSlider cDeslizante;
  int valor;
  private Graphics g;
  JFileChooser chooser;
  JFrame saveFrame;

  
  public ControleDeslizante(Image[] editIm,int w, int h){
    super("Uso do controle JSlider");
 
    //tela.setLayout(new FlowLayout());
    final Image[] Slid = editIm;
    final int w1;
	final int h1;
    w1 = w;
    h1 = h;
    
    
    
    
    button2 = new JButton("Restart");
    buttonSave = new JButton("Save");
    buttonBack = new JRadioButton("Cut the back");  
    buttonFront = new JRadioButton("Cut the Front");
    buttonFront.setSelected(true);
    ButtonGroup gro = new ButtonGroup();
    gro.add(buttonFront);
    gro.add(buttonBack);
    pan.add(buttonFront);
    pan.add(buttonBack);
    pan.add(buttonSave);
    setG(Slid[1].getGraphics());
    //g.drawString("dsadsaAASDSADSADASSADASDSADSA", 100, 100);
    tela = new JFrame();
    button1 = new JButton("Cut It!");
    tela.setSize(600,600);
    JLabel rotulo = new JLabel("Valor:");
    caixa = new JTextField("0", 5);
    caixa.setEditable(false);
    //dimg = (BufferedImage) editIm[0].getScaledInstance(300, 300,Image.SCALE_SMOOTH);
    icon = new ImageIcon(Slid[0].getScaledInstance(w, h,Image.SCALE_SMOOTH));
    //label = new JLabel(icon, JLabel.CENTER);
    label = new JLabel();
    //label.setSize(300,300);
    label.setIcon(icon);
    
    //pan.setSize(400,400);
    chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("choosertitle");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.setSelectedFile(new File("Saida.gif"));
    //saveFrame.add(chooser);
    //pan.add(label);
    pan.add(label);
    pan.add(button1);
   // pan.add(chooser);
    
    cDeslizante = new JSlider(JSlider.HORIZONTAL, 0, 200, 0);
    
    cDeslizante.setMajorTickSpacing(2);
    cDeslizante.setMinorTickSpacing(1);
    //cDeslizante.setPaintTicks(true);
    //cDeslizante.setPaintLabels(true);
    cDeslizante.setSnapToTicks(true);
    
    
   
    cDeslizante.addChangeListener(
      new ChangeListener(){
        public void stateChanged(ChangeEvent e) {
          JSlider comp = (JSlider) e.getSource();
          
          //if(!comp.getValueIsAdjusting()){
          
            valor = comp.getValue();
            caixa.setText((new Integer(valor - getMin())).toString());
            icon = new ImageIcon(Slid[(new Integer(valor))].getScaledInstance(w1, h1,Image.SCALE_SMOOTH));
            label.setIcon(icon);
            
            //}
        }
      }
    );
    
    button1.addActionListener(new ActionListener() {
   	 
        public void actionPerformed(ActionEvent e)
        {
        	if(buttonBack.isSelected()){
        		cDeslizante.setMinimum(valor);
        		setMin(valor);
        		System.out.println("Rusfussss");
        		caixa.setText((new Integer(valor - getMin())).toString());
        	}
        	else if(buttonFront.isSelected()){
        		cDeslizante.setMaximum(valor);
        		System.out.println("Não!!!");
        		//setMin(valor);
        		//caixa.setText((new Integer(valor - getMin())).toString());
        	}
        }
        

    });     
    
    button2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cDeslizante.setMaximum(40);
			cDeslizante.setMinimum(0);
			cDeslizante.setValue(0);
			caixa.setText(cDeslizante.getValue() + " ");
			
		}
    	
    });
    
    buttonSave.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//tela.setFocusable(false);
			int returnVal = chooser.showSaveDialog(tela);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
            	try {
    				File fc = chooser.getSelectedFile();
    				//System.out.println("Escolheu: " + fc.getAbsolutePath() + "\\Out.gif");
    				SaveGif(new Integer(cDeslizante.getMinimum()),new Integer(cDeslizante.getMaximum()),(Image[])Slid,fc);
    			} catch (AWTException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
                //This is where a real application would open the file.
                System.out.println("Opening: " + chooser.getSelectedFile() + ".");
            } else {
            	System.out.println("Save command cancelled by user.");
            }
			
			
		}
    	
    });
    
    pan.add(button2);
    pan.add(rotulo);
    pan.add(caixa);
    pan.add(cDeslizante);
    tela.add(pan);
 
    pack();
    tela.setVisible(true);
    tela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
  }
  
  public static void main(String args[]){
	Image[] im = new Image[4];
	try {
		im[3] = ImageIO.read(new File("C:\\Users\\User\\Desktop\\screenShot.jpg"));
		im[2] = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\sreenShot.png"));
		im[0] = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\Capturar.png"));
		im[1] = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\Capturar1.png"));
		//im[0] = getScaledImage(ImageIO.read(new File("C:\\Users\\USER\\Desktop\\screenShot.jpg")),300,300);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    ControleDeslizante app = new ControleDeslizante(im,300,300);
    //im[0] = app.getScaledImage(ImageIO.read(new File("C:\\Users\\USER\\Desktop\\screenShot.jpg")),300,300);
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void SaveGif(int max, int min, Image[] ImArray, File ff) throws AWTException, IOException{
	
		ImageOutputStream output = new FileImageOutputStream(ff);
		GifSequenceWriter writer= new GifSequenceWriter(output,2, 1, true);
		//System.out.println(max + "" + min);		
		for (int i = cDeslizante.getMinimum(); i <= cDeslizante.getMaximum(); i++) {
			BufferedImage nIm = (BufferedImage)ImArray[i];
			//ImageIO.write(nIm, "GIF", new File("C:\\Users\\USER\\Desktop\\sreenShot.gif"));
			try {
				writer.writeToSequence((BufferedImage)ImArray[i]);
				//System.out.println("Heyoo!" + ff.getAbsolutePath() + ff.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ssss!");
			}
		}
		writer.close();
		output.close();
	}
  
  
public Graphics getG() {
	return g;
}

public void setG(Graphics g) {
	this.g = g;
}

public int getMin() {
	return min;
}

public void setMin(int min) {
	this.min = min;
}
  
  
  
}