package lotto;

public class Exception {
    // 입력이 숫자인지
    boolean checkInputIsNumber(String input){
        try{
            Integer.valueOf(input);
            return true;
        }catch(NumberFormatException e){
            System.out.println("[ERROR] 입력이 숫자가 아닙니다.");
            return false;
        }
    }
    // 1~45 사이 인지
    boolean checkInputInRange(String input){
        int change = Integer.valueOf(input);
        if(change<1 || change>45){
            System.out.println("[ERROR] 로또 번호의 숫자는 1에서 45까지입니다.");
            return false;
        }
        return true;
    }
    // 1000 단위인지
    // 6개인지
    // 중복이 없는지
    // 보너스와 숫자간 중복이 없는지
}
