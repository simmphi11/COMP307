
public abstract class Iris {
	
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String name;
   
    
    public Iris(String name, double sepalLength, double sepalWidth, double petalLength, double petalWidth ){
    	  this.petalLength = petalLength;
    	  this.petalWidth = petalWidth;
    	  this.sepalLength = sepalLength;
    	  this.sepalWidth = sepalWidth;
    	  this.name = name;
    }
    
    
    public double getSepalLength(){
    	return this.sepalLength;
    }
    
    
    public double getSepalWidth(){
    	return this.sepalWidth;
    }
    
    
    public double getPetalLength(){
    	return this.petalLength;
    }
    
    
    public double getPetalWidth(){
    	return this.petalWidth;
    }
    
    
    public String getName(){
    	return this.name;
    }
   
}
