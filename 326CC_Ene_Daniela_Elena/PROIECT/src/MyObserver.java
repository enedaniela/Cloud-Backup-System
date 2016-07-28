import org.apache.log4j.Logger;


public class MyObserver extends Observer {

	final Logger logger = Logger.getLogger(MyObserver.class);
	@Override
	public void login(String username) {		
		logger.info(username + " logged in successfully!");
	}

	@Override
	public void logout(String username) {
		logger.info(username + " logged out successfully!");		
	}

	@Override
	public void exception(MyInvalidPathException e) {
		logger.error(e.toString() +": "+ e.message);
	}

	@Override
	public void exception(MyPathTooLongException e) {
		logger.error(e.toString()+": "+ e.message);
		
	}

	@Override
	public void exception(MyNotEnoughSpaceException e) {
		logger.error(e.toString());
		
	}	
}
