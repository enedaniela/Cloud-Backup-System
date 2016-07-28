

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;


public class Main {
	
	public static List<Utilizator> listaUtilizatori = new ArrayList<Utilizator>();
	public static Utilizator UtilizatorCurent;
	private static Utilizator UtilizatorRoot;
	private static Utilizator UtilizatorGuest;
	public static Folder FolderRoot;
	public static Folder FolderCurent;
	public static Vector<String> AbsolutePath;
	public static JScrollPane scroll;
	public static JTextArea jTextArea1;
	public static JDialog jd1;
	public static String comanda;
	public static TerminalFrame frame;
	
	public static void init(){
		//data curenta
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		   
		UtilizatorRoot = new Utilizator("root","root","","",dateFormat.format(date),dateFormat.format(date));
	    UtilizatorGuest = new Utilizator("guest","guest","","",dateFormat.format(date),dateFormat.format(date));
		listaUtilizatori.add(UtilizatorRoot);
		listaUtilizatori.add(UtilizatorGuest);
		UtilizatorCurent = UtilizatorRoot;
		FolderRoot = new Folder("root");
		FolderCurent = FolderRoot;
		AbsolutePath = new Vector<String>();
		AbsolutePath.add(FolderRoot.numeFolder);
		//creez terminal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               try {
				frame = new TerminalFrame();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               frame.setVisible(true);
            }
        });

	}
	public static void main(String[] args) throws IOException {
		//setez look and feel
        try {
        	UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        	} catch (Exception e) {
        	e.printStackTrace();
        	}
        //initializez date
		init();		
		//creez statiile de stocare
		for(int i = 0; i < 3; i++){
			MyStoreStation ss = new MyStoreStation();
			CloudService.arrStation.add(ss);
		}

	}

}
