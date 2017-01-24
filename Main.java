import javax.swing.JFrame;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Main extends JFrame {

	public static Model model = new Model();
	
	public static void main(String[] args) {
		
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Main();
	}
	
	public Main()
	{
		super("Ricks' Crowd Simulation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.add(new MainPanel());
		
		this.pack();
		
		this.setVisible(true);
	}

}
