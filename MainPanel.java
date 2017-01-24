import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MainPanel extends Component implements ActionListener {
	
	Timer timer = new Timer(10, this);
	
	
	public MainPanel() {
		super();
		
		this.setPreferredSize(new Dimension(800, 800));
		
		timer.start();
	}

	@Override
	public void paint(Graphics graphics) {
		
		Graphics2D g = (Graphics2D)graphics;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransform transform = g.getTransform();
		transform.scale(this.getWidth() / (Model.MAX_SIZE * 2) , this.getHeight() / (Model.MAX_SIZE * 2));
		transform.translate(Model.MAX_SIZE, Model.MAX_SIZE);
		transform.scale(.9, .9);
		
		g.setTransform(transform);
		
		
		g.setColor(Color.WHITE);
		g.fillRect(-Model.MAX_SIZE, -Model.MAX_SIZE, Model.MAX_SIZE * 2, Model.MAX_SIZE * 2);
		
		for(Agent agent : Main.model.agents)
		{
			
			g.setColor(Color.RED);
			
			Shape circle = new Ellipse2D.Double(agent.location.getX() - Agent.RADIUS, agent.location.getY() - Agent.RADIUS, Agent.RADIUS * 2, Agent.RADIUS * 2);
			
			g.fill(circle);
			
			Line2D line = new Line2D.Double(agent.location.getX(), agent.location.getY(), agent.location.getX() + Math.cos(agent.heading) * Agent.RADIUS * 1.5, agent.location.getY() + Math.sin(agent.heading) * Agent.RADIUS * 1.5);
			g.setStroke(new BasicStroke(.1f));
			
			g.setColor(Color.BLACK);
			g.draw(line);
			
			Line2D lineDestination = new Line2D.Double(agent.location.getX(), agent.location.getY(), agent.destination.getX(), agent.destination.getY());
			g.setStroke(new BasicStroke(.05f));
			
			g.setColor(Color.GRAY);
			g.draw(lineDestination);
			
			
		}
		
		
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		Main.model.update();
	}
	

}
