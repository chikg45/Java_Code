package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DoiMatKhau extends JDialog {
    
    private JPasswordField txtMatKhauCu, txtMatKhauMoi, txtNhapLaiMK;
    private JLabel lblError;
    private JButton btnXacNhan;
    
    public DoiMatKhau(JFrame parent) {
        super(parent, "Đổi mật khẩu", true);
        setSize(450, 350);
        setLayout(new BorderLayout()); // Dùng BorderLayout làm gốc
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);

        // Panel viền bo góc ở giữa
        JPanel pnlMain = new JPanel(new BorderLayout(0, 15));
        pnlMain.setBackground(new Color(248, 252, 255));
        pnlMain.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(15, 15, 15, 15), 
                BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(200, 200, 200), 1, true), // Viền bo góc
                        new EmptyBorder(15, 20, 15, 20) // Cách lề trong của viền
                )
        ));
        
        // Tiêu đề (Bắc)
        JLabel lblTitle = new JLabel("Đổi mật khẩu");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(21, 82, 120));
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTitle.setOpaque(false);
        pnlTitle.add(lblTitle);
        pnlMain.add(pnlTitle, BorderLayout.NORTH);

        // Vùng chứa Form (Giữa) - Dùng GridLayout 3 dòng, 2 cột
        JPanel pnlForm = new JPanel(new GridLayout(3, 2, 10, 15));
        pnlForm.setOpaque(false);

        pnlForm.add(createLabel("Mật khẩu cũ:"));
        txtMatKhauCu = new JPasswordField();
        pnlForm.add(txtMatKhauCu);

        pnlForm.add(createLabel("Mật khẩu mới:"));
        txtMatKhauMoi = new JPasswordField();
        txtMatKhauMoi.setToolTipText("Tối thiểu 8 ký tự");
        pnlForm.add(txtMatKhauMoi);

        pnlForm.add(createLabel("Nhập lại mật khẩu mới:"));
        txtNhapLaiMK = new JPasswordField();
        pnlForm.add(txtNhapLaiMK);

        pnlMain.add(pnlForm, BorderLayout.CENTER);

        // Vùng Lỗi + Nút bấm (Nam)
        JPanel pnlBottom = new JPanel(new BorderLayout(0, 10));
        pnlBottom.setOpaque(false);

        lblError = new JLabel("Sai mật khẩu!", SwingConstants.CENTER);
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.PLAIN, 13));
        // lblError.setVisible(false);
        pnlBottom.add(lblError, BorderLayout.NORTH);

        btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setFont(new Font("Arial", Font.BOLD, 14));
        btnXacNhan.setBackground(new Color(180, 210, 230));
        btnXacNhan.setFocusPainted(false);
        
        // Đặt nút bấm vào giữa (Dùng FlowLayout)
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlBtn.setOpaque(false);
        btnXacNhan.setPreferredSize(new Dimension(200, 35));
        pnlBtn.add(btnXacNhan);
        pnlBottom.add(pnlBtn, BorderLayout.CENTER);

        pnlMain.add(pnlBottom, BorderLayout.SOUTH);

        add(pnlMain, BorderLayout.CENTER);
    }
    
    // Hàm tạo label con để code bớt dài
    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        return lbl;
    }
}
