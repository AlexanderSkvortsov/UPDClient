package gui;

import javax.swing.*;

public class JButtonEx extends JButton {
    int cmd;
    int param;
    int inSize;

    public JButtonEx(String text, int cmd, int param, int inSize) {
        super(text);
        this.cmd = cmd;
        this.param = param;
        this.inSize = inSize;
    }

    public int getCmd() {
        return cmd;
    }

    public int getParam() {
        return param;
    }

    public int getInSize() {
        return inSize;
    }

}
