
package service.medicament.BilanPatient.BilanRadiologique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import service.medicament.BilanPatient.BilanBiologique;
import service.medicament.SGBD.Consultation;
import service.medicament.SGBD.MiseAjour;


public final class IRMBR extends Parent{
              final DatePicker    DateB               = new DatePicker();
              final ToggleGroup   Tgroup1             = new ToggleGroup(); 
              final ToggleGroup   Tgroup2             = new ToggleGroup();
              final ToggleGroup   Tgroup3             = new ToggleGroup();
              final ToggleGroup   Tgroup4             = new ToggleGroup();
              final ToggleGroup   Tgroup5             = new ToggleGroup(); 
              final CheckBox      S1                  = new CheckBox("I");
              final CheckBox      S2                  = new CheckBox("II");
              final CheckBox      S3                  = new CheckBox("III");
              final CheckBox      S4                  = new CheckBox("IV");
              final CheckBox      S5                  = new CheckBox("V");
              final CheckBox      S6                  = new CheckBox("VI");
              final CheckBox      S7                  = new CheckBox("VII");
              final CheckBox      S8                  = new CheckBox("VIII");
              final ToggleGroup   Tgroup6             = new ToggleGroup();
              final ToggleGroup   Tgroup7             = new ToggleGroup();
              final TextArea      precise             ; 
              final ToggleGroup   Tgroup8             = new ToggleGroup();
              final ToggleGroup   Tgroup10            = new ToggleGroup();
              final TextArea      autreIRM            ;
       final Label       IndIns        = new Label();
       final ImageView   ImgSucc       = new ImageView(new Image(getClass().getResourceAsStream("6_1.jpg")));
       final ImageView   ImgFaild      = new ImageView(new Image(getClass().getResourceAsStream("42.jpg")));  

            ///////////////////////////////////////////////////////////////////////////////////////////////////////
       ////// La Partie Cpnception ///////////////////////////////////////////////////////////////////////////
    
    private  Label TDateTDM          = new Label();
    private  Label TTDM                     = new Label();
    private  Label TTDMFoieReg              = new Label();
    private  Label TTDMFoieHom              = new Label();
    private  Label TTDMTaille               = new Label();
    private  Label TTDMNbr                  = new Label();
    private  Label TTDMSiege                = new Label();
    private  Label TTDMVasc                 = new Label();
    private  Label TSiOuiTypeEnvah          = new Label();
    private  Label TAutreTypeEnvah          = new Label();
    private  Label TTDMGanglionnaire        = new Label();
    private  Label TTypeTDM                 = new Label();
    private  Label TTDMAutres               = new Label();
    
                                          /**********/
    final HBox         barOutil            = new HBox(2);

    final Button       EnregButton         = new Button();  
    final ImageView    EnregfImg           = new ImageView(new Image(getClass().getResourceAsStream("4.png")));
    final Button       SuppButton          = new Button();
    final ImageView    SuppImg             = new ImageView(new Image(getClass().getResourceAsStream("7_3.jpg")));
    
    final VBox         vbMod           = new VBox(5);
    final Label        IndModifS       = new Label();
    final Hyperlink    hperAnnul       = new Hyperlink("Ou Annuler l'opération ... ");
    final ImageView    IndImgS         = new ImageView(new Image(getClass().getResourceAsStream("9_2.jpg")));
    final ImageView    IndImgF         = new ImageView(new Image(getClass().getResourceAsStream("38.jpg")));

    /**********/
     //////////////////////////////////////////////////////////////////////////////////////////////////////////
    final String DateInsBilan;
    
