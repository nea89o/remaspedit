package remasp;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import remasp.controller.RemaspController;
import remasp.view.RemaspView;

public class Remasp {
	static RemaspController remaspController;
	static RemaspView remaspView;

	public static void main(String[] args) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			Logger.getLogger(RemaspView.class.getName()).log(Level.SEVERE, null, ex);
		}

		remaspController = new RemaspController();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Remasp.remaspController.remaspView.setVisible(true);
				Remasp.remaspController.remaspView.getjTextPane().requestFocus();
			}
		});
	}
}
