package capl_first_design.FirstDesign.main;


public enum Settings
{
	/* no constants here */;

	public static final String MULTICAST_IP = "239.128.75.0";
	public static final int MULTICAST_PORT = 13075;
	public static final int PACKAGE_SIZE = 512;

	enum Command
	{
		Broadcast,  // broadcast to multicast group
		Confirm,    // confirm broadcast
		Update,     // update data
		Register,   // register new client
		Unregister, // unregister client
		Move,       // move command
		Stop,       // stop command
		Shoot,      // shoot command
	}

	enum Direction
	{
		North, South, East, West
	}

	// legacy
	public static final int PORT = 13075;
	// commands
	public static final byte JOIN = 0;
	public static final byte DISCONNECT = 1;
	public static final byte MOVE = 2;
	public static final byte STOP = 3;
	// directions
	public static final byte NORTH = 0;
	public static final byte SOUTH = 1;
	public static final byte EAST = 2;
	public static final byte WEST = 3;
}
