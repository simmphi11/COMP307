
public class DistanceIris implements Comparable<DistanceIris> {

	private Iris iris;
	private double distance;
	
	public DistanceIris(Iris iris, double distance){
		this.distance = distance ;
		this.iris = iris;
	}
	
	
	public String getClassType(){
		return this.iris.getName();
	}
	
	
	public double getDistance(){
		return this.distance;
	}
	
	
	@Override
	public int compareTo(DistanceIris o){
		if(this.distance > o.distance){
			return 1;
		}else if(this.distance < o.distance){
			return -1;
		}else{
			return 0;
			}
	}
	
}
