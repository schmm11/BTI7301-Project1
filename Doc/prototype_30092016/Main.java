import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

	
	public Main(){
	initUI();
	}	
	
	public void initUI(){
		add(new Board());
		
		setResizable(false);
		pack();
		
		setTitle("SST Prototype");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
		});
	}

}