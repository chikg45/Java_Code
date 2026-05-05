package ui;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

public class TabBanHang extends JPanel {
    private final Color color = Color.decode("044574"); 
    private JComboBox<Object> categoryComboBox; 
    private DefaultComboBoxModel<Object> categoryComboBoxModel; 
    private JTextField txtSearchProduct; 
    private JButton searchBtn; 
    private JButton abortBtn; 
    private JTable orderTable; 
    private DefaultTableModel orderTableModel; 
    private JButton deleteProductBtn; 
    private JButton payBtn; 
    private JButton manageDiscountBtn; 

    public TabBanHang() { 
        initPanel(); 
    }

    private void initPanel() { 
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);

        // Label Bán hàng
        add(createLabel(new JLabel("BÁN HÀNG", JLabel.CENTER), Font.BOLD, 32, color), BorderLayout.NORTH); 
        
        // Left area (Thêm sản phẩm)
        add(leftPanel(), BorderLayout.LINE_START); 
        
        // Right area (Đơn hàng)
        add(rightPanel(), BorderLayout.CENTER); 
    }

    private JPanel leftPanel() {
        JPanel panel = new JPanel(new BorderLayout());  
        panel.setBackground(Color.WHITE);

        // Labels
        JLabel titleLeftLabel = createLabel(new JLabel("Thêm sản phẩm"), Font.BOLD, 20, color); 
        JLabel categoryLabel = createLabel(new JLabel("Danh mục: "), Font.PLAIN, 15, Color.BLACK); 

        // ComboBox Model
        categoryComboBoxModel = new DefaultComboBoxModel<>(); 
        categoryComboBoxModel.addElement("Đồ uống");
        categoryComboBoxModel.addElement("Đồ ăn");
        categoryComboBoxModel.addElement("Đồ gia dụng");

        categoryComboBox = new JComboBox<>(categoryComboBoxModel); 
        txtSearchProduct = new JTextField(); 
        
        searchBtn = createButton(new JButton("Tìm sản phẩm"), Font.BOLD, 15, Color.WHITE, color); 
        searchBtn.setBorderPainted(false);
        searchBtn.setFocusPainted(false);

        // Panel chứa các thành phần nhập liệu
        JPanel northPanel = new JPanel(new GridBagLayout());
        northPanel.setBackground(Color.WHITE);
        panel.add(northPanel, BorderLayout.NORTH);

        // Add components dùng GridBagLayout
        addComponent(northPanel, titleLeftLabel, 0, 0, 3, 1, 0.0, 0.0);
        addComponent(northPanel, categoryLabel, 0, 1, 1, 1, 0.0, 0.0);
        addComponent(northPanel, categoryComboBox, 1, 1, 1, 1, 1.0, 0.0);
        addComponent(northPanel, txtSearchProduct, 0, 2, 2, 1, 1.0, 0.0);
        addComponent(northPanel, searchBtn, 3, 1, 1, 2, 1.0, 1.0);

        return panel; 
    }

    private JPanel rightPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);

        // North
        panel.add(createLabel(new JLabel("Đơn mua hàng", JLabel.LEFT), Font.BOLD, 20, color), BorderLayout.NORTH);

        // Center - Table
        String[] colsName = {"Sản Phẩm", "Số Lượng", "Giá Bán", "Thành Tiền"}; 
        orderTableModel = new DefaultTableModel(colsName, 0); 
        orderTable = new JTable(orderTableModel); 
        orderTable.setRowHeight(25);
        JScrollPane orderTableScroll = new JScrollPane(orderTable);  
        panel.add(orderTableScroll, BorderLayout.CENTER); 

        // South area
        JPanel southPanel = new JPanel(new BorderLayout(5, 5));
        southPanel.setBackground(Color.WHITE);
        panel.add(southPanel, BorderLayout.SOUTH); 

        // Buttons panel
        JPanel northOfSouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5)); 
        northOfSouthPanel.setBackground(Color.WHITE);
        southPanel.add(northOfSouthPanel, BorderLayout.NORTH); 

        abortBtn = createButton(new JButton("Huỷ đơn"), Font.BOLD, 15, Color.WHITE, color); 
        deleteProductBtn = createButton(new JButton("Xóa sản phẩm"), Font.BOLD, 15, Color.WHITE, color);
        payBtn = createButton(new JButton("Thanh Toán"), Font.BOLD, 20, Color.WHITE, color);
        
        // Icon (Đảm bảo đường dẫn này đúng trong project của bạn)
        try {
            ImageIcon icon = new ImageIcon("src/resources/tags-fill 1.png"); 
            Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
            manageDiscountBtn = createButton(new JButton("Quản lý khuyến mãi", new ImageIcon(image)), 
                                            Font.BOLD, 15, Color.WHITE, color);
        } catch (Exception e) {
            manageDiscountBtn = createButton(new JButton("Quản lý khuyến mãi"), Font.BOLD, 15, Color.WHITE, color);
        }

        northOfSouthPanel.add(abortBtn); 
        northOfSouthPanel.add(deleteProductBtn); 
        northOfSouthPanel.add(manageDiscountBtn); 

        // Panel tổng tiền
        JPanel totalPricePanel = new JPanel(new GridBagLayout()); 
        totalPricePanel.setBackground(Color.WHITE);
        totalPricePanel.setBorder(BorderFactory.createLineBorder(color, 1));
        southPanel.add(totalPricePanel, BorderLayout.CENTER); 

        addComponent(totalPricePanel, createLabel(new JLabel("Tổng tiền: "), Font.PLAIN, 15, Color.BLACK), 0, 0, 1, 1, 0.0, 0.0);
        addComponent(totalPricePanel, createLabel(new JLabel("00.000đ"), Font.BOLD, 15, Color.BLACK), 1, 0, 1, 1, 0, 0);
        
        addComponent(totalPricePanel, createLabel(new JLabel("Tổng khuyến mãi: "), Font.PLAIN, 15, Color.BLACK), 0, 1, 1, 1, 0.0, 0.0);
        addComponent(totalPricePanel, createLabel(new JLabel("00.000đ"), Font.BOLD, 15, Color.BLACK), 1, 1, 1, 1, 0, 0);
        
        addComponent(totalPricePanel, createLabel(new JLabel("Tổng tiền sau khuyến mãi: "), Font.PLAIN, 15, Color.BLACK), 0, 2, 1, 1, 0.0, 0.0);
        addComponent(totalPricePanel, createLabel(new JLabel("00.000đ"), Font.BOLD, 15, Color.BLACK), 1, 2, 1, 1, 0, 0);

        // Nút thanh toán dưới cùng
        JPanel payBtnPanel = new JPanel();
        payBtnPanel.setBackground(Color.WHITE);
        payBtnPanel.add(payBtn); 
        southPanel.add(payBtnPanel, BorderLayout.SOUTH); 

        return panel; 
    }

    private JLabel createLabel(JLabel label, int fontWeight, int size, Color color) {
        label.setFont(new Font("Arial", fontWeight, size));
        label.setForeground(color);
        return label; 
    }

    private void addComponent(JPanel panel, JComponent com, int x, int y, int width, int height, double weightX, double weightY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.gridx = x; 
        gbc.gridy = y; 
        gbc.gridwidth = width; 
        gbc.gridheight = height; 
        gbc.weightx = weightX; 
        gbc.weighty = weightY; 
        panel.add(com, gbc); 
    }

    private JButton createButton(JButton button, int fontWeight, int size, Color color, Color backGroundColor) { 
        button.setForeground(color);
        button.setBackground(backGroundColor);
        button.setFont(new Font("Arial", fontWeight, size));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button; 
    }
}
