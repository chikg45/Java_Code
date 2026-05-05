package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class QuanLyDanhMuc extends JPanel implements ActionListener,MouseListener{

	private JLabel lblQLDM;
	private Color colorChinh = new Color(0,51,102);
	private Color colorCyan = new Color(0, 204, 204);
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton btnThem, btnXoa, btnLuu, btnThoat;
	private JScrollPane scrollDM;
	private Window window;

	public QuanLyDanhMuc() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(Color.WHITE);
		
		Font fontTieuDe = new Font("Arial", Font.BOLD, 35);
		
		lblQLDM = new JLabel("Quản lý danh mục");
		lblQLDM.setText(lblQLDM.getText().toUpperCase());
		lblQLDM.setForeground(colorChinh);
		lblQLDM.setFont(fontTieuDe);
		pnlTieuDe.add(lblQLDM);
		
		Box b = Box.createHorizontalBox();
		
		JPanel pnlBang = new JPanel(new BorderLayout());
		pnlBang.setBackground(Color.white);
		String header[] = {"STT", "Tên danh mục"};
		tableModel = new DefaultTableModel(header,0);
		scrollDM = new JScrollPane();
		table = new JTable(tableModel);
		scrollDM.setViewportView(table);
		scrollDM.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table.setRowHeight(20);
		table.getTableHeader().setBackground(colorChinh);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setAutoCreateRowSorter(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(150);
        
        pnlBang.add(scrollDM,BorderLayout.CENTER);
		b.add(pnlBang);
		
		JPanel pnlNut = new JPanel(new GridLayout(6,1,10,10));
		pnlNut.setBackground(Color.white);
		Font font = new Font("Arial", Font.BOLD, 15);
		pnlNut.setBorder(BorderFactory.createTitledBorder(null,
				"Thao tác",
				TitledBorder.LEFT,      
			    TitledBorder.TOP,
				font,
				colorChinh
				));
		btnThem = new JButton("Thêm");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnThoat = new JButton("Thoát");
		pnlNut.add(btnThem);
		pnlNut.add(btnXoa);
		pnlNut.add(Box.createVerticalStrut(20));
		pnlNut.add(Box.createVerticalStrut(20));
		pnlNut.add(btnLuu);
		pnlNut.add(btnThoat);
		b.add(pnlNut);					
		this.add(pnlTieuDe, BorderLayout.NORTH);
		this.add(b, BorderLayout.CENTER);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		table.addMouseListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==btnThoat) {
			window = SwingUtilities.getWindowAncestor(this);
			if(window!=null) {
				window.dispose();
			}
		} else if(o.equals(btnThem)) {
			int stt = table.getRowCount()+1;
			tableModel.addRow(new Object[] {stt, ""});
			//Cho phep them dl lên bảng
			int dongCuoi = table.getRowCount()-1;
			table.setRowSelectionInterval(dongCuoi, dongCuoi);
		    table.scrollRectToVisible(table.getCellRect(dongCuoi, 0, true));
			table.editCellAt(dongCuoi, 1);
			if(table.getEditorComponent() != null) {
				table.getEditorComponent().requestFocus();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
	    if (o instanceof JPanel) {
	        ((JPanel)o).setBorder(new LineBorder(colorCyan, 2));
	    }
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
	
	
//	public static void main(String[] args) {
//      SwingUtilities.invokeLater(() -> {
//          JFrame frame = new JFrame();
//          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          frame.setSize(600, 400);
//          frame.setLocationRelativeTo(null);
//          frame.add(new QuanLyDanhMuc());
//          frame.setVisible(true);
//          });
//     }
}
