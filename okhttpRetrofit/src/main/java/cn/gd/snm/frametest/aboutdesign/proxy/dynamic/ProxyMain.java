package cn.gd.snm.frametest.aboutdesign.proxy.dynamic;

public class ProxyMain {
    public static void main(String[] args){
        LogHandler logHandler=new LogHandler();
        UserManager userManager=(UserManager)logHandler.newProxyInstance(new UserManagerImpl());
        userManager.addUser("1111", "张三");
//        userManager.delUser("asdfsadf");
    }
}
