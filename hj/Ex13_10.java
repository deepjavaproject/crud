package hj;
import java.util.*;
/*
 * 
 */
public class Ex13_10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("몇개의 수를 저장할 공간을 생성하시나요 ? ");
        int num1 = sc.nextInt();

        int[] arr = new int[num1];
        for (int i = 0; i < num1; i++) {
            System.out.println("저장할 숫자");
            arr[i] = sc.nextInt();
        }
        while (true) {
            System.out.println("2.읽기 3.고치기, 4.삭제하기(종료시1입력)");
            int num = sc.nextInt();
            if (num == 1)
                break;
            if(num<1||num>4) {
                System.out.println("잘못된 입력입니다 다시 입력해주세요");
                continue;
            }
            switch (num) {
                case 2:
                    System.out.println(Arrays.toString(arr));
                    break;
                case 3:

                    System.out.println("몇번째수를 바꾸고 싶나요?");
                    int c = sc.nextInt();
                    System.out.println("어떤수로 바꾸고싶나요?");
                    int k = sc.nextInt();
                    arr[c - 1] = k;
                    break;
                case 4:
                    for (int i = 0; i <num1; i++) {
                        arr[i] = 0;
                    }
                    System.out.println(Arrays.toString(arr));
                    break;
            }
        }
    }
}
