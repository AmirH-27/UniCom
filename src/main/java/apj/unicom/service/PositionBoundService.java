package apj.unicom.service;

import apj.unicom.data.FormPosition;

import javax.swing.*;

public interface PositionBoundService<T extends JComponent> {
    void setPosition(T component, FormPosition position);
}
