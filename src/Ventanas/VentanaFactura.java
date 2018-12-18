package Ventanas;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.BD;
import Datos.Compra;

import javax.swing.JScrollPane;

public class VentanaFactura extends JFrame{
	
	private JPanel contentPane;
	private JFrame v = this;
	
	public VentanaFactura() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100,750,350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		setLocationRelativeTo(null); //Para centrar la ventana
		setResizable(false);
		
		JPanel panelArriba = new JPanel();
		contentPane.add(panelArriba, BorderLayout.NORTH);
		
		JPanel panelAbajo = new JPanel();
		contentPane.add(panelAbajo, BorderLayout.SOUTH);
		
		JButton btnRealizarPago = new JButton("REALIZAR PAGO");
		btnRealizarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				v.dispose();
				VentanaPago vp = new VentanaPago();
				vp.setVisible(true);
			}
		});
		panelAbajo.add(btnRealizarPago);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 725, 257);
		panelCentro.add(scrollPane);
		
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		double total = 0;
		String factura = "\t \t \t \t FACTURA: \n";
		String texto = new String();
		texto = factura + texto+ String.format("%40s%40s%40s%40s\n","Nombre", "Precio unidad", "Unidades","Precio total");
		for(Compra c: VentanaLogin.carrito) {
			texto = texto + String.format("\n %40s%40.2f%40d%40.2f\n", c.getP().getNombre(), c.getP().getPrecio(), c.getCantidad(),c.getP().getPrecio()*c.getCantidad());
			total = total + c.getP().getPrecio()*c.getCantidad();
		}
		
		texto = texto + String.format("\n \n %-50s%28.2f €", "TOTAL FACTURA:", total);
		texto = texto.toUpperCase();
		textArea.setText(texto);

		
		this.setVisible(true);
		  
	}

}
