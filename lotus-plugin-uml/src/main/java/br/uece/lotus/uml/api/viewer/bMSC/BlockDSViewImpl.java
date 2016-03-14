package br.uece.lotus.uml.api.viewer.bMSC;


import br.uece.lotus.uml.api.ds.BlockDS;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;



/**
 * Created by lva on 11/12/15.
 */
public class BlockDSViewImpl extends Region implements BlockDSView, BlockDS.Listener{
    static final double ALTURA_RETANGULO = 60;
    static final double LARGURA_RETANGULO = 100;
    private static final String DEFAULT_COLOR = "green" ;
    private final Rectangle mRectangle;
    private BlockDS mDS;
    private Line mLine;
    private Label mName;





    public BlockDSViewImpl(){
        mRectangle  = new Rectangle(LARGURA_RETANGULO,ALTURA_RETANGULO);
        getChildren().addAll(mRectangle);
        mRectangle.setLayoutX(0);
        mRectangle.setLayoutY(0);

        mName = new Label();
        mName.layoutXProperty().bind(mRectangle.layoutXProperty().add(mRectangle.widthProperty().divide(2)).subtract(mName.widthProperty().divide(2)));
        mName.layoutYProperty().bind(mRectangle.layoutYProperty().add(mRectangle.heightProperty().divide(2)).subtract(mName.heightProperty().divide(2)));
        getChildren().add(mName);

        mLine =new Line(0,0,0,0);
        mLine.startXProperty().bind(mRectangle.layoutXProperty().add(mRectangle.widthProperty().divide(2)));
        mLine.endXProperty().bind(mRectangle.layoutXProperty().add(mRectangle.widthProperty().divide(2)));
        mLine.startYProperty().bind(mRectangle.layoutXProperty().add(mRectangle.heightProperty()));
        mLine.endYProperty().setValue(500);
        getChildren().add(mLine);
    }


    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public boolean isInsideBounds(Circle circle) {
        if(circle.getBoundsInParent().intersects(mRectangle.getParent().getLayoutX(),mRectangle.getParent().getLayoutY()
                ,0,LARGURA_RETANGULO,ALTURA_RETANGULO,0)){
            System.out.println("true");
            return true;
        }
        /*if(circle.getBoundsInParent().intersects(mLine.getBoundsInParent())){
            return true;
        }
        if(circle.getBoundsInParent().intersects(mRectangle.getBoundsInParent())){
            return true;
        }*/

        return false;
    }

    /*@Override
    public boolean isInsideBounds(Point2D point) {
        Point2D auxRec= mRectangle.localToScene(Point2D.ZERO);
        double deltaX = point.getX()-auxRec.getX();
        double deltaY= point.getY()-auxRec.getY();
        if((deltaX>=0 && deltaX<=LARGURA_RETANGULO) && (deltaY>=0 && deltaY<=ALTURA_RETANGULO)){
            return true;
        }
        double constante = 10;
        Point2D auxLine = mLine.localToParent(Point2D.ZERO);
        Bounds aux = mLine.getBoundsInParent();
        double xMaisConstante=point.getX()+constante;
        double yMaisConstante=point.getY()+constante;
        if((aux.getWidth() == (point.getX()-auxLine.getX()) && (aux.getHeight() == (point.getY()-auxLine.getY())))){
            return true;
        }
        return false;
    }*/

    @Override
    public BlockDS getBlockDS() {
        return mDS;
    }

    @Override
    public void setBlockDS(BlockDS ds){
        if (mDS != null) {
            mDS.removeListener(this);
        }
        mDS = ds;
        if (ds != null) {
            mDS.addListener(this);
            updateView();
        }
    }

    @Override
    public void onChange(BlockDS ds) {updateView();}

    private void updateView() {
        String style = "-fx-effect: dropshadow( gaussian , gray , 3 , 0.2 , 1 , 1);";
        style += "-fx-fill: linear-gradient(to bottom right, white, " + computedColor() + ");";
        style += "-fx-stroke: " + (mDS.getBorderColor() == null ? "black" : mDS.getBorderColor()) + ";";
        style += "-fx-stroke-width: " + (mDS.getBorderWidth() == null ? "1" : mDS.getBorderWidth()) + ";";
        mRectangle.setStyle(style);
        mName.setText(mDS.getLabel());
//
//        style = "-fx-text-fill: " + (mState.getTextColor() == null ? "black" : mState.getTextColor()) + ";";
//        style += "-fx-font-weight: " + (mState.getTextStyle() == null ? "normal" : mState.getTextStyle()) + ";";
//        style += "-fx-font-size: " + (mState.getTextSize() == null ? "12" : mState.getTextSize()) + ";";
//        mText.setStyle(style);
        setLayoutX(mDS.getLayoutX());
        setLayoutY(mDS.getLayoutY());
//        mText.setText(computedLabel());
    }
    private String computedColor() {
        String cor = mDS.getColor();
        if (cor == null) {
          return DEFAULT_COLOR;
        }
        return cor;
    }
    private String computedLabel() {
        /*if (mState.isError()) {
            return "-1";
        } else if (mState.isFinal()) {
            return "E";
        }
        return mState.getLabel();*/
        return null;
    }
}