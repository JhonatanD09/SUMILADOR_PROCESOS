package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import presenters.Events;

public class JDialogCreatePorcess extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JSpinner jSpinner;
	private JCheckBox r1;

	public JDialogCreatePorcess(ActionListener l) {
		setSize(300, 400);
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		initComponents(l);
	}

	private void initComponents(ActionListener l) {
		JLabel jLabel = new JLabel("Agregar proceso");
		label(jLabel);
		add(jLabel, BorderLayout.NORTH);

		// panel centro
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 1));

		center.add(new JPanel());
		
		JPanel input = new JPanel();
		input.setLayout(new GridLayout(2, 1));
		area = editJTextArea("Nombre del proceso");
		input.add(area);
		jSpinner = jSpinner("Tiempo de ejecucion");
		input.add(jSpinner);
		input.setPreferredSize(new Dimension(100,400));
		center.add(input);

		JPanel rbutons = new JPanel();
		
		
		r1 = jRadioButton("Bloqueado");
	
		
		rbutons.add(r1);
		
		center.add(rbutons);
		
		add(center,BorderLayout.CENTER);
		
		add(button(l, "Aceptar", Events.ACEPT.name()), BorderLayout.SOUTH);
	}

	private void label(JLabel label) {
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
	}
	
	private JCheckBox jRadioButton(String name) {
		JCheckBox button = new JCheckBox(name);
		return button;                                                                      
	}
	
	private JTextArea editJTextArea( String name) {
		JTextArea area = new JTextArea();
		area.setBorder(BorderFactory.createTitledBorder(name));
		return area;
	}
	
	private JSpinner jSpinner(String name) {
		SpinnerModel sm = new SpinnerNumberModel(0, 0, 900000, 1);
		JSpinner jSpinner = new JSpinner(sm);
		((DefaultEditor) jSpinner.getEditor()).getTextField().setEditable(false);
		jSpinner.setAlignmentX(CENTER_ALIGNMENT);
		jSpinner.setBorder(BorderFactory.createTitledBorder(name));
		return jSpinner;
	}
	
	private JButton button(ActionListener l, String name, String command) {
		JButton button = new JButton(name);
		button.setBackground(Color.decode("#4FCD69"));
		button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		button.setActionCommand(command);
		button.addActionListener(l);
		return button;
	}
	
	public Object[] data() {
		return new Object[] {area.getText(),jSpinner.getValue(),r1.isSelected()};
	}

}
