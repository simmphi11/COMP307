

public class Factory {
	
	

	private Factory(){
		
	}
	
	public static Iris setClass(String nameInFile, double sepalLength, double sepalWidth, double petalLength, double petalWidth){
	     switch (nameInFile){
	     case "Iris-virginica": 
	    	 return new IrisVirginica(nameInFile,sepalLength,sepalWidth,petalLength,petalWidth);
	     case "Iris-versicolor":
	    	 return new IrisVersicolor(nameInFile, sepalLength,sepalWidth,petalLength,petalWidth);
	     case "Iris-setosa":
	    	 return new IrisSetosa(nameInFile, sepalLength,sepalWidth,petalLength,petalWidth);
	     default: 
	    	 System.out.println("Invalid class name in the text file");
	    	 return null;
	    	
	     }
	}	
}
