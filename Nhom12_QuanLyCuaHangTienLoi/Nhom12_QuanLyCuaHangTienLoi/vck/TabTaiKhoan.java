	package gui;
	
	import java.awt.*;
	import javax.swing.*;
	
	import Demo.UIKhachHang;
	import Demo.UINhanVien;
	
	public class TabTaiKhoan extends JPanel {
		
		private CardLayout cardLayout;
		private JPanel pnlCard;
		private JComboBox<String> cbLoai;
		
	    public TabTaiKhoan() {
	       
	    	setLayout(new BorderLayout());
	         add(createMainContent(), BorderLayout.CENTER);
	     
	    }
	    
	    private JPanel createTitle() {
	    	JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	        panel.setBackground(Color.WHITE);
	
	        JLabel lblTitle = new	 JLabel("TÀI KHOẢN");
	        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
	        lblTitle.setForeground(new Color(21, 82, 120));
	
	        String[] types = { "Khách hàng", "Nhân viên" };
	        cbLoai = new JComboBox<>(types);
	
	        cbLoai.addActionListener(e -> {
	            String selected = (String) cbLoai.getSelectedItem();
	            cardLayout.show(pnlCard, selected); 
	        });
	        
	        panel.add(lblTitle);
	        panel.add(cbLoai);
	
	        return panel;
	    	
	    }
	    
	    private JPanel createMainContent() {
	        JPanel panel = new JPanel(new BorderLayout());
	        
	        cardLayout = new CardLayout();
	        pnlCard = new JPanel(cardLayout);
	        
	        panel.add(createTitle(),BorderLayout.NORTH);
	       
	        
	        
	        UIKhachHang  guiKhachHang = new UIKhachHang();
	        UINhanVien guiNhanVien = new UINhanVien();
	        
	        
	      
	        
	        pnlCard.add(guiKhachHang,"Khách hàng");    
	        pnlCard.add(guiNhanVien,"Nhân viên");
	        
	        
	        panel.add(pnlCard,BorderLayout.CENTER);
	        
	        return panel;
	    }
	    
	    private JButton createMenuButton(JButton btn,String text, boolean active) {
	    	 
	        
	        btn.setMaximumSize(new Dimension(200, 60));
	      
	
	          btn.setAlignmentX(Component.LEFT_ALIGNMENT);
	          btn.setHorizontalAlignment(SwingConstants.LEFT);
	
	          btn.setForeground(Color.WHITE);
	          btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
	
	          btn.setFocusPainted(false);
	          btn.setBorderPainted(false);
	
	          if (active) {
	              btn.setBackground(new Color(0, 188, 212)); // xanh sáng
	          } else {
	              btn.setBackground(new Color(21, 82, 120)); // xanh đậm
	          }
	
	          btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
	
	          return btn;
	      }
	    
	}
