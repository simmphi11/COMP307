import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {
	
	    private double sepalLenRange;
	    private double sepalWidRange;
	    private double petalLenRange;
	    private double petalWidRange;
		private List<Iris> trainingIris;
		private List<Iris> testIris;
		private List<DistanceIris> distanceIrisList;
		private int k; 
		
	
	public Main(String fileOne, String fileTwo, int k){
		this.k = k;
		File training = new File(fileOne);
		File test = new File(fileTwo);
       trainingIris =  readFile(training);	
       testIris = readFile(test);
       findRange(testIris);
       //printOutForDisplay(trainingIris,testIris);
       int total = 0;
       for(Iris t: testIris){
    	   String classifiedName = classiffy(t);
    	   System.out.println("Actual class: " + t.getName() + "     Predicted class: " + classifiedName);
    	   if(classifiedName.equals(t.getName())){
    		   total++;
    	   }
       }
       double p = ((double)total/(double)testIris.size())*100;
	   System.out.println("\n");
       System.out.println("Total Correct: " + total + "/" + testIris.size() + "     K value = " + this.k);
       System.out.printf("Percent correct %f\n",p);
	}

	
	
	//Main method reads in the arguments of the command line	
	public static void main(String[] args) throws IOException {
		if (args.length > 2) {
			String trainingSetFile = args[0];
			String testSetFile = args[1];
			int k = Integer.parseInt(args[2]);
			new Main(trainingSetFile, testSetFile,k);
		} else {
			System.out.println("Invalid Argument Length");
		}
	}
	
	
	
	//reads in the files and returns a list of iris
	@SuppressWarnings("finally")
	public List<Iris> readFile(File file){
		Scanner scan = null;
		List<Iris> irisList = new ArrayList<Iris>();
		try{
			 scan = new Scanner(new BufferedReader(new FileReader(file)));
			 while(scan.hasNextLine()){
				  double sepalLength = scan.nextDouble();
				  double sepalWidth = scan.nextDouble();
				  double petalLength = scan.nextDouble();
				  double petalWidth = scan.nextDouble();
				  String classType = scan.next();
				  Iris iris = Factory.setClass(classType,sepalLength , sepalWidth, petalLength,petalWidth); //code to test class types
				  irisList.add(iris);
				  scan.nextLine();
			 }
			 scan.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			scan.close();
			return irisList;
		}
	}

	 
	 
	 //used to print to the console, for debug purposes only
	 public void printOutForDisplay(List<Iris> trainingList, List<Iris> testList){
		 for(Iris i: trainingList){
			 System.out.println(i.getName());
		 }
		 for(Iris i: testList){
			 System.out.println(i.getName());
		 }	
	 }
	 
	 
	 
	 //classify the test vector in the the appropriate class as a string
	 public String classiffy(Iris testIris){
		 distanceIrisList = new ArrayList<DistanceIris>();
		 for(Iris trainIris: trainingIris){
			 double dist = distanceCalculator(testIris, trainIris); 
			 distanceIrisList.add(new DistanceIris(trainIris, dist));
		 }
		 List<String> kNearest = new ArrayList<String>();
		 Collections.sort(distanceIrisList);
		 for(int i = 0; i < this.k; i++){
			 kNearest.add(distanceIrisList.get(i).getClassType());
		 }		 
		 return getTypeFromVote(kNearest);
	 }
	 
	  
	 
	 //returns string of class with most votes (called in classify)
	 public String getTypeFromVote(List <String> neighbours){
		 HashMap<String, Integer> votes = new HashMap<String, Integer>();
		 votes.put("Iris-virginica", 0);
		 votes.put("Iris-setosa", 0);
		 votes.put("Iris-versicolor", 0);
		 for(String s: neighbours){
			 if(s.equals("Iris-versicolor")){
			     votes.put("Iris-versicolor", votes.get("Iris-versicolor")+1);
			 }else if(s.equals("Iris-setosa")){
				 votes.put("Iris-setosa", votes.get("Iris-setosa")+1);
			 }else if(s.equals("Iris-virginica")){
				 votes.put("Iris-virginica", votes.get("Iris-virginica")+1);
			 }else{
				 System.out.println("problem with class type");
				 return  "Not a valid class name";
			 }
			 
		 }
		 return mostVotes(votes);
	 }
	 
	 
	 
	 //actually does the calculation for the votes and returns a string (called in getTyoefromVote)
	 public String mostVotes(HashMap<String, Integer> votes){
		 String most = "";
		 int countMax = 0;
		 for (Entry<String, Integer> entry: votes.entrySet()){
			 if(entry.getValue() > countMax){
				 countMax = entry.getValue();
				 most = entry.getKey();
			 }
		 }
		 return most;
	 }
	
	 	
	//sets the range fields to the ranges for each vector component
		 public void findRange(List<Iris> testIris2){
		    	double sepalLenMin = 1000;
		    	double sepalLenMax = 0;
		    	double sepalWidMax = 0;
		    	double sepalWidMin = 1000;
		    	double petalLenMin = 1000;
		    	double petalLenMax = 0;
		    	double petalWidMax = 0;
		    	double petalWidMin = 1000;    	
		    	for(Iris i: testIris2){
		    		if(i.getPetalLength()<petalLenMin){
		    			petalLenMin = i.getPetalLength();
		    		}if(i.getPetalLength()>petalLenMax){
		    			petalLenMax = i.getPetalLength();
		    		}if(i.getSepalLength()<sepalLenMin){
		    			sepalLenMin = i.getSepalLength();
		    		}if(i.getSepalLength()>sepalLenMax){
		    			sepalLenMax = i.getSepalLength();
		    		}if(i.getPetalWidth()<petalWidMin){
		    			petalWidMin = i.getPetalWidth();
		    		}if(i.getPetalWidth()>petalWidMax){
		    			petalWidMax = i.getPetalWidth();
		    		}if(i.getSepalWidth()<sepalWidMin){
		    			sepalWidMin = i.getSepalWidth();
		    		}if(i.getSepalWidth()>sepalWidMax){
		    			sepalWidMax = i.getSepalWidth();
		    		}
		    	}
		         this.sepalWidRange = sepalWidMax - sepalWidMin; 
		         this.sepalLenRange = sepalLenMax - sepalLenMin;
		         this.petalLenRange = petalLenMax - petalLenMin;
		         this.petalWidRange = petalWidMax - petalWidMin;
		    }
	 
	 
	 //returns a double with the distances between two iris's
	 public double distanceCalculator(Iris irisFromTest, Iris fromTraining){
		 double sepalLenPart = Math.pow((irisFromTest.getSepalLength() - fromTraining.getSepalLength()), 2)/sepalLenRange; 
		 double sepalWidPart = Math.pow((irisFromTest.getSepalWidth() - fromTraining.getSepalWidth()), 2)/sepalWidRange; 
		 double petalLenPart = Math.pow((irisFromTest.getPetalLength() - fromTraining.getPetalLength()),2)/petalLenRange;
		 double petalWidPart = Math.pow((irisFromTest.getPetalWidth() - fromTraining.getPetalWidth()), 2)/petalWidRange;
		 return Math.sqrt(sepalLenPart + sepalWidPart+ petalLenPart + petalWidPart);
	 }
	 
}







