package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QuanLyKhuyenMai extends JPanel{
    private final Color color = Color.decode("044574"); 
    private JComboBox<Object> providerComboBox; 
    private DefaultComboBoxModel<Object> providerComboBoxModel; 
    private JComboBox<Object> categoryComboBox; 
    private DefaultComboBoxModel<Object> categoryComboBoxModel; 
    private JTextField txtProductName; 
    private JTextField txtIdProduct; 
    private JTextField txtfrom; 
    private JTextField txtTo; 
    private JComboBox<Object> stateComboBox; 
    private DefaultComboBoxModel<Object> stateComboBoxModel; 
    private JButton removeFilterBtn; 
    private JButton searchBtn; 

    private JTextField txtNameProductDiscount; 
    private JTextField txtProductIDiscount; 
    private JComboBox <Object> stateDiscount; 
    private DefaultComboBoxModel <Object> stateDiscountModel; 
    private JComboBox <Object> filterDiscount; 
    private DefaultComboBoxModel <Object> filterDiscountModel; 
    private JTextField txtbeginDate; 
    private JTextField txtEndDate; 
    private JButton removeFilterDiscountBtn; 
    private JButton searchDiscountBtn;
    
    private JTable discountTable; 
    private DefaultTableModel discountTableModel; 
    private JButton inputAgainBtn; 
    private JButton deletebtn; 
    private JButton addBtn; 
    private JButton saveBtn; 
    private JButton exitBtn; 
    public QuanLyKhuyenMai() {
        initPanel(); 
    }

    private void initPanel() { 
        setLayout(new BorderLayout(5, 5));
        //north
        add(createLabel(new JLabel("QUẢN LÝ KHUYẾN MÃI", JLabel.CENTER), Font.BOLD, 32, color), BorderLayout.NORTH); 
        //left
        add(leftPanel(), BorderLayout.LINE_START); 
        //center
        ///////////////////////////////////////
        // right
        add(rightPanel(), BorderLayout.LINE_END); 
    }
    private JPanel leftPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5)); 
        panel.add(createLabel(new JLabel("Chọn sản phẩm"), Font.BOLD, 20, color), BorderLayout.NORTH); 
        //center
        JPanel containerInputPanel = new JPanel(new BorderLayout()); 
        JPanel inputPanel = new JPanel(new GridBagLayout()); 
        containerInputPanel.add(inputPanel, BorderLayout.NORTH); 
        panel.add(containerInputPanel, BorderLayout.LINE_START); 
        //declare
        providerComboBoxModel = new DefaultComboBoxModel<>(); 
        providerComboBoxModel.addElement("Vinamik");
        providerComboBoxModel.addElement("Coca Cola");
        providerComboBoxModel.addElement("Pesi");
        providerComboBox = new JComboBox<>(providerComboBoxModel); 

        categoryComboBoxModel = new DefaultComboBoxModel<>(); 
        categoryComboBoxModel.addElement("Đồ ăn");
        categoryComboBoxModel.addElement("Đồ uống");
        categoryComboBoxModel.addElement("Đồ gia dụng");
        categoryComboBox = new JComboBox<>(categoryComboBoxModel); 

        txtProductName = new JTextField(); 
        txtIdProduct = new JTextField();
        txtfrom = new JTextField(); 
        txtTo = new JTextField(); 

        stateComboBoxModel = new DefaultComboBoxModel<>(); 
        stateComboBoxModel.addElement("Còn hạn");
        stateComboBoxModel.addElement("Hết hạn");
        stateComboBox = new JComboBox<>(stateComboBoxModel); 

        removeFilterBtn = createButton(new JButton("Bỏ lọc"), Font.BOLD, 15, Color.WHITE, color); 
        removeFilterBtn.setBorderPainted(false);
        removeFilterBtn.setFocusPainted(false);
        searchBtn = createButton(new JButton("Tìm sản phẩm"), Font.BOLD, 15, Color.WHITE, color); 
        searchBtn.setBorderPainted(false);
        searchBtn.setFocusPainted(false);
        //add component in Panel 
        addComponent(inputPanel, createLabel(new JLabel("Nhà cung cấp:"), Font.BOLD, 15, Color.BLACK), 
                    0, 0, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, providerComboBox, 0, 1, 4, 1, 1.0, 0.0);
        //
        addComponent(inputPanel, createLabel(new JLabel("Danh mục:"), Font.BOLD, 15, Color.BLACK), 
                    0, 2, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, categoryComboBox, 0, 3, 4, 1, 1.0, 0.0);
        //
        addComponent(inputPanel, createLabel(new JLabel("Tên sản phẩm:"), Font.BOLD, 15, Color.BLACK), 
                    0, 4, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, txtProductName, 0, 5, 4, 1, 1.0, 0.0);
        //
        addComponent(inputPanel, createLabel(new JLabel("Mã sản phẩm:"), Font.BOLD, 15, Color.BLACK), 
                    0, 6, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, txtIdProduct, 0, 7, 4, 1, 1.0, 0.0);
        //
         addComponent(inputPanel, createLabel(new JLabel("Số lượng:"), Font.BOLD, 15, Color.BLACK), 
                    0, 8, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, createLabel(new JLabel("Từ:"), Font.BOLD, 12, Color.BLACK), 
                    0, 9, 1, 1, 0.0, 0.0);
        addComponent(inputPanel, txtfrom, 1, 9, 1, 1, 1.0, 0.0);
        //
        addComponent(inputPanel, createLabel(new JLabel("Đến:"), Font.BOLD, 12, Color.BLACK), 
                    2, 9, 1, 1, 0.0, 0.0);
        addComponent(inputPanel, txtTo, 3, 9, 1, 1, 1.0, 0.0);
        // 
        addComponent(inputPanel, createLabel(new JLabel("Trạng thái:"), Font.BOLD, 15, Color.BLACK), 
                    0, 10, 4, 1, 0.0, 0.0);
        addComponent(inputPanel, stateComboBox, 0, 11, 4, 1, 1.0, 0.0);
        //
        addComponent(inputPanel, removeFilterBtn, 0, 12, 2, 1, 0.0, 0.0);
        //
        addComponent(inputPanel, searchBtn, 2, 12, 2, 1, 0.0, 0.0);
        return panel; 
        
    }

    private JPanel rightPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension(650, 0));
        JPanel northPanel = new JPanel(new GridBagLayout()); 
        panel.add(northPanel, BorderLayout.NORTH);
        //declare 
        txtNameProductDiscount = new JTextField(); 
        txtProductIDiscount = new JTextField(); 
        
        stateDiscountModel = new DefaultComboBoxModel<>();
        stateDiscountModel.addElement("Đang áp dụng"); 
        stateDiscountModel.addElement("Hết hạn");
        stateDiscount = new JComboBox<>(stateDiscountModel);  

        filterDiscountModel = new DefaultComboBoxModel<>(); 
        filterDiscountModel.addElement("Từ thấp đến cao");
        filterDiscountModel.addElement("Từ cao đến thấp");
        filterDiscount = new JComboBox<>(filterDiscountModel); 

        txtbeginDate = new JTextField(); 
        txtEndDate = new JTextField(); 

        removeFilterDiscountBtn = createButton(new JButton("Bỏ lọc"), Font.BOLD, 15, Color.WHITE, color); 
        removeFilterDiscountBtn.setBorderPainted(false);
        removeFilterDiscountBtn.setFocusPainted(false);
        searchDiscountBtn = createButton(new JButton("Tìm Kiếm"), Font.BOLD, 15, Color.WHITE, color); 
        searchDiscountBtn.setBorderPainted(false);
        searchDiscountBtn.setFocusPainted(false);

        inputAgainBtn = createButton(new JButton("Điền lại"), Font.BOLD, 15, Color.WHITE, color); 
        deletebtn = createButton(new JButton("Xóa"), Font.BOLD, 15, Color.WHITE, color); 
        addBtn = createButton(new JButton("Thêm"), Font.BOLD, 15, Color.WHITE, color);
        saveBtn = createButton(new JButton("Lưu"), Font.BOLD, 15, Color.WHITE, color);
        exitBtn = createButton(new JButton("Thoát"), Font.BOLD, 15, Color.WHITE, color);

        //
        addComponent(northPanel, createLabel(new JLabel("Tên sản phẩm:"), Font.BOLD, 15, Color.BLACK), 
                0, 0, 1, 1, 0.0, 0.0);
        addComponent(northPanel,txtNameProductDiscount , 0, 1, 1, 1, 1.0, 0.0);
        addComponent(northPanel, createLabel(new JLabel("Mã sản phẩm:"), Font.BOLD, 15, Color.BLACK), 
                0, 2, 1, 1, 0.0,0.0);
        addComponent(northPanel,txtProductIDiscount , 0, 3, 1, 1, 1.0, 0.0);
        //
        addComponent(northPanel, createLabel(new JLabel("Trạng thái:"), Font.BOLD, 15, Color.BLACK), 
                1, 0, 1, 1, 0.0,0.0);
        addComponent(northPanel,stateDiscount , 1, 1, 1, 1, 1.0, 0.0);
        addComponent(northPanel, createLabel(new JLabel("Khuyến mãi:"), Font.BOLD, 15, Color.BLACK), 
                1, 2, 1, 1, 0.0,0.0);
        addComponent(northPanel, filterDiscount, 1, 3, 1, 1, 1.0, 0.0);
        //
        addComponent(northPanel, createLabel(new JLabel("Thời gian áp dụng:"), Font.BOLD, 15, Color.BLACK), 
                2, 0, 1, 1, 0.0,0.0);
        addComponent(northPanel, txtbeginDate, 2, 1, 1, 1, 1.0, 0.0);
        addComponent(northPanel, txtEndDate, 2, 3, 1, 1, 1.0, 0.0);
        //
        addComponent(northPanel, removeFilterDiscountBtn, 0, 4, 1, 1, 0.0, 0.0);
        addComponent(northPanel, searchDiscountBtn, 1, 4, 1, 1, 0.0, 0.0);

        // center contain table
        String [] colsName = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Khuyến mãi(%)", "Từ ngày", "Đến ngày", "Trạng thái"}; 
        discountTableModel = new DefaultTableModel(colsName, 0);
        discountTable = new JTable(discountTableModel); 
        JScrollPane scroll = new JScrollPane(discountTable); 
        panel.add(scroll, BorderLayout.CENTER); 
        //south
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); 
        panel.add(southPanel, BorderLayout.SOUTH); 
        southPanel.add(inputAgainBtn); 
        southPanel.add(deletebtn); 
        southPanel.add(addBtn); 
        southPanel.add(saveBtn); 
        southPanel.add(exitBtn); 
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
        return button; 
    }
}
