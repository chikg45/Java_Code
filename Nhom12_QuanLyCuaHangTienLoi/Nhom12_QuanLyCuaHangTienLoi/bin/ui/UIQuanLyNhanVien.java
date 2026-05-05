package ui;

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

public class UIQuanLyNhanVien extends JPanel {

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
    
    public UIQuanLyNhanVien() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        add(createFormNVPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createFormNVPanel() {
        JPanel ThongTinNV = new JPanel(new BorderLayout());
        ThongTinNV.setBackground(Color.WHITE);
        ThongTinNV.setBorder(new TitledBorder("Thông tin nhân viên"));

        JPanel formTTNV = new JPanel(new GridLayout(4, 4, 20, 20));
        formTTNV.setBackground(Color.WHITE); // Đã thêm nền trắng
        
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
        cboCaLam.setBackground(Color.WHITE);
        
        cboChucVu = new JComboBox<>(chucvu);
        cboChucVu.setBackground(Color.WHITE);
        
        btnNam = new JRadioButton("Nam");
        btnNam.setBackground(Color.WHITE); // Đã thêm nền trắng
        
        btnNu = new JRadioButton("Nữ"); // Đã sửa có dấu
        btnNu.setBackground(Color.WHITE); // Đã thêm nền trắng
        
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
        gtinh.setBackground(Color.WHITE); // Đã thêm nền trắng
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
        TimKiemNV.setBackground(Color.WHITE); // Đã thêm nền trắng
        
        JPanel timMaNV = new JPanel();
        timMaNV.setBackground(Color.WHITE);
        timMaNV.add(new JLabel("Mã nhân viên:"));
        timMaNV.add(tfTimMaNV);
        TimKiemNV.add(timMaNV);
        
        JPanel timTenNV = new JPanel();
        timTenNV.setBackground(Color.WHITE);
        timTenNV.add(new JLabel("Tên nhân viên:"));
        timTenNV.add(tfTimTenNV);
        TimKiemNV.add(timTenNV);
        
        JPanel timSDT = new JPanel();
        timSDT.setBackground(Color.WHITE);
        timSDT.add(new JLabel("Số điện thoại:")); // Đã sửa có dấu
        timSDT.add(tfTimSoDT);
        TimKiemNV.add(timSDT);

        JPanel timKiem = new JPanel();
        timKiem.setBackground(Color.WHITE);
        btnTimKiem = new JButton("Tìm Kiếm"); // Đã sửa có dấu
        timKiem.add(btnTimKiem);
        TimKiemNV.add(timKiem);

        ThongTinNV.add(formTTNV, BorderLayout.CENTER);
        ThongTinNV.add(TimKiemNV, BorderLayout.SOUTH);
        ThongTinNV.add(createActionPanel(), BorderLayout.EAST);

        return ThongTinNV;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(Color.WHITE); // Đã thêm nền trắng
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        btnThem = new JButton("Thêm"); // Đã sửa có dấu
        btnCapNhat = new JButton("Cập Nhật"); // Đã sửa có dấu
        btnXoa = new JButton("Xóa"); // Đã sửa có dấu
        btnDienLai = new JButton("Điền lại");

        panel.add(btnThem);
        panel.add(btnCapNhat);
        panel.add(btnXoa);
        panel.add(btnDienLai);
        
        return panel;
    }
    
    // ===== TABLE =====
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Đã thêm nền trắng
        panel.setBorder(new TitledBorder("Danh sách nhân viên")); // Đã sửa tiêu đề sai
        
        // Đã thiết kế lại cột cho khớp với form nhập liệu Nhân Viên
        String[] columns = {
                "Mã Nhân viên", "Họ và tên", "Giới tính", "SĐT",
                "Chức vụ", "Ca làm", "Tên đăng nhập", "Mật khẩu"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Sample data chuẩn cho Nhân Viên
        model.addRow(new Object[]{"NV001", "Nguyễn Chí Tài", "Nam", "0987654321", "Quản lý", "Ca chiều", "chitai_admin", "123456"});

        JTable table = new JTable(model);
        table.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE); // Đảm bảo bảng trống vẫn có màu trắng

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}