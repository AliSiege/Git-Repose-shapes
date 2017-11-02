package ir.javacup.paint;

public class Circle extends Shape {
    /* the circle has extended of shap becaue there 
    are some fields in it and 
    we need them for our shape*/
	Double redius;

	public Circle(Color c, Pattern p, double r) {
		super(c, p);
		this.redius = r;
	}
	

}
