package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

//Las ventanas de los sitios van a tener el mismo formato, como juntarlas
public class VentanaTiendas extends JFrame {

	private JPanel contentPane;
	JFrame v = this;
	
	/**
	 * Create the frame.
	 */
	public VentanaTiendas(String ciudad, String url1, String url2, String texto) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); //Para centrar la ventana
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
				
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		//JLabel lblBilbao = new JLabel("Barcelona");
		JLabel lblBilbao = new JLabel(ciudad);
		lblBilbao.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		panelNorte.add(lblBilbao);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("Volver");
		panelSur.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				VentanaMenu vm = new VentanaMenu(v);
				vm.setVisible(true);
				
			}
		});
		
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(null);
		
		//Image img1 = new ImageIcon("Imagenes/fotoBarcelona.jpg").getImage();
		Image img1 = new ImageIcon(url1).getImage();

		ImageIcon img12 = new ImageIcon(img1.getScaledInstance(532, 302, Image.SCALE_SMOOTH));
		JLabel lblFoto1 = new JLabel("");
		lblFoto1.setBounds(552, 6, 532, 302);
		lblFoto1.setIcon(img12);
		panelCentro.add(lblFoto1);
		
		//Image img2 = new ImageIcon("Imagenes/locBarcelona.png").getImage();
		Image img2 = new ImageIcon(url2).getImage();

		ImageIcon img22 = new ImageIcon(img2.getScaledInstance(532, 302, Image.SCALE_SMOOTH));
		JLabel lblFoto2 = new JLabel("");
		lblFoto2.setBounds(6, 6, 532, 302);
		lblFoto2.setIcon(img22);
		panelCentro.add(lblFoto2);
		
		/*String text = "Barcelona es una ciudad española, capital de la comunidad autónoma de Cataluña, de la comarca del Barcelonés y de la provincia homónima. \n" + 
				"Con una población de 1 620 809 habitantes en 2017,6​ es la segunda ciudad más poblada de España después de Madrid, y la undécima de la Unión Europea. \n" + 
				"El área metropolitana de Barcelona, incluida en el ámbito metropolitano de Barcelona, cuenta con 5 029 181 habitantes (2011), siendo así la \n" + 
				"sexta ciudad de mayor población de la Unión Europea.\n"
				+ "Se ubica a orillas del mar Mediterráneo, a unos 120 km al sur de la cadena montañosa de los Pirineos y de la frontera con Francia, en un pequeño \n"
				+ "llano litoral limitado por el mar al este, la sierra de Collserola al oeste, el río Llobregat al sur y el río Besós al norte. Por haber sido capital del condado \n"
				+ "de Barcelona, se suele aludir a ella con la denominación antonomástica de Ciudad Condal." ;
		*/
		  

		
		JTextArea txt = new JTextArea(texto,20,10);
		txt.setEditable(false);
		txt.setOpaque(false);
		txt.setBounds(6, 320, 1078, 115);
		panelCentro.add(txt);
		

	}
}
