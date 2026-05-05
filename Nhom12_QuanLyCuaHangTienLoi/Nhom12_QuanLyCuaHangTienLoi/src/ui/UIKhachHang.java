package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class UIKhachHang extends JPanel {

    private JButton btnThem;
    private JButton btnCapNhat;
    private JButton btnXoa;
    private JButton btnDienLai;
    private JButton btnTimKiem;
    
    private JTextField tfMaKH;
    private JTextField tfHoTen;
    private JTextField tfDiemTL;
    private JTextField tfSoDT;
    private JTextField tfTimMAKH;
    private JTextField tfTimTenKH;
    private JTextField tfTimSDT;
    private JRadioButton nam;
    private JRadioButton nu;
    private ButtonGroup groupGtinh;
    
    private CardLayout cardLayout;
    private JPanel pnlCard;
    private JComboBox<String> cbLoai;

    public UIKhachHang() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        add(createFormKHPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createFormKHPanel() {
        JPanel ThongTinKH = new JPanel(new BorderLayout());
        ThongTinKH.setBackground(Color.WHITE);
        ThongTinKH.setBorder(new TitledBorder("Thông tin khách hàng"));

        JPanel formTTKH = new JPanel(new GridLayout(3, 2, 20, 20));
        formTTKH.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho form
        
        tfHoTen = new JTextField(10);
        tfMaKH = new JTextField(10);
        tfDiemTL = new JTextField(10);
        tfSoDT = new JTextField(10);
        tfTimMAKH = new JTextField(10);
        tfTimSDT = new JTextField(10);
        tfTimTenKH = new JTextField(10);
        
        nam = new JRadioButton("Nam");
        nam.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho RadioButton
        
        nu = new JRadioButton("Nữ"); // Sửa lỗi: Viết có dấu
        nu.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho RadioButton
        
        groupGtinh = new ButtonGroup();
        groupGtinh.add(nu);
        groupGtinh.add(nam);
        
        formTTKH.add(new JLabel("Mã Khách hàng :"));
        formTTKH.add(tfMaKH);
        formTTKH.add(new JLabel("Họ và tên:"));
        formTTKH.add(tfHoTen);

        formTTKH.add(new JLabel("Điểm Tích Lũy:"));
        formTTKH.add(tfDiemTL);
        formTTKH.add(new JLabel("Số điện thoại:"));
        formTTKH.add(tfSoDT);

        formTTKH.add(new JLabel("Giới tính:"));
        JPanel gtinh = new JPanel();
        gtinh.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho panel giới tính
        gtinh.setLayout(new BoxLayout(gtinh, BoxLayout.X_AXIS));
        gtinh.add(nam);
        gtinh.add(nu);
        formTTKH.add(gtinh);
        
        JPanel TimKiemKH = new JPanel();
        TimKiemKH.setLayout(new BoxLayout(TimKiemKH, BoxLayout.X_AXIS));
        TimKiemKH.setBorder(new TitledBorder("Tìm kiếm khách hàng")); // Sửa lỗi: Viết có dấu
        TimKiemKH.setBackground(Color.WHITE);
        
        JPanel timMaKH = new JPanel();
        timMaKH.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng
        timMaKH.add(new JLabel("Mã khách hàng:"));
        timMaKH.add(tfTimMAKH);
        TimKiemKH.add(timMaKH);
        
        JPanel timTenKH = new JPanel();
        timTenKH.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng
        timTenKH.add(new JLabel("Tên khách hàng:"));
        timTenKH.add(tfTimTenKH);
        TimKiemKH.add(timTenKH);
        
        JPanel timSDT = new JPanel();
        timSDT.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng
        timSDT.add(new JLabel("Số điện thoại:"));
        timSDT.add(tfTimSDT);
        TimKiemKH.add(timSDT);

        JPanel timKiem = new JPanel();
        timKiem.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng
        btnTimKiem = new JButton("Tìm Kiếm");
        timKiem.add(btnTimKiem);
        TimKiemKH.add(timKiem);

        ThongTinKH.add(formTTKH, BorderLayout.CENTER);
        ThongTinKH.add(TimKiemKH, BorderLayout.SOUTH);
        ThongTinKH.add(createActionPanel(), BorderLayout.EAST);

        return ThongTinKH;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho panel chứa nút
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        btnThem = new JButton("Thêm");
        btnCapNhat = new JButton("Cập Nhật");
        btnXoa = new JButton("Xóa");
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
        panel.setBackground(Color.WHITE); // Sửa lỗi: Set nền trắng cho panel chứa bảng
        panel.setBorder(new TitledBorder("Danh sách khách hàng")); // Sửa lỗi: Đổi từ "Sản phẩm" sang "Khách hàng"
        
        String[] columns = {
                "Mã Khách hàng", "Tên khách hàng", "Giới tính", "SĐT",
                "Điểm tích lũy"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Sửa lỗi: Đổi data mẫu cho phù hợp với 5 cột của khách hàng (code cũ đang truyền vào 7 cột của sản phẩm)
        model.addRow(new Object[]{"KH0001", "Nguyễn Văn A", "Nam", "0987654321", "150"});

        JTable table = new JTable(model);
        table.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE); // Set nền trắng cho thanh cuộn
        scrollPane.getViewport().setBackground(Color.WHITE); // Set nền trắng cho vùng hiển thị bên dưới bảng

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }
}