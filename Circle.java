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
	@Override
	public String toString() {
		
		String res="Circle[color : "+this.color.toString()
		+",pattern : "+this.pattern.toString()+",radius :"+this.redius+"]";
		
		return res;
	}
	public boolean equals(Circle obj)
	{
		boolean res=false;
		
		if(this.color.equals(obj.color) && this.pattern.equals(obj.pattern)
				&& this.redius.equals(obj.redius))
			res=true;
		
		return res;
	}

	

}
