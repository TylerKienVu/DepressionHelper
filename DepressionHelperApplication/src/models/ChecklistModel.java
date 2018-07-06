package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tyler
 */
public class ChecklistModel {
    private final StringProperty text = new SimpleStringProperty("Initial text...");
    
    public StringProperty textProperty() {
        return text;
    }
    
    public final void setText(String text) {
        textProperty().set(text);
    }
    
    public final String getText() {
        return textProperty().get();
    }
}
