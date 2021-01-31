import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 저장소 : 메모리, 캐시, 파일, 데이터베이스
 * 데이터베이스 : DBMS -> 데이터 하우스
 * oralc, posgtresql, mysql, maria, tibero, sqlite
 *
 */



/**
 * Project : yong
 * Create by IntelliJ IDEA
 * User: otrodevym
 * Date: 2021/1/30/0030
 * Time: 오후 7:01:31
 */
public class Crud {
    public static void main(String[] args) {
        Menu m = new Menu();
        System.out.println("-----시작-----");
        boolean check = true;
        while (check) {
            try {
                m.printMenu();
                BufferedReader sbr = new BufferedReader(new InputStreamReader(System.in));
                String input = sbr.readLine();
                check = m.menuChoose(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Person {
    private int num;
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "num : " + this.getNum() + ", name : " + this.getName() + ", age : " + this.getAge();
    }
}

class Menu {
    BufferedReader br;
    private List<Person> list;

    public Menu() {
        this. list = new ArrayList<>();
    }

    public void printMenu() {
        System.out.println("---menu---");
        System.out.println("1. 조회");
        System.out.println("2. 저장");
        System.out.println("3. 수정");
        System.out.println("4. 삭제");
        System.out.println("5. 종료");
        System.out.println("------");
    }

    public boolean menuChoose(String input) {
        switch (input) {

            case "1":
                dataRead();
                break;

            case "2":
                dataSave();
                break;

            case "3":
                dataEdit();
                break;

            case "4":
                dataDel();
                break;
            case "5":
                System.out.println("잘가~");
                return false;
            default:
                System.out.println("잘못된 메뉴 선택");
        }
        return true;
    }

    private void dataDel() {
        System.out.println("삭제할 번호 입력");
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                this.list.remove(num);
            }else {
                dataDel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataEdit() {
        try {
            System.out.println("선택할 번호 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine());
            if(valid(num)) {
                System.out.println("이름 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setName(br.readLine());
                System.out.println("나이 입력");
                br = new BufferedReader(new InputStreamReader(System.in));
                this.list.get(num).setAge(br.readLine());
            } else {
                dataEdit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dataSave() {
        Person p = new Person();
        try {
            System.out.println("이름 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setName(br.readLine());
            System.out.println("나이 입력");
            br = new BufferedReader(new InputStreamReader(System.in));
            p.setAge(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        p.setNum(this.list.size()); // 버그 같은 번호 발생
//        int num = this.list.isEmpty() ? 0 :
//                this.list.stream().max(Comparator.comparingInt(Person::getNum)).get().getNum() + 1;
//
        int num2 = this.list.isEmpty() ? 0 : this.list.get(this.list.size() - 1).getNum() + 1;
//
//        p.setNum(num);
        p.setNum(num2);

        this.list.add(p);
        this.list.sort(Comparator.comparing(Person::getNum));
    }

    private void dataRead() {
        System.out.println("조회 시작");
        for (Person p:
                this.list) {
            System.out.println(p.toString());
        }
        System.out.println("조회 끝");
    }

    private boolean valid(int num) {
        // 배열 범위
        if (this.list.size() <= num) {
            System.err.println("없는 번호 입니다.");
            return false;
        }

        return true;
    }
}
