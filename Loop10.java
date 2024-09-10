


public class Loop10 {
    public static int reverseNumber(int n) {
        int reverse = 0;
        while (n > 0) {
            int lastDig = n % 10;
            reverse = reverse * 10 + lastDig;
            n = n / 10;
        }
        return reverse;
    }

    public static int fac(int n) {
        int fac = 1;
        for (int i = n; i >= 2; i--) {
            fac *= i;
        }
        return fac;
    }

    public static boolean checkPrime(int n) {
        if (n < 0) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void binaryToDecimal(int n) {
        int dec = 0;
        int pow = 0;
        while (n != 0) {
            int lastDig = n % 10;
            dec = dec + lastDig * (int) Math.pow(2, pow);
            n = n / 10;
            pow++;
        }
        System.out.println(dec);
    }

    public static void DecimalToBinary(int n) {
        int bin = 0;
        int pow = 0;
        while (n != 0) {
            int rem = n % 2;
            bin = rem * (int) Math.pow(10, pow) + bin;
            n = n / 2;
            pow++;
        }
        System.out.println(bin);
    }

    public static void leapYear(int n) {
        System.out.print("Input the year: ");
        int year = 1900;
        boolean x = (year % 4) == 0;
        boolean y = (year % 100) != 0;
        boolean z = ((year % 100 == 0) && (year % 400 == 0));
        if (x && (y || z)) {
            System.out.println(year + " is a leap year");
        } else {
            System.out.println(year + " is not a leap year");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 101; i++) {
            if (checkPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }
}