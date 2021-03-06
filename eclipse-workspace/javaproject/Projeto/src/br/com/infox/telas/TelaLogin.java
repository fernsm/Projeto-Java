package br.com.infox.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ImageView;

import br.com.infox.dal.ModuloConexao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public void logar() {

		String sql = "select * from tbusuarios where login=? and senha = ?";
		try {

			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			String captura = new String(txtSenha.getPassword());
			pst.setString(2, captura);

			rs = pst.executeQuery();

			if (rs.next()) {

				TelaPrincipal principal = new TelaPrincipal();
				principal.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(null, "Usu?rio e/ou senha inv?lido(s).");

			}

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);
			
		}

	}

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

	// Launch the application.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.

	public TelaLogin() {

		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("");
		txtUsuario.setBounds(250, 86, 155, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				logar();
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 139, 139));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(285, 170, 83, 37);
		contentPane.add(btnLogin);

		txtSenha = new JPasswordField();
		txtSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			
			}
		});
		txtSenha.setToolTipText("");
		txtSenha.setBounds(250, 117, 155, 20);
		contentPane.add(txtSenha);

		JLabel lblStatus = new JLabel("");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setVerticalAlignment(SwingConstants.TOP);
		lblStatus.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblStatus.setHorizontalTextPosition(SwingConstants.LEFT);
		lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatus.setIcon(null);
		lblStatus.setBounds(341, 230, 83, 31);
		contentPane.add(lblStatus);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 139, 139));
		panel.setBounds(0, 0, 177, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Seja bem-vindo!");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(21, 122, 146, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setForeground(new Color(0, 139, 139));
		lblNewLabel_1.setBounds(273, 27, 74, 23);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Usu\u00E1rio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(199, 89, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Senha");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(199, 120, 46, 14);
		contentPane.add(lblNewLabel_2_1);

		conexao = ModuloConexao.conector();
		if (conexao != null) {

			lblStatus.setText("Conectado");
		} else {
			lblStatus.setText("N?o conectado");
		}

	}
}
