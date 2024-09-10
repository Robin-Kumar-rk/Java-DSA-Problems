
public class Error {
    public class ExceptionHandlingExample {
        public static void main(String[] args) {
            try {
                int result = divide(10, 0);
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Caught an ArithmeticException: " + e.getMessage());
            } finally {
                System.out.println("This is the finally block, it always executes.");
            }
        }
    
        public static int divide(int a, int b) throws ArithmeticException {
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return a / b;
        }
    }

}

