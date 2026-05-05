package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class QuanLyNhapHang extends JPanel implements ActionListener, MouseListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorMain = new Color(0, 51, 102);
    private Color colorCyan = new Color(0, 204, 204);
    private Font fontTieuDe;
    private JLabel lblTieuDe, lblTongTienVal;
    
    private JButton btnHuy, btnXoa, btnTim, btnBoLoc, btnNhapHang;
    private DefaultTableModel tableModel;
    private JTable table;
    private JPanel pnlGrid;
	private JButton btnThem;
	
	private JLabel lblNCC, lblDM, lblMaSP,lblTenSP,lblSL, lblTu, lblDen;
	private JComboBox<String> cboNCC, cboDM, cboTrangThai;
	private JTextField txtMaSP, txtTenSP, txtTu, txtDen;
	private JLabel lblHinh;
	private JRadioButton radTienMat,radChuyenKhoan;
	private JLabel lblPT;
	private JPanel pnlCard;
	private JLabel lblTongTien;
	private JLabel lblTieuDeGrid;
	private JScrollPane scrollGrid;
	private JLabel lblSLCard;

    public QuanLyNhapHang() {
        this.setLayout(new BorderLayout(10, 0));
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(10, 10, 30, 10));

        // 1. PHẦN HEADER
        lblTieuDe = new JLabel("NHẬP HÀNG", SwingConstants.CENTER);
        lblTieuDe.setFont(fontTieuDe = new Font("Arial", Font.BOLD, 28));
        lblTieuDe.setForeground(colorMain);
        lblTieuDe.setBorder(new EmptyBorder(0, 0, 5, 0));
        this.add(lblTieuDe, BorderLayout.NORTH);

        // 2. PHẦN THÂN
        JPanel pnlThan = new JPanel(new GridBagLayout());
        pnlThan.setBackground(Color.WHITE);
        
        GridBagConstraints gbcThan = new GridBagConstraints();
        gbcThan.fill = GridBagConstraints.BOTH;
        gbcThan.weighty = 1;
        
        gbcThan.gridx = 0; gbcThan.weightx = 0.7;
        gbcThan.insets = new Insets(0, 0, 0, 10);
        pnlThan.add(taoPanelThemSanPham(), gbcThan);
        
        gbcThan.gridx = 1; gbcThan.weightx = 0.3;
        gbcThan.insets = new Insets(0, 10, 0, 0);
        pnlThan.add(taoPanelDonNhapHang(), gbcThan);

        this.add(pnlThan, BorderLayout.CENTER);
        
        dangKySK();
        loadDuLieuTuSQL(); // load dữ liệu cứng mẫu (có thể xóa)
    }

    private JPanel taoPanelThemSanPham() {
        JPanel pnlSP = new JPanel(new BorderLayout(10, 0));
        pnlSP.setBackground(Color.WHITE);

        //Form Loc san pham
        JPanel pnlLoc = new JPanel(new GridBagLayout());
        pnlLoc.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 5); 
        gbc.gridx = 0; gbc.weightx = 1.0;

        int y = 0;
        pnlLoc.add(lblNCC = new JLabel("Nhà cung cấp:"), createGbc(gbc, y++));
        pnlLoc.add(cboNCC = new JComboBox<>(new String[]{}), createGbc(gbc, y++));
        pnlLoc.add(lblDM = new JLabel("Danh mục:"), createGbc(gbc, y++));
        pnlLoc.add(cboDM = new JComboBox<>(new String[]{}), createGbc(gbc, y++));
        pnlLoc.add(lblTenSP = new JLabel("Tên sản phẩm:"), createGbc(gbc, y++));
        pnlLoc.add(txtTenSP = new JTextField(), createGbc(gbc, y++));
        pnlLoc.add(lblMaSP = new JLabel("Mã sản phẩm:"), createGbc(gbc, y++));
        pnlLoc.add(txtMaSP = new JTextField(), createGbc(gbc, y++));
        //SO LUONG
        pnlLoc.add(lblSL = new JLabel("Số lượng:"), createGbc(gbc, y++));

        JPanel pnlSL = new JPanel();
        pnlSL.setLayout(new BoxLayout(pnlSL, BoxLayout.X_AXIS));
        pnlSL.setBackground(Color.WHITE);

        pnlSL.add(new JLabel("Từ: ")); 
        pnlSL.add(txtTu = new JTextField(5)); 
        pnlSL.add(Box.createHorizontalStrut(10)); 
        pnlSL.add(new JLabel("Đến: ")); 
        pnlSL.add(txtDen = new JTextField(5));

        // Thay vì dùng Box, hãy add trực tiếp panel vào pnlLoc để GridBagLayout quản lý tốt hơn
        pnlLoc.add(pnlSL, createGbc(gbc, y++));
        
        JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 5, 0));
        pnlButtons.setBackground(Color.white);
        btnBoLoc = new JButton("Bỏ lọc");
        btnTim = new JButton("Tìm");
        btnTim.setBackground(new Color(153, 204, 255));
        pnlButtons.add(btnBoLoc); 
        pnlButtons.add(btnTim);
        gbc.insets = new Insets(15, 0, 5, 5);
        pnlLoc.add(pnlButtons, createGbc(gbc, y++));

        JPanel pnlCanhTrai = new JPanel(new BorderLayout());
        pnlCanhTrai.setBackground(Color.WHITE);
        pnlCanhTrai.setPreferredSize(new Dimension(230, 0));
        pnlCanhTrai.add(new JLabel(" "), BorderLayout.NORTH);
        
        JPanel pnlCanhLeLoc = new JPanel(new BorderLayout());
        pnlCanhLeLoc.setBackground(Color.white);
        pnlCanhLeLoc.add(pnlLoc, BorderLayout.NORTH);
        pnlCanhTrai.add(pnlCanhLeLoc, BorderLayout.CENTER);

        // --- Khối Lưới sản phẩm ---
        pnlGrid = new JPanel(new GridLayout(0, 3, 6, 6));
        scrollGrid = new JScrollPane(pnlGrid);
        scrollGrid.setBorder(BorderFactory.createEmptyBorder());
        scrollGrid.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollGrid.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel pnlKhungGrid = new JPanel(new BorderLayout());
        pnlKhungGrid.setBackground(Color.WHITE);
        lblTieuDeGrid = new JLabel("Thêm sản phẩm", SwingConstants.CENTER);
        Font fontThemSP = new Font("Arial", Font.BOLD, 18);
        lblTieuDeGrid.setFont(fontThemSP);
        lblTieuDeGrid.setForeground(colorMain);
        pnlKhungGrid.add(lblTieuDeGrid, BorderLayout.NORTH);
        pnlKhungGrid.add(scrollGrid, BorderLayout.CENTER);

        pnlSP.add(pnlCanhTrai, BorderLayout.WEST);
        pnlSP.add(pnlKhungGrid, BorderLayout.CENTER);

        return pnlSP;
    }

    private JPanel taoPanelDonNhapHang() {
        JPanel pnlDonNhap = new JPanel(new BorderLayout());
        pnlDonNhap.setBackground(Color.WHITE);

        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JLabel lblTitle = new JLabel("Đơn Nhập Hàng", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(colorMain);
        btnHuy = new JButton("Hủy đơn");
        
        pnlHeader.add(lblTitle, BorderLayout.CENTER);
        pnlHeader.add(btnHuy, BorderLayout.EAST);
        pnlDonNhap.add(pnlHeader, BorderLayout.NORTH);

        String[] columns = {"Sản phẩm", "Nhà cung cấp", "Số lượng", "Giá mua", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setRowHeight(35);
        table.getTableHeader().setBackground(colorMain);
        table.getTableHeader().setForeground(Color.WHITE);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pnlDonNhap.add(scroll, BorderLayout.CENTER);

        // Footer đơn hàng
        JPanel pnlFooter = new JPanel(new BorderLayout());
        pnlFooter.setBackground(Color.WHITE);
        btnXoa = new JButton("Xóa sản phẩm");
        JPanel pnlXoa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        pnlXoa.setBackground(Color.WHITE); pnlXoa.add(btnXoa);
        pnlFooter.add(pnlXoa, BorderLayout.NORTH);

        JPanel pnlThanhToan = new JPanel(new GridLayout(3, 1));
        pnlThanhToan.setBackground(Color.WHITE);
        pnlThanhToan.add(lblPT = new JLabel("Phương thức thanh toán:"));
        radTienMat = new JRadioButton("Tiền mặt", true);
        radTienMat.setBackground(Color.WHITE);
        radChuyenKhoan= new JRadioButton("Chuyển khoản");
        radChuyenKhoan.setBackground(Color.WHITE);
        //Tao button group 
        ButtonGroup bg = new ButtonGroup(); 
        bg.add(radTienMat); 
        bg.add(radChuyenKhoan);
        pnlThanhToan.add(radTienMat); 
        pnlThanhToan.add(radChuyenKhoan);

        JPanel pnlTongTien = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        pnlTongTien.setBackground(Color.WHITE);
        lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setForeground(colorCyan);
        lblTongTienVal = new JLabel("0 đ");
        Font fontTT = new Font("Arial", Font.BOLD, 22);
        lblTongTienVal.setFont(fontTT); 
        lblTongTienVal.setForeground(colorCyan);
        
        btnNhapHang = new JButton("NHẬP HÀNG");
        btnNhapHang.setBackground(colorMain); btnNhapHang.setForeground(Color.WHITE);
        btnNhapHang.setPreferredSize(new Dimension(140, 45));

        pnlTongTien.add(lblTongTien); 
        pnlTongTien.add(lblTongTienVal); 
        pnlTongTien.add(btnNhapHang);
        pnlFooter.add(pnlThanhToan, BorderLayout.WEST);
        pnlFooter.add(pnlTongTien, BorderLayout.EAST);
        pnlDonNhap.add(pnlFooter, BorderLayout.SOUTH);

        return pnlDonNhap;
    }

    // --- HÀM TẠO 1 THẺ SẢN PHẨM (GIỮ NGUYÊN NÚT +) ---
    private JPanel taoTheSanPham(String ten, String ma, String gia, String ncc, int slTon) {
        pnlCard = new JPanel(new BorderLayout());
        pnlCard.setBackground(Color.WHITE);
        pnlCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        pnlCard.addMouseListener(this); // Để làm hiệu ứng hover

        lblSLCard = new JLabel("SL: " + slTon + "  ", SwingConstants.RIGHT);
        Font fontSL = new Font("Arial", Font.PLAIN, 10);
        lblSLCard.setFont(fontSL); 
        pnlCard.add(lblSLCard, BorderLayout.NORTH);

        lblHinh = new JLabel("IMAGE", SwingConstants.CENTER);
        lblHinh.setOpaque(true); 
        lblHinh.setBackground(Color.RED); 
        lblHinh.setForeground(Color.WHITE);
        lblHinh.setPreferredSize(new Dimension(45, 45));
        JPanel pnlImg = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        pnlImg.setBackground(Color.WHITE); pnlImg.add(lblHinh);
        pnlCard.add(pnlImg, BorderLayout.CENTER);

        JPanel pnlInfo = new JPanel(new GridLayout(3, 1));
        pnlInfo.setBackground(Color.WHITE);
        pnlInfo.setBorder(new EmptyBorder(0, 5, 0, 0));
        
        JLabel lblTen = new JLabel(ten); 
        lblTen.setFont(new Font("Arial", Font.BOLD, 11));
        JLabel lblMaID = new JLabel(ma); 
        lblMaID.setFont(new Font("Arial", Font.PLAIN, 10));
        
        JPanel pnlGia = new JPanel(new BorderLayout());
        pnlGia.setBackground(Color.WHITE);
        JLabel lblGiaVal = new JLabel(gia + " đ");
        lblGiaVal.setFont(new Font("Arial", Font.BOLD, 11));
        
        // --- NÚT (+) QUAN TRỌNG ---
        btnThem = new JButton("+");
        btnThem.setBackground(colorCyan);
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusable(false);
        // Lưu dữ liệu vào ActionCommand để khi bấm nút thì lấy ra được 
        Dimension szthem = new Dimension(25, 25);
        btnThem.setPreferredSize(szthem);
        btnThem.setActionCommand(ten + "|" + ma + "|" + gia + "|" + ncc);
        btnThem.setBackground(colorMain);
        btnThem.setMargin(new Insets(0, 0, 0, 0));
        btnThem.addActionListener(this); 
        JPanel pnlNutThem = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pnlNutThem.setBackground(Color.WHITE);
        pnlNutThem.add(btnThem);
        
        pnlGia.add(lblGiaVal, BorderLayout.CENTER);
        pnlGia.add(pnlNutThem, BorderLayout.EAST);

        pnlInfo.add(lblTen); pnlInfo.add(lblMaID); pnlInfo.add(pnlGia);
        pnlCard.add(pnlInfo, BorderLayout.SOUTH);

        return pnlCard;
    }

    private void loadDuLieuTuSQL() {
        pnlGrid.removeAll();
//         Giả sử đây là dữ liệu load từ DAO
        //Có thể xóa for
        for (int i = 0; i < 18; i++) {
            pnlGrid.add(taoTheSanPham("CocaCola", "W000" + i, "10.000", "Vinamilk", 67));
        }
        pnlGrid.revalidate(); 
        pnlGrid.repaint();
    }

    private void dangKySK() {
        btnHuy.addActionListener(this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnBoLoc.addActionListener(this);
        btnNhapHang.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();       
        // Kiểm tra nếu là nút (+) trong các card
        if (o instanceof JButton && ((JButton)o).getText().equals("+")) {
            String data = e.getActionCommand();
            String[] info = data.split("\\|");
            // Thêm vào bảng: Sản phẩm (Tên\nMã), Nhà CC, Số lượng (mặc định 1), Giá, Thành tiền
            tableModel.addRow(new Object[]{info[0] + "\n" + info[1], info[3], "1", info[2] + " đ", info[2] + " đ"});
            return;
        } 
        if (o == btnHuy) {
        	Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        } else if (o == btnXoa) {
            int row = table.getSelectedRow();
            if (row != -1) tableModel.removeRow(row);
        } else if (o == btnBoLoc) {
        	BoLoc();
        }
    	
    }
    
    private void BoLoc() {
//    	cboNCC.setSelectedIndex(0);
//    	cboDM.setSelectedIndex(0);
//    	cboTrangThai.setSelectedIndex(0); 
    	txtTenSP.setText("");
    	txtMaSP.setText("");
    	txtTu.setText("");
    	txtDen.setText("");
    		
    	txtTenSP.requestFocus();
	}

    // --- HIỆU ỨNG HOVER CHO CARD ---
    @Override
    public void mouseEntered(MouseEvent e) {
    	Object o = e.getSource();
        ((JPanel)o).setBorder(new LineBorder(colorCyan, 2));	
    }
    @Override 
    public void mouseExited(MouseEvent e) {
    	Object o = e.getSource();
        ((JPanel)o).setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
    }

    private GridBagConstraints createGbc(GridBagConstraints gbc, int y) {
        gbc.gridy = y; return gbc;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
//	public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Hệ Thống Nhập Hàng");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(1200, 750);
//            frame.setLocationRelativeTo(null);
//            frame.add(new QuanLyNhapHang());
//            frame.setVisible(true);
//        });
//    }

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}