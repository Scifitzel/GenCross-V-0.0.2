
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;



public class Tiles {

    protected Tile currentTile;
    protected boolean RoworColumn = false;
    protected StopWatch stopWatch = new StopWatch();
    protected ArrayList<ArrayList<Tile>> list = new ArrayList<ArrayList<Tile>>();
    public Scene scene;
    protected boolean paused = false;
  
    protected ArrayList<String> CrosswordClues = new ArrayList<String>(); 
    protected ArrayList<String> correctCrossword = new ArrayList<String>(); 
    protected AnchorPane anchorPane = new AnchorPane();

     public Tiles() {
        

         anchorPane.setPrefSize(1000,700);
         anchorPane.setStyle("-fx-background-color: brown");   
         Label acrossLabel = new Label("Horizantal");
         
        acrossLabel.setLayoutX(625);
        acrossLabel.setLayoutY(-50);
        acrossLabel.setPrefSize(200, 200);
       acrossLabel.setFont(Font.font("Times New Roman", 20));
        acrossLabel.setTextFill(Color.BLACK);
         anchorPane.getChildren().add(acrossLabel);   


         for (int i = 0; i <5; i++){
            Label numberLabel = new Label(String.valueOf(i+1)+'.');
            numberLabel.setLayoutX(625);
            numberLabel.setLayoutY(-20+i*50);
            numberLabel.setPrefSize(200, 200);
           numberLabel.setFont(Font.font("Times New Roman", 15));
            numberLabel.setTextFill(Color.BLACK);
             anchorPane.getChildren().add(numberLabel);
         }  

          Label downLabel = new Label("Vertical");  
         downLabel.setLayoutX(625);
        downLabel.setLayoutY(260);
        downLabel.setPrefSize(200, 200);
        downLabel.setFont(Font.font("Times New Roman", 20));
        downLabel.setTextFill(Color.BLACK);
         anchorPane.getChildren().add(downLabel);   

         for (int i = 0; i <5; i++){
            Label numberLabel = new Label(String.valueOf(i+1)+'.');
            numberLabel.setLayoutX(625);
            numberLabel.setLayoutY(290+i*50);
            numberLabel.setPrefSize(200, 200);
           numberLabel.setFont(Font.font("Times New Roman", 15));
            numberLabel.setTextFill(Color.BLACK);
             anchorPane.getChildren().add(numberLabel);
         }  

        for (int r = 0; r < 5; r++) {
            ArrayList<Tile> temp = new ArrayList<Tile>();
            for (int c = 0; c < 5; c++) {
                Tile tempTile = new Tile();
                tempTile.stackPane.setLayoutY(c*100+100);
                tempTile.stackPane.setLayoutX(r*100+100);
                tempTile.column = c;
                tempTile.row = r;
                temp.add(tempTile);
                anchorPane.getChildren().add(tempTile.stackPane);
            }            
            list.add(temp);
        }
        
        scene = new Scene(anchorPane);

        scene.setOnKeyPressed(e-> {
            currentTile.currentValue = keyCodeToCharacter(e.getCode());
            switchTile();
        });
 

    }         

    public char keyCodeToCharacter(KeyCode keyCode) {
        if (keyCode == KeyCode.A) {
            return 'A';
        } 
        else if (keyCode == KeyCode.B) {
         return 'B';
        }
        else if (keyCode == KeyCode.C) {
             return 'C';
         } 
        else if (keyCode == KeyCode.D) {
          return 'D';
         }
         else if (keyCode == KeyCode.E) {
             return 'E';
            }
         else if (keyCode == KeyCode.F) {
                 return 'F';
             } 
         else if (keyCode == KeyCode.G) {
              return 'G';
             }
         else if (keyCode == KeyCode.H) {
                 return 'H';
             }
         else if (keyCode == KeyCode.I) {
                     return 'I';
             } 
         else if (keyCode == KeyCode.J) {
                  return 'J';
             }
         else if (keyCode == KeyCode.K) {
                     return 'K';
             }
         else if (keyCode == KeyCode.L) {
                         return 'L';
                     } 
         else if (keyCode == KeyCode.M) {
                      return 'M';
             }  
         else if (keyCode == KeyCode.N) {
                 return 'N';
                }
                else if (keyCode == KeyCode.O) {
                    return 'O';
                } 
               else if (keyCode == KeyCode.P) {
                 return 'P';
                }
                else if (keyCode == KeyCode.Q) {
                     return 'Q';
                 } 
                else if (keyCode == KeyCode.R) {
                  return 'R';
                 }
                 else if (keyCode == KeyCode.S) {
                     return 'S';
                    }
                 else if (keyCode == KeyCode.T) {
                         return 'T';
                     } 
                 else if (keyCode == KeyCode.U) {
                      return 'U';
                     }
                 else if (keyCode == KeyCode.V) {
                         return 'V';
                     }
                 else if (keyCode == KeyCode.W) {
                             return 'W';
                     } 
                 else if (keyCode == KeyCode.X) {
                          return 'X';
                     }
                 else if (keyCode == KeyCode.Y) {
                             return 'Y';
                     }
                 else if (keyCode == KeyCode.Z) {
                                 return 'Z';
                             } 
                return '0';         
         
     }

