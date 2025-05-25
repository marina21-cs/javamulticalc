import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MultiCalculatorSystem extends JFrame {
    
    // Main components
    private JTextField inputField;
    private JTextArea resultArea;
    private JTextArea historyArea;
    private JComboBox<String> calculatorTypes;
    private JPanel calculatorPanel;
    private CardLayout cardLayout;
    private List<String> calculationHistory;
    
    // Color scheme for professional appearance
    private final Color PRIMARY_COLOR = new Color(52, 73, 94);
    private final Color SECONDARY_COLOR = new Color(46, 125, 50);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color ACCENT_COLOR = new Color(33, 150, 243);
    
    // References to panel-specific components
    private JTextField loanAmountField, interestRateField, loanTermField;
    private JTextField investmentAmountField, returnRateField, investmentYearsField;
    private JComboBox<String> conversionTypeCombo, fromUnitCombo, toUnitCombo;
    private JTextField unitValueField;
    private JTextArea statsDataArea;

    public MultiCalculatorSystem() {
        calculationHistory = new ArrayList<>();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        setDefaultCalculator();
    }
    
    private void initializeComponents() {
        setTitle("Advanced Multi-Calculator System v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Create main input field
        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Create result display area
        resultArea = new JTextArea(8, 40);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        resultArea.setEditable(false);
        resultArea.setBackground(Color.WHITE);
        resultArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        resultArea.setText("Welcome to Advanced Multi-Calculator System!\n\n" +
                          "Features:\n" +
                          "• Basic & Scientific Calculations\n" +
                          "• Financial & Business Calculations\n" +
                          "• Unit Conversions\n" +
                          "• Statistical Analysis\n\n" +
                          "Enter your calculation above and press Calculate or Enter.");
        
        // Create history area
        historyArea = new JTextArea(8, 20);
        historyArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        historyArea.setEditable(false);
        historyArea.setBackground(new Color(248, 249, 250));
        historyArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        historyArea.setText("Calculation History:\n\n");
        
        // Create calculator type selector
        String[] calculatorOptions = {
            "Basic Calculator",
            "Scientific Calculator", 
            "Financial Calculator",
            "Unit Converter",
            "Statistics Calculator"
        };
        calculatorTypes = new JComboBox<>(calculatorOptions);
        calculatorTypes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        calculatorTypes.setBackground(Color.WHITE);
        
        // Create card layout for different calculator panels
        cardLayout = new CardLayout();
        calculatorPanel = new JPanel(cardLayout);
        calculatorPanel.setBackground(BACKGROUND_COLOR);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        
        // Center panel with calculator and results
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(BACKGROUND_COLOR);
        
        // Create different calculator panels
        createCalculatorPanels();
        centerPanel.add(calculatorPanel, BorderLayout.WEST);
        
        // Results panel
        JPanel resultsPanel = createResultsPanel();
        centerPanel.add(resultsPanel, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // History panel
        JPanel historyPanel = createHistoryPanel();
        mainPanel.add(historyPanel, BorderLayout.EAST);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Status bar
        JPanel statusPanel = createStatusPanel();
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PRIMARY_COLOR);
        header.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Advanced Multi-Calculator System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Professional calculation tools for education and business");
        subtitleLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        subtitleLabel.setForeground(new Color(200, 200, 200));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(subtitleLabel, BorderLayout.SOUTH);
        
        header.add(titlePanel, BorderLayout.WEST);
        
        return header;
    }
    
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel inputLabel = new JLabel("Enter Calculation:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        inputLabel.setForeground(PRIMARY_COLOR);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(inputLabel, BorderLayout.WEST);
        topPanel.add(calculatorTypes, BorderLayout.EAST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        
        JButton calculateButton = createStyledButton("Calculate", SECONDARY_COLOR);
        JButton clearButton = createStyledButton("Clear", new Color(231, 76, 60));
        JButton saveButton = createStyledButton("Save Result", ACCENT_COLOR);
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        
        inputPanel.add(topPanel, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return inputPanel;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void createCalculatorPanels() {
        // Basic Calculator Panel
        JPanel basicPanel = createBasicCalculatorPanel();
        calculatorPanel.add(basicPanel, "Basic Calculator");
        
        // Scientific Calculator Panel
        JPanel scientificPanel = createScientificCalculatorPanel();
        calculatorPanel.add(scientificPanel, "Scientific Calculator");
        
        // Financial Calculator Panel
        JPanel financialPanel = createFinancialCalculatorPanel();
        calculatorPanel.add(financialPanel, "Financial Calculator");
        
        // Unit Converter Panel
        JPanel unitPanel = createUnitConverterPanel();
        calculatorPanel.add(unitPanel, "Unit Converter");
        
        // Statistics Calculator Panel
        JPanel statsPanel = createStatisticsPanel();
        calculatorPanel.add(statsPanel, "Statistics Calculator");
    }
    
    private JPanel createBasicCalculatorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Basic Calculator",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), PRIMARY_COLOR
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        int row = 0, col = 0;
        for (String btnText : buttons) {
            JButton btn = createCalculatorButton(btnText);
            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(btn, gbc);
            
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        
        return panel;
    }
    
    private JPanel createScientificCalculatorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            "Scientific Calculator",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), PRIMARY_COLOR
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.BOTH;
        
        String[] sciButtons = {
            "sin", "cos", "tan", "log",
            "ln", "√", "x²", "x^y",
            "π", "e", "(", ")",
            "!", "°", "rad", "abs"
        };
        
        int row = 0, col = 0;
        for (String btnText : sciButtons) {
            JButton btn = createCalculatorButton(btnText);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(btn, gbc);
            
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
        
        return panel;
    }
    
    private JPanel createFinancialCalculatorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            "Financial Calculator",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), SECONDARY_COLOR
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        JLabel loanAmountLabel = new JLabel("Loan Amount ($):");
        loanAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(loanAmountLabel, gbc);
        
        loanAmountField = new JTextField(10);
        loanAmountField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(loanAmountField, gbc);
        
        JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        interestRateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(interestRateLabel, gbc);
        
        interestRateField = new JTextField(10);
        interestRateField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(interestRateField, gbc);
        
        JLabel loanTermLabel = new JLabel("Loan Term (years):");
        loanTermLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(loanTermLabel, gbc);
        
        loanTermField = new JTextField(10);
        loanTermField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(loanTermField, gbc);
        
        JButton loanCalcBtn = createStyledButton("Calculate Loan Payment", SECONDARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(loanCalcBtn, gbc);
        
        
        gbc.gridwidth = 1;
        JLabel investAmountLabel = new JLabel("Initial Investment ($):");
        investAmountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(investAmountLabel, gbc);
        
        investmentAmountField = new JTextField(10);
        investmentAmountField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(investmentAmountField, gbc);
        
        JLabel returnRateLabel = new JLabel("Annual Return (%):");
        returnRateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(returnRateLabel, gbc);
        
        returnRateField = new JTextField(10);
        returnRateField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(returnRateField, gbc);
        
        JLabel investYearsLabel = new JLabel("Years:");
        investYearsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(investYearsLabel, gbc);
        
        investmentYearsField = new JTextField(10);
        investmentYearsField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(investmentYearsField, gbc);
        
        JButton investCalcBtn = createStyledButton("Calculate Investment", SECONDARY_COLOR);
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 2;
        panel.add(investCalcBtn, gbc);
        
        
        loanCalcBtn.addActionListener(e -> {
            try {
                double principal = Double.parseDouble(loanAmountField.getText());
                double rate = Double.parseDouble(interestRateField.getText()) / 100 / 12;
                int months = (int)(Double.parseDouble(loanTermField.getText()) * 12);
                
                String result = calculateLoanPayment(principal, rate, months);
                String input = "loan " + principal + " " + (rate * 1200) + " " + (months / 12.0);
                displayResult(input, result);
                addToHistory(input, result);
            } catch (Exception ex) {
                showError("Invalid loan calculation input: " + ex.getMessage());
            }
        });
        
        investCalcBtn.addActionListener(e -> {
            try {
                double principal = Double.parseDouble(investmentAmountField.getText());
                double rate = Double.parseDouble(returnRateField.getText()) / 100;
                int years = Integer.parseInt(investmentYearsField.getText());
                
                String result = calculateInvestment(principal, rate, years);
                String input = "investment " + principal + " " + (rate * 100) + " " + years;
                displayResult(input, result);
                addToHistory(input, result);
            } catch (Exception ex) {
                showError("Invalid investment calculation input: " + ex.getMessage());
            }
        });
        
        return panel;
    }
    
    private JPanel createUnitConverterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            "Unit Converter",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), ACCENT_COLOR
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Conversion type selector
        String[] conversionTypes = {"Length", "Weight", "Temperature", "Volume", "Area"};
        conversionTypeCombo = new JComboBox<>(conversionTypes);
        conversionTypeCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("Conversion Type:"), gbc);
        gbc.gridy = 1;
        panel.add(conversionTypeCombo, gbc);
        
        // From unit
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("From:"), gbc);
        
        fromUnitCombo = new JComboBox<>(new String[]{"Meter", "Foot", "Inch", "Kilometer", "Mile"});
        fromUnitCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(fromUnitCombo, gbc);
        
        // To unit
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("To:"), gbc);
        
        toUnitCombo = new JComboBox<>(new String[]{"Meter", "Foot", "Inch", "Kilometer", "Mile"});
        toUnitCombo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(toUnitCombo, gbc);
        
        // Value input
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Value:"), gbc);
        
        unitValueField = new JTextField(10);
        unitValueField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(unitValueField, gbc);
        
        // Convert button
        JButton convertBtn = createStyledButton("Convert", ACCENT_COLOR);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(convertBtn, gbc);
        
        // Update units based on conversion type
        conversionTypeCombo.addActionListener(e -> updateUnitOptions());
        
        // Add action listener for convert button
        convertBtn.addActionListener(e -> {
            try {
                double value = Double.parseDouble(unitValueField.getText());
                String fromUnit = (String) fromUnitCombo.getSelectedItem();
                String toUnit = (String) toUnitCombo.getSelectedItem();
                
                String result = performUnitConversion(value + " " + fromUnit.toLowerCase() + " to " + toUnit.toLowerCase());
                displayResult(value + " " + fromUnit + " to " + toUnit, result);
                addToHistory(value + " " + fromUnit + " to " + toUnit, result);
            } catch (Exception ex) {
                showError("Invalid unit conversion input: " + ex.getMessage());
            }
        });
        
        return panel;
    }
    
    private void updateUnitOptions() {
        String type = (String) conversionTypeCombo.getSelectedItem();
        String[] units;
        switch (type) {
            case "Length":
                units = new String[]{"Meter", "Foot", "Inch", "Kilometer", "Mile", "Yard", "Centimeter"};
                break;
            case "Weight":
                units = new String[]{"Kilogram", "Pound", "Ounce", "Gram"};
                break;
            case "Temperature":
                units = new String[]{"Celsius", "Fahrenheit", "Kelvin"};
                break;
            case "Volume":
                units = new String[]{"Liter", "Gallon", "Milliliter", "Cubic Meter"};
                break;
            case "Area":
                units = new String[]{"Square Meter", "Square Foot", "Acre", "Hectare"};
                break;
            default:
                units = new String[]{"Meter", "Foot", "Inch", "Kilometer", "Mile"};
        }
        fromUnitCombo.setModel(new DefaultComboBoxModel<>(units));
        toUnitCombo.setModel(new DefaultComboBoxModel<>(units));
    }
    
    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(156, 39, 176), 2),
            "Statistics Calculator",
            0, 0, new Font("Segoe UI", Font.BOLD, 14), new Color(156, 39, 176)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Data input
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("Enter data (comma-separated):"), gbc);
        
        statsDataArea = new JTextArea(3, 20);
        statsDataArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statsDataArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        statsDataArea.setText("1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
        
        JScrollPane scrollPane = new JScrollPane(statsDataArea);
        gbc.gridy = 1;
        panel.add(scrollPane, gbc);
        
        // Statistics buttons
        JButton meanBtn = createStyledButton("Calculate Mean", new Color(156, 39, 176));
        JButton medianBtn = createStyledButton("Calculate Median", new Color(156, 39, 176));
        JButton stdDevBtn = createStyledButton("Standard Deviation", new Color(156, 39, 176));
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(meanBtn, gbc);
        gbc.gridx = 1;
        panel.add(medianBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(stdDevBtn, gbc);
        
        // Add action listeners for statistics buttons
        meanBtn.addActionListener(e -> {
            try {
                String input = "mean " + statsDataArea.getText();
                String result = performStatisticalCalculation(input);
                displayResult(input, result);
                addToHistory(input, result);
            } catch (Exception ex) {
                showError("Invalid statistics input: " + ex.getMessage());
            }
        });
        
        medianBtn.addActionListener(e -> {
            try {
                String input = "median " + statsDataArea.getText();
                String result = performStatisticalCalculation(input);
                displayResult(input, result);
                addToHistory(input, result);
            } catch (Exception ex) {
                showError("Invalid statistics input: " + ex.getMessage());
            }
        });
        
        stdDevBtn.addActionListener(e -> {
            try {
                String input = "stddev " + statsDataArea.getText();
                String result = performStatisticalCalculation(input);
                displayResult(input, result);
                addToHistory(input, result);
            } catch (Exception ex) {
                showError("Invalid statistics input: " + ex.getMessage());
            }
        });
        
        return panel;
    }
    
    private JButton createCalculatorButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(50, 40));
        button.setBackground(new Color(230, 230, 230));
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addActionListener(e -> {
            String current = inputField.getText();
            if (text.equals("=")) {
                performCalculation();
            } else {
                inputField.setText(current + text);
            }
        });
        
        return button;
    }
    
    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel resultsLabel = new JLabel("Results & Analysis");
        resultsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resultsLabel.setForeground(PRIMARY_COLOR);
        resultsLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        
        return resultsPanel;
    }
    
    private JPanel createHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(BACKGROUND_COLOR);
        historyPanel.setPreferredSize(new Dimension(250, 0));
        
        JLabel historyLabel = new JLabel("Calculation History");
        historyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        historyLabel.setForeground(PRIMARY_COLOR);
        historyLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        JButton clearHistoryBtn = createStyledButton("Clear History", new Color(231, 76, 60));
        clearHistoryBtn.addActionListener(e -> {
            calculationHistory.clear();
            historyArea.setText("Calculation History:\n\n");
        });
        
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(historyScroll, BorderLayout.CENTER);
        historyPanel.add(clearHistoryBtn, BorderLayout.SOUTH);
        
        return historyPanel;
    }
    
    private JPanel createStatusPanel() {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(PRIMARY_COLOR);
        statusPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        JLabel statusLabel = new JLabel("Ready - Select calculator type and enter your calculation");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(Color.WHITE);
        
        JLabel versionLabel = new JLabel("v1.0 | © 2024 Multi-Calculator System");
        versionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        versionLabel.setForeground(new Color(200, 200, 200));
        
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);
        
        return statusPanel;
    }
    
    private void setupEventHandlers() {
        // Calculator type change handler
        calculatorTypes.addActionListener(e -> {
            String selected = (String) calculatorTypes.getSelectedItem();
            cardLayout.show(calculatorPanel, selected);
            inputField.requestFocus();
        });
        
        // Input field enter key handler
        inputField.addActionListener(e -> performCalculation());
        
        // Button event handlers
        Component[] components = getContentPane().getComponents();
        addButtonListeners(components);
    }
    
    private void addButtonListeners(Component[] components) {
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                String text = btn.getText();
                
                if (text.equals("Calculate")) {
                    btn.addActionListener(e -> performCalculation());
                } else if (text.equals("Clear")) {
                    btn.addActionListener(e -> {
                        inputField.setText("");
                        resultArea.setText("Enter a calculation to see results here.");
                    });
                } else if (text.equals("Save Result")) {
                    btn.addActionListener(e -> saveCurrentResult());
                }
            } else if (comp instanceof Container) {
                addButtonListeners(((Container) comp).getComponents());
            }
        }
    }
    
    private void performCalculation() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            showError("Please enter a calculation.");
            return;
        }
        
        try {
            String selectedCalc = (String) calculatorTypes.getSelectedItem();
            String result = "";
            
            switch (selectedCalc) {
                case "Basic Calculator":
                    result = performBasicCalculation(input);
                    break;
                case "Scientific Calculator":
                    result = performScientificCalculation(input);
                    break;
                case "Financial Calculator":
                    result = performFinancialCalculation(input);
                    break;
                case "Unit Converter":
                    result = performUnitConversion(input);
                    break;
                case "Statistics Calculator":
                    result = performStatisticalCalculation(input);
                    break;
                default:
                    result = performBasicCalculation(input);
            }
            
            displayResult(input, result);
            addToHistory(input, result);
            
        } catch (Exception e) {
            showError("Calculation error: " + e.getMessage());
        }
    }
    
    private String performBasicCalculation(String expression) {
        try {
            expression = expression.replaceAll("\\s", "");
            double result = evaluateExpression(expression);
            DecimalFormat df = new DecimalFormat("#.##########");
            return df.format(result);
        } catch (Exception e) {
            throw new RuntimeException("Invalid expression: " + expression);
        }
    }
    
    private String performScientificCalculation(String expression) {
        try {
            expression = expression.toLowerCase().replaceAll("\\s", "");
            
            // Handle scientific functions
            if (expression.startsWith("sin(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(4, expression.length()-1));
                return String.valueOf(Math.sin(Math.toRadians(value)));
            } else if (expression.startsWith("cos(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(4, expression.length()-1));
                return String.valueOf(Math.cos(Math.toRadians(value)));
            } else if (expression.startsWith("tan(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(4, expression.length()-1));
                return String.valueOf(Math.tan(Math.toRadians(value)));
            } else if (expression.startsWith("sqrt(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(5, expression.length()-1));
                return String.valueOf(Math.sqrt(value));
            } else if (expression.startsWith("log(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(4, expression.length()-1));
                return String.valueOf(Math.log10(value));
            } else if (expression.startsWith("ln(") && expression.endsWith(")")) {
                double value = Double.parseDouble(expression.substring(3, expression.length()-1));
                return String.valueOf(Math.log(value));
            } else if (expression.equals("pi") || expression.equals("π")) {
                return String.valueOf(Math.PI);
            } else if (expression.equals("e")) {
                return String.valueOf(Math.E);
            }
            
            // Fall back to basic calculation
            return performBasicCalculation(expression);
            
        } catch (Exception e) {
            throw new RuntimeException("Invalid scientific expression: " + expression);
        }
    }
    
    private String performFinancialCalculation(String input) {
        String[] parts = input.toLowerCase().split("\\s+");
        
        try {
            if (parts.length >= 4 && parts[0].equals("loan")) {
                double principal = Double.parseDouble(parts[1]);
                double rate = Double.parseDouble(parts[2]) / 100 / 12; // Monthly rate
                int months = (int)(Double.parseDouble(parts[3]) * 12);
                
                return calculateLoanPayment(principal, rate, months);
            }
            
            if (parts.length >= 4 && parts[0].equals("investment")) {
                double principal = Double.parseDouble(parts[1]);
                double rate = Double.parseDouble(parts[2]) / 100;
                int years = Integer.parseInt(parts[3]);
                
                return calculateInvestment(principal, rate, years);
            }
            
            throw new RuntimeException("Use format: 'loan amount rate years' or 'investment amount rate years'");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in input: " + input);
        }
    }
    
    private String calculateLoanPayment(double principal, double monthlyRate, int months) {
        double payment = principal * (monthlyRate * Math.pow(1 + monthlyRate, months)) / 
                        (Math.pow(1 + monthlyRate, months) - 1);
        
        DecimalFormat df = new DecimalFormat("#.##");
        return "Monthly Payment: $" + df.format(payment) + 
               "\nTotal Interest: $" + df.format((payment * months) - principal);
    }
    
    private String calculateInvestment(double principal, double rate, int years) {
        double futureValue = principal * Math.pow(1 + rate, years);
        DecimalFormat df = new DecimalFormat("#.##");
        return "Future Value: $" + df.format(futureValue) + 
               "\nTotal Gain: $" + df.format(futureValue - principal);
    }
    
    private String performUnitConversion(String input) {
        String[] parts = input.toLowerCase().split("\\s+");
        
        try {
            if (parts.length >= 4 && parts[2].equals("to")) {
                double value = Double.parseDouble(parts[0]);
                String fromUnit = parts[1];
                String toUnit = parts[3];
                
                String conversionType = (String) conversionTypeCombo.getSelectedItem();
                double result;
                
                switch (conversionType) {
                    case "Length":
                        double meters = convertToMeters(value, fromUnit);
                        result = convertFromMeters(meters, toUnit);
                        break;
                    case "Weight":
                        double kilograms = convertToKilograms(value, fromUnit);
                        result = convertFromKilograms(kilograms, toUnit);
                        break;
                    case "Temperature":
                        result = convertTemperature(value, fromUnit, toUnit);
                        break;
                    case "Volume":
                        double liters = convertToLiters(value, fromUnit);
                        result = convertFromLiters(liters, toUnit);
                        break;
                    case "Area":
                        double squareMeters = convertToSquareMeters(value, fromUnit);
                        result = convertFromSquareMeters(squareMeters, toUnit);
                        break;
                    default:
                        throw new RuntimeException("Invalid conversion type");
                }
                
                DecimalFormat df = new DecimalFormat("#.##########");
                return value + " " + fromUnit + " = " + df.format(result) + " " + toUnit;
            }
            
            throw new RuntimeException("Use format: 'value fromUnit to toUnit' (e.g., '10 meter to foot')");
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format in input: " + input);
        }
    }
    
    private double convertToMeters(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "meter": case "m": return value;
            case "foot": case "ft": return value * 0.3048;
            case "inch": case "in": return value * 0.0254;
            case "kilometer": case "km": return value * 1000;
            case "mile": case "mi": return value * 1609.34;
            case "yard": case "yd": return value * 0.9144;
            case "centimeter": case "cm": return value * 0.01;
            default: throw new RuntimeException("Unknown length unit: " + unit);
        }
    }
    
    private double convertFromMeters(double meters, String unit) {
        switch (unit.toLowerCase()) {
            case "meter": case "m": return meters;
            case "foot": case "ft": return meters / 0.3048;
            case "inch": case "in": return meters / 0.0254;
            case "kilometer": case "km": return meters / 1000;
            case "mile": case "mi": return meters / 1609.34;
            case "yard": case "yd": return meters / 0.9144;
            case "centimeter": case "cm": return meters / 0.01;
            default: throw new RuntimeException("Unknown length unit: " + unit);
        }
    }
    
    private double convertToKilograms(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "kilogram": case "kg": return value;
            case "pound": case "lb": return value * 0.453592;
            case "ounce": case "oz": return value * 0.0283495;
            case "gram": case "g": return value * 0.001;
            default: throw new RuntimeException("Unknown weight unit: " + unit);
        }
    }
    
    private double convertFromKilograms(double kilograms, String unit) {
        switch (unit.toLowerCase()) {
            case "kilogram": case "kg": return kilograms;
            case "pound": case "lb": return kilograms / 0.453592;
            case "ounce": case "oz": return kilograms / 0.0283495;
            case "gram": case "g": return kilograms / 0.001;
            default: throw new RuntimeException("Unknown weight unit: " + unit);
        }
    }
    
    private double convertTemperature(double value, String fromUnit, String toUnit) {
        if (fromUnit.equalsIgnoreCase("celsius")) {
            if (toUnit.equalsIgnoreCase("fahrenheit")) return (value * 9/5) + 32;
            if (toUnit.equalsIgnoreCase("kelvin")) return value + 273.15;
            if (toUnit.equalsIgnoreCase("celsius")) return value;
        } else if (fromUnit.equalsIgnoreCase("fahrenheit")) {
            if (toUnit.equalsIgnoreCase("celsius")) return (value - 32) * 5/9;
            if (toUnit.equalsIgnoreCase("kelvin")) return (value - 32) * 5/9 + 273.15;
            if (toUnit.equalsIgnoreCase("fahrenheit")) return value;
        } else if (fromUnit.equalsIgnoreCase("kelvin")) {
            if (toUnit.equalsIgnoreCase("celsius")) return value - 273.15;
            if (toUnit.equalsIgnoreCase("fahrenheit")) return (value - 273.15) * 9/5 + 32;
            if (toUnit.equalsIgnoreCase("kelvin")) return value;
        }
        throw new RuntimeException("Unknown temperature unit: " + fromUnit + " or " + toUnit);
    }
    
    private double convertToLiters(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "liter": case "l": return value;
            case "gallon": case "gal": return value * 3.78541;
            case "milliliter": case "ml": return value * 0.001;
            case "cubic meter": case "m3": return value * 1000;
            default: throw new RuntimeException("Unknown volume unit: " + unit);
        }
    }
    
    private double convertFromLiters(double liters, String unit) {
        switch (unit.toLowerCase()) {
            case "liter": case "l": return liters;
            case "gallon": case "gal": return liters / 3.78541;
            case "milliliter": case "ml": return liters / 0.001;
            case "cubic meter": case "m3": return liters / 1000;
            default: throw new RuntimeException("Unknown volume unit: " + unit);
        }
    }
    
    private double convertToSquareMeters(double value, String unit) {
        switch (unit.toLowerCase()) {
            case "square meter": case "m2": return value;
            case "square foot": case "ft2": return value * 0.092903;
            case "acre": return value * 4046.86;
            case "hectare": return value * 10000;
            default: throw new RuntimeException("Unknown area unit: " + unit);
        }
    }
    
    private double convertFromSquareMeters(double squareMeters, String unit) {
        switch (unit.toLowerCase()) {
            case "square meter": case "m2": return squareMeters;
            case "square foot": case "ft2": return squareMeters / 0.092903;
            case "acre": return squareMeters / 4046.86;
            case "hectare": return squareMeters / 10000;
            default: throw new RuntimeException("Unknown area unit: " + unit);
        }
    }
    
    private String performStatisticalCalculation(String input) {
        String[] parts = input.toLowerCase().split("\\s+");
        
        if (parts[0].equals("mean") || parts[0].equals("average")) {
            double[] numbers = parseNumberList(input.substring(parts[0].length()).trim());
            double sum = 0;
            for (double num : numbers) sum += num;
            DecimalFormat df = new DecimalFormat("#.##########");
            return "Mean: " + df.format(sum / numbers.length);
        }
        
        if (parts[0].equals("median")) {
            double[] numbers = parseNumberList(input.substring(parts[0].length()).trim());
            java.util.Arrays.sort(numbers);
            double median;
            if (numbers.length % 2 == 0) {
                median = (numbers[numbers.length/2 - 1] + numbers[numbers.length/2]) / 2;
            } else {
                median = numbers[numbers.length/2];
            }
            DecimalFormat df = new DecimalFormat("#.##########");
            return "Median: " + df.format(median);
        }
        
        if (parts[0].equals("stddev") || parts[0].equals("stdev")) {
            double[] numbers = parseNumberList(input.substring(parts[0].length()).trim());
            double mean = 0;
            for (double num : numbers) mean += num;
            mean /= numbers.length;
            
            double variance = 0;
            for (double num : numbers) {
                variance += Math.pow(num - mean, 2);
            }
            variance /= numbers.length;
            
            DecimalFormat df = new DecimalFormat("#.##########");
            return "Standard Deviation: " + df.format(Math.sqrt(variance));
        }
        
        // Default: assume it's a list of numbers to analyze
        double[] numbers = parseNumberList(input);
        return analyzeNumbers(numbers);
    }
    
    private double[] parseNumberList(String input) {
        String[] parts = input.replaceAll("[,;]", " ").trim().split("\\s+");
        List<Double> numbers = new ArrayList<>();
        
        for (String part : parts) {
            try {
                numbers.add(Double.parseDouble(part));
            } catch (NumberFormatException e) {
                // Skip non-numeric parts
            }
        }
        
        if (numbers.isEmpty()) {
            throw new RuntimeException("No valid numbers found in input");
        }
        
        return numbers.stream().mapToDouble(Double::doubleValue).toArray();
    }
    
    private String analyzeNumbers(double[] numbers) {
        DecimalFormat df = new DecimalFormat("#.##########");
        
        // Calculate mean
        double sum = 0;
        for (double num : numbers) sum += num;
        double mean = numbers.length > 0 ? sum / numbers.length : 0;
        
        // Calculate median
        double[] sorted = numbers.clone();
        java.util.Arrays.sort(sorted);
        double median;
        if (sorted.length % 2 == 0 && sorted.length > 0) {
            median = (sorted[sorted.length/2 - 1] + sorted[sorted.length/2]) / 2;
        } else if (sorted.length > 0) {
            median = sorted[sorted.length/2];
        } else {
            median = 0;
        }
        
        // Calculate standard deviation
        double variance = 0;
        if (numbers.length > 0) {
            for (double num : numbers) {
                variance += Math.pow(num - mean, 2);
            }
            variance /= numbers.length;
        }
        double stdDev = Math.sqrt(variance);
        
        // Find min and max
        double min = sorted.length > 0 ? sorted[0] : 0;
        double max = sorted.length > 0 ? sorted[sorted.length - 1] : 0;
        
        StringBuilder result = new StringBuilder();
        result.append("Statistical Analysis:\n");
        result.append("Count: ").append(numbers.length).append("\n");
        result.append("Mean: ").append(df.format(mean)).append("\n");
        result.append("Median: ").append(df.format(median)).append("\n");
        result.append("Standard Deviation: ").append(df.format(stdDev)).append("\n");
        result.append("Minimum: ").append(df.format(min)).append("\n");
        result.append("Maximum: ").append(df.format(max)).append("\n");
        result.append("Range: ").append(df.format(max - min));
        
        return result.toString();
    }
    
    // Simple expression evaluator for basic arithmetic
    private double evaluateExpression(String expression) {
        return new ExpressionEvaluator().evaluate(expression);
    }
    
    private void displayResult(String input, String result) {
        resultArea.setText("Input: " + input + "\n\n" + 
                          "Result: " + result + "\n\n" +
                          "Calculator Type: " + calculatorTypes.getSelectedItem() + "\n" +
                          "Timestamp: " + java.time.LocalDateTime.now().format(
                              java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                          ));
    }
    
    private void addToHistory(String input, String result) {
        String historyEntry = input + " = " + result;
        calculationHistory.add(historyEntry);
        
        StringBuilder historyText = new StringBuilder("Calculation History:\n\n");
        for (int i = calculationHistory.size() - 1; i >= 0 && i >= calculationHistory.size() - 10; i--) {
            historyText.append(calculationHistory.get(i)).append("\n");
        }
        
        historyArea.setText(historyText.toString());
    }
    
    private void saveCurrentResult() {
        String currentResult = resultArea.getText();
        if (currentResult.trim().isEmpty() || currentResult.contains("Enter a calculation")) {
            showError("No result to save. Please perform a calculation first.");
            return;
        }
        
        try (java.io.FileWriter writer = new java.io.FileWriter("calculation_results.txt", true)) {
            writer.write("=== Saved Result ===\n");
            writer.write("Date: " + java.time.LocalDateTime.now() + "\n");
            writer.write(currentResult + "\n\n");
            
            JOptionPane.showMessageDialog(this, 
                "Result saved to calculation_results.txt", 
                "Save Successful", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (java.io.IOException e) {
            showError("Failed to save result: " + e.getMessage());
        }
    }
    
    private void showError(String message) {
        resultArea.setText("Error: " + message + "\n\n" +
                          "Please check your input and try again.\n\n" +
                          "Examples:\n" +
                          "Basic: 2 + 3 * 4\n" +
                          "Scientific: sin(30) or sqrt(16)\n" +
                          "Financial: loan 100000 5 30\n" +
                          "Unit: 10 meter to foot\n" +
                          "Statistics: mean 1,2,3,4,5");
        
        JOptionPane.showMessageDialog(this, message, "Calculation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void setDefaultCalculator() {
        calculatorTypes.setSelectedIndex(0);
        cardLayout.show(calculatorPanel, "Basic Calculator");
        inputField.requestFocus();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MultiCalculatorSystem().setVisible(true);
        });
    }
}


class ExpressionEvaluator {
    private int pos = -1;
    private int ch;
    private String expression;
    
    public double evaluate(String expression) {
        this.expression = expression.replaceAll("\\s", "");
        this.pos = -1;
        nextChar();
        double result = parseExpression();
        if (pos < this.expression.length()) {
            throw new RuntimeException("Unexpected character: " + (char)ch);
        }
        return result;
    }
    
    private void nextChar() {
        ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    }
    
    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }
    
    private double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if (eat('+')) x += parseTerm();
            else if (eat('-')) x -= parseTerm();
            else return x;
        }
    }
    
    private double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if (eat('*')) x *= parseFactor();
            else if (eat('/')) x /= parseFactor();
            else return x;
        }
    }
    
    private double parseFactor() {
        if (eat('+')) return parseFactor();
        if (eat('-')) return -parseFactor();
        
        double x;
        int startPos = this.pos;
        if (eat('(')) {
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(expression.substring(startPos, this.pos));
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }
        
        if (eat('^')) x = Math.pow(x, parseFactor());
        
        return x;
    }
}