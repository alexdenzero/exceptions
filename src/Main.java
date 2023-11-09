import exception.WrongLoginException;
import exception.WrongPasswordException;

public class Main {
    private  static  final  String VALIDATE_PATTERN = "^[a-zA-Z0-9_]+$";
    public static void main(String[] args) {
        check("login","pass","pass");
        check("login%%%%%","pass","pass");
        check("login","pass%%%%%%","pass");
        check("login11111111111111111111111","pass","pass");
        check("login","pass","pass111111");
    }
    private static boolean check(String login, String pass, String confirmPass){
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPass(pass, confirmPass);
        } catch (WrongLoginException e) {
            e.printStackTrace();
            System.out.println("Ошибка с введенным логином: " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            e.printStackTrace();
            System.out.println("Ошибка с введенным паролем: " + e.getMessage());
            isValid = false;

        }
        return isValid;
    }
    private static void checkLogin(String login) throws WrongLoginException {
        if(!login.matches(VALIDATE_PATTERN)){
            throw new WrongLoginException("Логин должен содержать в себе только латинские буквы, цифры и знаки подчеркивания.");
        } else if(login.length()>20){
            throw new WrongLoginException("Логин не может быть длинее 20 символов");
        }
    }
    private static void checkPass(String pass,String confirmPass) throws WrongPasswordException{
        if (!pass.matches(VALIDATE_PATTERN)){
            throw new WrongPasswordException("Пароль должен содержать в себе только латинские буквы, цифры и знаки подчеркивания.");
        } else if(pass.length() > 20) {
            throw new WrongPasswordException("Пароль не может быть длинее 20 символов");
        } else  if (!pass.equals(confirmPass)){
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }
}