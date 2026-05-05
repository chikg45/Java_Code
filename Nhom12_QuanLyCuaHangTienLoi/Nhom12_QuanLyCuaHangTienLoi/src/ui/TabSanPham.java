package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class TabSanPham extends JPanel implements ActionListener,MouseListener {
	//Khai bao
    private JPanel pnlNoiDung;
    private JTextField txtMaSP, txtTenSP, txtSoLuong, txtGiaNhap, txtGiaBan;
    private JComboBox<String> cboNhaCC, cboDanhMuc, cboTrangThai;
    private DefaultTableModel tableModel;
    private JTable table;
    private Color colorMain = new Color(0, 51, 102);
    private Color colorForm = new Color(248, 252, 255);
    private JButton btnThem, btnCapNhat, btnXoa, btnDien;
	private JButton btnNcc, btnDMuc;
	private JButton btnTaiLen;
	private JLabel lblMa, lblTen, lblNCC, lblDMuc, lblSoLuong, lblGiaNhap, lblGiaBan,lblTrangThai,lblHSD;
	//Tim
	private JLabel lblTimTen, lblTimDM, lblTimTT, lblTimMa, lblTimSL, lblTu, lblDen, lblTimNCC;
	private JTextField txtTimTen, txtTimMa, txtTu, txtDen;
	private JComboBox<String> cboTimDM, cboTimTT,cboTimNCC;
	private JButton btnBoLoc,btnTim;
	private JPanel pnlChinh;
	private JLabel lblAnh;
	
	private JDatePickerImpl date;
	
	private CardLayout subCardLayout;
    private JPanel subCardPanel;
    private List<JPanel> tabs = new ArrayList<>();
	private UtilDateModel mauNgayHSD;
	private JDatePickerImpl boChonNgayHSD;
	private JDatePanelImpl khungChonNgayHSD;
	private URL urlAnh;

    public TabSanPham() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);

        JPanel pnlTieuDe = new JPanel();
        pnlTieuDe.setBackground(Color.WHITE);
        JLabel lblTieuDe = new JLabel("SẢN PHẨM");
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 40));
        lblTieuDe.setForeground(colorMain);
        pnlTieuDe.add(lblTieuDe);
        this.add(pnlTieuDe, BorderLayout.NORTH);

        pnlNoiDung = new JPanel();
        pnlNoiDung.setBackground(Color.WHITE);
        pnlNoiDung.setLayout(new BoxLayout(pnlNoiDung, BoxLayout.Y_AXIS));
        pnlNoiDung.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        pnlNoiDung.add(taoFormNhapLieu());
        pnlNoiDung.add(Box.createVerticalStrut(10));
        pnlNoiDung.add(taoBoLocTimKiem());
        pnlNoiDung.add(Box.createVerticalStrut(10));
        pnlNoiDung.add(taoBangDuLieu());

        this.add(pnlNoiDung, BorderLayout.CENTER);

        dangKySuKien();
        docDuLieuVaoBang();//Tạm Thời
    }

    private JPanel taoFormNhapLieu() {
        JPanel pnlForm = new JPanel(new BorderLayout(20, 0));
        pnlForm.setBackground(colorForm);
        JPanel pnlInfo = new JPanel(new GridBagLayout());
        pnlInfo.setBackground(colorForm);
        pnlInfo.setBorder(new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18), colorMain));
        
        Dimension fieldSize = new Dimension(150, 25);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cột 1
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 0; 
        pnlInfo.add(lblMa = new JLabel("Mã sản phẩm:"), gbc);
        gbc.weightx = 1.0; gbc.gridx = 1; 
        pnlInfo.add(txtMaSP = new JTextField(10), gbc);
        
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 1; 
        pnlInfo.add(lblTen = new JLabel("Tên sản phẩm:"), gbc);
        gbc.weightx = 1.0; gbc.gridx = 1; 
        pnlInfo.add(txtTenSP = new JTextField(), gbc);
        
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 2; 
        pnlInfo.add(lblNCC =new JLabel("Nhà cung cấp:"), gbc);btnNcc = new JButton();
        gbc.weightx = 1.0; gbc.gridx = 1; 
        pnlInfo.add(taoCboVoiBtn(cboNhaCC = new JComboBox<>(new String[]{}),btnNcc), gbc);
        
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 3; 
        pnlInfo.add(lblDMuc = new JLabel("Danh mục:"), gbc);btnDMuc = new JButton();
        gbc.weightx = 1.0; gbc.gridx = 1; 
        pnlInfo.add(taoCboVoiBtn(cboDanhMuc = new JComboBox<>(new String[]{}), btnDMuc), gbc);

        // --- Cột 2: Hình ảnh sản phẩm ---
        gbc.weightx = 0; gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 3;
        lblAnh = new JLabel();
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblAnh.setPreferredSize(new Dimension(140, 140));
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            URL url = getClass().getResource("/images/KhungHinh_SP.png");           
            if (url != null) {
                ImageIcon icon = new ImageIcon(url);
                Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
                lblAnh.setIcon(new ImageIcon(img));
                lblAnh.setText(""); 
            } else {
                lblAnh.setText("Không tìm thấy ảnh");
            }
        } catch (Exception e) {
            lblAnh.setText("Lỗi load ảnh");
            e.printStackTrace();
        }
        pnlInfo.add(lblAnh, gbc);
        
        gbc.gridy = 3; gbc.gridheight = 1;
        btnTaiLen = new JButton("Tải lên");
        pnlInfo.add(btnTaiLen, gbc);

        // Cột 3
        gbc.weightx = 0; gbc.gridx = 3; gbc.gridy = 0; 
        pnlInfo.add(lblSoLuong = new JLabel("Số lượng:"), gbc);
        gbc.weightx = 1; gbc.gridx = 4; 
        pnlInfo.add(txtSoLuong = new JTextField(), gbc);
        
        gbc.weightx = 0; gbc.gridx = 3; gbc.gridy = 1; 
        pnlInfo.add(lblGiaNhap =new JLabel("Giá nhập:"), gbc);
        gbc.weightx = 1; gbc.gridx = 4; 
        pnlInfo.add(txtGiaNhap = new JTextField(), gbc);
        
        gbc.weightx = 0; gbc.gridx = 3; gbc.gridy = 2; 
        pnlInfo.add(lblGiaBan =new JLabel("Giá bán:"), gbc);
        gbc.weightx = 1; gbc.gridx = 4; 
        pnlInfo.add(txtGiaBan = new JTextField(), gbc);
        
        gbc.weightx = 0; gbc.gridx = 3; gbc.gridy = 3; 
        pnlInfo.add(lblTrangThai = new JLabel("Trạng Thái:"), gbc);
        gbc.weightx = 1; gbc.gridx = 4; 
        pnlInfo.add(cboTrangThai = new JComboBox<>(new String[]{}), gbc);
        //Han su dung dung JDatePickerImpl        
        // Cột 3 - Dòng 4: Hạn sử dụng
        gbc.weightx = 0; gbc.gridx = 3; gbc.gridy = 4; 
        pnlInfo.add(lblHSD = new JLabel("Hạn sử dụng:"), gbc);

        // 1. Tạo Model cho ngày (mặc định là ngày hiện tại)
        mauNgayHSD = new UtilDateModel();
        mauNgayHSD.setSelected(true); 
        // 2. Cấu hình thuộc tính hiển thị tiếng Việt
        Properties p = new Properties();
        p.put("text.today", "Hôm nay");
        p.put("text.month", "Tháng");
        p.put("text.year", "Năm");
        // 3. Khởi tạo Panel chứa lịch
        khungChonNgayHSD = new JDatePanelImpl(mauNgayHSD, p);
        // 4. Khởi tạo JDatePickerImpl với lớp định dạng ngày
        boChonNgayHSD = new JDatePickerImpl(khungChonNgayHSD, new DinhDangNgay());

        gbc.weightx = 1; gbc.gridx = 4; 
        pnlInfo.add(boChonNgayHSD, gbc);

        //Thao tác
        JPanel pnlThaoTac = new JPanel(new GridLayout(4, 1, 0, 10));
        pnlThaoTac.setBackground(colorForm);
        pnlThaoTac.setBorder(new TitledBorder("Thao tác"));
        pnlThaoTac.setPreferredSize(new Dimension(130, 0));
        
        pnlThaoTac.add(btnThem = new JButton("Thêm"));
        pnlThaoTac.add(btnCapNhat = new JButton("Cập nhật"));
        pnlThaoTac.add(btnXoa = new JButton("Xóa"));
        pnlThaoTac.add(btnDien = new JButton("Điền lại"));
        //ADD
        pnlForm.add(pnlInfo, BorderLayout.CENTER);
        pnlForm.add(pnlThaoTac, BorderLayout.EAST);
        return pnlForm;
    }

    private JPanel taoCboVoiBtn(JComboBox<String> cb, JButton btnCustom) {
        JPanel pnlCVB = new JPanel(new BorderLayout(5, 0));
        pnlCVB.setBackground(colorForm);
        pnlCVB.add(cb, BorderLayout.CENTER);
        
        // Thiết lập cho nút 
        btnCustom.setText("+");
        btnCustom.setBackground(colorMain);
        btnCustom.setForeground(Color.white);
        btnCustom.setMargin(new Insets(0, 0, 0, 0));
        btnCustom.setPreferredSize(new Dimension(40, 20));
        
        pnlCVB.add(btnCustom, BorderLayout.EAST);
        return pnlCVB;
    }
    //FORM TIM KIEM
    private JPanel taoBoLocTimKiem() {
        pnlChinh = new JPanel(new GridBagLayout());
        pnlChinh.setBackground(Color.WHITE);
        pnlChinh.setBorder(new TitledBorder(null, "Danh sách sản phẩm", 
        		TitledBorder.LEFT, TitledBorder.TOP, 
                new Font("Arial", Font.BOLD, 18), colorMain));

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.WEST;
        Insets insetsLabel = new Insets(5, 10, 5, 5); 
        Insets insetsField = new Insets(5, 0, 5, 20);
        Insets insetsCuoi = new Insets(5, 0, 5, 10);

        g.gridy = 0;
        g.gridx = 0; g.weightx = 0; g.insets = insetsLabel; 
        pnlChinh.add(lblTimTen = new JLabel("Tên sản phẩm:"), g);
        g.gridx = 1; g.weightx = 1; g.insets = insetsField; 
        pnlChinh.add(txtTimTen = new JTextField(), g);
        
        g.gridx = 2; g.weightx = 0; g.insets = insetsLabel; 
        pnlChinh.add(lblTimDM = new JLabel("Danh mục:"), g);
        g.gridx = 3; g.weightx = 1; g.insets = insetsField; 
        pnlChinh.add(cboTimDM = new JComboBox<>(new String[]{}), g);
        
        g.gridx = 4; g.weightx = 0; g.insets = insetsLabel; 
        pnlChinh.add(lblTimTT = new JLabel("Trạng thái:"), g);
        g.gridx = 5; g.weightx = 1; g.insets = insetsField; 
        pnlChinh.add(cboTimTT = new JComboBox<>(new String[]{}), g);
        
        g.gridx = 6; g.weightx = 0; g.insets = insetsCuoi; 
        pnlChinh.add(btnBoLoc = new JButton("Bỏ lọc"), g);

        g.gridy = 1;
        g.gridx = 0; g.weightx = 0; g.insets = insetsLabel; 
        pnlChinh.add(lblTimMa = new JLabel("Mã sản phẩm:"), g);
        g.gridx = 1; g.weightx = 1.0; g.insets = insetsField; 
        pnlChinh.add(txtTimMa = new JTextField(), g);
        
        g.gridx = 2; g.weightx = 0; g.insets = insetsLabel; 
        pnlChinh.add(lblTimSL = new JLabel("Số lượng:"), g);
        JPanel pnlGop= new JPanel(new GridLayout(1, 2, 5, 0));
        pnlGop.setBackground(Color.WHITE);
        JPanel pnlTu = new JPanel(new BorderLayout(5, 0));
        pnlTu.setBackground(Color.WHITE);
        pnlTu.add(lblTu = new JLabel("Từ:"), BorderLayout.WEST); 
        pnlTu.add(txtTu = new JTextField(), BorderLayout.CENTER);
        JPanel pnlDen = new JPanel(new BorderLayout(5, 0));
        pnlDen.setBackground(Color.WHITE);
        pnlDen.add(lblDen = new JLabel("Đến:"), BorderLayout.WEST); 
        pnlDen.add(txtDen = new JTextField(), BorderLayout.CENTER);
        pnlGop.add(pnlTu); 
        pnlGop.add(pnlDen);
        
        g.gridx = 3; g.weightx = 1.0; g.insets = insetsField; pnlChinh.add(pnlGop, g);
        g.gridx = 4; g.weightx = 0; g.insets = insetsLabel; pnlChinh.add(lblTimNCC =  new JLabel("Nhà cung cấp:"), g);
        g.gridx = 5; g.weightx = 1.0; g.insets = insetsField; pnlChinh.add(cboTimNCC = new JComboBox<>(new String[]{}), g);
        g.gridx = 6; g.weightx = 0; g.insets = insetsCuoi;
        btnTim = new JButton("Tìm kiếm");
        btnTim.setBackground(new Color(135, 206, 250));
        pnlChinh.add(btnTim, g);

        return pnlChinh;
    }
    //BANG TABLE
    private JPanel taoBangDuLieu() {
        JPanel pnlBang = new JPanel(new BorderLayout());
        pnlBang.setBackground(Color.white);
        String[] headers = {"Mã sản phẩm", "Hình ảnh", "Tên sản phẩm", "Số lượng","Giá Nhập","Giá bán", "Danh mục", "Trạng thái","Hạn sử dụng"};
        tableModel = new DefaultTableModel(headers, 0){
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			//Lay anh
        	@Override
            public Class<?> getColumnClass(int Cot) {
                if (Cot == 1) {
                    return ImageIcon.class; 
                }
                return Object.class;
            }
        	//Tranh click xoa du lieu trên table
        	@Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table = new JTable(tableModel));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getViewport().setBackground(Color.WHITE);
        table.setRowHeight(80);
        table.setBackground(Color.white);
        
        //Can giua cac o trong bang
        canGiuaChuTrongBang();
        
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setBackground(colorMain);
        table.getTableHeader().setForeground(Color.WHITE);
        pnlBang.add(scroll, BorderLayout.CENTER);
        return pnlBang;
    }
    //SET KICH THUOC ANH TRONG BANG
    private ImageIcon taoKichThuocAnh(String tenFile, int rong, int cao) {
        try {
            URL url = getClass().getResource("/images/" + tenFile);
            if (url == null) {
                System.err.println("Không tìm thấy ảnh: " + tenFile);
                return null;
            }
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(rong, cao, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //------------Test dữ liệu bảng---------------//Có thể bỏ
    public void docDuLieuVaoBang() {
        // 1. Xóa dữ liệu cũ 
        tableModel.setRowCount(0);

        // 2. set kích thước 70x70 để nằm vừa trong height 80 của table
        ImageIcon iconCoca = taoKichThuocAnh("coca.jpg", 70, 70);

        // 3. Dữ liệu cứng
        Object[] row1 = {
            "SP001", 
            (iconCoca != null) ? iconCoca : "No Image", 
            "Coca Cola", 
            "100", 
            "12.000",
            "15.000", 
            "Đồ uống", 
            "Còn hạn",
            "24/11/2027"
        };

        // 4. Thêm vào model
        tableModel.addRow(row1);
    }
    //SET NOI DUNG BANG CENTER
    private void canGiuaChuTrongBang() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != 1) { 
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }
    
    private void dangKySuKien() {
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnXoa.addActionListener(this);
        btnDien.addActionListener(this);
        btnTaiLen.addActionListener(this);
        //Cac thanh phan combobox và button them
        btnNcc.addActionListener(this);
        btnDMuc.addActionListener(this);
        //Muc Tim
        btnBoLoc.addActionListener(this);
        btnTim.addActionListener(this);
        //Su kien click bang
        table.addMouseListener(this);
    }
    
    //Su Kien
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==btnThem) {} 
		else if(o==btnCapNhat) {}
		else if(o==btnXoa) {}
		else if(o==btnDien) {
			dienLaiForm();
		}
		else if(o==btnNcc) {
	        Window owner = SwingUtilities.getWindowAncestor(this);
	        
	        JDialog popup = new JDialog(owner, "Quản lý nhà cung cấp", 
	        		ModalityType.APPLICATION_MODAL);
	        
	        popup.setSize(1100, 700);
	        popup.setLocationRelativeTo(null);
	        popup.setResizable(true);
	        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        QuanLyNhaCungCap NCC = new QuanLyNhaCungCap();
	        popup.add(NCC);

	        popup.setVisible(true);
		} else if(o.equals(btnDMuc)) {
            Window owner = SwingUtilities.getWindowAncestor(this);
	        
	        JDialog popup = new JDialog(owner, "Quản lý danh mục", 
	        		ModalityType.APPLICATION_MODAL);
	        
	        popup.setSize(600, 400);
	        popup.setLocationRelativeTo(null);
	        popup.setResizable(true);
	        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	        QuanLyDanhMuc danhMuc = new QuanLyDanhMuc();
	        popup.add(danhMuc);

	        popup.setVisible(true);
		} else if(o.equals(btnTaiLen)) {}
		else if(o.equals(btnBoLoc)) {}
		else if(o.equals(btnTim)) {};
	}
	private void dienLaiForm() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtGiaBan.setText("");
		txtMaSP.setText("");
		//CBO chưa có dl nên chưa khởi tạo