     protected void switchTile() {
         if (currentTile.currentValue == null) {
             return;
         }
        if ( currentTile.currentValue != '0') {
        Label label = (Label) currentTile.stackPane.getChildren().get(1);
        label.setText(Character.toString(currentTile.currentValue));
        int nextAvalableTile = 1;

        if (currentTile.row+nextAvalableTile < 5 || currentTile.column+nextAvalableTile < 5) {
        if (RoworColumn) {
             while (currentTile.row+nextAvalableTile < 5 && !list.get(currentTile.row+nextAvalableTile).get(currentTile.column).writeable )  {
                 nextAvalableTile++;
             }
            if (currentTile.row+nextAvalableTile < 5) { 
          list.get(currentTile.row+nextAvalableTile).get(currentTile.column).selectTile(false,0); 
          } else {
            nextAvalableTile = 0;
            while (nextAvalableTile < 5 && currentTile.column < 4 && !list.get(nextAvalableTile).get(currentTile.column+1).writeable )  {
             nextAvalableTile++;
            }
                if (currentTile.column < 4) {
            list.get(nextAvalableTile).get(currentTile.column+1).selectTile(false,0); }
          }           
       } 
        else {
         while (currentTile.column+nextAvalableTile < 5 && !list.get(currentTile.row).get(currentTile.column+nextAvalableTile).writeable)  {
             nextAvalableTile++;
         }
            if (currentTile.column+nextAvalableTile < 5) {
           list.get(currentTile.row).get(currentTile.column+nextAvalableTile).selectTile(false,0); 
         } else {
             nextAvalableTile = 0;
             while (nextAvalableTile < 5 &&  ! list.get(currentTile.row+1).get(nextAvalableTile).writeable)  {
                 nextAvalableTile++;
             }
            list.get(currentTile.row+1).get(nextAvalableTile).selectTile(false,0);
           }     
        }
    }
   }
}

    public class Tile{
        StackPane stackPane = new StackPane();
        int column;
        int row;
        Character currentValue;
        boolean writeable = true;

        public void selectTile(boolean SwitchWay, int WhichWay) {
            if (WhichWay == 1) {
                RoworColumn = true;
            } else if (WhichWay == 2) {
                RoworColumn = false;
            }

            if (writeable && !paused) {
               if (currentTile != null) { 
                for (int i = 0; i < 5; i++) {
                    if ((!RoworColumn) ||  (SwitchWay && WhichWay == 2) || (WhichWay ==1 && RoworColumn && SwitchWay))   {
                     Tile tile = (Tile)(list.get(currentTile.row).get(i));
                      if (tile.writeable) {
                       Rectangle rectangle = (Rectangle)(tile.stackPane.getChildren().get(0));   
                      rectangle.setFill(Color.BLACK); 
                        } 
                      } else {
                           Tile tile = (Tile)(list.get(i).get(currentTile.column));
                           if (tile.writeable) {
                            Rectangle rectangle = (Rectangle)(tile.stackPane.getChildren().get(0));   
                           rectangle.setFill(Color.BLACK); 
                        } 
                      } 
                  }  
                
                  Rectangle previousRectangle = (Rectangle)stackPane.getChildren().get(0);  
                  previousRectangle.setFill(Color.RED);                
                    if ((currentTile.row != row || currentTile.column != column) && currentTile.writeable) {
                        if (SwitchWay) {
                            RoworColumn = false; } 
                        if (SwitchWay && ((currentTile.row == row && RoworColumn) || (!RoworColumn && currentTile.column == column))) {
                             previousRectangle = (Rectangle)currentTile.stackPane.getChildren().get(0);  
                             previousRectangle.setFill(Color.BLUE);
                        } 
                }
                } else {
                    currentTile = this;
                    Rectangle previousRectangle = (Rectangle)stackPane.getChildren().get(0);  
                    previousRectangle.setFill(Color.RED);
                }
                
                currentTile = this;

                for (int i = 0; i < 5; i++) {
                  if (RoworColumn == SwitchWay)   {
                    if (i != column) {
                   Tile tile = (Tile)(list.get(row).get(i));
                   if (tile.writeable) {
                   Rectangle rectangle = (Rectangle) tile.stackPane.getChildren().get(0);
                    rectangle.setFill(Color.BLUE); }
                        }  
                    } else {
                        if (i != row) {
                         Tile tile = (Tile)(list.get(i).get(column));
                         if (tile.writeable) {
                            Rectangle rectangle = (Rectangle) tile.stackPane.getChildren().get(0);
                             rectangle.setFill(Color.BLUE); }
                                 }  
                        }   
                    } 
                }    
                if (SwitchWay) {
                RoworColumn = !RoworColumn;
            }
            }



        Tile() {
            Rectangle rect = new Rectangle(100,100,Color.BLACK);
            rect.setStroke(Color.WHITE);
         rect.setOnMousePressed(e-> {
                selectTile(true,0);
           }); 
            Label label = new Label();
            label.setTextFill(Color.WHITE);
            stackPane.getChildren().addAll(rect, label);


        }
        public void setWritable(boolean value) {
            if (value) {
                writeable = true;
                currentValue = null;
                Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0); 
                rectangle.setFill(Color.BLACK);  
                Label label = (Label) stackPane.getChildren().get(1);
                label.setText(" ");
            } else {
                currentValue = '0';
                writeable = false;
                Rectangle rectangle = (Rectangle) stackPane.getChildren().get(0); 
                rectangle.setFill(Color.WHITE);            }
        }

    }



}
