package calc;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.scene.image.*;

public class Ui {
    private static int buttonsInARow = 10;
    private static int width = 444;
    private static int height = 333;
    private static int paddingLeftRight = 15;
    private static int paddingTopBotton = 12;
    private static Btn[] inputButtons = {
        new Btn("1", "add"),
        new Btn("2", "add"),
        new Btn("3", "add"),
        new Btn("4", "add"),
        new Btn("5", "add"),
        new Btn("6", "add"),
        new Btn("7", "add"),
        new Btn("8", "add"),
        new Btn("9", "add"),
        new Btn("0", "add"),
        new Btn(".", "add"),
        new Btn("+", "add"),
        new Btn("-", "add"),
        new Btn("*", "add"),
        new Btn("/", "add"),
        new Btn("^", "add"),
        new Btn("(", "add"),
        new Btn(")", "add"),
        new Btn("<", "backspace"),
        new Btn("C", "clear"),
        new Btn("=", "evaluate"),
    };

    private TextField expressionInput;
    private MyExpression expression;

    public Ui() {
        expressionInput = new TextField();
        expressionInput.setStyle("-fx-font-size: 1.5em");
        expression = new MyExpression();
        expressionInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != expression.getText())
                expression.setText(newValue);
        });
        syncExpression();
    }

    private void syncExpression() {
        expressionInput.setText(expression.getText());
    }

    private EventHandler createButtonHandler(String action) {
        EventHandler handler = new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                expression.action(action, ((Button)e.getTarget()).getText());
                syncExpression();
            }
        };
        return handler;
    }

    private GridPane createButtonsBox() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        for (int i=0; i<=buttonsInARow-1; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            pane.getColumnConstraints().add(cc);
        }
        for (int j = 0; j <= (int)Math.ceil((double)inputButtons.length / (double)(buttonsInARow-1)); j++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            pane.getRowConstraints().add(rc);
        }

        int x = 0;
        int y = 0;

        for (Btn btnInfo : inputButtons) {
            Button btn = new Button(btnInfo.text);
            btn.setMinSize(30d, 30d);
            btn.setMaxSize(300d, 300d);
            btn.setOnAction(createButtonHandler(btnInfo.action));
            btn.setStyle("-fx-font-size: 1.5em");
            pane.add(btn, x, y);
            if (++x >= buttonsInARow) {
                y++;
                x = 0;
            }
        }

        return pane;
    }

    public Scene build() {
        Image image = new Image("/krotovuha.jpg", true);
        BackgroundImage bImage = new BackgroundImage(image, null, null, BackgroundPosition.CENTER, null);
        // imageView.setImage(image);
        // imageView.setMaxWidth(2000);
        // imageView.setMaxHeight(2000);

        GridPane buttons = createButtonsBox();
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(paddingTopBotton, paddingLeftRight, paddingTopBotton, paddingLeftRight));
        mainPane.setBackground(new Background(bImage));
        mainPane.setTop(expressionInput);
        mainPane.setBottom(buttons);
        Scene scene = new Scene(mainPane, width, height);
        return scene;
    }
}