package jy;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Thread {
    private static ExecutorService sendWorkerPool;

    public static void main(String[] args) throws InterruptedException {

        /*
         * #1. 사용자 관리 메뉴 작성
         * #2. 사용자 메뉴에 따른 데이터 구성 처리.
         * #3. 추가 , 수정 , 삭제 , 조회 기능 처리.
         * #4. 쓰레드에 활성화된 queue 에 데이터 add 처리
         * #5. 활성화된 queue 에 take 하여 이벤트 분기 처리
         *
         * */

        BlockingQueue< UserModelTest > requestQueue = new ArrayBlockingQueue<UserModelTest>(30);
        init(requestQueue);

        System.out.println("start main");
        InputMenu menu = new InputMenu ( requestQueue );
        Scanner scan = new Scanner(System.in);

        menu.selectMenu(scan);

    }

    public static void init( BlockingQueue<UserModelTest> userModel ) {

        UserThread userThread = new UserThread(userModel);

        sendWorkerPool = Executors.newFixedThreadPool( 1 );
        sendWorkerPool.execute( userThread );
    }
}
