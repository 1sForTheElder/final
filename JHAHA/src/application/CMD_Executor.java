package application;
public class CMD_Executor{  
  
    public void execCommand(String[] arstringCommand) {  
        for (int i = 0; i < arstringCommand.length; i++) {  
        }  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public void execCommand(String arstringCommand) {  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
  
    public void cmd(){  
//        //打开记算器  
//        String[] arstringCommand = new String[] {  
//                "cmd ",   
//                "/k",  
//                "start", // cmd Shell命令  
//                "calc"  
//        };  
//        execCommand(arstringCommand);  
//        //打开记事本  
        String cmd = "cmd /k start call running.bat";  
        execCommand(cmd);  
    }  
      
//    public static void main(String[] args){  
//        new eeee().cmd();  
//    }  
}  