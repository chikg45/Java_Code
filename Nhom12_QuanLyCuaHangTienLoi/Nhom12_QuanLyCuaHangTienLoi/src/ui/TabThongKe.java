package ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TabThongKe extends JPanel {
    private final Color NAVY_BLUE = new Color(4, 69, 116);
    private final Color CYAN = new Color(0, 205, 226);
    private final Color PASTEL_BLUE = new Color(242, 249, 255);
    private final Color GRAY_BORDER = new Color(201, 201, 201);
    private final Font FONT_26_BOLD = new Font("Arial", Font.BOLD, 26);
    
    private CardLayout subCardLayout;
    private JPanel subCardPanel;
    private List<JPanel> tabs = new ArrayList<>();

    public TabThongKe() {
        setLayout(null);
        setBounds(0, 0, 914, 714);
        setBackground(Color.WHITE);

        // 2 tab tai chinh va lich su giao dich
        JPanel tab1 = createHorizontalTab("Tài chính", 0, 0, 160, 38);
        JPanel tab2 = createHorizontalTab("Lịch sử giao dịch", 160, 0, 280, 38);
        
        tab1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchTab(0);
            }
        });
        tab2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switchTab(1);
            }
        });

        tabs.add(tab1);
        tabs.add(tab2);
        add(tab1);
        add(tab2);

        // Duong ke ngang
        JPanel line = new JPanel();
        line.setBounds(0, 38, 914, 5);
        line.setBackground(CYAN);
        add(line);

        // Noi dung cho tung tab
        subCardLayout = new CardLayout();
        subCardPanel = new JPanel(subCardLayout);
        subCardPanel.setBounds(0, 43, 914, 671);
        subCardPanel.setOpaque(false);

        subCardPanel.add(createTaiChinhPanel(), "TaiChinh");
        subCardPanel.add(createLichSuPanel(), "LichSu");

        add(subCardPanel);
        
        // Mac dinh chon tab Tai Chinh
        switchTab(0);
    }

    private JPanel createHorizontalTab(String text, int x, int y, int width, int height) {
        JPanel tab = new JPanel(null);
        tab.setBounds(x, y, width, height);
        tab.setBackground(NAVY_BLUE);

        JLabel label = new JLabel(text);
        label.setFont(FONT_26_BOLD);
        label.setForeground(Color.WHITE);
        label.setBounds(13, 0, width - 13, height);
        tab.add(label);

        return tab;
    }

    private JPanel createSubPanel(String text) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        JLabel l = new JLabel(text, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 30));
        l.setForeground(NAVY_BLUE);
        p.add(l, BorderLayout.CENTER);
        return p;
    }

    private JPanel createTaiChinhPanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);

        JLabel title = new JLabel("THỐNG KÊ TÀI CHÍNH");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(NAVY_BLUE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 20, 914, 39);
        p.add(title);

        JLabel timeLabel = new JLabel("Khoảng thời gian:");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setBounds(309, 59, 150, 30);
        p.add(timeLabel);

        String[] timeOptions = {"Từ trước đến nay", "1 tuần", "1 tháng", "3 tháng", "6 tháng", "1 năm", "3 năm", "5 năm", "Tùy chỉnh"};
        JComboBox<String> timeCombo = new JComboBox<>(timeOptions);
        timeCombo.setFont(new Font("Arial", Font.PLAIN, 14));
        timeCombo.setBounds(460, 59, 160, 30);
        p.add(timeCombo);

        JPanel customDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        customDatePanel.setOpaque(false);
        customDatePanel.setBounds(630, 59, 280, 30);
        JTextField startDate = new JTextField("dd/MM/yyyy", 8);
        JTextField endDate = new JTextField("dd/MM/yyyy", 8);
        customDatePanel.add(new JLabel("Từ:"));
        customDatePanel.add(startDate);
        customDatePanel.add(new JLabel("Đến:"));
        customDatePanel.add(endDate);
        customDatePanel.setVisible(false);
        p.add(customDatePanel);

        timeCombo.addActionListener(e -> {
            if ("Tùy chỉnh".equals(timeCombo.getSelectedItem())) {
                customDatePanel.setVisible(true);
            } else {
                customDatePanel.setVisible(false);
            }
        });

        JPanel overviewRow = new JPanel(null);
        overviewRow.setOpaque(false);
        overviewRow.setBounds(0, 102, 914, 79);
        p.add(overviewRow);

        JLabel overviewHeading = new JLabel("Tổng quan");
        overviewHeading.setFont(new Font("Arial", Font.BOLD, 20));
        overviewHeading.setForeground(NAVY_BLUE);
        overviewHeading.setBounds(13, 0, 150, 79);
        overviewHeading.setVerticalAlignment(SwingConstants.TOP);
        overviewRow.add(overviewHeading);

        JPanel col2Container = new JPanel(new GridLayout(1, 4)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.setColor(GRAY_BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };
        col2Container.setOpaque(false);
        col2Container.setBackground(PASTEL_BLUE);
        col2Container.setBounds(189, 0, 695, 79);
        overviewRow.add(col2Container);

        col2Container.add(createStatCol("Tổng doanh thu", "1,000,000,000 đ"));
        col2Container.add(createStatCol("Số đơn hàng", "300"));
        col2Container.add(createStatCol("Số sản phẩm đã bán", "5000"));

        JPanel subCol4 = new JPanel();
        subCol4.setLayout(new BoxLayout(subCol4, BoxLayout.Y_AXIS));
        subCol4.setOpaque(false);
        subCol4.add(Box.createVerticalGlue());

        JLabel sc4Title = new JLabel("Sản phẩm bán chạy nhất:");
        sc4Title.setFont(new Font("Arial", Font.BOLD, 13));
        sc4Title.setForeground(Color.BLACK);
        sc4Title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sc4Item1 = new JLabel("1. Cơm nắm: 321 đã bán");
        sc4Item1.setFont(new Font("Arial", Font.PLAIN, 12));
        sc4Item1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sc4Item2 = new JLabel("2. Mì ăn liền ly: 280 đã bán");
        sc4Item2.setFont(new Font("Arial", Font.PLAIN, 12));
        sc4Item2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sc4Item3 = new JLabel("3. Trứng luộc: 110 đã bán");
        sc4Item3.setFont(new Font("Arial", Font.PLAIN, 12));
        sc4Item3.setAlignmentX(Component.LEFT_ALIGNMENT);

        subCol4.add(sc4Title);
        subCol4.add(sc4Item1);
        subCol4.add(sc4Item2);
        subCol4.add(sc4Item3);
        subCol4.add(Box.createVerticalGlue());
        col2Container.add(subCol4);

        JPanel chartRow = new JPanel(null);
        chartRow.setOpaque(false);
        chartRow.setBounds(0, 194, 914, 26);
        p.add(chartRow);

        JLabel chartHeading = new JLabel("Biểu đồ");
        chartHeading.setFont(new Font("Arial", Font.BOLD, 20));
        chartHeading.setForeground(NAVY_BLUE);
        chartHeading.setBounds(13, 0, 100, 26);
        chartRow.add(chartHeading);

        JPanel chartOptions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        chartOptions.setOpaque(false);
        chartOptions.setBounds(113, 0, 801, 26);

        JLabel reportLabel = new JLabel("Báo cáo:");
        reportLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        reportLabel.setForeground(Color.BLACK);
        JComboBox<String> reportCombo = new JComboBox<>(new String[]{"Chi phí", "Doanh thu", "Lợi nhuận"});
        reportCombo.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel groupLabel = new JLabel("Gộp dữ liệu:");
        groupLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        groupLabel.setForeground(Color.BLACK);
        JComboBox<String> groupCombo = new JComboBox<>(new String[]{"1 ngày", "3 ngày", "1 tuần", "2 tuần", "1 tháng", "3 tháng", "6 tháng", "1 năm", "3 năm", "5 năm"});
        groupCombo.setFont(new Font("Arial", Font.PLAIN, 13));

        chartOptions.add(reportLabel);
        chartOptions.add(reportCombo);
        chartOptions.add(groupLabel);
        chartOptions.add(groupCombo);
        chartRow.add(chartOptions);

        JPanel graphPlaceholder = new JPanel(new BorderLayout());
        graphPlaceholder.setBounds(13, 240, 888, 410);
        graphPlaceholder.setBackground(new Color(240, 240, 240));
        graphPlaceholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        JLabel graphLabel = new JLabel("Vẽ biểu đồ ở đây", SwingConstants.CENTER);
        graphLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        graphLabel.setForeground(Color.GRAY);
        graphPlaceholder.add(graphLabel, BorderLayout.CENTER);
        p.add(graphPlaceholder);

        return p;
    }

    private JPanel createStatCol(String title, String value) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        
        panel.add(Box.createVerticalGlue());
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.BOLD, 16));
        lblValue.setForeground(CYAN);
        lblValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(5));
        panel.add(lblValue);
        
        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createLichSuPanel() {
        JPanel p = new JPanel(null);
        p.setBackground(Color.WHITE);

        JLabel title = new JLabel("LỊCH SỬ GIAO DỊCH");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(NAVY_BLUE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 20, 914, 39);
        p.add(title);

        JLabel timeLabel = new JLabel("Khoảng thời gian:");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setBounds(13, 70, 150, 30);
        p.add(timeLabel);

        // Từ ngày
        JLabel fromLabel = new JLabel("Từ ngày:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        fromLabel.setBounds(13, 100, 150, 20);
        p.add(fromLabel);
        
        JSpinner fromDateSpinner = createDateSpinner(-7);
        fromDateSpinner.setBounds(13, 120, 140, 30);
        p.add(fromDateSpinner);

        // Đến ngày
        JLabel toLabel = new JLabel("Đến ngày:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        toLabel.setBounds(170, 100, 150, 20);
        p.add(toLabel);
        
        JSpinner toDateSpinner = createDateSpinner(0);
        toDateSpinner.setBounds(170, 120, 140, 30);
        p.add(toDateSpinner);

        // Buttons
        JButton btnView = new JButton("Xem hóa đơn");
        btnView.setBounds(610, 80, 130, 30);
        btnView.setFont(new Font("Arial", Font.BOLD, 13));
        btnView.setBackground(NAVY_BLUE);
        btnView.setForeground(Color.WHITE);
        btnView.setVisible(false);
        p.add(btnView);
        
        JButton btnDownload = new JButton("Tải hóa đơn (pdf)");
        btnDownload.setBounds(740, 80, 150, 30);
        btnDownload.setFont(new Font("Arial", Font.BOLD, 13));
        btnDownload.setBackground(NAVY_BLUE);
        btnDownload.setForeground(Color.WHITE);
        btnDownload.setVisible(false);
        p.add(btnDownload);
        
        JButton btnDownloadAll = new JButton("Tải toàn bộ hóa đơn (pdf)");
        btnDownloadAll.setBounds(610, 120, 280, 30);
        btnDownloadAll.setFont(new Font("Arial", Font.BOLD, 13));
        btnDownloadAll.setBackground(NAVY_BLUE);
        btnDownloadAll.setForeground(Color.WHITE);
        p.add(btnDownloadAll);
        
        // Table
        String[] columns = {"Mã GD", "Loại GD", "Thời gian", "Nhân viên", "Tổng tiền", "Đối tác", "Phương thức thanh toán"};
        Object[][] data = {
            {"GD001", "Bán hàng", "01-01-2026 6:57", "NV01", "1,000,000", "Khách lẻ", "Tiền mặt"},
            {"GD002", "Nhập hàng", "01-01-2026 8:30", "NV02", "5,000,000", "NCC A", "Chuyển khoản"},
            {"GD003", "Bán hàng", "02-01-2026 14:20", "NV01", "250,000", "Khách lẻ", "Tiền mặt"},
            {"GD004", "Bán hàng", "02-01-2026 15:30", "NV03", "450,000", "Khách lẻ", "Chuyển khoản"}
        };
        JTable table = new JTable(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(NAVY_BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                boolean hasSelection = table.getSelectedRow() != -1;
                btnView.setVisible(hasSelection);
                btnDownload.setVisible(hasSelection);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(13, 170, 888, 480);
        p.add(scrollPane);

        return p;
    }

    private JSpinner createDateSpinner(int offsetDays) {
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "dd/MM/yyyy");
        spinner.setEditor(editor);
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(java.util.Calendar.DAY_OF_MONTH, offsetDays);
        model.setValue(cal.getTime());
        return spinner;
    }

    private void switchTab(int index) {
        for (int i = 0; i < tabs.size(); i++) {
            tabs.get(i).setBackground(i == index ? CYAN : NAVY_BLUE);
        }
        String[] names = {"TaiChinh", "LichSu"};
        subCardLayout.show(subCardPanel, names[index]);
    }
}
