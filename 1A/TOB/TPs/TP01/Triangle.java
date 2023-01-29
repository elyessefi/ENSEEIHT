
public class Triangle {
    public static void main (String[] args){
        Point p1 = new Point(2,3);  //creer un point et l'attacher au poigne p1
        Point p2 = new Point(9,6);  //creer un point et l'attacher au poigne p2
        Point p3 = new Point(4,11); //creer un point et l'attacher au poigne p3

        Point barycentre;
        double bx = (p1.getX()+p2.getX()+p3.getX())/3;
        double by = (p1.getY()+p2.getY()+p3.getY())/3;
        barycentre = new Point(bx, by);
    }
}
