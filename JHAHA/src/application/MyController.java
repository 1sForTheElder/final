package application;

import static org.reactfx.EventStreams.changesOf;
import static org.reactfx.EventStreams.merge;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import org.fxmisc.undo.UndoManager;
import org.fxmisc.undo.UndoManagerFactory;
import org.reactfx.Change;
import org.reactfx.EventStream;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txt;

    @FXML
    private AnchorPane ana;

    @FXML
    private Label lbl;

    @FXML
    private Button btn_redo;

    @FXML
    private Button btn_undo;

    @FXML
    void click_undo(ActionEvent event) {

    }

    @FXML
    void click_redo(ActionEvent event) {

    }
    private abstract class CircleChange<T> {
        protected final T oldValue;
        protected final T newValue;

        protected CircleChange(T oldValue, T newValue) {
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        abstract void redo();
        abstract CircleChange<T> invert();

        Optional<CircleChange<?>> mergeWith(CircleChange<?> other) {
            // don't merge changes by default
            return Optional.empty();
        }

        @Override
        public int hashCode() {
            return Objects.hash(oldValue, newValue);
        }
    };
    private class TxTChange extends CircleChange<String> {
        public TxTChange(String oldValue, String newValue) {
            super(oldValue, newValue);
        }
        public TxTChange(Change<String> c) {
            super(c.getOldValue().toString(), c.getNewValue().toString());
        }
//        @Override void redo() { centerY.setValue(newValue); }
        @Override void redo() { System.out.println("haha");; }
        @Override TxTChange invert() { return new TxTChange(newValue, oldValue); }
        @Override Optional<CircleChange<?>> mergeWith(CircleChange<?> other) {
            if(other instanceof TxTChange) {
                return Optional.of(new TxTChange(oldValue, ((TxTChange) other).newValue));
            } else {
                return Optional.empty();
            }
        }
        
        @Override
        public boolean equals(Object other) {
            if(other instanceof TxTChange) {
                TxTChange that = (TxTChange) other;
                return Objects.equals(this.oldValue, that.oldValue)
                    && Objects.equals(this.newValue, that.newValue);
            } else {
                return false;
            }
        }
    }

    
    private final EventStream<CircleChange<?>> changes;
    private final UndoManager<CircleChange<?>> undoManager;

    {
//    	txt.promptTextProperty().bind(txt.promptTextProperty());

        EventStream<TxTChange> colorChanges = changesOf(lbl.textProperty()).map(c -> new TxTChange(c));

        changes = merge(colorChanges);

        undoManager = UndoManagerFactory.unlimitedHistoryUndoManager(
                    changes, // stream of changes to observe
                    c -> c.invert(), // function to invert a change
                    c -> c.redo(), // function to undo a change
                    (c1, c2) -> c1.mergeWith(c2)); // function to merge two changes

        btn_undo.disableProperty().bind(undoManager.undoAvailableProperty().map(x -> !x));
        btn_redo.disableProperty().bind(undoManager.redoAvailableProperty().map(x -> !x));
        btn_undo.setOnAction(evt -> undoManager.undo());
        btn_redo.setOnAction(evt -> undoManager.redo());

    }
    
    @FXML
    void initialize() {
    	System.out.println(txt.textProperty());
    	System.out.println(txt.promptTextProperty());
        assert txt != null : "fx:id=\"txt\" was not injected: check your FXML file 'AAA.fxml'.";
        assert ana != null : "fx:id=\"ana\" was not injected: check your FXML file 'AAA.fxml'.";
        assert lbl != null : "fx:id=\"lbl\" was not injected: check your FXML file 'AAA.fxml'.";
        assert btn_redo != null : "fx:id=\"btn_redo\" was not injected: check your FXML file 'AAA.fxml'.";
        assert btn_undo != null : "fx:id=\"btn_undo\" was not injected: check your FXML file 'AAA.fxml'.";

    }
}

