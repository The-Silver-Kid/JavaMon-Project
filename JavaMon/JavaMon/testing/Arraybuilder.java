/**
 * Array builder
 * (C) 2015 DevTSK
 */
package JavaMon.testing;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import DevTSK.Util.StringWriter;

// ur a nerd
public class Arraybuilder {

	private JFrame window;
	private static JTextArea output;
	private static JTextArea input;
	private static JScrollPane skrollout;
	private static JScrollPane skrollin;
	private static JButton button;

	private static File f;

	private final Action build = new Builder();

	public static void main(String[] args) {
		f = findDir();
		Arraybuilder ab = new Arraybuilder();
		ab.window.setVisible(true);
	}

	private static File findDir() {
		JFileChooser choose = new JFileChooser();
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		choose.showDialog(null, "Open Directory");

		return choose.getSelectedFile();
	}

	public Arraybuilder() {
		window = new JFrame();
		window.getContentPane().setBackground(SystemColor.window);
		window.setTitle("Array builder");
		window.setBackground(SystemColor.window);
		window.setResizable(false);
		window.setBounds(100, 100, 515, 475);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setLayout(null);

		output = new JTextArea();
		output.setEditable(false);
		output.setBackground(new Color(230, 230, 230));
		output.setForeground(new Color(0, 0, 0));
		output.setBounds(10, 250, 490, 190);
		window.getContentPane().add(output);

		input = new JTextArea();
		input.setBounds(10, 10, 490, 190);
		input.setEditable(true);
		input.setForeground(new Color(0, 0, 0));
		input.setBackground(new Color(230, 230, 230));
		window.getContentPane().add(input);

		button = new JButton();
		button.setAction(build);
		button.setBounds(10, 215, 490, 20);
		window.getContentPane().add(button);

		skrollin = new JScrollPane(input);
		skrollin.setBounds(input.getBounds());
		window.getContentPane().add(skrollin);

		skrollout = new JScrollPane(output);
		skrollout.setBounds(output.getBounds());
		window.getContentPane().add(skrollout);
	}

	public class Builder extends AbstractAction {
		private static final long serialVersionUID = 2753173073359879175L;

		public Builder() {
			putValue(NAME, "Build!");
		}

		public void actionPerformed(ActionEvent arg0) {
			String corr = "";
			String[] slice = input.getText().split("\\n+");
			String[][] lol = new String[slice.length][];
			for (int i = 0; i < slice.length; i++)
				lol[i] = slice[i].split(",");
			for (int i = 0; i < slice.length; i++) {
				corr = lol[i][0].replaceAll("\\s", "");
				corr = corr.replaceAll("-", "_");
				String end = "package DevTSK.JavaMon.Defaults.Generation2;" +
						"\n" +
						"\nimport DevTSK.JavaMon.Monster;" +
						"\nimport DevTSK.JavaMon.Move;" +
						"\nimport DevTSK.JavaMon.Type;" +
						"\n" +
						"\npublic class " + corr + " extends Move {" +
						"\n" +
						"\n	/* Category" +
						"\n	 * 0 = Physical" +
						"\n	 * 1 = Special" +
						"\n	 * 2 = Status" +
						"\n	 */" +
						"\n" +
						"\n	public " + corr + "() {" +
						"\n		name = \"" + lol[i][0] + "\";" +
						"\n" +
						"\n		neverMiss = false;" +
						"\n		OHKO = false;" +
						"\n" +
						"\n		type = new Type(\"" + lol[i][1] + "\");" +
						"\n" +
						"\n		baseDamage = " + lol[i][4] + ";" +
						"\n		baseAccuracy = " + lol[i][5] + ";" +
						"\n		PP = " + lol[i][3] + ";" +
						"\n" +
						"\n		priority = 1;" +
						"\n" +
						"\n		category = " + lol[i][2] + ";" +
						"\n	}" +
						"\n" +
						"\n	@Override" +
						"\n	public Monster[] getEffect(Monster atk, Monster def) {" +
						"\n	return new Monster[] { atk , def };" +
						"\n	}" +
						"\n" +
						"}";
				StringWriter s = new StringWriter();
				try {
					s.Write(end, corr + ".java", false, f.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				output.setText(end);
			}
		}

	}
}
