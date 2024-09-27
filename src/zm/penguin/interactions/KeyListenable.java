package zm.penguin.interactions;

import java.awt.event.KeyEvent;

public interface KeyListenable {
    default void keyPressed(char c, int keycode) { keyPressed(c, keycode, false); }
    void keyPressed(char c, int keycode, boolean shift);
    void keyReleased(char c, int keycode);

    static char shiftCharacter(char c, int keyCode) {
        if (Character.isAlphabetic(c)) {
            return Character.toUpperCase(c);
        }
        return switch (keyCode) {
            case KeyEvent.VK_1 -> '!';
            case KeyEvent.VK_2 -> '@';
            case KeyEvent.VK_3 -> '#';
            case KeyEvent.VK_4 -> '$';
            case KeyEvent.VK_5 -> '%';
            case KeyEvent.VK_6 -> '^';
            case KeyEvent.VK_7 -> '&';
            case KeyEvent.VK_8 -> '*';
            case KeyEvent.VK_9 -> '(';
            case KeyEvent.VK_0 -> ')';
            case KeyEvent.VK_MINUS -> '_';
            case KeyEvent.VK_EQUALS -> '+';
            case KeyEvent.VK_OPEN_BRACKET -> '{';
            case KeyEvent.VK_CLOSE_BRACKET -> '}';
            case KeyEvent.VK_SEMICOLON -> ':';
            case KeyEvent.VK_QUOTE -> '"';
            case KeyEvent.VK_COMMA -> '<';
            case KeyEvent.VK_PERIOD -> '>';
            case KeyEvent.VK_SLASH -> '?';
            case KeyEvent.VK_BACK_SLASH -> '|';
            default -> c; // Return the original character if no shift mapping is found
        };
    }

    static String shiftCharacterToString(char c, int keyCode) {
        if (Character.isAlphabetic(c)) {
            return String.valueOf(Character.toUpperCase(c));
        }
        return switch (keyCode) {
            case KeyEvent.VK_1 -> "!";
            case KeyEvent.VK_2 -> "@";
            case KeyEvent.VK_3 -> "#";
            case KeyEvent.VK_4 -> "$";
            case KeyEvent.VK_5 -> "%";
            case KeyEvent.VK_6 -> "^";
            case KeyEvent.VK_7 -> "&";
            case KeyEvent.VK_8 -> "*";
            case KeyEvent.VK_9 -> "(";
            case KeyEvent.VK_0 -> ")";
            case KeyEvent.VK_MINUS -> "_";
            case KeyEvent.VK_EQUALS -> "+";
            case KeyEvent.VK_OPEN_BRACKET -> "{";
            case KeyEvent.VK_CLOSE_BRACKET -> "}";
            case KeyEvent.VK_SEMICOLON -> ":";
            case KeyEvent.VK_QUOTE -> "\"";
            case KeyEvent.VK_COMMA -> "<";
            case KeyEvent.VK_PERIOD -> ">";
            case KeyEvent.VK_SLASH -> "?";
            case KeyEvent.VK_BACK_SLASH -> "|";
            default -> String.valueOf(c);
        };
    }
}
