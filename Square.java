package ir.javacup.paint;


public class Square extends Shape {

	Integer length;

	public Square(Color c, Pattern p, int length) {
		super(c, p);
		this.length = length; // autoboxing
	}



	public boolean equals (Square obj){
		boolean a = this.color.equals(obj);
		boolean b = this.pattern.equals(obj);
		boolean c = this.length.equals(obj);

		if (a==true && b==true && c==true){

			System.out.println();
		}
		return false;
	}


}
