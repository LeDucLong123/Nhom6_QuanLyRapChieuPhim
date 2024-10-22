package App;

import Model.CinemaManager;
import Model.Movie;
import Model.Showtime;
import Model.Ticket;
import Model.User;
import Model.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class CinemaApp extends JFrame {
    private CinemaManager cinemaManager = new CinemaManager();
    private User currentUser;

    public CinemaApp(User user) {
        this.currentUser = user;
        setTitle("Cinema Management System - Welcome " + user.getUsername());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tạo JTabbedPane cho các tab
        JTabbedPane tabbedPane = new JTabbedPane();

        // Thêm các tab vào tabbedPane
        tabbedPane.addTab("Home", createHomePanel());
        tabbedPane.addTab("Show Movies", createShowMoviesPanel());
        tabbedPane.addTab("Show Showtimes", createShowShowtimesPanel());
        tabbedPane.addTab("Show Tickets", createShowTicketsPanel());
        tabbedPane.addTab("Book Ticket", createBookTicketPanel());

        // Nếu là admin, thêm tab thêm phim
        if ("admin".equals(user.getUsername())) {
            tabbedPane.addTab("Add Movie", createAddMoviePanel());
        }

        // Cài đặt ảnh nền
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        background.setLayout(new BorderLayout());
        background.add(tabbedPane, BorderLayout.CENTER);
        
        // Thiết lập background cho JFrame
        setContentPane(background);
        setVisible(true);
    }

    // Tạo panel chính
    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Cinema Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        // Cài đặt ảnh nền
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        panel.add(background, BorderLayout.CENTER);
        return panel;
    }

    // Tạo panel hiển thị danh sách phim
    private JPanel createShowMoviesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton showMoviesButton = new JButton("Show Movies");
        panel.add(showMoviesButton, BorderLayout.SOUTH);
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        panel.add(background, BorderLayout.CENTER);
        showMoviesButton.addActionListener(e -> showMovies());
        
        return panel;
    }

    // Tạo panel hiển thị suất chiếu
    private JPanel createShowShowtimesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton showShowtimesButton = new JButton("Show Showtimes");
        panel.add(showShowtimesButton, BorderLayout.SOUTH);
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        panel.add(background, BorderLayout.CENTER);
        showShowtimesButton.addActionListener(e -> showShowtimes());
        
        return panel;
    }

    // Tạo panel hiển thị vé
    private JPanel createShowTicketsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton showTicketsButton = new JButton("Show Tickets");
        panel.add(showTicketsButton, BorderLayout.SOUTH);
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        panel.add(background, BorderLayout.CENTER);
        showTicketsButton.addActionListener(e -> showTickets());
        
        return panel;
    }

    // Tạo panel đặt vé
    private JPanel createBookTicketPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton bookTicketButton = new JButton("Book Ticket");
        panel.add(bookTicketButton,BorderLayout.NORTH);
        JLabel background = new JLabel(new ImageIcon(CinemaApp.class.getResource("/App/hinhnen.png")));
        panel.add(background, BorderLayout.CENTER);
        bookTicketButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog("Enter movie title to book:");
            Movie movie = cinemaManager.getMovies().stream()
                    .filter(m -> m.getTitle().equals(title))
                    .findFirst()
                    .orElse(null);
            if (movie != null) {
                Showtime showtime = new Showtime(movie, LocalDateTime.now());
                cinemaManager.addShowtime(showtime);
                Ticket ticket = new Ticket(showtime, currentUser.getUsername());
                cinemaManager.bookTicket(ticket);
                JOptionPane.showMessageDialog(null, "Ticket booked for " + currentUser.getUsername());
            } else {
                JOptionPane.showMessageDialog(null, "Movie not found!");
            }
        });

        return panel;
    }

    // Hiển thị bảng phim bằng JTable
    private void showMovies() {
        String[] columnNames = {"Movie Title"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Movie movie : cinemaManager.getMovies()) {
            Object[] row = {movie.getTitle()};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        showTableInDialog(scrollPane, "Movies List");
    }

    // Hiển thị bảng suất chiếu bằng JTable
    private void showShowtimes() {
        String[] columnNames = {"Movie Title", "Showtime"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Showtime showtime : cinemaManager.getShowtimes()) {
            Object[] row = {showtime.getMovie().getTitle(), showtime.getTime().toString()};
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        showTableInDialog(scrollPane, "Showtimes List");
    }

    // Hiển thị bảng vé bằng JTable
    private void showTickets() {
        String[] columnNames = {"Movie Title", "Showtime", "User"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Ticket ticket : cinemaManager.getTickets()) {
            Object[] row = {
                    ticket.getShowtime().getMovie().getTitle(),
                    ticket.getShowtime().getTime().toString(),
                    this.currentUser.getUsername()
            };
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        showTableInDialog(scrollPane, "Tickets List");
    }

    // Hàm hiển thị bảng trong một dialog
    private void showTableInDialog(JScrollPane scrollPane, String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(600, 400);
        dialog.add(scrollPane);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Panel thêm phim dành riêng cho admin
    private JPanel createAddMoviePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Movie Title:"));
        JTextField titleField = new JTextField();
        panel.add(titleField);

        JButton addMovieButton = new JButton("Add Movie");
        panel.add(addMovieButton);

        addMovieButton.addActionListener(e -> {
            String title = titleField.getText();
            if (!title.isEmpty()) {
                cinemaManager.addMovie(new Movie(title));
                JOptionPane.showMessageDialog(null, "Movie added: " + title);
            } else {
                JOptionPane.showMessageDialog(null, "Movie title cannot be empty.");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        // Ví dụ tạo tài khoản admin và người dùng thường
        UserManager userManager = new UserManager();
        userManager.registerUser("admin", "admin123");
        userManager.registerUser("user", "user123");

        // Đăng nhập với tài khoản admin để mở CinemaApp
        User adminUser = userManager.loginUser("admin", "admin123");
        new CinemaApp(adminUser);
    }
}
