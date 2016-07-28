
public abstract class Observer {
public abstract void login(String username);
public abstract void logout(String username);
public abstract void exception(MyInvalidPathException e);
public abstract void exception(MyPathTooLongException e);
public abstract void exception(MyNotEnoughSpaceException e);
}
