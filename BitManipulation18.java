
public class BitManipulation18 {
  public static void oddOrEven(int n) {
    int bitMask = 1;
    if ((bitMask & n) == 0) { // even
      System.out.print("Even number");
    } else { // odd
      System.out.print("Odd number");
    }
  }

  public static int getBit(int n, int i) {
    int bitMask = 1 << i;
    if ((n & bitMask) == 0) {
      return 0;
    } else {
      return 1;
    }
  }

  public static int setBit(int n, int i) {
    int bitMask = 1 << i;
    return n | bitMask;
  }

  public static int clearBit(int n, int i) {
    int bitMask = ~(1 << i);
    return n & bitMask;
  }

  public static int updateBit(int n, int i, int newBit) {
    n = clearBit(n, i);
    int bitMask = newBit << i;
    return n | bitMask;

    // if(newBit == 0){
    // return clearBit(n, i);
    // } else {
    // return setBit(n, i);
    // }

  }

  public static int clearLastIBits(int n, int i) {
    int bitMask = -1 << i;
    return n & bitMask;
  }

  public static int clearRangeOfBits(int n, int i, int j) {
    int bitMask = (-1 << j + 1) | ((1 << i) - 1);
    return n & bitMask;
  }

  public static boolean isNumberPowerof2(int n) {
    return (n & (n - 1)) == 0;
  }

  public static int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
      if ((n & 1) != 0) {
        count++;
      }
      n >>= 1;
    }
    return count;
  }

  public static int fastExponentiation(int a, int n) {
    int ans = 1;
    while (n > 0) {
      if ((n & 1) != 0) {
        ans = ans * a;
      }
      a = a * a;
      n = n >> 1;
    }
    return ans;
  }

  public static int modularExponentiation(int a, int n, int p) {
    // calculate pow(a, n) % p
    int ans = 1;
    while (n > 0) {
      if ((n & 1) != 0) {
        ans = ans * a;
      }
      a *= a;
      n >>= 1;
    }
    return ans % p;
  }

  public static void swap(int a, int b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    System.out.println(a + "," + b);
  }

  public static int add1(int n) {
    return -~n;
  }

  public static char upperToLowerCase(char ch) {
    return (char) (ch | ' ');
  }

  public static void main(String[] args) {
    System.out.println(clearRangeOfBits(2483, 2, 7));
  }
}