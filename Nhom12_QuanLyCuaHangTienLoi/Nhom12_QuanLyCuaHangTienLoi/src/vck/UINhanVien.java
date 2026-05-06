package Demo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class UINhanVien extends JPanel {

	private JTextField tfTenDN;
	private JTextField tfMaNV;
	private JTextField tfTenNV;
	private JTextField tfSDT;
	private JTextField tfMK;
	private JTextField tfTimMaNV;
	private JTextField tfTimTenNV;
	private JTextField tfTimSoDT;
	private JComboBox<String> cboChucVu;
	private JComboBox<String> cboCaLam;
	private JRadioButton btnNam;
	private JRadioButton btnNu;
	private ButtonGroup groupGT;
	
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnDienLai;
	private JButton btnTimKiem;
	
	
	public UINhanVien() {
		  setLayout(new BorderLayout());
		    setBackground(Color.WHITE);
		    
		    add(createFormNVPanel(), BorderLayout.NORTH); // Toàn bộ phần nhập liệu ở trên
		    add(createTablePanel(), BorderLayout.CENTER); // Bảng hiển thị chiếm phần còn lại
		
		
	}
	
	private JPanel createFormNVPanel() {
		
        JPanel ThongTinNV = new JPanel(new BorderLayout());
        ThongTinNV.setBackground(Color.WHITE);
        ThongTinNV.setBorder(new TitledBorder("Thông tin nhân viên"));

        JPanel formTTNV = new JPanel(new GridLayout(4, 4, 20, 20));
        
        tfTenDN = new JTextField(10);
        tfTenNV = new JTextField(10);
        tfSDT = new JTextField(10);
        tfMK = new JTextField(10);
        tfMaNV = new JTextField(10);
        tfTimMaNV = new JTextField(10);
        tfTimSoDT = new JTextField(10);
        tfTimTenNV = new JTextField(10);
                
        String[] calam = {"Ca đêm","Ca chiều","Ca tối"};
        String[] chucvu = {"Nhân viên bán hàng","Trợ lý quản lý","Quản lý"};
    
        cboCaLam = new JComboBox<>(calam); 
        cboChucVu = new JComboBox<>(chucvu);
        
        
        
        btnNam = new JRadioButton("Nam");
        btnNu = new JRadioButton("Nu");
        groupGT = new ButtonGroup();
       groupGT.add(btnNam);
       groupGT.add(btnNu);
        

       formTTNV.add(new JLabel("Tên đăng nhập:"));
       formTTNV.add(tfTenDN);
       formTTNV.add(new JLabel("Chức vụ:"));
       formTTNV.add(cboChucVu);

       formTTNV.add(new JLabel("Mã nhân viên:"));
        formTTNV.add(tfMaNV);
        formTTNV.add(new JLabel("Ca làm việc:"));
        formTTNV.add(cboCaLam);
        
        formTTNV.add(new JLabel("Họ và tên:"));
        formTTNV.add(tfTenNV);
        formTTNV.add(new JLabel("Giới tính:"));
      
        JPanel gtinh = new JPanel();
        gtinh.setLayout(new BoxLayout(gtinh, BoxLayout.X_AXIS));
        gtinh.add(btnNam);
        gtinh.add(btnNu);
        formTTNV.add(gtinh);
   
        formTTNV.add(new JLabel("Số điện thoại:"));
        formTTNV.add(tfSDT);
        formTTNV.add(new JLabel("Mật khẩu:"));
        formTTNV.add(tfMK);
        
        JPanel TimKiemNV = new JPanel();
        TimKiemNV.setLayout(new BoxLayout(TimKiemNV, BoxLayout.X_AXIS));
        TimKiemNV.setBorder(new TitledBorder("Tìm kiếm nhân viên"));
        TimKiemNV.setBackground(Color.WHITE);
        
        
        JPanel timMaNV = new JPanel();
        timMaNV.add(new JLabel("Mã nhân viên:"));
        timMaNV.add(tfTimMaNV);
        TimKiemNV.add(timMaNV);
        
        JPanel timTenNV = new JPanel();
        timTenNV.add(new JLabel("Tên nhân viên:"));
        timTenNV.add(tfTimTenNV);
        TimKiemNV.add(timTenNV);
        
        JPanel timSDT = new JPanel();
        timSDT.add(new JLabel("So dien thoai:"));
        timSDT.add(tfTimSoDT);
        TimKiemNV.add(timSDT);

        JPanel timKiem = new JPanel();
        btnTimKiem = new JButton("Tim Kiem");
        timKiem.add(btnTimKiem);
        TimKiemNV.add(timKiem);

        ThongTinNV.add(formTTNV, BorderLayout.CENTER);
        ThongTinNV.add(TimKiemNV, BorderLayout.SOUTH);
        
   
        
    
        ThongTinNV.add(createActionPanel(), BorderLayout.EAST);

        return ThongTinNV;
    }
	
	 private JPanel createActionPanel() {
	        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
	        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	        
	        btnThem = new JButton("Them");
	        btnCapNhat = new JButton("Cap Nhat");
	        btnXoa = new JButton("Xoa");
	        btnDienLai = new JButton("Dien lai");

	        
	            panel.add(btnThem);
	            panel.add(btnCapNhat);
	            panel.add(btnXoa);
	            panel.add(btnDienLai);
	        
	        return panel;
	    }
	
	 // ===== TABLE =====
	    private JPanel createTablePanel() {
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBorder(new TitledBorder("Danh sách sản phẩm"));
	        
	        String[] columns = {
	                "Mã Khách hàng", "Tên khách hàng", "Giới tính", "SĐT",
	                "Điểm tích lũy"
	        };

	        DefaultTableModel model = new DefaultTableModel(columns, 0);

	        // sample data
	        model.addRow(new Object[]{"W0001", "CocaCola", 67, "Lon", "10000", "Đồ uống", "Còn hạn"});

	        JTable table = new JTable(model);
	        JScrollPane scrollPane = new JScrollPane(table);
	        table.setBackground(Color.WHITE);

	        panel.add(scrollPane, BorderLayout.CENTER);

	        return panel;
	    }
	    
	
	
	
}
