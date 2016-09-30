package health;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;

	private String user;
	private String pass;

	/*
	 * get user
	 */
	public String getUser() {

		String userStr = this.user;

		return userStr;

	}

	/*
	 * get pass
	 */
	public String getPass() {

		String passStr = this.pass;

		return passStr;

	}


	// 画面名称(画面遷移で使用)




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("ログイン");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("ユーザーID");
		contentPane.add(lblNewLabel, "4, 4, right, default");

		userName = new JTextField();
		contentPane.add(userName, "6, 4, left, default");
		userName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("※半角英数字で入力してください");
		lblNewLabel_2.setForeground(Color.GRAY);
		contentPane.add(lblNewLabel_2, "6, 6");

		JLabel lblNewLabel_1 = new JLabel("パスワード");
		contentPane.add(lblNewLabel_1, "4, 8, right, default");

		password = new JPasswordField();
		password.setColumns(10);
		contentPane.add(password, "6, 8, left, default");

		JLabel lblNewLabel_3 = new JLabel("※半角英数字で入力してください");
		lblNewLabel_3.setForeground(Color.GRAY);
		contentPane.add(lblNewLabel_3, "6, 10");

		JButton btnNewButton = new JButton("ログイン");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// user,passに入っているtextを取り出す
				user = userName.getText();
				char[] passArray = password.getPassword();
				pass = charArrayToString(passArray);

				// user,pass未入力時return
				boolean chk = nullChk(user, pass);
				if (chk == false) {
					return;
				}

				// 半角英数字以外はNG
				boolean valChkUser = validate(user);
				if (valChkUser == false) {
					lblNewLabel_2.setForeground(Color.RED);
					lblNewLabel_2.setText("※半角英数字で入力してください！");
					return;
				}

				// DB接続、検索
				try {
					connect(user, pass);
				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					JOptionPane.showMessageDialog(null, "DB接続エラー！");
					e1.printStackTrace();
				}

			}

		});
		contentPane.add(btnNewButton, "8, 14, center, default");
	}

	/*
	 * char[] → String
	 */
	public String charArrayToString(char[] array) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {

			sb.append(array[i]);
		}

		String str = sb.toString();

		return str;

	}

	/**
	 * 未入力ちぇっく
	 */
	public boolean nullChk(String user, String pass) {

		if ("".equals(user)) {
			JOptionPane.showMessageDialog(null, "userが未入力です");
			return false;
		}
		if ("".equals(pass)) {
			JOptionPane.showMessageDialog(null, "passが未入力です");
			return false;
		}

		return true;
	}

	/**
	 * Validation formatChk
	 *
	 * @param str
	 *            処理対象となる文字列
	 * @return true:半角英数字である(もしくは対象文字がない), false:半角英数字でない
	 */
	public boolean validate(String str) {

		int len = str.length();
		byte[] bytes = str.getBytes();

		if (len != bytes.length) {
			return false;
		}

		return true;
	}

	/**
	 * Connect Db
	 */
	public void connect(String user, String pass) throws SQLException {

		// コネクションを取得する
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:XE", // URL
				"system", // ユーザID
				"manager" // パスワード
		);

		// SQL文を表すjava.sql.Statementオブジェクトを作成
		Statement stmt = conn.createStatement();

		StringBuilder sb = new StringBuilder();
		sb.append("select * from ARS_USER ");
		sb.append(" WHERE ");
		sb.append("MEMBER_ID = ");
		sb.append("'");
		sb.append(user);
		sb.append("'");
		String sql = sb.toString();

		// データベースに問い合わせ
		ResultSet result = stmt.executeQuery(sql);

		// データ有無(true:あり/ false：なし）
		boolean isExists = result.next();

		//
		if (isExists == true) {

			// while (result.next()) {
			String pwd = result.getString("PASS");

			if (pwd.equals(pass)) {

				HealthCheck hc = new HealthCheck();
				// 遷移先に情報を渡す
				hc.setUser(user);
				// healthcheck画面へ遷移
				hc.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "照合失敗: \n passが違います");
				return;
			}
			// }
		} else {
			JOptionPane.showMessageDialog(null, "ログインエラー！: \n user: " + user
					+ "は登録されていません。");
			return;
		}
	}

	/*
	 * 画面遷移
	 */
	public void panelChange(JPanel jp, String str) {

		String name = jp.getName();

		HealthCheck hc = new HealthCheck();


		// パネル名により画面遷移先を変える
		HealthCheck.getFrames();
	}
}
