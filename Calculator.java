import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private final JTextField display;
    private final StringBuilder currentInput;
    private double lastResult;
    private String lastOperator;

    public Calculator() {
        setTitle("계산기");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 메뉴바 추가
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // New 메뉴 아이템 생성
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New 메뉴 아이템 클릭 시 동작
                System.out.println("New 메뉴 아이템 클릭됨");
            }
        });

        // Open 메뉴 아이템 생성
        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open 메뉴 아이템 클릭 시 동작
                System.out.println("Open 메뉴 아이템 클릭됨");
            }
        });

        // File 메뉴에 메뉴 아이템 추가
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);

        // 디스플레이 추가
        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        add(display, BorderLayout.NORTH);

        // 버튼 패널 추가
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        currentInput = new StringBuilder();
        lastResult = 0;
        lastOperator = "=";

        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if ("/*-+".contains(command)) {
            calculate(Double.parseDouble(currentInput.toString()));
            lastOperator = command;
            currentInput.setLength(0);
        } else if ("=".equals(command)) {
            calculate(Double.parseDouble(currentInput.toString()));
            lastOperator = "=";
            currentInput.setLength(0);
        } else if ("C".equals(command)) {
            currentInput.setLength(0);
            lastResult = 0;
            lastOperator = "=";
            display.setText("");
        }
    }

    private void calculate(double number) {
        switch (lastOperator) {
            case "+" -> lastResult += number;
            case "-" -> lastResult -= number;
            case "*" -> lastResult *= number;
            case "/" -> lastResult /= number;
            case "=" -> lastResult = number;
        }
        display.setText(String.valueOf(lastResult));
    }
}
