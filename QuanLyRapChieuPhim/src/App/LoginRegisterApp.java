package App;

import Model.User;
import Model.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterApp extends JFrame {
    private UserManager userManager = new UserManager();
    private User currentUser = null;

    public LoginRegisterApp() {
        userManager.registerUser("admin", "admin");
        setTitle("Login and Register");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo một panel chứa CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());

        // Tạo các panel đăng nhập và đăng ký
        JPanel loginPanel = createLoginPanel(cardPanel);
        JPanel registerPanel = createRegisterPanel(cardPanel);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(registerPanel, "Register");

        // Tạo background và thêm vào cardPanel
        ImageIcon img = new ImageIcon(LoginRegisterApp.class.getResource("/App/hinhnen.png"));
        JLabel background = new JLabel(img);
        background.setLayout(new BorderLayout());
        background.add(cardPanel, BorderLayout.CENTER);

        // Thiết lập background cho JFrame
        setContentPane(background);

        setVisible(true);
    }

    // Tạo panel đăng nhập
    private JPanel createLoginPanel(JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Sử dụng layout tự do

        // Tiêu đề đăng nhập
        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 255)); // Màu chữ xanh dương
        titleLabel.setBounds(140, 50, 150, 40);  // Vị trí và kích thước
        panel.add(titleLabel);

        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(100, 120, 200, 40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(usernameField);

        // Password field với nút hiển thị mật khẩu
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 180, 200, 40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(passwordField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(100, 230, 200, 20);
        showPassword.setOpaque(false);  // Làm trong suốt checkbox
        showPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0); // Hiển thị mật khẩu
            } else {
                passwordField.setEchoChar('*'); // Ẩn mật khẩu
            }
        });
        panel.add(showPassword);

        // Nút đăng nhập
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 270, 100, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 102, 255));  // Màu nền xanh dương
        loginButton.setForeground(Color.WHITE);  // Màu chữ trắng
        panel.add(loginButton);

        // "Forgot Password?" và "Create an Account?" link
        JLabel forgotPasswordLabel = new JLabel("Forgot Password?");
        forgotPasswordLabel.setBounds(140, 330, 120, 20);
        forgotPasswordLabel.setForeground(Color.BLUE.darker());
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Con trỏ khi di chuột
        panel.add(forgotPasswordLabel);

        JLabel createAccountLabel = new JLabel("Create an Account?");
        createAccountLabel.setBounds(140, 360, 120, 20);
        createAccountLabel.setForeground(Color.BLUE.darker());
        createAccountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        createAccountLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Con trỏ khi di chuột
        panel.add(createAccountLabel);

        // Action cho nút đăng nhập
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            currentUser = userManager.loginUser(username, password);
            if (currentUser != null) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                new CinemaApp(currentUser);
                dispose();  // Đóng cửa sổ đăng nhập sau khi thành công
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
            }
        });

        // Action khi nhấn vào "Create an Account?" để chuyển sang màn hình đăng ký
        createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Register");
            }
        });

        return panel;
    }

    // Tạo panel đăng ký
    private JPanel createRegisterPanel(JPanel cardPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Sử dụng layout tự do

        // Tiêu đề đăng ký
        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0, 102, 255)); // Màu chữ xanh dương
        titleLabel.setBounds(140, 50, 150, 40);  // Vị trí và kích thước
        panel.add(titleLabel);

        // Username field
        JTextField newUsernameField = new JTextField();
        newUsernameField.setBounds(100, 120, 200, 40);
        newUsernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        newUsernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        panel.add(newUsernameField);

        // Password field
        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(100, 180, 200, 40);
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        newPasswordField.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(newPasswordField);

        // Nút đăng ký
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 270, 100, 40);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 102, 255));  // Màu nền xanh dương
        registerButton.setForeground(Color.WHITE);  // Màu chữ trắng
        panel.add(registerButton);

        // Nút quay lại màn hình đăng nhập
        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(150, 320, 100, 40);
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setForeground(Color.BLUE.darker());
        panel.add(backButton);

        // Action cho nút đăng ký
        registerButton.addActionListener(e -> {
            String username = newUsernameField.getText();
            String password = new String(newPasswordField.getPassword());
            if (userManager.registerUser(username, password)) {
                JOptionPane.showMessageDialog(null, "Registration successful!");
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Login");
            } else {
                JOptionPane.showMessageDialog(null, "Username already exists.");
            }
        });

        // Action cho nút "Back to Login" để quay lại màn hình đăng nhập
        backButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            cl.show(cardPanel, "Login");
        });

        return panel;
    }

    public static void main(String[] args) {
        new LoginRegisterApp();
    }
}
