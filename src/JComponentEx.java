import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComponentEx extends JFrame {
	public JComponentEx() {
		super("JComponent의 공통 메소드 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//윈도우 종료를 위해 필요!
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JButton b1 = new JButton("Magenta/Yellow Button");
		JButton b2 = new JButton("  Disabled Button  ");
		JButton b3 = new JButton("getX(), getY()");
		
		b1.setBackground(Color.YELLOW);
		b1.setForeground(Color.MAGENTA); 
		b1.setFont(new Font("Arial", Font.ITALIC, 20));
		b2.setEnabled(false); 
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				setTitle(b.getX() + "," + b.getY());
			}
		});
		
		c.add(b1); c.add(b2); c.add(b3); 
		//여기서 부턴 10-2 예제
		JLabel textLabel = new JLabel("제임스 고슬링 입니더!");
		ImageIcon img = new ImageIcon("images/gosling.jpg");		
		JLabel imageLabel = new JLabel(img); 
		ImageIcon icon = new ImageIcon("images/icon.gif");
		JLabel label = new JLabel("커피한잔 하실래예, 전화주이소", icon, SwingConstants.CENTER);

		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);

		setSize(260,200); setVisible(true);
	}

	public static void main(String[] args) {
		new JComponentEx();
	}
}