    public IRMBR(int TypeCCP,String NumDossier,String DateInsBilan) throws SQLException{
      this.DateInsBilan = DateInsBilan;
        /////
      
                   /****************Partie Biologique  ************************/
        vbMod.setVisible(false);
    
    EnregfImg.setFitHeight(50);  EnregfImg.setPreserveRatio(true); EnregButton.setDisable(true);
    EnregButton.setGraphic(EnregfImg);
    SuppImg.setFitHeight(50);    SuppImg.setPreserveRatio(true);
    SuppButton.setGraphic(SuppImg);
    barOutil.getChildren().addAll(SuppButton,EnregButton);
    barOutil.getStyleClass().add("barOutil");
    
    IndImgS.setFitHeight(50); IndImgS.setPreserveRatio(true);
    IndImgF.setFitHeight(50); IndImgF.setPreserveRatio(true);
    
    vbMod.getChildren().addAll(IndModifS,hperAnnul);
        /***********************************************************/
        //   
        ImgSucc.setFitHeight(50);          ImgFaild.setFitHeight(50);
        ImgSucc.setPreserveRatio(true);    ImgFaild.setPreserveRatio(true);
        IndIns.setPrefWidth(350);
        //
        /////
         DateB.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory =
                     new Callback<DatePicker, DateCell>() {
                         @Override
                         public DateCell call(final DatePicker datePicker) {
                             return new DateCell() {
                        @Override
                       public void updateItem(LocalDate item, boolean empty) {
                           super.updateItem(item, empty);
                           if (item.isAfter(LocalDate.now())) {
                            setDisable(true);
                            getStyleClass().add("setStyleClassification");
                            }
                             };
                         };
                     }};
        DateB.setDayCellFactory(dayCellFactory);
        final Label DateBilan                     = new Label("Date IRM ");  
        final HBox  hbDate                        = new HBox(10);
        hbDate.getChildren().addAll(DateBilan,DateB); hbDate.setPadding(new Insets(30,20,30,20));
 
      final GridPane     grid                               = new GridPane();
      grid.setVgap(5); grid.setHgap(5);
      
      final Label        IRM               = new Label("IRM");
     
              final RadioButton   OuiIRM          = new RadioButton("Oui");  OuiIRM.setUserData("Oui"); OuiIRM.setSelected(true);
              final RadioButton   NonIRM          = new RadioButton("Non");  NonIRM.setUserData("Non");
                
              OuiIRM.setToggleGroup(Tgroup1);  NonIRM.setToggleGroup(Tgroup1);
              IRM.getStyleClass().add("setStylesousParteBilan");
        grid.add(IRM,0,0);  grid.add(OuiIRM,1,0); grid.add(NonIRM,2,0);
        
        final Label          Foie                 = new Label("Foie");  
              final RadioButton   regulièr             = new RadioButton("régulier");   regulièr.setUserData("régulier");   regulièr.setSelected(true);
              final RadioButton   irregulier           = new RadioButton("irrégulier"); irregulier.setUserData("irrégulier");
              final RadioButton   homgène              = new RadioButton("homgène");    homgène.setUserData("homgène");     homgène.setSelected(true);
              final RadioButton   hétérogèn            = new RadioButton("hétérogène"); hétérogèn.setUserData("hétérogène");
              
              regulièr.setToggleGroup(Tgroup2); irregulier.setToggleGroup(Tgroup2);
              
              homgène.setToggleGroup(Tgroup3); hétérogèn.setToggleGroup(Tgroup3);
        grid.add(Foie,0,1); grid.add(regulièr, 1,1); grid.add(irregulier,2,1); grid.add(homgène,3,1); grid.add(hétérogèn,4,1);
        
        final Label          TailleNodules        = new Label("Taille du Nodule");
              final RadioButton   T1                   = new RadioButton("<2 cm"); T1.setUserData("<2 cm");
              final RadioButton   T2                   = new RadioButton("2-4");   T2.setUserData("2-4");
              final RadioButton   T3                   = new RadioButton(">4-6");  T3.setUserData(">4-6");
              final RadioButton   T4                   = new RadioButton(">6-8");  T4.setUserData(">6-8");
              final RadioButton   T5                   = new RadioButton(">8-10"); T5.setUserData(">8-10");
              final RadioButton   T6                   = new RadioButton(">10-12");T6.setUserData(">10-12"); 
              final RadioButton   T7                   = new RadioButton(">12-14");T7.setUserData(">12-14");
              final RadioButton   T8                   = new RadioButton(">14");   T8.setUserData(">14");
                                                                                   T1.setSelected(true);
              T1.setToggleGroup(Tgroup4); T2.setToggleGroup(Tgroup4); T3.setToggleGroup(Tgroup4); T4.setToggleGroup(Tgroup4);
              T5.setToggleGroup(Tgroup4); T6.setToggleGroup(Tgroup4); T7.setToggleGroup(Tgroup4); T8.setToggleGroup(Tgroup4);
        grid.add(TailleNodules,0,2); grid.add(T1, 1,2); grid.add(T2, 2,2); grid.add(T3, 3,2); grid.add(T4, 4,2);
                                     grid.add(T5, 1,3); grid.add(T6, 2,3); grid.add(T7, 3,3); grid.add(T8, 4,3);
                                     
        final Label          Nombre                = new Label("Nombre");
              final RadioButton  N1                    = new RadioButton("1");     N1.setUserData("1");
              final RadioButton  N2                    = new RadioButton("2-4");   N2.setUserData("2-4");
              final RadioButton  N3                    = new RadioButton(">4");    N3.setUserData(">4");
                                                                                   N3.setSelected(true);
              N1.setToggleGroup(Tgroup5); N2.setToggleGroup(Tgroup5); N3.setToggleGroup(Tgroup5);
        grid.add(Nombre,0,4); grid.add(N1,1,4); grid.add(N2,2,4); grid.add(N3,3,4);
        
        final Label          Siege                 = new Label("Siege");
              
        grid.add(Siege,0,5); grid.add(S1,1,5); grid.add(S2,2,5); grid.add(S3,3,5); grid.add(S4,4,5);
                             grid.add(S5,1,6); grid.add(S6,2,6); grid.add(S7,3,6); grid.add(S8,4,6);
       
        final Label          EnvahissementVascul   = new Label("Envahissement vasculaire");
              final RadioButton  E1                    = new RadioButton("Oui"); E1.setUserData("Oui");
              final RadioButton  E2                    = new RadioButton("Non"); E2.setUserData("Non");
                                                                                 E1.setSelected(true);
              E1.setToggleGroup(Tgroup6); E2.setToggleGroup(Tgroup6);
        grid.add(EnvahissementVascul,0,7); grid.add(E1, 1,7); grid.add(E2,2,7);
              final HBox         hboxE                 = new HBox(); hboxE.setSpacing(5);hboxE.setPadding(new Insets(10,10,10,10));
              final Label        SIoui                 = new Label("SI Oui");
                    final RadioButton   ThrombosPort       = new RadioButton("thrombose porte");  ThrombosPort.setUserData("thrombose porte");
                    final RadioButton   BranchPortabl      = new RadioButton("branche portable"); BranchPortabl.setUserData("branche portable");
                    final RadioButton   autre              = new RadioButton("autre");            autre.setUserData("autre");
                                                                                                  BranchPortabl.setSelected(true);
                    ThrombosPort.setToggleGroup(Tgroup7); BranchPortabl.setToggleGroup(Tgroup7); autre.setToggleGroup(Tgroup7);
                    hboxE.getChildren().addAll(SIoui,ThrombosPort,BranchPortabl,autre); //hboxE.setVisible(false);
                    final VBox           vboxE             = new VBox(); vboxE.setPadding(new Insets(10,10,10,10)); vboxE.setSpacing(5);
                    vboxE.getChildren().add(hboxE);
        grid.add(vboxE,0,8,4,4);
        precise = champSaisierA();
        Tgroup6.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle,
        Toggle new_toggle)->{
                    if(Tgroup6.getSelectedToggle().getUserData().equals("Oui"))  {
                        vboxE.setVisible(true);ThrombosPort.setSelected(true);
                    }else {
                        vboxE.setVisible(false);precise.setText("");
                        Tgroup7.getSelectedToggle().setSelected(false);
                    }  
                    });
                    precise.setPromptText("préciser");
                    precise.setPrefSize(180,80);
                    precise.setVisible(false);
        Tgroup7.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov ,Toggle old_toggle,
        Toggle new_toggle)->{
            if(this.getEvahissementVascul().equals("Oui")){
                   if(Tgroup7.getSelectedToggle().getUserData().equals("autre")) {
                       precise.setVisible(true);
                   }else{
                       precise.setVisible(false);precise.setText("");
                   }
            }
    });
    vboxE.getChildren().add(precise);                                            
             final Label Ganglionnaire                     = new Label("Ganglionnaire");
                   final RadioButton    OuiG                  = new RadioButton("Oui");OuiG.setUserData("Oui");
                   final RadioButton    NonG                  = new RadioButton("Non");NonG.setUserData("Non");
                                                                                       OuiG.setSelected(true);
                   OuiG.setToggleGroup(Tgroup8); NonG.setToggleGroup(Tgroup8);
             final HBox hboxG     = new HBox();  hboxG.setPadding(new Insets(10,10,10,10)); hboxG.setSpacing(5);
             hboxG.getChildren().addAll(Ganglionnaire,OuiG,NonG);
             vboxE.getChildren().add(hboxG);
        
        final RadioButton   whachIn                        = new RadioButton("WASH IN ");whachIn.setUserData("WASH IN ");
        final RadioButton   whachOut                       = new RadioButton("WASH OUT");whachOut.setUserData("WHASH OUT");
        final RadioButton   ATYPIQUE                       = new RadioButton("ATYPIQUE");ATYPIQUE.setUserData("ATYPIQUE");
                                                                                         ATYPIQUE.setSelected(true);
                
        whachIn.setToggleGroup(Tgroup10); whachOut.setToggleGroup(Tgroup10); ATYPIQUE.setToggleGroup(Tgroup10);
        grid.add(whachIn,0,13); grid.add(whachOut,1,13); grid.add(ATYPIQUE,2,13);
        
           autreIRM = champSaisierA();
           autreIRM.setPromptText("Autre");     
           autreIRM.setPrefSize(250,80);
    grid.add(autreIRM,0,14,2,2);
    
    ////
    Tgroup1.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>ov,Toggle old_value,
    Toggle new_value)->{
        if(Tgroup1.getSelectedToggle().getUserData().equals("Non")){
            
            grid.getChildren().removeAll(Foie,regulièr,irregulier,homgène,hétérogèn,TailleNodules,T1,T2,T3,T4,T5,T6,T7,T8,
                                     Nombre,N1,N2,N3,Siege,S1,S2,S3,S4,S5,S6,S7,S8,EnvahissementVascul,E1,E2,vboxE,
                                     whachIn,whachOut,ATYPIQUE,autreIRM);
        }else {
            grid.getChildren().addAll(Foie,regulièr,irregulier,homgène,hétérogèn,TailleNodules,T1,T2,T3,T4,T5,T6,T7,T8,
                                     Nombre,N1,N2,N3,Siege,S1,S2,S3,S4,S5,S6,S7,S8,EnvahissementVascul,E1,E2,vboxE,
                                     whachIn,whachOut,ATYPIQUE,autreIRM);
        }
    });
    
    final DropShadow shadow  = new DropShadow();
        shadow.setOffsetX(1);
        shadow.setOffsetY(0.5);
        shadow.setColor(Color.AQUA);
        final Button  Ajouter        = new Button("Ajouter");
        Ajouter.setEffect(null);
        Ajouter.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
            Ajouter.setScaleX(1.1);
            Ajouter.setScaleY(1.1);
            Ajouter.setEffect(shadow);
            }
        });
        Ajouter.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
            Ajouter.setScaleX(1);
            Ajouter.setScaleY(1);
            Ajouter.setEffect(null);   
            }
        });
        Ajouter.setOnAction((ActionEvent)->{
            try {
                int i = this.InsertionSGBD(NumDossier);
                   if(i == 1){
                    IndIns.setGraphic(ImgSucc); IndIns.getStyleClass().remove("erreurchamp");
                    IndIns.setText(" L’insertion du Bilan\n Radiologique(IRM) \n au niveau du \n Dossier numero "+NumDossier+"\n est faite par succès  ");
                   }else {
                    IndIns.setGraphic(ImgFaild);IndIns.getStyleClass().add("erreurchamp");
                    IndIns.setText(" L’insertion des Bilans \n est bloquée\n par le système ,\n Vous ne pouvez affecter\n qu'une seule insertion \n des bilan de même type \n au niveau d'un dossier \n sur la même date ");
                   }
            } catch (SQLException ex) {
                Logger.getLogger(IRMBR.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        grid.add(Ajouter, 5,16);
        grid.add(IndIns, 4,11,4,4);
    ///
    
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////// La Partie Conception ////////////////////////////////////////////////////////////////
    final GridPane        gridC    = new GridPane();
    gridC.setVgap(10);
    gridC.setHgap(7);
         final Label BilanRadLabel              = new Label("Bilan Radiologique  :");                        
         BilanRadLabel.getStyleClass().add("setStyleCirconsanceDic");                                  gridC.add(BilanRadLabel,0,0);
         final Label BilanEchoLabel             = new Label("IRM  :");
         BilanEchoLabel.getStyleClass().add("setStyleCirconsanceDic");                                 gridC.add(BilanEchoLabel, 0,1);
     final Label ConsDateEchographie             = new Label("Date IRM                         :");    gridC.add(ConsDateEchographie, 0,2);      gridC.add(TDateTDM, 5,2);
     final Label ConsEcho                        = new Label("IRM                              :");    gridC.add(ConsEcho, 0,3);                 gridC.add(TTDM, 5,3);
     final Label ConsEchoFoieReg                 = new Label("Foie                             :");    gridC.add(ConsEchoFoieReg, 0,4);          gridC.add(TTDMFoieReg, 5,4);
     final Label ConsEchoFoieHom                 = new Label("Foie                             :");    gridC.add(ConsEchoFoieHom, 0,5);          gridC.add(TTDMFoieHom, 5,5);
     final Label ConsEchoTaille                  = new Label("Taille du nodule                 :");    gridC.add(ConsEchoTaille, 0,6);           gridC.add(TTDMTaille, 5,6);
     final Label ConsEchoNbr                     = new Label("Nombre                           :");    gridC.add(ConsEchoNbr, 0,7);              gridC.add(TTDMNbr, 5,7);
     final Label ConsEchoSiege                   = new Label("Siege                            :");    gridC.add(ConsEchoSiege, 0,8);            gridC.add(TTDMSiege, 5,8);
     final Label ConsEchoVasc                    = new Label("Envahissement vasculaire         :");    gridC.add(ConsEchoVasc, 0,9);             gridC.add(TTDMVasc, 5,9);
     final Label ConsSiOuiTypeEnvah              = new Label("Si oui                           :");    gridC.add(ConsSiOuiTypeEnvah, 0,10);      gridC.add(TSiOuiTypeEnvah, 5,10);
     final Label ConsAutreTypeEnvah              = new Label("Autre                            :");    gridC.add(ConsAutreTypeEnvah, 0,11);      gridC.add(TAutreTypeEnvah, 5,11);
     final Label ConsEchoGanglionnaire           = new Label("Ganglionnaire                    :");    gridC.add(ConsEchoGanglionnaire ,0,12);   gridC.add(TTDMGanglionnaire ,5,12);
     final Label ConsEchoLv                      = new Label("Type IRM                         :");    gridC.add(ConsEchoLv, 0,13);              gridC.add(TTypeTDM, 5,13);
     final Label ConsEchoAutres                  = new Label("Autres                           :");    gridC.add(ConsEchoAutres, 0,15,2,3);      gridC.add(TTDMAutres,5,15,2,3);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
       /**********************Partie Modification ***********************************************************/
        final ImageView ImgEnregSup   = new ImageView(new Image(getClass().getResourceAsStream("12_1.jpg")));
        ImgEnregSup.setFitHeight(40); ImgEnregSup.setPreserveRatio(true);
        
         SuppButton.setOnAction((ActionEvent)->{
         hperAnnul.setVisible(true);
         
     
    IndModifS.setText("Appuyez sur l'icon d'enregistrement \n pour effectuer la Suppression du document");
    IndModifS.setGraphic(ImgEnregSup);
    vbMod.setVisible(true);
    EnregButton.setDisable(false);
        
    });
    /****/
     hperAnnul.setOnAction((ActionEvent)->{
     vbMod.setVisible(false);
     EnregButton.setDisable(true);
   
        });
        
        EnregButton.setOnAction((ActionEvent)->{
        
        
        
            try {
                int j = this.DeleteSGBD(NumDossier);
                this.ConsultationE(NumDossier);
                 if(j == 0){
               IndModifS.setText("doncument n'est pas enregistré ");
               IndModifS.setGraphic(IndImgF); 
            }else {
               IndModifS.setText("Le Document est Supprimé ");
               IndModifS.setGraphic(IndImgS); 
               hperAnnul.setVisible(false);
               EnregButton.setDisable(true);
               this.ConsultationE(NumDossier);
               
            }
            } catch (SQLException ex) {
                Logger.getLogger(BilanBiologique.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        
        });
                   
     
        
        /*****************************************************************************************************/

     grid.setPadding(new Insets(30,10,10,30));
     gridC.setPadding(new Insets(30,10,10,30));
        if(TypeCCP == 0){
        final StackPane   pane     =  new StackPane();
        pane.getStyleClass().add("setStylingRoot");
        grid.setTranslateY(50);
        pane.getChildren().addAll(hbDate,grid);
        getChildren().add(pane);
        }else {
        final StackPane   paneC     =  new StackPane();
        paneC.getStyleClass().add("setStylingRootCons");
        this.ConsultationE(NumDossier);
        gridC.setPrefWidth(900);
        gridC.add(barOutil, 6,18,2,2);
        gridC.add(vbMod, 0,18,5,2);
        paneC.getChildren().addAll(gridC);
        getChildren().add(paneC);
        }  
     

    }
    
    
    public String getIRM() {
        return Tgroup1.getSelectedToggle().getUserData().toString();
    }

    public String getFoieReg() {
        return Tgroup2.getSelectedToggle().getUserData().toString();
    }

    public String getFoieHom() {
        return Tgroup3.getSelectedToggle().getUserData().toString();
    }

    public String getTailleNodule() {
        return Tgroup4.getSelectedToggle().getUserData().toString();
    }

    public String getNombre() {
        return Tgroup5.getSelectedToggle().getUserData().toString();
    }

    public String getS1() {
        if(S1.isSelected()){
        return S1.getText();
    }else{
            return "";
        }
    }

    public String getS2() {
        if(S2.isSelected()){
        return S2.getText();
    }else{
            return "";
        }
    }

    public String getS3() {
        if(S3.isSelected()){
        return S3.getText();
    }else{
            return "";
        }
    }

    public String getS4() {
        if(S4.isSelected()){
        return S4.getText();
    }else{
            return "";
        }
    }

    public String getS5() {
        if(S5.isSelected()){
        return S5.getText();
    }else{
            return "";
        }
    }

    public String getS6() {
        if(S6.isSelected()){
        return S6.getText();
    }else{
            return "";
        }
    }

    public String getS7() {
        if(S7.isSelected()){
        return S7.getText();
    }else{
            return "";
        }
    }

    public String getS8() {
        if(S8.isSelected()){
        return S8.getText();
    }else{
            return "";
        }
    }

    public String getEvahissementVascul() {
        return Tgroup6.getSelectedToggle().getUserData().toString();
    }

    public String getSiOui() {
        if(Tgroup6.getSelectedToggle().getUserData().toString().equals("Oui")){
        return Tgroup7.getSelectedToggle().getUserData().toString();}
        else return "";
    }

    public String getPrecise() {
        return precise.getText();
    }

    public String getGanglionnaire() {
        return Tgroup8.getSelectedToggle().getUserData().toString();
    }
    public String getWach() {
        return Tgroup10.getSelectedToggle().getUserData().toString();
    }
    public String getAutreIRM() {
        return autreIRM.getText();
    }

    public LocalDate getDateB() {
        return DateB.getValue();
    }
   
    public int InsertionSGBD(String NumDossier) throws SQLException{
        System.out.println(
                this.getIRM()+"\n"+this.getFoieReg()+"   "+this.getFoieHom()+"\n"+this.getTailleNodule()+"\n"+
        this.getNombre()+"\n"+
        this.getS1()+" "+this.getS2()+" "+this.getS3()+" "+this.getS4()+" "+this.getS5()+" "+this.getS6()+" "+this.getS7()+" "+this.getS8()+"\n"+
        this.getEvahissementVascul()+"\n"+this.getSiOui()+"\n"+this.getPrecise()+"\n"+this.getGanglionnaire()+"\n"+
        this.getWach()+"\n"+this.getAutreIRM()+"\n"
                        );
        
        MiseAjour   bddInsert  =  new MiseAjour();
       int i = bddInsert.AddElement("insert into irm values (0,'"+this.getIRM()+"','"+this.getFoieReg()+"','"+this.getFoieHom()+"','"+this.getTailleNodule()+"','"+
        this.getNombre()+"','"+
        this.getS1()+" "+this.getS2()+" "+this.getS3()+" "+this.getS4()+" "+this.getS5()+" "+this.getS6()+" "+this.getS7()+" "+this.getS8()+"','"+
        this.getEvahissementVascul()+"','"+this.getSiOui()+"','"+this.getPrecise()+"','"+this.getGanglionnaire()+"','"+
        this.getWach()+"','"+this.getAutreIRM()+"','"+NumDossier+"','"+this.getDateB()+"')");
       
       return i ;
    }
    public TextArea champSaisierA(){
 TextArea textA = new TextArea(){
            
            public void replaceText(int start , int end , String text){
                if(!text.matches("[',]")){
                    super.replaceText(start, end, text);
                   
                }
              
            }
           
           @Override
         
           public void replaceSelection(String text) {
               
               if (!text.matches("[a-z, A-Z]")) {
                    super.replaceSelection(text);
             }

            }
 };   

   return textA;  
    
}
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      public void setDateTDM(String a) {
        TDateTDM.setText(a); 
    }

    public void setTDM(String a) {
        TTDM.setText(a); 
    }

    public void setTDMFoieReg(String a) {
        TTDMFoieReg.setText(a); 
    }

    public void setTDMFoieHom(String a) {
        TTDMFoieHom.setText(a); 
    }

    public void setTDMTaille(String a) {
        TTDMTaille.setText(a); 
    }

    public void setTDMNbr(String a) {
        TTDMNbr.setText(a); 
    }

    public void setTDMSiege(String a) {
        TTDMSiege.setText(a); 
    }

    public void setTDMVasc(String a) {
        TTDMVasc.setText(a); 
    }

    public void setSiOuiTypeEnvah(String a) {
        TSiOuiTypeEnvah.setText(a); 
    }

    public void setAutreTypeEnvah(String a) {
        TAutreTypeEnvah.setText(a); 
    }

    public void setTDMGanglionnaire(String a) {
        TTDMGanglionnaire.setText(a); 
    }

    public void setTypeTDM(String a) {
        TTypeTDM.setText(a); 
    }

   

    public void setTDMAutres(String a) {
        TTDMAutres.setText(a); 
    }
    
    public void ConsultationE(String NumDossier) throws SQLException{
        
        TDMClasse  Bilan         = null;
        Consultation bddCons     = new Consultation();
        ResultSet res = bddCons.ExtElement("select * from irm where NumDossier = '"+NumDossier+"'"
                                           +"and Dateirm = '"+DateInsBilan+"'");
    while(res.next()){
     Bilan   = new TDMClasse(res.getDate(15),res.getString(2),res.getString(3),res.getString(4),res.getString(5)
     ,res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10),res.getString(11),res.getString(12)
     ,res.getString(13));
    }try{
     this.setTDM(Bilan.getTDM());
     this.setTDMFoieHom(Bilan.getTDMFoieHom());
     this.setTDMFoieReg(Bilan.getTDMFoieReg());
     this.setDateTDM(Bilan.getDateTDM().toString());
     this.setTDMTaille(Bilan.getTDMTaille());
     this.setTDMGanglionnaire(Bilan.getTDMGanglionnaire());
     this.setTDMNbr(Bilan.getTDMNbr());
     this.setTDMSiege(Bilan.getTDMSiege());
     this.setTDMVasc(Bilan.getTDMVasc());
     this.setSiOuiTypeEnvah(Bilan.getSiOuiTypeEnvah());
     this.setAutreTypeEnvah(Bilan.getAutreTypeEnvah());
     this.setTypeTDM(Bilan.getTDMType());
     this.setTDMAutres(Bilan.getTDMAutre());
     
        }
    catch(Exception e){
        this.setTDM("------------");
     this.setTDMFoieHom("------------");
     this.setTDMFoieReg("------------");
     this.setDateTDM("------------");
     this.setTDMTaille("------------");
     this.setTDMGanglionnaire("------------");
     this.setTDMNbr("------------");
     this.setTDMSiege("------------");
     this.setTDMVasc("------------");
     this.setSiOuiTypeEnvah("------------");
     this.setAutreTypeEnvah("------------");
     this.setTypeTDM("------------");
     this.setTDMAutres("------------");
    }
    }
    
            public int  DeleteSGBD(String NumDossier) throws SQLException{
            MiseAjour bddDelete   = new MiseAjour();
        
            int j = bddDelete.AddElement("delete from irm where NumDossier ='"+NumDossier+"' and dateirm ='"+DateInsBilan+"'" );
            
            return j;
        }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
}
