package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class LottoGame {
    public static int purchase = 0;
    public static int count;
    public static List<Lotto> lottos = new ArrayList<>();
    public static Lotto winningLotto;

    void StartGame(){
        System.out.println("구입금액을 입력해 주세요.");
        inputPurchase();
        System.out.println(count+"개를 구입했습니다.");
        printLottos();
        System.out.println("당첨 번호를 입력해 주세요");
        inputWinningNumber();
    }

    void inputPurchase(){
        checkValidPurchase(readLine());
        count = purchase/1000;
    }

    void checkValidPurchase(String input){
        try{
            purchase = Integer.valueOf(input);
        }catch(NumberFormatException e){
            System.out.println("[ERROR] 투입 금액은 숫자여야합니다.");
            inputPurchase();
        }
        if(purchase%1000!=0){
            System.out.println("[ERROR] 투입 금액은 1,000원 단위여야합니다.");
            inputPurchase();
        }
    }

    List<Integer> makeLotto(){
        List<Integer> lotto = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(lotto);
        return lotto;
    }

    void printLottos(){
        for(int i=0;i<count;i++){
            List<Integer> lotto = makeLotto();
            lottos.add(new Lotto(lotto));
            System.out.println(lotto);
        }
    }

    void inputWinningNumber(){
        String input = readLine();
        List<Integer> splitNumber = new ArrayList<>();
        for(int i=0;i<6;i++){
            splitNumber.add(Integer.valueOf(input.split(",")[i]));
        }
        winningLotto = new Lotto(splitNumber);
    }


}
