package apj.unicom.data;

public enum FormPosition {

    FORM_POSITION(0, 0, 800, 600),

    TITLE_LABEL(400, 100, 150, 50),
    STUDENT_ID_LABEL(200, 200, 150, 20),
    LOGIN_PASSWORD_LABEL(200, 250, 150, 20),
    USER_NAME_LABEL(200, 250, 150, 20),
    REGISTER_PASSWORD_LABEL(200, 300, 150, 20),
    CONFIRM_PASSWORD_LABEL(200, 350, 150, 20),
    PRIVACY_LABEL(200, 400, 150, 20),

    STUDENT_ID_TEXT_FIELD(400, 200, 150, 20),
    LOGIN_PASSWORD_TEXT_FIELD(400, 250, 150, 20),
    USER_NAME_TEXT_FIELD(400, 250, 150, 20),
    REGISTER_PASSWORD_TEXT_FIELD(400, 300, 150, 20),
    CONFIRM_PASSWORD_TEXT_FIELD(400, 350, 150, 20),

    PUBLIC_RADIO_BUTTON(400, 400, 70, 20),
    PRIVATE_RADIO_BUTTON(475, 400, 75, 20),

    NEXT_BUTTON(400, 250, 60, 20),
    LOGIN_BUTTON(400, 300, 60, 20),
    REGISTER_BUTTON(400, 450, 60, 20),
    BACK_BUTTON(700, 540, 60, 20);

    final public int x;
    final public int y;
    final public int width;
    final public int height;

    FormPosition(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
