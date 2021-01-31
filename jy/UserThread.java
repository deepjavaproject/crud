package jy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class UserThread implements Runnable{

    private BlockingQueue< UserModelTest > requestQueue;
    private Map<String,String> userMap = new HashMap<String,String>();

    public UserThread ( BlockingQueue< UserModelTest > q ) {
        this.requestQueue = q;
    }

    @Override
    public void run() {

        UserModelTest userModel;

        while(true) {
            try {
                userModel = requestQueue.take();

                switch (userModel.getUserType()) {

                    case "create" :

                        userMap.put(userModel.getUserId(), userModel.getUserNm());

                        break;
                    case "search" :
                        for( Map.Entry<String, String> entry : userMap.entrySet() ) {
                            System.out.println("userId : "+entry.getKey()+" : "+"userNm : "+entry.getValue());
                        }

                        break;
                    case "update" :

                        if( userMap.containsKey( userModel.getUserId() ) ) {

                            userMap.replace(userModel.getUserId(), userModel.getUserNm());
                        }
                        break;

                    case "delete" :

                        if( userMap.containsKey( userModel.getUserId() ) ) {

                            userMap.remove(userModel.getUserId());
                        }
                        break;
                    default:
                        break;
                }

            } catch (InterruptedException e) {
                System.out.println(""+e);
            }
        }
    }


}
