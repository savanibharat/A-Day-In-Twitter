package edu.sjsu.cmpe.ADayInTwitter.BaseLineCoding;

// TODO: Auto-generated Javadoc
/**
 * The Class PipeSeperator.
 */
public class PipeSeperator {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		String str="Who is C.J. Ross|0|05:33:33|410|381|Malik X|en";
		
		String newStr[]=str.split("\\|");
		System.out.println(newStr.length);
		for (int i = 0; i < newStr.length; i++) {
		//	System.out.println(newStr[i]);
		}
		//System.out.println("|");
		//System.out.println("||");
		
		String s="155k";
		if(s.matches("[0-9]")){
			System.out.println(s);
		}
		
	}
}