//		cboDanhMuc.setSelectedIndex(0);
//		cboNhaCC.setSelectedIndex(0);
//		cboTrangThai.setSelectedIndex(0);
		
		//Anh reset
		
		resetAnhMacDinh();
		
		//JDatePicker
		mauNgayHSD.setValue(null);
		mauNgayHSD.setSelected(false);		
	}
	private void resetAnhMacDinh() {
	    try {
	        urlAnh = getClass().getResource("/images/KhungHinh_SP.png");           
	        if (urlAnh != null) {
	            ImageIcon icon = new ImageIcon(urlAnh);
	            // Scale lại đúng kích thước 130x130
	            Image img = icon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
	            lblAnh.setIcon(new ImageIcon(img));
	            lblAnh.setText(""); 
	        } else {
	            lblAnh.setIcon(null);
	            lblAnh.setText("Không tìm thấy ảnh");
	        }
	    } catch (Exception e) {
	        lblAnh.setText("Lỗi load ảnh");
	    }
	}
	
	//Click
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if(row != -1) {
		   txtMaSP.setText(table.getValueAt(row, 0).toString());
		   Object objAnh = table.getValueAt(row, 1);
		
		   if(objAnh instanceof ImageIcon) {
			  ImageIcon icon = (ImageIcon) objAnh;
			  int chieuRong = 130;
			  int chieuCao = 130;
			  Image img = icon.getImage().getScaledInstance(
					  chieuRong, 
					  chieuCao, 
					Image.SCALE_SMOOTH
					);
			  lblAnh.setIcon(new ImageIcon(img));
			  lblAnh.setText("");
		   } else {
			  lblAnh.setIcon(null);
			  lblAnh.setText("Không tìm thấy ảnh");
		   }
		   txtTenSP.setText(table.getValueAt(row, 2).toString());
		   txtSoLuong.setText(table.getValueAt(row, 3).toString());
		   txtGiaNhap.setText(table.getValueAt(row, 4).toString());
		   txtGiaBan.setText(table.getValueAt(row, 5).toString());
		}
		
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
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(1150, 850);
//            frame.setLocationRelativeTo(null);
//            frame.add(new TabSanPham());
//            frame.setVisible(true);
//        });
//    }
    
}