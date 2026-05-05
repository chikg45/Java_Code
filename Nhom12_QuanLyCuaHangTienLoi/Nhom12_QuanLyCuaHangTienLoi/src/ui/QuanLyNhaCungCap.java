package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhaCungCap extends JPanel implements ActionListener, MouseListener{
	private JLabel lblQLNCC;
	private Color colorChinh = new Color(0,51,102);
	private JLabel lblMaNCC, lblTenNCC, lblSdt, lblDC, lblEmail;
	private JTextField txtMaNCC, txtTenNCC, txtSdt, txtDC, txtEmail;
	private JButton btnDienLai;
	private JLabel lblThongBao;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnThem, btnXoa, btnCapNhat, btnLuu, btnThoat;
	private JScrollPane scrollNCC;

	public QuanLyNhaCungCap() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.white);
		
		Font fontTieuDe = new Font("Arial", Font.BOLD, 35);
		
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(Color.WHITE);				
		
		lblQLNCC = new JLabel("Quản lý nhà cung cấp");
		lblQLNCC.setText(lblQLNCC.getText().toUpperCase());
		lblQLNCC.setForeground(colorChinh);
		lblQLNCC.setFont(fontTieuDe);
		pnlTieuDe.add(lblQLNCC);
		
		Box b = Box.createVerticalBox();
		
		//Dong 1
		Box b1 = Box.createHorizontalBox();
		b.add(b1);
		b.add(Box.createVerticalStrut(15));
		b1.add(lblMaNCC = new JLabel("Mã nhà cung cấp: "));
		b1.add(txtMaNCC = new JTextField());
		b1.add(lblTenNCC = new JLabel("Tên nhà cung cấp: "));
		b1.add(txtTenNCC = new JTextField());
		//Dong 2
		Box b2 = Box.createHorizontalBox();
	    b.add(b2);
	    b.add(Box.createVerticalStrut(15));
	    b2.add(lblSdt = new JLabel("Số điện thoại: "));
		b2.add(txtSdt = new JTextField());
		b2.add(lblEmail = new JLabel("Email: "));
		b2.add(txtEmail = new JTextField());
		//Dong 3
		Box b3 = Box.createHorizontalBox();
		b.add(b3);
		b.add(Box.createVerticalStrut(15));
		b3.add(lblDC = new JLabel("Địa chỉ"));
		b3.add(txtDC = new JTextField());
		//Dong 4 Thông báo ở đây
		Box b4 = Box.createHorizontalBox();
		JPanel pnlTB = new JPanel(new FlowLayout()); 
		b.add(b4);
		b.add(Box.createVerticalStrut(15));
		btnDienLai =  new JButton("Điền lại");
		lblThongBao = new JLabel(" ");
		lblThongBao.setForeground(Color.RED);
		lblThongBao.setFont(new Font("Arial", Font.ITALIC, 14));
		b4.add(Box.createHorizontalStrut(100));
		b4.add(lblThongBao);
		b4.add(Box.createHorizontalGlue()); 
		b4.add(btnDienLai);
		
		lblSdt.setPreferredSize(lblMaNCC.getPreferredSize());
		lblDC.setPreferredSize(lblMaNCC.getPreferredSize());
		lblEmail.setPreferredSize(lblTenNCC.getPreferredSize());
		
		//Table
		JPanel pnlBang = new JPanel();
		pnlBang.setLayout(new BoxLayout(pnlBang, BoxLayout.Y_AXIS));
		pnlBang.setBackground(Color.white);
		String headers[] = {"Mã nhà cung cấp", "Tên nhà cung cấp","Số điện thoại", "Email", "Địa chỉ"};
		tableModel = new DefaultTableModel(headers,0);
		scrollNCC = new JScrollPane(table = new JTable(tableModel));
		scrollNCC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		table.setRowHeight(80);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setBackground(colorChinh);
		table.getTableHeader().setForeground(Color.WHITE);
		pnlBang.add(scrollNCC);
		b.add(pnlBang);
		
		//Footer
		JPanel pnlNut = new JPanel(new FlowLayout());
		pnlNut.setBackground(Color.WHITE);
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnCapNhat = new JButton("Cập nhật");
		btnLuu = new JButton("Lưu");
		btnThoat = new JButton("Thoát");
		pnlNut.add(btnThem);
		pnlNut.add(Box.createHorizontalStrut(20));
		pnlNut.add(btnXoa);
		pnlNut.add(Box.createHorizontalStrut(20));
		pnlNut.add(btnCapNhat);
		pnlNut.add(Box.createHorizontalStrut(200));
		pnlNut.add(btnLuu);
		pnlNut.add(Box.createHorizontalStrut(20));
		pnlNut.add(btnThoat);
		b.add(pnlNut);
		this.add(pnlTieuDe,BorderLayout.NORTH);
		this.add(b,BorderLayout.CENTER);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		btnDienLai.addActionListener(this);
		table.addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();	    
	    if (o.equals(btnThoat)) {
	        Window window = SwingUtilities.getWindowAncestor(this);
	        if (window != null) {
	            window.dispose();
	        }
	    }
	    else if (o.equals(btnDienLai)) {
	        xoaTrang();
	    }
		
	}
	
	private void xoaTrang() {
	    txtMaNCC.setText("");
	    txtTenNCC.setText("");
	    txtSdt.setText("");
	    txtEmail.setText("");
	    txtDC.setText("");
	    txtMaNCC.requestFocus();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void showMessage(String msg, int type) {
	    // type: JOptionPane.ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE
	    JOptionPane.showMessageDialog(this, msg, "Thông báo", type);
	}
//	public static void main(String[] args) {
//	      SwingUtilities.invokeLater(() -> {
//	          JFrame frame = new JFrame();
//	          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	          frame.setSize(1000, 600);
//	          frame.setLocationRelativeTo(null);
//	          frame.add(new QuanLyNhaCungCap());
//	          frame.setVisible(true);
//	          });
//	     }
}
