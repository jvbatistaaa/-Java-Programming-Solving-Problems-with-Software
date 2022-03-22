import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int n = 0;
        for (Point p : s.getPoints()){
            n++;
        }
        return n;
    }

    public double getAverageLength(Shape s) {
        int n = getNumPoints (s);
        double perim = getPerimeter (s);
        double avg = perim/n;
        return avg;
    }

    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double max = 0;
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > max) {
                max = currDist;
            }
            prevPt = currPt;
        }
        return max ;
    }

    public double getLargestX(Shape s) {
        Point prevPt = s.getLastPoint();
        int lastPointX = prevPt.getX();
        double maxX = (double) lastPointX;
        
        for (Point currPt : s.getPoints()) {
           int x = currPt.getX();
            if (x > maxX) {
                maxX = (double) x;
            }
            prevPt = currPt;
        }
        return maxX ;
        
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if(perim > largestPerim) {
                largestPerim = perim;
            }
            
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        
        File lgstFile = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if(perim > largestPerim) {
                largestPerim = perim;
                lgstFile = f;
            }
            
        }
        
        return lgstFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource("datatest4.txt");
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int points = getNumPoints (s);
        System.out.println(points + " points");
        double avg = getAverageLength(s);
        System.out.println("Average Length: " + avg);
        System.out.println("Largest Side : " + getLargestSide(s));
        System.out.println("Largest X point : " + getLargestX(s));
        
        
    }
    
    public void testPerimeterMultipleFiles() {
        double lg = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter is: " + lg);
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("The file with largest perimeter is: " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
