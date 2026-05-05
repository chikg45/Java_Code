package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GiaoDienChinh extends JFrame{

    private final Color NAVY_BLUE = new Color(4, 69, 116);
    private final Color CYAN = new Color(0, 205, 226);
    private final Font FONT_40_BOLD = new Font("Arial", Font.BOLD, 40);
    private final Font FONT_26_BOLD = new Font("Arial", Font.BOLD, 26);
    private final Font FONT_15_BOLD = new Font("Arial", Font.BOLD, 15);

    private CardLayout mainCardLayout;
    private JPanel mainCardPanel;
    private List<JPanel> sidebarTabs = new ArrayList<>();
	private JPanel pnlNhapHang;
	private JButton btnNhapHang;

    public GiaoDienChinh() {
        setTitle("Phần mềm quản lý bán hàng MinMart");
        setSize(1210, 888);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        createHeader();
        createSidebar();
        createMainContentArea();
        createNhapHangButton();//hiện khi chọn menu sp
        
        switchSidebarTab(2); // Default chon tab Thong Ke
    }

    private void createHeader() {
        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0, 0, 1210, 92);
        headerPanel.setBackground(NAVY_BLUE);

        // Logo
        JLabel minLabel = new JLabel("Min");
        minLabel.setFont(FONT_40_BOLD);
        minLabel.setForeground(Color.WHITE);
        minLabel.setBounds(28, 11, 80, 50);
        headerPanel.add(minLabel);

        JLabel martLabel = new JLabel("Mart");
        martLabel.setFont(FONT_40_BOLD);
        martLabel.setForeground(CYAN);
        martLabel.setBounds(28 + 72, 11, 120, 50);
        headerPanel.add(martLabel);

        // Slogan
        JLabel sloganLabel = new JLabel("Phần mềm quản lý bán hàng - Nhóm 12");
        sloganLabel.setFont(FONT_15_BOLD);
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setBounds(28, 59, 400, 20);
        headerPanel.add(sloganLabel);

        // Thong tin nguoi dung + ngay gio
        JLabel info1Label = new JLabel("Nhân viên: Nguyễn Văn A");
        info1Label.setFont(FONT_15_BOLD);
        info1Label.setForeground(Color.WHITE);
        info1Label.setBounds(948, 34, 250, 20);
        headerPanel.add(info1Label);

        JLabel info2Label = new JLabel("Thứ Năm, 01-01-2026 6:57 PM");
        info2Label.setFont(FONT_15_BOLD);
        info2Label.setForeground(Color.WHITE);
        info2Label.setBounds(948, 60, 250, 20);
        headerPanel.add(info2Label);

        add(headerPanel);
    }

    private void createSidebar() {
        String[] tabNames = {"Bán Hàng", "Sản Phẩm", "Thống Kê", "Tài Khoản"};
        String[] icons = {"banhang.png", "sanpham.png", "thongke.png", "taikhoan.png"};
        int[] yPositions = {217, 299, 382, 464};

        for (int i = 0; i < tabNames.length; i++) {
            final int index = i;
            JPanel tab = createSidebarTab(tabNames[i], icons[i], 0, yPositions[i]);
            tab.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switchSidebarTab(index);
                }
            });
            sidebarTabs.add(tab);
            add(tab);
        }
    }

    private JPanel createSidebarTab(String text, String iconName, int x, int y) {
        JPanel tab = new JPanel(null);
        tab.setBounds(x, y, 241, 76);
        tab.setBackground(NAVY_BLUE);

        ImageIcon icon = new ImageIcon("src/images/" + iconName);
        Image img = icon.getImage().getScaledInstance(42, 42, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(img));
        iconLabel.setBounds(19, 17, 42, 42);
        tab.add(iconLabel);

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(FONT_26_BOLD);
        textLabel.setForeground(Color.WHITE);
        textLabel.setBounds(85, 0, 150, 76);
        tab.add(textLabel);

        return tab;
    }

    private void createMainContentArea() {
        mainCardLayout = new CardLayout();
        mainCardPanel = new JPanel(mainCardLayout);
        mainCardPanel.setBounds(250, 119, 914, 714);
        mainCardPanel.setOpaque(false);

        mainCardPanel.add(new TabBanHang(), "Bán Hàng");
        mainCardPanel.add(new TabSanPham(), "Sản Phẩm");
        mainCardPanel.add(new TabThongKe(), "Thống Kê");
        mainCardPanel.add(new TabTaiKhoan(), "Tài Khoản");
        mainCardPanel.add(new QuanLyNhapHang(), "Nhập Hàng");

        add(mainCardPanel);
    }

    private void switchSidebarTab(int index) {
        for (int i = 0; i < sidebarTabs.size(); i++) {
            sidebarTabs.get(i).setBackground(i == index ? CYAN : NAVY_BLUE);
        }
        String[] tabNames = {"Bán Hàng", "Sản Phẩm", "Thống Kê", "Tài Khoản"};
        if (mainCardLayout != null) {
            mainCardLayout.show(mainCardPanel, tabNames[index]);
        }
        //Moi them Nhap Hang button
        if (pnlNhapHang != null) {
            pnlNhapHang.setVisible(index == 1); 
            if (index != 1) {
                btnNhapHang.setBackground(new Color(2, 62, 138)); 
            }
        }
        getContentPane().validate();
        getContentPane().repaint();
    }
    //Nhap Hang
    private void createNhapHangButton() {
        btnNhapHang = new JButton("Nhập hàng");
        btnNhapHang.setBounds(40, 750, 160, 40); 
        btnNhapHang.setBackground(new Color(0, 51, 102));
        btnNhapHang.setForeground(Color.WHITE);
        btnNhapHang.setFont(new Font("Arial", Font.BOLD, 14));
        btnNhapHang.setFocusPainted(false);
        btnNhapHang.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        btnNhapHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNhapHang.setHorizontalAlignment(SwingConstants.CENTER);
        btnNhapHang.setIconTextGap(10);
        try {
            ImageIcon iconOriginal = new ImageIcon("src/images/NhapHang.jpg"); 
            Image img = iconOriginal.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            btnNhapHang.setIcon(new ImageIcon(img));
            btnNhapHang.setMargin(new Insets(0, 20, 0, 0));
        } catch (Exception e) {
            System.out.println("Không tìm thấy file icon nhập hàng");
        }

        btnNhapHang.addActionListener(e -> {
            JDialog popup = new JDialog(this, "Quản lý nhập hàng", true);
            popup.setSize(1100, 750); 
            popup.setLocationRelativeTo(this); 
            popup.setResizable(false); 
            popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            QuanLyNhapHang content = new QuanLyNhapHang();
            popup.add(content);
            popup.setVisible(true); 
        });
        pnlNhapHang = new JPanel(null);
        pnlNhapHang.setBounds(0, 750, 241, 60);
        pnlNhapHang.add(btnNhapHang);
        btnNhapHang.setBounds(0, 0, 241, 60);
        
        pnlNhapHang.setVisible(false);
        add(pnlNhapHang); 
    }
    
    public static void main(String[] args) {
		new GiaoDienChinh().setVisible(true);
	}
}
