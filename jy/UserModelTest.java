package jy;



public class UserModelTest {

    private String userId;
    private String userNm;

    private Type userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserType() {
        return userType.getTypeValue();
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }



}
