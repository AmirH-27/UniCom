package apj.unicom.service;

import apj.unicom.data.LoginFormPosition;

import javax.swing.*;
import java.awt.*;

public interface PositionBoundService<T extends JComponent> {
    void setPosition(T component, LoginFormPosition position);
}
