package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Departamento;

public class CreateNewDeptDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldUbicacion;
	private JTextField textFieldNombreDept;
	private JButton okButton;
	
	private Departamento departamentoACrearOActualizar=null;
	
	public Departamento getResult() {
		return this.departamentoACrearOActualizar;
	}



	/**
	 * Create the dialog.
	 */
	public void initComponents() {
		
		setBounds(100, 100, 598, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblDeptName = new JLabel("Nombre departamento");
		lblDeptName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeptName.setBounds(39, 34, 208, 24);
		contentPanel.add(lblDeptName);

		textFieldUbicacion = new JTextField();
		textFieldUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUbicacion.setBounds(330, 83, 197, 23);
		contentPanel.add(textFieldUbicacion);
		textFieldUbicacion.setColumns(10);

		JLabel lblDeptLocation = new JLabel("Ubicación");
		lblDeptLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeptLocation.setBounds(39, 82, 140, 24);
		contentPanel.add(lblDeptLocation);

		textFieldNombreDept = new JTextField();
		textFieldNombreDept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNombreDept.setColumns(10);
		textFieldNombreDept.setBounds(330, 35, 197, 23);
		contentPanel.add(textFieldNombreDept);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Guardar");

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				departamentoACrearOActualizar=null;
				CreateNewDeptDialog.this.dispose();
				
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		ActionListener crearBtnActionListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!(textFieldUbicacion.getText().trim().equals(""))
						&& !(textFieldNombreDept.getText().trim().equals(""))) {
					if(departamentoACrearOActualizar==null) {
						//Solo para creación
						departamentoACrearOActualizar= new Departamento();
					}
					departamentoACrearOActualizar.setDname(textFieldNombreDept.getText().trim());
					departamentoACrearOActualizar.setLoc(textFieldUbicacion.getText().trim());
					CreateNewDeptDialog.this.dispose();
				}
			}
		};

		this.okButton.addActionListener(crearBtnActionListener);

	}

	public CreateNewDeptDialog(Window owner, String title, ModalityType modalityType, Departamento dept) {
		super(owner, title, modalityType);
		initComponents();
		departamentoACrearOActualizar=dept;
		if(departamentoACrearOActualizar!=null) {
			textFieldNombreDept.setText(departamentoACrearOActualizar.getDname());
			textFieldUbicacion.setText(departamentoACrearOActualizar.getLoc());
			
		}
		this.setLocationRelativeTo(owner);
	}
	
	
}
