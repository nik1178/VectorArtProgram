import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

class MyPaint2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}
}
class MyFrame extends JFrame implements KeyListener, ActionListener{

	
	DrawCanvas2 drawCanvas = new DrawCanvas2();
	JButton clearButton = new JButton("Clear");
	JButton colorButton = new JButton();
	MyFrame(){

		drawCanvas.setBounds(0,50,500,500);

		clearButton.addActionListener(
			e -> {
				drawCanvas.paths.clear();
				drawCanvas.currentPath = 0;
				repaint();
			}
		);
		clearButton.setBounds(10,10,50,20);
		clearButton.setFocusable(false);

		this.addKeyListener(this);

		this.add(clearButton);
		this.add(drawCanvas);

		
		this.getContentPane().setBackground(Color.gray);
		this.setSize(new Dimension(500,600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setLayout(new FlowLayout());
		this.setLayout(null);
		this.setVisible(true);
	}

	//Keyboard
	boolean ctrlKey = false;
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==17){
			ctrlKey = true;
		}
		if(e.getKeyCode()==90){
			if(ctrlKey){
				if(drawCanvas.currentPath>0)
					drawCanvas.paths.remove(--drawCanvas.currentPath);
				repaint();
			}
		}
		if(e.getKeyCode()==89){
			if(ctrlKey){
				repaint();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==17){
			ctrlKey = false;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

//The canvas on which you draw
class DrawCanvas2 extends JPanel implements MouseListener, MouseMotionListener{
	DrawCanvas2(){
		this.setPreferredSize(new Dimension(500,500));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(Color.black);
	}
	
	static Color paintColor = Color.white;
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
	
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON); 
	
		g2.setStroke(new BasicStroke(5));
		for(int i=0; i<paths.size(); i++){
			g2.draw(paths.get(i).path);
		}
	}


	ArrayList<MyPath> paths = new ArrayList<>();
	int currentPath = 0;
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.add(new MyPath(e.getX(), e.getY()));
		paths.get(currentPath).path.moveTo(e.getX(), e.getY());
		repaint();
	}
	ArrayList<String> lineNameList = new ArrayList<>();
	int tempX;
	int tempY;
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.get(currentPath).path.lineTo(e.getX(), e.getY());
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		paths.get(currentPath).path.moveTo(e.getX(), e.getY());
		paths.get(currentPath).path.closePath();
		currentPath++;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class MyPath {
	Color color = DrawCanvas2.paintColor;
	double currentX;
	double currentY;
	Path2D path = new Path2D.Double();
	MyPath(double x, double y){
		currentX = x;
		currentY = y;
		path.moveTo(x, y);
	}
}