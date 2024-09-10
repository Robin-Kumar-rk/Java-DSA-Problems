
public class Conditional9 {
    public static boolean isLeapYear(int year){
      // leap year comes after 4 years but we do'nt count after 100 years but we
      // count after 400 tears
        if( (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)){
           return true;
        } else {
            return false;
        }
    }
    public static int commonGreatestDivisor(int a, int b){
        while(a!=b){
            if(a>b){
                a=a-b;
            } else {
                b=b-a;
            }
        }
        return a;  // or return b;
    }
    public static void main(String[] args) {
     System.out.println(commonGreatestDivisor(4, 19));
    }
}
