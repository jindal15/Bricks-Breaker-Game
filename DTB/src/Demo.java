import javax.swing.*;

public class Demo {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gp = new Gameplay();
		obj.add(gp);
		obj.setBounds(10,10,700,600);
		obj.setTitle("DTB");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
