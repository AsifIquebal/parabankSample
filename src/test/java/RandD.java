import org.testng.annotations.Test;

public class RandD {

    @Test
    public void test123(){
        System.out.println(System.getProperty("headless"));
        if(System.getProperty("headless").equalsIgnoreCase("yes")){
            System.out.println("Headless YES");
        }else {
            System.out.println("Headless NO");
        }
    }

}
