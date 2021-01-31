package jy;



import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InputMenu {

    private BlockingQueue< UserModelTest > requestQueue;

    public InputMenu ( BlockingQueue< UserModelTest > requestQueue ) {
        this.requestQueue = requestQueue;
    }

    public Object selectMenu(Scanner scan) throws InterruptedException {
        UserModelTest userModel = new UserModelTest();
        String userId = "";
        String userNm = "";
        Integer menuValue;

        System.out.println("insert menu : 1. create , 2. serach , 3. update , 4. delete , 5. exit");
        menuValue = scan.nextInt();

        if( menuValue < 1 && menuValue > 5) {
            System.out.println(" pls input value range of 1~5 ");
            return selectMenu(scan);
        }

        switch(menuValue) {

            case 1:

                System.out.print("input UserId : ");
                userId = scan.next();
                System.out.print("input UserNm : ");
                userNm = scan.next();

                userModel.setUserId( userId );
                userModel.setUserNm( userNm );
                userModel.setUserType( Type.CREATE );

                break;

            case 2:

                userModel.setUserType(Type.SEARCH);
                break;

            case 3:

                System.out.println("input UserId : ");
                userModel.setUserId(scan.next());
                System.out.println("input value change to UserNm : ");
                userModel.setUserNm(scan.next());
                userModel.setUserType(Type.UPDATE);
                break;

            case 4:

                System.out.println("input UserId : ");
                userModel.setUserId(scan.next());
                userModel.setUserType(Type.DELETE);
                break;

            case 5:
                System.out.println("exit");
                System.exit(0);
                break;
        };

        requestQueue.put(userModel);
        return selectMenu(scan);
    }
}
