package cn.gd.snm.frametest.aboutdesign.proxy.dynamic;

public interface UserManager {
    public void addUser(String userId, String userName);

    public void delUser(String userId);

    public String findUser(String userId);

    public void modifyUser(String userId, String userName);
}
