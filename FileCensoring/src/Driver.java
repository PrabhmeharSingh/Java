import java.io.File;

public class Driver {
	public static void main(String[] args)
	{
		File f1=new File("src.txt"),f2=new File("dest.txt"),f3=new File("bad.txt");
		Censor.censor(f1, f2, f3);
	}
}
