package store;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HTMLEditorModifyer extends Application {
  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) {
    final HTMLEditor htmlEditor = new HTMLEditor();
    stage.setScene(new Scene(htmlEditor));
    stage.show();

    hideImageNodesMatching(htmlEditor, Pattern.compile(".*(Cut|Copy|Paste).*"), 0);
    Node seperator = htmlEditor.lookup(".separator");
    seperator.setVisible(false); seperator.setManaged(false);
  }

  public void hideImageNodesMatching(Node node, Pattern imageNamePattern, int depth) {
    if (node instanceof ImageView) {
      ImageView imageView = (ImageView) node;
      String url = imageView.getImage().impl_getUrl();
      if (url != null && imageNamePattern.matcher(url).matches()) {
        Node button = imageView.getParent().getParent();
        button.setVisible(false); button.setManaged(false);
      }
    }
    if (node instanceof Parent) 
      for (Node child : ((Parent) node).getChildrenUnmodifiable()) 
        hideImageNodesMatching(child, imageNamePattern, depth + 1);
  }
}