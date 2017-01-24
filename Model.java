import java.util.ArrayList;
import java.util.List;

public class Model {
	
	public static final int MAX_SIZE = 20;
	
	public static final double TIME_STEP = .09;
	
	public static final int AGENTS = 10;
	
	public List<Agent> agents = new ArrayList<Agent>();
	
	public Model()
	{
		for(int inc = 0; inc < AGENTS; inc++)
		{
			agents.add(new Agent());
		}
	}

	public void update() {
		for(int inc = 0; inc < AGENTS; inc++)
		{
			agents.get(inc).update();
		}
		
	}
	
	

}
