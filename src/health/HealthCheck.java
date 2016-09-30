package health;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HealthCheck extends JFrame {
	private JTextField height;
	private JTextField weight;
	private String user;


/*
 * bluetoothとかと連動してる端末でアプリにデータ送れれば自動で記録してくれるんだけどな
 * まあそれはおいといて
 */



	/*
	 * set user
	 */
	public void setUser(String str){

		this.user = str;
	}

	/**
	 * get weight
	 */
	public double getWht() {

		double weightD = Double.valueOf(this.weight.getText());

		return weightD;
	}

	/**
	 * get height
	 */
	public double getHgt() {

		double heightD = Double.valueOf(this.height.getText());

		return heightD;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthCheck frame = new HealthCheck();
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
	public HealthCheck() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 30, 50, 65, 50, 39, 91, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 19, 21, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel label_user = new JLabel("　");
		GridBagConstraints gbc_label_user = new GridBagConstraints();
		gbc_label_user.insets = new Insets(0, 0, 5, 5);
		gbc_label_user.gridx = 4;
		gbc_label_user.gridy = 0;
		getContentPane().add(label_user, gbc_label_user);

		JLabel label_username = new JLabel(" ");
		GridBagConstraints gbc_label_username = new GridBagConstraints();
		gbc_label_username.insets = new Insets(0, 0, 5, 0);
		gbc_label_username.gridx = 5;
		gbc_label_username.gridy = 0;
		getContentPane().add(label_username, gbc_label_username);

		JLabel lblNewLabel = new JLabel("身長");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		height = new JTextField();
		GridBagConstraints gbc_height = new GridBagConstraints();
		gbc_height.fill = GridBagConstraints.HORIZONTAL;
		gbc_height.insets = new Insets(0, 0, 5, 5);
		gbc_height.gridx = 2;
		gbc_height.gridy = 1;
		getContentPane().add(height, gbc_height);
		height.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ｃｍ");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 1;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel("体重");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		weight = new JTextField();
		GridBagConstraints gbc_weight = new GridBagConstraints();
		gbc_weight.fill = GridBagConstraints.HORIZONTAL;
		gbc_weight.insets = new Insets(0, 0, 5, 5);
		gbc_weight.gridx = 2;
		gbc_weight.gridy = 2;
		getContentPane().add(weight, gbc_weight);
		weight.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("ｋｇ");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 2;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("BMI：　");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		JLabel bmi = new JLabel(" ");
		GridBagConstraints gbc_bmi = new GridBagConstraints();
		gbc_bmi.insets = new Insets(0, 0, 5, 5);
		gbc_bmi.gridx = 2;
		gbc_bmi.gridy = 4;
		getContentPane().add(bmi, gbc_bmi);

		JButton btnNewButton = new JButton("チェック");

		// チェックボタン押下時のイベント
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				double heightD = 0;
				double weightD = 0;
				double bmiValue = 0;

				String heightStr = height.getText();
				if (heightChk(heightStr) == true) {
					heightD = Double.valueOf(heightStr);
				}

				String weightStr = weight.getText();
				if (weightChk(weightStr) == true) {
					weightD = Double.valueOf(weightStr);
				}

				bmiValue = calcBmi(heightD, weightD);

				String bmiValueStr = String.valueOf(bmiValue);
				bmi.setText(bmiValueStr);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 2;
		getContentPane().add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("データを保存");

		// データ保存
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// sql文作成
				Login login = new Login();
				String user = login.getUser();
				String pass = login.getPass();

				double bmi = calcBmi(jTextToDouble(height), jTextToDouble(weight));

				String sql = createSql(getHgt(), getWht(), bmi);

				// DB接続
				try {
					boolean result = executeSql(sql);

					if (result == true) {
						JOptionPane.showMessageDialog(null, "登録しました");
					}

				} catch (SQLException e1) {
					// TODO 自動生成された catch ブロック
					JOptionPane.showMessageDialog(null, "DB接続エラー！");
					return;
				}

			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 9;
		getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

	}

	/**
	 * 重複チェック
	 */
	public boolean isNewdata() {

		String sql = createSqlnewData();

		// DB接続
		try {
			boolean result = executeSql(sql);

			if (result == true) {



				JOptionPane.showMessageDialog(null, "登録しました");
			}

		} catch (SQLException e1) {
			// TODO 自動生成された catch ブロック
			JOptionPane.showMessageDialog(null, "DB接続エラー！");

		}



		// ここまで来ればOK
		return true;
	}

	/**
	 * Jtext→double
	 */
	public double jTextToDouble(JTextField text) {

		double d = 0;

		String str = text.getText();
		d = Double.valueOf(str);

		return d;

	}

	/**
	 * Connect Db
	 */
	public boolean executeSql(String sql)
			throws SQLException {

		// コネクションを取得する
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:XE", // URL
				"system", // ユーザID
				"manager" // パスワード
		);

		// SQL文を表すjava.sql.Statementオブジェクトを作成
		Statement stmt = conn.createStatement();

		// データベースに問い合わせ
		stmt.executeQuery(sql);

		// ここまでくれば成功
		return true;

	}

	/**
	 * create sql
	 *
	 * @return
	 */
	public String createSqlnewData() {

		String sqlStr = "";
		StringBuilder sb = new StringBuilder();

		// 現在日付
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = sdf1.format(date);

		sb.append("select count (*) from health ");
		sb.append("where");
		sb.append("USER_ID =");
		sb.append("'");
		sb.append(this.user);
		sb.append("'");
		sb.append(" and ");
		sb.append(" MEASURE_DATE = ");
		sb.append("'");
		sb.append(dateStr);
		sb.append("'");
		sqlStr = sb.toString();

		return sqlStr;

	}

	/**
	 * create sql
	 *
	 * @return
	 */
	public String createSql( double weight, double height,
			double bmi) {

		String sqlStr = "";
		StringBuilder sb = new StringBuilder();

		// 現在日付
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
		String dateStr = sdf1.format(date);

		sb.append("insert into health ");
		sb.append(" ( ");
		sb.append("USER_ID ");
		sb.append(",");
		sb.append("WEIGHT ");
		sb.append(",");
		sb.append("HEIGHT ");
		sb.append(",");
		sb.append("BMI ");
		sb.append(",");
		sb.append("MEASURE_DATE ");
		sb.append(")");
		sb.append(" values ");
		sb.append("(");
		sb.append("'");
		sb.append(this.user);
		sb.append("'");
		sb.append(",");
		sb.append(weight);
		sb.append(",");
		sb.append(height);
		sb.append(",");
		sb.append(bmi);
		sb.append(",");
		sb.append("'");
		sb.append(dateStr);
		sb.append("'");
		sb.append(")");
		sqlStr = sb.toString();

		return sqlStr;

	}

	/*
	 * 文字列で受け取った値を数値に変換 バリデーションチェック
	 */
	public boolean heightChk(String str) {

		try {
			double d = Double.valueOf(str);

			if (100 > d || 220 < d) {

				JOptionPane.showMessageDialog(null, "身長は100～200で入力してください");
				return false;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "エラー発生\n数値が不正です");
		}

		// ここまで来ればOK
		return true;

	}

	/*
	 * 文字列で受け取った値を数値に変換 バリデーションチェック
	 */
	public boolean weightChk(String str) {

		try {
			double d = Double.valueOf(str);

			if (30 > d || 200 < d) {

				JOptionPane.showMessageDialog(null, "体重は30～200で入力してください");
				return false;
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "エラー発生\n数値が不正です");
		}

		// ここまで来ればOK
		return true;

	}

	/*
	 * BMI計算
	 */
	public double calcBmi(double height, double weight) {

		double bmi = 0;

		// BMI = 体重÷（身長×身長）
		bmi = (weight / (height * height)) * 10000;

		// bigdecimalクラスの四捨五入メソッドを使うためいったん変換
		BigDecimal bd = new BigDecimal(bmi);
		// 小数点第2位で四捨五入
		bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
		// doubleに戻す
		bmi = Double.valueOf(bd.toString());

		return bmi;

	}
}
