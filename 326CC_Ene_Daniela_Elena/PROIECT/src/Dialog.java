
import javax.swing.JLabel;

public class Dialog extends javax.swing.JInternalFrame {

	private static final long serialVersionUID = 8823023318110589123L;

	public Dialog(String label) {
		initComponents();
		this.label.setText(label);
	}

	public JLabel getLabel() {
		return label;
	}

	private void initComponents() {
		label = new javax.swing.JLabel();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(label).addGap(0, 11, Short.MAX_VALUE)));

		pack();
	}
	private javax.swing.JLabel label;

}
