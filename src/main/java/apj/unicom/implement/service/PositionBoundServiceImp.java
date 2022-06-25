package apj.unicom.implement.service;

import apj.unicom.data.LoginFormPosition;
import apj.unicom.service.PositionBoundService;

import javax.swing.*;
import java.awt.*;

public class PositionBoundServiceImp<T extends JComponent> implements PositionBoundService<T> {
    @Override
    public void setPosition(T component, LoginFormPosition position) {
        component.setBounds(position.x, position.y, position.width, position.height);
    }
}
