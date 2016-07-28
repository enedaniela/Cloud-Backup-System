
public class MyNotEnoughSpaceException extends Exception{
	
	public MyNotEnoughSpaceException(){
        super();
    }

    public MyNotEnoughSpaceException(String message){
        super(message);
    }
}
