import gui.JButtonEx;
import gui.Toast;
import separator.FieldSeparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class EchoClient extends JFrame {
    private final String SERVER_ADDR = "192.168.11.6";
    private final int SERVER_PORT = 8083;

    private String ss = "04010100020300020111041000393939393037383930303031313931380D0414003030303030303030303130303532303920202020FA030C003738303430363736333220201004040084000000F4030400F457A25C35040600310491E563D70E0404000400000012040400480000001E04010001FC0302000F021F04010001B3040C00373830303030303030303031FD030F008FA5A2A02020313131203232322033B9040100022304450006041B003434343434342035353535353535353535203636363636363636363704010064FF03030004DCCD130402000F02BE04010001BC04010001AF04010004B004010030530401003039040000BF040000C0040000C1040000070402000F0281062F1ED68B19B60000";

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    JButton [] btnCmd = {
            new JButtonEx("00",00,0,0),
            new JButtonEx("01",01,1,1),
            new JButtonEx("02",02,2,5),
            new JButtonEx("03",03,0,0),
            new JButtonEx("04",04,0,0),
          //  new JButtonEx("Parce",-2,0,0),
            new JButtonEx("Connect",-1,0,0),
    };

    public EchoClient() {
        prepareGUI();
    }

    static public String toHex(byte b) {
        String result = "";
        Integer I = new Integer(((b) << 24) >>> 24);
        int i = I.intValue();

        if (i < (byte) 16) {
            result = "0" + Integer.toString(i, 16);
        } else {
            result = Integer.toString(i, 16);
        }
        return result.toUpperCase();
    }

    static public String toHex(byte[] b, int len) {
        if (b == null) {
            return "";
        }
        StringBuffer s = new StringBuffer("");
        int i;
        for (i = 0; i < len; i++) {
            s.append(toHex(b[i]));
            s.append(" ");
        }
        return s.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private  void fileRead(){
        byte[] buffer = hexStringToByteArray(ss);
        FieldSeparator fs = null;

        try {
            fs = new FieldSeparator(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chatArea.append("Поля:\n");
        for (String s : fs.getFieldArray()) {
            chatArea.append(s + "\n");
        }
    }

    private  void fileRead1(){
        byte[] buffer ;

        try(FileInputStream fin=new FileInputStream("c:\\111\\222\\hypertrm\\commands\\c19.txt"))
        {
            buffer = new byte[fin.available()];
            fin.read(buffer, 0, fin.available());
            FieldSeparator fs = null;
            try {
                fs = new FieldSeparator(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            chatArea.append("Поля:\n");
            for (String s : fs.getFieldArray()) {
                chatArea.append(s + "\n");
            }



        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream( socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                byte [] tempArray = new byte[1024];
                int bytesToRead=1;
                int totalBytes=0;
                int cmdInBytes=0;
                int tempBytes;

                while (true) {

                    try {
                        tempBytes = in.read(tempArray,totalBytes,bytesToRead-totalBytes);
                    } catch (IOException e) {
                         System.out.println(e.getMessage());
                         break;
                    }

                    totalBytes+=tempBytes;


                    if (cmdInBytes == 0) {
                        if (totalBytes > 2) {

                            if (tempArray[0] != 4){
                                totalBytes=0;
                                bytesToRead=1;
                                continue;
                            }
                            cmdInBytes = (tempArray[1]&0xff)+(((tempArray[2]&0xff))<<8);

                            bytesToRead=cmdInBytes + 3;
                        } else
                        {
                            bytesToRead+=tempBytes;
                        }
                    }else {
                        if(totalBytes == cmdInBytes + 3 ) {

                            chatArea.append("Всего получено " +totalBytes +"\n");
                            chatArea.append(toHex(tempArray, totalBytes));
                            chatArea.append("\n");

                            if (tempArray[3]==0) {
                                FieldSeparator fs = new FieldSeparator(tempArray);
                                chatArea.append("Поля:\n");
                                for (String s : fs.getFieldArray()) {
                                    chatArea.append(s + "\n");
                                }
                            }

                            bytesToRead = 1;
                            totalBytes = 0;
                            cmdInBytes = 0;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage( byte[] message) {
        if (message.length > 0) {
            try {
                out.write(message);
                //msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
            }
        }
    }

    private int getInputField(){
        int i;
        try {
            i = Integer.parseInt(msgInputField.getText());
        } catch (Exception e){
            i=0;
        }
        return i;
    }

    public void prepareGUI() {
        // Параметры окна
        setBounds(300, 100, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Текстовое поле для вывода сообщений
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Нижняя панель с полем для ввода сообщений и кнопкой отправки сообщений
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel bottomGridPanel = new JPanel(new GridLayout(2,0,5,5));
        JPanel bottomTextPanel = new JPanel(new FlowLayout());


        for (int i =0 ;i< btnCmd.length;i++) {
            bottomGridPanel.add(btnCmd[i], i);
        }

        bottomPanel.add(bottomGridPanel , BorderLayout.EAST);

        bottomTextPanel.add(new JLabel("Параметры:"));
        msgInputField = new JTextField("",10);
        bottomTextPanel.add(msgInputField );

        bottomPanel.add(bottomTextPanel , BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);

        ActionListener actionListener = e -> {

            int indexCmd;
            for (indexCmd =0;indexCmd < btnCmd.length;indexCmd++){
                if (e.getSource()  == btnCmd[indexCmd]) break;
            }


            if (((JButtonEx) btnCmd[indexCmd]).getCmd() == -2){
                fileRead();
                return;
            }

            if ((indexCmd == btnCmd.length-1) || (btnCmd[btnCmd.length-1].isEnabled())) {
                chatArea.append("Connecting...");
                try {
                    openConnection();
                    chatArea.append("done.\n");
                    btnCmd[ btnCmd.length-1].setEnabled(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    chatArea.append("fail.\n");
                }
                if (indexCmd == btnCmd.length-1) return;
            }


            if (indexCmd < btnCmd.length){

                JButtonEx jb = (JButtonEx ) btnCmd[indexCmd];

                byte [] cmdArray = new byte[ jb.getInSize()+4];
                cmdArray[0] = 0x04;
                cmdArray[1] = (byte) (jb.getInSize()+1);
                cmdArray[2] = 0;
                cmdArray[3] = (byte) jb.getCmd();

                switch (jb.getParam()){
                    case 1:
                            cmdArray[4] = (byte) getInputField();
                            break;
                    case 2:
                            int val  = (byte) getInputField();
                            cmdArray[4] = 0;
                            cmdArray[5] = (byte)((val)&0xff);
                            cmdArray[6] = (byte)((val>>8)&0xff);
                            cmdArray[7] = (byte)((val>>16)&0xff);
                            cmdArray[8] = (byte)((val>>24)&0xff);
                        break;
                }

                sendMessage(cmdArray);
                chatArea.append(toHex(cmdArray, jb.getInSize()+4));
                chatArea.append("\n");
            }

/*
            // create a toast message
            Toast t = new Toast(String.valueOf(i), 700, 400);
            // call the method
            t.showToast();
*/
        };

        for (int i =0 ;i< btnCmd.length;i++) {
            btnCmd[i].addActionListener(actionListener);
        }

        // Настраиваем действие на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                closeConnection();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EchoClient();
            }
        });
    }
}
