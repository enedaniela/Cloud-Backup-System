import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ProcesorComenzi {
		private MyObserver observer = new MyObserver();
		public boolean Procesare(String comanda) throws CommandException
		{
			if (comanda.startsWith("login ")){
				String[] arr = comanda.split(" ");
				ProceseazaLogin(arr[1], arr[2]);
			}
			else if (comanda.startsWith("newuser ")){
				String[] arrCreate = comanda.split(" ");
				ProceseazaCreareUtilizator(arrCreate[1], arrCreate[2], arrCreate[3], arrCreate[4]);
			}
			else if(comanda.startsWith("logout"))
				ProceseazaLogout();
			else if(comanda.startsWith("userinfo"))
				ProceseazaUserinfo(comanda);
			else if(comanda.startsWith("echo ")){
				ProceseazaEcho(comanda);
			}
			else if (comanda.equals("exit"))
				return false;
			else if(!comanda.startsWith("cd")&&
					!comanda.startsWith("ls")&&
					!comanda.startsWith("mkdir")&&
					!comanda.startsWith("pwd")&&
					!comanda.startsWith("rm")&&
					!comanda.startsWith("touch")&&
					!comanda.startsWith("upload")&&
					!comanda.startsWith("sync"))
				throw new CommandException("Wrong command format");
				//Main.frame.addTextArea("Wrong command format");
			return true;
		}
		
		public String getCurrentDate(){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			   //get current date time with Date()
			Date date = new Date();
			return dateFormat.format(date);
		}
		
		private void ProceseazaLogin(String username, String parola) throws CommandException
		{
			Utilizator utilizatorIdentificat = null;
			int i;
			boolean ok = false;
			for( i = 0; i < Main.listaUtilizatori.size();i++){
				if(Main.listaUtilizatori.get(i).username.equals(username) && Main.listaUtilizatori.get(i).parola.equals(parola)){
					//modific data ultimei logari
					Main.listaUtilizatori.get(i).dataLogare = getCurrentDate();
					utilizatorIdentificat = Main.listaUtilizatori.get(i);
					ok = true;
				}
			}
			if(ok){
			//utilizatorul curent devine utilizatorul tocmai logat
			Main.UtilizatorCurent = utilizatorIdentificat;
			//Main.frame.addTextArea("<Success");
			//notific logger-ul
			observer.login(username);
			}
			else
				throw new CommandException("Error: User does not exist");
			
		}

		private void ProceseazaCreareUtilizator(String username, String parola, String nume, String prenume) throws CommandException{
			boolean ok = true;
			//creez noul utilizator
			Utilizator newUser = new Utilizator(username,parola,nume,prenume,getCurrentDate(),getCurrentDate());
			//il adaug la lista de utilizatori
			for(Utilizator user : Main.listaUtilizatori )
				if(user.username.equals(username))
					ok = false;
			if(ok == false)
				throw new CommandException("This username is taken. Try another one!");
			else{
				Main.listaUtilizatori.add(newUser);
			}
			
		}
		
		private void ProceseazaLogout(){
			//notific logger-ul
			observer.logout(Main.UtilizatorCurent.username);
			//la logout utilizatorul curent devine utilizatorul guest
			Main.UtilizatorCurent = Main.listaUtilizatori.get(1);
			
		}
		
		private void ProceseazaUserinfo(String comanda) throws CommandException{
			boolean ok = false;
			String[] arrCreate = comanda.split(" ");
			for(int i = 1; i < arrCreate.length; i++)
				if(arrCreate[i].contains("-POO")){
					String[] label = { Main.UtilizatorCurent.username, Main.UtilizatorCurent.nume, Main.UtilizatorCurent.prenume, Main.UtilizatorCurent.dataCreare, Main.UtilizatorCurent.dataLogare};
					Main.frame.addList(label);
					ok = true;
				}
				 if(ok==false){
					 throw new CommandException("<UserName: " + Main.UtilizatorCurent.username +" FirstName: " + Main.UtilizatorCurent.nume +" LastName: "+ Main.UtilizatorCurent.prenume +" Created: "+ Main.UtilizatorCurent.dataCreare +" LastLogin: "+ Main.UtilizatorCurent.dataLogare);
					
				}
		}
		
		private void ProceseazaEcho(String text) throws CommandException{
			boolean ok = false;
			String echo = "";
			String[] arrCreate = text.split(" ");
			for( int i = 1; i < arrCreate.length; i++)
				if(!arrCreate[i].equals("-POO"))
					echo = echo + arrCreate[i]+ " ";
			for( int i = 1; i < arrCreate.length; i++){
				if(arrCreate[i].contains("-POO")){
					Main.frame.addDialog(echo);
					ok = true;
				}
				else if (ok == false){
					throw new CommandException(echo);
				}

			}
		}
	}

