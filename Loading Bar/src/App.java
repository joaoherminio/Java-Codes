import javax.swing.SwingUtilities;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//Instanciando a barra de loading
				new LoadingBarGUI().setVisible(true);
			}
		});
	}

}
