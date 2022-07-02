package apj.unicom.implement.service;

import apj.unicom.data.FormPosition;
import apj.unicom.service.PositionBoundService;

import javax.swing.*;

public class PositionBoundServiceImp<T extends JComponent> implements PositionBoundService<T> {
    @Override
    public void setPosition(T component, FormPosition position) {
        component.setBounds(position.x, position.y, position.width, position.height);
    }
}
