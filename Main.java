import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            /* Frame setup */
            JFrame frame = new JFrame("Bulletin Board Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(910, 560);
            frame.setLayout(null);

            int leftX = 20;
            int width = 350;

            /* Connection */
            JLabel ipLabel = new JLabel("Server IP");
            ipLabel.setBounds(leftX, 15, 80, 25);

            JTextField ipField = new JTextField("127.0.0.1");
            ipField.setBounds(leftX + 80, 15, 120, 25);

            JLabel portLabel = new JLabel("Port");
            portLabel.setBounds(leftX + 210, 15, 40, 25);

            JTextField portField = new JTextField("4554");
            portField.setBounds(leftX + 250, 15, 60, 25);

            JButton connectBtn = new JButton("Connect");
            connectBtn.setBounds(leftX, 45, 150, 30);

            JButton disconnectBtn = new JButton("Disconnect");
            disconnectBtn.setBounds(leftX + 170, 45, 150, 30);
            disconnectBtn.setEnabled(false);

            /* POST */
            JLabel postTitle = new JLabel("POST");
            postTitle.setBounds(leftX, 90, 100, 25);

            JLabel postXLabel = new JLabel("X");
            postXLabel.setBounds(leftX, 120, 20, 25);

            JTextField postX = new JTextField();
            postX.setBounds(leftX + 20, 120, 40, 25);

            JLabel postYLabel = new JLabel("Y");
            postYLabel.setBounds(leftX + 70, 120, 20, 25);

            JTextField postY = new JTextField();
            postY.setBounds(leftX + 90, 120, 40, 25);

            JComboBox<String> colorBox =
                    new JComboBox<>(new String[]{"red", "white", "green", "yellow"});
            colorBox.setBounds(leftX + 140, 120, 120, 25);

            JTextArea msgArea = new JTextArea();
            JScrollPane msgScroll = new JScrollPane(msgArea);
            msgScroll.setBounds(leftX, 150, width, 60);

            JButton postBtn = new JButton("Send POST");
            postBtn.setBounds(leftX, 220, width, 30);
            postBtn.setEnabled(false);

            /* GET */
            JLabel getTitle = new JLabel("GET");
            getTitle.setBounds(leftX, 260, 100, 25);

            JCheckBox colorCheck = new JCheckBox("color");
            colorCheck.setBounds(leftX, 290, 70, 25);

            JComboBox<String> getColorBox =
                    new JComboBox<>(new String[]{"red", "white", "green", "yellow"});
            getColorBox.setBounds(leftX + 80, 290, 120, 25);

            JCheckBox containsCheck = new JCheckBox("contains");
            containsCheck.setBounds(leftX, 320, 90, 25);

            JLabel containsXLabel = new JLabel("X");
            containsXLabel.setBounds(leftX + 95, 320, 15, 25);

            JTextField containsX = new JTextField();
            containsX.setBounds(leftX + 110, 320, 40, 25);

            JLabel containsYLabel = new JLabel("Y");
            containsYLabel.setBounds(leftX + 155, 320, 15, 25);

            JTextField containsY = new JTextField();
            containsY.setBounds(leftX + 170, 320, 40, 25);

            JCheckBox refersCheck = new JCheckBox("refersTo");
            refersCheck.setBounds(leftX, 350, 90, 25);

            JTextField refersField = new JTextField();
            refersField.setBounds(leftX + 95, 350, 255, 25);

            JButton getBtn = new JButton("Send GET");
            getBtn.setBounds(leftX, 380, width, 30);
            getBtn.setEnabled(false);

            /* PIN / UNPIN */
            JLabel pinTitle = new JLabel("PIN / UNPIN");
            pinTitle.setBounds(leftX, 420, 100, 25);

            JLabel pinXLabel = new JLabel("X");
            pinXLabel.setBounds(leftX, 450, 20, 25);

            JTextField pinX = new JTextField();
            pinX.setBounds(leftX + 20, 450, 40, 25);

            JLabel pinYLabel = new JLabel("Y");
            pinYLabel.setBounds(leftX + 70, 450, 20, 25);

            JTextField pinY = new JTextField();
            pinY.setBounds(leftX + 90, 450, 40, 25);

            JButton pinBtn = new JButton("PIN");
            pinBtn.setBounds(leftX + 140, 450, 90, 25);
            pinBtn.setEnabled(false);

            JButton unpinBtn = new JButton("UNPIN");
            unpinBtn.setBounds(leftX + 240, 450, 110, 25);
            unpinBtn.setEnabled(false);

            /* CLEAR / SHAKE */
            JButton clearBtn = new JButton("CLEAR");
            clearBtn.setBounds(leftX, 480, 170, 30);
            clearBtn.setEnabled(false);

            JButton shakeBtn = new JButton("SHAKE");
            shakeBtn.setBounds(leftX + 180, 480, 170, 30);
            shakeBtn.setEnabled(false);

            /* Output board */
            JTextArea output = new JTextArea();
            output.setEditable(false);

            JScrollPane outScroll = new JScrollPane(output);
            outScroll.setBounds(400, 15, 470, 495);

            /* Enable and disable helpers */
            Runnable enableCommands = () -> {
                connectBtn.setEnabled(false);
                disconnectBtn.setEnabled(true);
                postBtn.setEnabled(true);
                getBtn.setEnabled(true);
                pinBtn.setEnabled(true);
                unpinBtn.setEnabled(true);
                clearBtn.setEnabled(true);
                shakeBtn.setEnabled(true);
            };

            Runnable disableCommands = () -> {
                connectBtn.setEnabled(true);
                disconnectBtn.setEnabled(false);
                postBtn.setEnabled(false);
                getBtn.setEnabled(false);
                pinBtn.setEnabled(false);
                unpinBtn.setEnabled(false);
                clearBtn.setEnabled(false);
                shakeBtn.setEnabled(false);
            };

            /* Button actions */
            connectBtn.addActionListener(e -> {
                output.append("CONNECT " + ipField.getText() + ":" + portField.getText() + "\n\n");
                enableCommands.run();
            });

            disconnectBtn.addActionListener(e -> {
                output.append("DISCONNECT\n\n");
                disableCommands.run();
            });

            postBtn.addActionListener(e ->
                    output.append("POST " + postX.getText() + " " + postY.getText() + " "
                            + colorBox.getSelectedItem() + " " + msgArea.getText() + "\n\n"));

            getBtn.addActionListener(e -> {
                String cmd = "GET";
                if (colorCheck.isSelected()) cmd += " color " + getColorBox.getSelectedItem();
                if (containsCheck.isSelected())
                    cmd += " contains " + containsX.getText() + " " + containsY.getText();
                if (refersCheck.isSelected())
                    cmd += " refersTo " + refersField.getText();
                output.append(cmd + "\n\n");
            });

            pinBtn.addActionListener(e ->
                    output.append("PIN " + pinX.getText() + " " + pinY.getText() + "\n\n"));

            unpinBtn.addActionListener(e ->
                    output.append("UNPIN " + pinX.getText() + " " + pinY.getText() + "\n\n"));

            clearBtn.addActionListener(e -> output.append("CLEAR\n\n"));
            shakeBtn.addActionListener(e -> output.append("SHAKE\n\n"));

            /* Add components */
            frame.add(ipLabel);
            frame.add(ipField);
            frame.add(portLabel);
            frame.add(portField);
            frame.add(connectBtn);
            frame.add(disconnectBtn);

            frame.add(postTitle);
            frame.add(postXLabel);
            frame.add(postX);
            frame.add(postYLabel);
            frame.add(postY);
            frame.add(colorBox);
            frame.add(msgScroll);
            frame.add(postBtn);

            frame.add(getTitle);
            frame.add(colorCheck);
            frame.add(getColorBox);
            frame.add(containsCheck);
            frame.add(containsXLabel);
            frame.add(containsX);
            frame.add(containsYLabel);
            frame.add(containsY);
            frame.add(refersCheck);
            frame.add(refersField);
            frame.add(getBtn);

            frame.add(pinTitle);
            frame.add(pinXLabel);
            frame.add(pinX);
            frame.add(pinYLabel);
            frame.add(pinY);
            frame.add(pinBtn);
            frame.add(unpinBtn);

            frame.add(clearBtn);
            frame.add(shakeBtn);
            frame.add(outScroll);

            frame.setVisible(true);
        });
    }
}
