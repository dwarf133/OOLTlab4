import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Main {

    private static int[] first(int a, int b) {
        int[] res = new int[2];
        res[0] = evklid(a, b);
        res[1] = (a*b) / res[0];
        return res;
    }
    private static int evklid(int a, int b) {
        if ( a == 0 || b == 0) {
            return (a + b);
        }
        else {
            if (a > b) a = a % b;
            else b = b % a;
            return evklid(a, b);
        }
    }

    private static int[] second(int n) {
        System.out.printf("%d ", n % 10);
        if (n < 10) {
            int[] ans = new int[3];
            ans[0] = n;
            ans[1] = 1;
            ans[2] = n;
            return ans;
        }
        else {
            int[] ans = second(n / 10);
            ans[0] = n;
            ans[1] += 1;
            ans[2] += n % 10;
            return ans;
        }
    }

    private static int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    private static int third(int n) {
        int count = 0;
        while (n > 0) {
            count += 1;
            n -= digitSum(n);
        }
        return count;
    }

    private static int fourth(String s) {
        if (s.isEmpty()) return 0;
        String last = s.length() >= 2 ? s.substring(s.length()-2, s.length()-1) : s;
        String car = s.length() >= 2 ? s.substring(0, s.length()-2) : s;
        try {
            int i = Integer.parseInt(last);
            return fourth(car) + 1;
        }
        catch (NumberFormatException ex) {
            return fourth(car);
        }
    }

    private static void runFirst(Scanner in) {
        System.out.print("Введите a и b: ");
        int a = in.nextInt();
        int b = in.nextInt();
        int[] res = first(a, b);
        System.out.printf("НОД(%d, %d): %d НОК(%d, %d): %d", a, b, res[0], a, b, res[1]);
    }

    private static void runSecond(Scanner in) {
        System.out.print("Введите n: ");
        int n = in.nextInt();
        System.out.println("Состоит из цифр:");
        int[] res = second(n);
        System.out.println();
        System.out.printf("Колличество цифр: %d, сумма цифр: %d", res[1], res[2]);
    }

    private static void runThird(Scanner in) {
        System.out.print("Введите число n: ");
        int n = in.nextInt();
        System.out.printf("Необходимо %d итераций", third(n));
    }

    private static void runFourth(Scanner in) throws IOException {
        System.out.println("Введите строку:");
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String input = bi.readLine();
        input = input.replaceAll("\\\\n", "");
        System.out.printf("Найденно %d чисел в строке", fourth(input));
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задания: ");
        int taskNumber = in.nextInt();
        switch (taskNumber) {
            case 1: runFirst(in);
            case 2: runSecond(in);
            case 3: runThird(in);
        }
    }
}
