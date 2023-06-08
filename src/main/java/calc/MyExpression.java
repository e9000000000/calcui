package calc;

import java.lang.RuntimeException;
import org.mariuszgromada.math.mxparser.*;

public class MyExpression {
    private String defaultText = "0";
    private String text;

    public MyExpression() {
        text = defaultText;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        text = newText;
    }

    public void action(String name, String text) {
        if (name == "add") {
            if (text == null) {
                throw new RuntimeException("text is null, can't add it");
            }

            add(text);
        } else if (name == "backspace") {
            backspace();
        } else if (name == "clear") {
            clear();
        } else if (name == "evaluate") {
            evaluate();
        } else {
            throw new RuntimeException("unknown action: " + name);
        }
    }

    private void add(String symbol) {
        if (text.equals(defaultText)) {
            text = symbol;
        } else {
            text += symbol;
        }
    }

    private void backspace() {
        if (text == null || text.length() == 0) {
            return;
        }
        if (text.length() == 1) {
            text = defaultText;
            return;
        }
        text = text.substring(0, text.length() - 1);
    }

    private void clear() {
        text = defaultText;
    }

    private void evaluate() {
        Expression e = new Expression(text);
        text = Double.toString(e.calculate());
    }
}