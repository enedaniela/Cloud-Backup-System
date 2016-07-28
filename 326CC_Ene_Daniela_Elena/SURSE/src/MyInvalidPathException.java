import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyInvalidPathException extends Exception{
	
	Folder folder;
	String invalidPath;
	Utilizator utilizator;
	String date;
	String message;
	
	public MyInvalidPathException(String invalidPath){
        this.folder = Main.FolderCurent;
        this.invalidPath = invalidPath;
        this.utilizator = Main.UtilizatorCurent;
        this.date = getCurrentDate();
        this.message = folder.numeFolder +" "+ invalidPath +" "+ utilizator.username +" "+ date;
    }

    public String getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		Date date = new Date();
		return dateFormat.format(date);
	}
}
