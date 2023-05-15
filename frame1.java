package finalCaseStudytrue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class frame1 extends JFrame implements ActionListener {

    private DefaultListModel<String> menuListModel;
    private JList<String> menuList;
    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private JLabel totalLabel;

    private double totalCost = 0.0;
    private DecimalFormat currencyFormat;
    private double cash;
    private String formattedChange;

    frame1() {

        ImageIcon image1 = new ImageIcon("tsuLogo.png");
        this.setIconImage(image1.getImage());

        ImageIcon image = new ImageIcon(
                new ImageIcon("tsuLogo.png").getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
        JLabel bgLabel = new JLabel("", image, JLabel.CENTER);

        /*
         * Scale the image to fit the frame
         * Image scaledImage = pic.getScaledInstance(this.getWidth(), this.getHeight(),
         * Image.SCALE_SMOOTH);
         * ImageIcon backgroundImageIcon = new ImageIcon(scaledImage);
         * 
         * // Set the background of the frame
         * JLabel backgroundLabel = new JLabel(backgroundImageIcon);
         * backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
         */
        JLabel header = new JLabel();
        header.setText("TSU Canteen");
        header.setForeground(new Color(58, 152, 185));
        header.setFont(new Font("Monospace", Font.BOLD, 50));

        JPanel logo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logo.add(bgLabel);
        logo.add(header);
        logo.setBackground(new Color(175, 211, 226));
        logo.setBounds(0, 0, 750, 100);

        JPanel menuPanel = new JPanel(new GridLayout(2, 1));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setBounds(50, 180, 300, 250);

        JPanel foodPanel = new JPanel(new BorderLayout());
        foodPanel.setBorder(BorderFactory.createTitledBorder("Food"));

        DefaultListModel<String> foodListModel = new DefaultListModel<>();
        foodListModel.addElement("Sisig ----------------------------------------------- Php 40");
        foodListModel.addElement("Menudo ------------------------------------------- Php 40");
        foodListModel.addElement("Kilayin --------------------------------------------- Php 40");
        foodListModel.addElement("Extra Rice ---------------------------------------- Php 5");

        JList<String> foodList = new JList<>(foodListModel);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        foodList.setVisibleRowCount(5);
        foodPanel.add(new JScrollPane(foodList), BorderLayout.CENTER);

        JPanel drinksPanel = new JPanel(new BorderLayout());
        drinksPanel.setBorder(BorderFactory.createTitledBorder("Drinks"));

        DefaultListModel<String> drinksListModel = new DefaultListModel<>();
        drinksListModel.addElement("Coke ----------------------------------------------- Php 15");
        drinksListModel.addElement("Sprite ---------------------------------------------- Php 15");
        drinksListModel.addElement("Royal ----------------------------------------------- Php 15");
        drinksListModel.addElement("mineral -------------------------------------------- Php 10");

        JList<String> drinksList = new JList<>(drinksListModel);
        drinksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        drinksList.setVisibleRowCount(5);
        drinksPanel.add(new JScrollPane(drinksList), BorderLayout.CENTER);

        foodList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    drinksList.clearSelection();
                }
            }
        });

        drinksList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    foodList.clearSelection();
                }
            }
        });

        menuPanel.add(foodPanel);
        menuPanel.add(drinksPanel);

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Details"));
        orderPanel.setBackground(Color.LIGHT_GRAY);
        orderPanel.setBounds(400, 180, 300, 200);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.setVisibleRowCount(5);
        orderPanel.add(new JScrollPane(orderList), BorderLayout.CENTER);

        JButton addToOrderButton = new JButton("Add to Order");
        addToOrderButton.setBounds(50, 440, 120, 30);
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOrder(foodList);
                addToOrder(drinksList);
            }
        });

        JLabel Payment = new JLabel("Payment: ");
        Payment.setBounds(400, 400, 200, 20);

        JTextField payment = new JTextField();
        payment.setText("0.0");
        payment.setEditable(true);
        payment.setBounds(460, 400, 80, 20);

        JLabel changeLabel = new JLabel("Change: Php 0.00");

        payment.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {

                cash = Double.parseDouble(payment.getText());

                double change = cash - totalCost;
                currencyFormat = new DecimalFormat("Php 0.00");
                formattedChange = currencyFormat.format(change);

                changeLabel.setText("Change: " + formattedChange);
            }

            @Override
            public void focusGained(FocusEvent e) {

            }
        });

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        totalLabel = new JLabel("Total: Php0.0");
        totalLabel.setBounds(400, 415, 200, 20);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(180, 433, 150, 20);
        JComboBox<String> paymentComboBox = new JComboBox<>();
        paymentComboBox.addItem("Cash");
        paymentComboBox.addItem("G Cash");
        paymentComboBox.addItem("Credit Card");
        paymentComboBox.setBounds(180, 450, 100, 20);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(500, 440, 100, 30);
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(checkoutButton)) {
                    if (payment.getText().equals("0.0")) {
                        JOptionPane.showMessageDialog(null, "Please Enter Amount of payment", "Warning!",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (orderListModel.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No ordered items", "Warning!",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            double paymentAmount = Double.parseDouble(payment.getText());
                            if (paymentAmount < totalCost) {
                                JOptionPane.showMessageDialog(null, "Insufficient amount!", "Warning!",
                                        JOptionPane.WARNING_MESSAGE);
                            } else {
                                // generate receipt
                                StringBuilder receipt = new StringBuilder();
                                receipt.append("+-------------TSU Canteen Receipt-------------+\n\n");
                                receipt.append("Employee: Owner\n\n");
                                receipt.append("Items:\n");

                                for (int i = 0; i < orderListModel.size(); i++) {
                                    receipt.append(orderListModel.getElementAt(i)).append("\n\n");
                                }
                                receipt.append("Payment Method: ").append(paymentComboBox.getSelectedItem().toString())
                                        .append("\n");
                                receipt.append("\nTotal: Php").append(String.format("%.2f", totalCost)).append("\n");
                                receipt.append("Cash: ").append(currencyFormat.format(cash)).append("\n");
                                receipt.append("Change: ").append(formattedChange).append("\n\n");
                                receipt.append("Di mahalaga ang nagwagi,\nang mahalaga ika'y nakibahagi ❤");
                                receipt.append("\nThank you for your purchase!\n\n");
                                receipt.append(formattedDate).append("\n");
                                JOptionPane.showMessageDialog(null, receipt.toString(), "Receipt",
                                        JOptionPane.INFORMATION_MESSAGE);
                            	}
                        	}
                    	}
                	}
            	}
            });

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(600, 440, 100, 30);
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                orderListModel.clear();
                totalCost = 0.0;
                updateTotalLabel();
                payment.setText("");
            }

        });

        this.setTitle("TSU Canteen");
        this.setSize(750, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(Payment);
        this.add(payment);
        this.add(logo);
        this.add(menuPanel);
        this.add(orderPanel);
        this.add(addToOrderButton);
        this.add(paymentLabel);
        this.add(paymentComboBox);
        this.add(totalLabel);
        this.add(checkoutButton);
        this.add(clearButton);
        this.getContentPane().setBackground(new Color(175, 211, 226));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedIndex = menuList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedItem = menuListModel.getElementAt(selectedIndex);
            orderListModel.addElement(selectedItem);

            String[] itemDetails = selectedItem.split("- \\Php");
            double itemPrice = Double.parseDouble(itemDetails[1]);
            totalCost += itemPrice;
            updateTotalLabel();
        }
    }

    private void updateTotalLabel() {
        totalLabel.setText("Total: Php" + String.format("%.2f", totalCost));
    }

    private void addToOrder(JList<String> list) {
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex != -1) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) list.getModel();
            String selectedItem = listModel.getElementAt(selectedIndex);
            orderListModel.addElement(selectedItem);

            String[] itemDetails = selectedItem.split("\\s*-\\s*Php\\s*");
            double itemPrice = Double.parseDouble(itemDetails[1]);
            totalCost += itemPrice;
            updateTotalLabel();
        }
    }
}