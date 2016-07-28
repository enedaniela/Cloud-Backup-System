
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;

public class TerminalFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = -5980851569764680095L;
	private JPanel container;
	private JTextArea currentTextArea;
	
	public TerminalFrame() throws BadLocationException {
        initComponents();
        addTextArea(getLastLine());
    }
    private void initComponents() {
    	
    	container = new JPanel();
    	container.setLayout(new GridLayout(0, 1));
    	Main.jTextArea1 = new javax.swing.JTextArea();
    	
    	Main.scroll = new javax.swing.JScrollPane();
    	Main.scroll.setViewportView(container);
        Main.scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        Main.jd1 = new javax.swing.JDialog();
        Main.jd1.setSize(400,100);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Main.jTextArea1.setColumns(10);
        Main.jTextArea1.setRows(5);
        Font font = new Font("Verdana", Font.BOLD, 12);
        Main.jTextArea1.setFont(font);
        Main.jTextArea1.setForeground(Color.DARK_GRAY);
        Color c = new Color(0,206,209);
        Main.jTextArea1.setBackground(c);

        showStartLine();
        Main.jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
            	try {
					jTextArea1WriteCommand(evt);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Main.scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Main.scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
	private void showStartLine() {
		String line = "\n";
		line = line + Main.UtilizatorCurent.nume + " @Cloud:~";
		for (String s : Main.AbsolutePath)
			line = line + "/" + s;
		line = line + ":";
		Main.jTextArea1.append(line);
	}

	private String getStartLine() {
		String line = "";
		line = line + Main.UtilizatorCurent.nume + " @Cloud:~";
		for (String s : Main.AbsolutePath)
			line = line + "/" + s;
		line = line + ":";
		return line;
	}
	
	public String getLastLine() {
		try {
			int offset = Main.jTextArea1.getLineOfOffset(Main.jTextArea1.getCaretPosition());
			int start = Main.jTextArea1.getLineStartOffset(offset);
			int end = Main.jTextArea1.getLineEndOffset(offset);
			return Main.jTextArea1.getText(start, end);
		} catch (BadLocationException ex) {
			ex.getMessage();
		}
		return getStartLine();
	}

	private void jTextArea1WriteCommand(java.awt.event.KeyEvent evt) throws BadLocationException {
		ProcesorComenzi pc = new ProcesorComenzi();
		CommandFactory cf = new CommandFactory();
		CloudService cs = new CloudService();
		String comanda;

		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			if (null != currentTextArea) {
				Main.jTextArea1.setText(Main.jTextArea1.getText() + currentTextArea.getText().split(":")[2]);
			}

			evt.consume();
			String[] lines = Main.jTextArea1.getText().split("\n");
			String[] almostCommand = lines[lines.length - 1].split(":");
			comanda = almostCommand[2];
			try{
			pc.Procesare(comanda);
			cf.getCommand(comanda);
			cs.getCommand(comanda);
			Main.frame.addTextArea("\n<Success");
            }
            catch(CommandException e){
            	Main.frame.addTextArea(e.getMessage());
            }
			showStartLine();
			
			addTextArea(getLastLine());
			Main.scroll.setViewportView(container);
			return;
		}
	}
    
	public void addTable(String columns[], String data[][]) {
		// adaug Table in terminal
		DefaultTableModel model = new DefaultTableModel(data, columns);
		model.setColumnCount(2);
		model.setNumRows(10);
		container.add(new JTable(model));
		refreshView();
	}
	public void addList(String[] data){
		JList<String> list = new JList<String>(data);
		list.setBorder(BorderFactory.createLineBorder(Color.white));
		container.add(list);
		refreshView();
	}

	public void addTextArea(String text) throws BadLocationException {
		// add text area to container
		currentTextArea = new JTextArea(text == null ? "" : text);
        AbstractDocument document = (AbstractDocument) currentTextArea.getDocument();
        document.setDocumentFilter(new Filter());
		currentTextArea.setBorder(BorderFactory.createLineBorder(Color.white));
		currentTextArea.setSize(750,2);
		container.add(currentTextArea);
		currentTextArea.requestFocusInWindow();
		currentTextArea.setCaretPosition(currentTextArea.getDocument().getLength());

		currentTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				try {
					jTextArea1WriteCommand(evt);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		refreshView();
	}

	public void addDialog(String text) {
		// adaug Dialog in terminal
		Dialog d = new Dialog(text == null ? "" : text);
		container.add(d);
		d.setVisible(true);
		refreshView();
	}

	private void refreshView(){
		// fac resize la scroll
		Main.scroll.setViewportView(container);
		// setez scrollul jos
		Main.scroll.getVerticalScrollBar().setValue(Main.scroll.getVerticalScrollBar().getMaximum());
	}
}