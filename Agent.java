import java.awt.geom.Point2D;

public class Agent {
	
	public static final double RADIUS = .5;
	
	public static final double MAX_SPEED_CHANGE = 1;
	public static final double MAX_ANGLE_CHANGE = 1;
	public static final double MAX_SPEED = 1;
	
	public Point2D location;
	public Point2D destination;
	public double velocity;
	public double heading;
	
	public Agent()
	{
		location = new Point2D.Double();
		destination = new Point2D.Double();
		
		pickRandomDestination();
		pickRandomPosition();
	}
	
	private void pickRandomPosition() {
		location.setLocation(Helper.getRandom() * Model.MAX_SIZE, Helper.getRandom()*Model.MAX_SIZE);
		//location.setLocation(0, 0);
		
	}

	private void pickRandomDestination() {
		destination.setLocation(Helper.getRandom() * Model.MAX_SIZE, Helper.getRandom()*Model.MAX_SIZE);
		//destination.setLocation(15, 20);
		
	}
	
	public void update()
	{
		chooseSpeedAndVelocity();
		updateDestination();
		updatePosition();
	}

	private void updatePosition() {
		location.setLocation(
				location.getX() + Math.cos(heading) * velocity * Model.TIME_STEP,
				location.getY() + Math.sin(heading) * velocity * Model.TIME_STEP
				);
		
	}

	public void chooseSpeedAndVelocity()
	{
		double offsetToDestinationX = destination.getX() - location.getX();
		double offsetToDestinationY = destination.getY() - location.getY();
		
		double absoluteAngle = Math.atan2(offsetToDestinationY, offsetToDestinationX);
		
		updateSpeedAndVelocity(1, absoluteAngle);
	}
	
	private void updateSpeedAndVelocity(double desiredSpeed, double desiredAngle) {
		
		double desiredChangeInSpeedAbs = Math.abs(velocity - desiredSpeed);
		if(desiredChangeInSpeedAbs < MAX_SPEED_CHANGE * Model.TIME_STEP)
		{
			velocity = desiredSpeed;
		}
		else
		{
			if(desiredSpeed > velocity)
				velocity += MAX_SPEED_CHANGE * Model.TIME_STEP;
			else
				velocity -= MAX_SPEED_CHANGE * Model.TIME_STEP;
		}
		
		double desiredChangeInAngle = getMinimumAngle(desiredAngle, heading);
		
		if(Math.abs(desiredChangeInAngle) < MAX_ANGLE_CHANGE * Model.TIME_STEP)
		{
			heading = desiredAngle;
		}
		else
		{
			if(desiredChangeInAngle > 0)
				heading += MAX_ANGLE_CHANGE * Model.TIME_STEP;
			else
				heading -= MAX_ANGLE_CHANGE * Model.TIME_STEP;
		}
		
		clampHeadingAndValue();
	}

	private double getMinimumAngle(double desiredAngle, double heading2) {
		 double difference = desiredAngle - heading2;
        while (difference < -Math.PI) difference += Math.PI * 2;
        while (difference > Math.PI) difference -= Math.PI * 2;
        return difference;
		
	}

	private void clampHeadingAndValue() {
		if(velocity > MAX_SPEED)
			velocity = MAX_SPEED;
		if(velocity < 0)
			velocity = 0;
		while(heading < -Math.PI)
			heading += Math.PI * 2;
		while(heading > Math.PI)
			heading -= Math.PI * 2;
		
	}

	public void updateDestination()
	{
		if(location.distance(destination) < 2)
		{
			pickRandomDestination();
		}
	}

}
