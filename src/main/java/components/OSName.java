package components;

public class OSName {
    String path;
    String osName = System.getProperty("os.name");

    public String getOsName(){
       if(osName.length()>0&&osName.contains("Windows")){
            path = System.getProperty("user.dir") + "\\src\\main\\resources";
        }else{
            path = System.getProperty("user.dir") + "/src/main/resources";
        }
       return path;
    }

}
