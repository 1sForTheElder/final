package store;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
 
public class HTMLEditorCustomizationSample extends Application {
  // limits the fonts a user can select from in the html editor.
  private static final List<String> limitedFonts = FXCollections.observableArrayList("Arial", "Times New Roman", "Courier New", "Comic Sans MS");

  public static void main(String[] args) { launch(args); }
  @Override public void start(Stage stage) {
    // create a new html editor and show it before we start modifying it.
    final HTMLEditor htmlEditor = new HTMLEditor();
    stage.setScene(new Scene(htmlEditor));
    stage.show();

    // hide controls we don't need.
    hideImageNodesMatching(htmlEditor, Pattern.compile(".*(Cut|Copy|Paste|Color|outdent|indent|numbers|bullets).*"), 0);
    Node seperator = htmlEditor.lookup(".separator");
    seperator.setVisible(false); seperator.setManaged(false);

    
    // modify font selections.
    int i = 0;
    for (Node candidate: (htmlEditor.lookupAll("MenuButton"))) {
      // fonts are selected by the second menu in the htmlEditor.
//    	if (candidate instanceof MenuButton && i == 1) {
      if (i==0) {
        // limit the font selections to our predefined list.
        MenuButton menuButton = (MenuButton) candidate;
        
        List<MenuItem> removalList = FXCollections.observableArrayList();
        final List<MenuItem> fontSelections = menuButton.getItems();
        for (MenuItem item: fontSelections) {
          if (!limitedFonts.contains(item.getText())) {
            removalList.add(item);
          }
        }
        fontSelections.removeAll(removalList);
        // Select a font from out limited font selection.
        // Selection done in Platform.runLater because if you try to do
        // the selection immediately, it won't take place.
        Platform.runLater(new Runnable() {
          @Override public void run() {
            boolean fontSelected = false;
            for (final MenuItem item: fontSelections) {
              if ("Comic Sans MS".equals(item.getText())) {
                if (item instanceof RadioMenuItem) {
                  ((RadioMenuItem) item).setSelected(true);
                  fontSelected = true;
                }
              }
            }
            if (!fontSelected && fontSelections.size() > 0 && fontSelections.get(0) instanceof RadioMenuItem) {
              ((RadioMenuItem) fontSelections.get(0)).setSelected(true);
            }
          }
        });  
      }
      i++;
    }
    // add a custom button to the top toolbar.
      System.out.println(htmlEditor.lookup(".combo-box-base"));
      htmlEditor.lookup(".combo-box-base").setManaged(false);
      htmlEditor.lookup(".combo-box-base").setVisible(false);
      System.out.println(htmlEditor.lookup(".combo-box-base"));
      htmlEditor.lookup(".tool-bar").setManaged(false);
      htmlEditor.lookup(".tool-bar").setVisible(false);
      htmlEditor.lookup(".tool-bar").setDisable(true);
      int ind = 0;
      for(Node node: htmlEditor.lookupAll(".combo-box-base")){
    	  System.out.println(ind);
    	  if(ind!=4){
    		  System.out.println(node);
    		  
        	  node.setManaged(false);
        	  node.setDisable(true);
        	  node.setVisible(false);
        	  
    	  }
    	  ind++;
      };
      for(Node node: htmlEditor.lookupAll(".toggle-button")){   	    		  
          node.setManaged(false);
          node.setDisable(true);
          node.setVisible(false);       	     	    	  
      };
      for(Node node: htmlEditor.lookupAll(".button")){   	    		  
          node.setManaged(false);
          node.setDisable(true);
          node.setVisible(false);       	     	    	  
      };
//      for(Node node:htmlEditor.lookupAll(".tool-bar")){
//    	  node.setManaged(false);
//    	  node.setDisable(true);
//    	  node.setVisible(false);
//      }
//      htmlEditor.lookup("html-editor-copy").setManaged(false);
//      htmlEditor.lookup("html-editor-copy").setVisible(true);

//      System.out.println(htmlEditor.lookup(".button"));
//    System.out.println(htmlEditor.lookup(".tool-bar"));
//    htmlEditor.lookup(".tool-bar").setVisible(false);
//    htmlEditor.lookup(".tool-bar").setManaged(false);
//    htmlEditor.lookup("ComboBox").setManaged(false);
//    htmlEditor.lookup("ComboBox").setVisible(false);
//    htmlEditor.lookup("Button").setManaged(false);
//    htmlEditor.lookup("ToggleButton").setManaged(false);
//    htmlEditor.lookup("ToggleButton").setVisible(false);
//    htmlEditor.lookup("Separator").setManaged(false);
//    htmlEditor.lookup(".top-toolbar").setDisable(true);
//    htmlEditor.lookup(".top-toolbar").setVisible(false);
//    htmlEditor.lookup("Button").setDisable(true);
    Node node = htmlEditor.lookup(".top-toolbar");
    if (node instanceof ToolBar) {
      ToolBar bar = (ToolBar) node;
      ImageView graphic = new ImageView(new Image("http://bluebuddies.com/gallery/title/jpg/Smurf_Fun_100x100.jpg", 32, 32, true, true));
      graphic.setEffect(new DropShadow());
      Button smurfButton = new Button("", graphic);
      bar.getItems().add(smurfButton);
      smurfButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent arg0) {
          htmlEditor.setHtmlText("<font color='blue'>Smurfs are having fun</font> <font color='red'>ha<br>ha</font>");
          String a = "sgdfdfgd";
          String b = a.replace("gd", "sdfsadfdfdsfds");
          System.out.println(b);
          String cc = htmlEditor.getHtmlText();
          System.out.println(cc);
          printChildren(htmlEditor,100);
        }
      });
    }
  }

  // hide buttons containing nodes whose image url matches a given name pattern.
  public void hideImageNodesMatching(Node node, Pattern imageNamePattern, int depth) {
    if (node instanceof ImageView) {
      ImageView imageView = (ImageView) node;
      String url = imageView.getImage().impl_getUrl();
      System.out.println(url);
      if (url != null && imageNamePattern.matcher(url).matches()) {
        Node button = imageView.getParent().getParent();
        button.setVisible(false); button.setManaged(false);
      }
    }
    if (node instanceof Parent) 
      for (Node child : ((Parent) node).getChildrenUnmodifiable()) 
//    	
        hideImageNodesMatching(child, imageNamePattern, depth + 1);
    	System.out.println(node);
  }
  
  public void printChildren(HTMLEditor he, int MAXDEPTH)
  {
      System.out.println("Print Children ==========>>>>");
      String[] hieraArray = new String[MAXDEPTH]; 
      int maxDepth = 0;
      int lastDepth = 0;
      Node parent;

        /* List all elements of the HTMLeditor */
        for (Node element: (he.lookupAll("*")))
        {
            parent = element.getParent();
            if (maxDepth == 0) 
            {
                hieraArray[0] = element.getClass().getSimpleName().toString();
                System.out.print(hieraArray[0]);
                System.out.println("");
                maxDepth = 1;                   
            }
            else 
            {
                int i = 0, i2 = 0;
                boolean found = false;
                for(i=maxDepth; i>=0; i--)
                {
                    if (hieraArray[i] == null || parent.getClass().getSimpleName() == null) continue;
                    if (hieraArray[i].equals(parent.getClass().getSimpleName()))
                    {
                        for (i2 = 0; i2 <= i; i2++)
                        {
                            System.out.print("|");
                        }   
                        if ((Math.abs(lastDepth-i2)) > 2) System.out.print("->" + element.getClass().getSimpleName() + " {p: " + parent.getClass().getSimpleName() + "}");
                        else System.out.print("->" + element.getClass().getSimpleName());
                        //if (element.getClass().getSimpleName().equals("PopupButton"))  System.out.print(" ??: " + element + " ::: " + element.getClass());
                        lastDepth = i2;

                        hieraArray[(i+1)] = element.getClass().getSimpleName();
                        if (maxDepth < (i+1)) maxDepth = (i+1);
                        found = true;
                        System.out.println("");
                        break;
                    }
                }
                if (found == false) 
                {
                    hieraArray[(i+1)] = parent.getClass().getSimpleName();
                    if (maxDepth < (i+1)) maxDepth = (i+1);
                }
                if ((maxDepth+1) >= MAXDEPTH) 
                {
                    System.out.println("MAXDEPTH reached! increase ArraySize!");
                    return;
                }
            }
        }


  }
}