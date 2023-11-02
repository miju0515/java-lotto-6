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
    public static int bonusNumber;
    public static int result;
    public static List<Integer> rewardList = Arrays.asList(0,0,0,0,0);

    void StartGame(){
        System.out.println("구입금액을 입력해 주세요.");
        inputPurchase();
        System.out.println(count+"개를 구입했습니다.");
        printLottos();
        System.out.println("당첨 번호를 입력해 주세요.");
        inputWinningNumber();
        System.out.println("보너스 번호를 입력해 주세요.");
        inputBonusNumber();
        calculateReward();
        printReward();
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

    void inputBonusNumber(){
        bonusNumber = Integer.valueOf(readLine());
    }

    void calculateReward(){
        for(Lotto lotto : lottos){
            int correct = 0;
            for(int i=0;i<6;i++){
                if(lotto.contain(winningLotto.returnNumber(i))){
                    correct++;
                }
            }
            int here=0;
            if(correct==3){
                here = rewardList.get(0);
                rewardList.set(0,here+1);
            }
            if(correct==4){
                here = rewardList.get(1);
                rewardList.set(1,here+1);
            }
            if(correct==5 && !lotto.contain(bonusNumber)){
                here = rewardList.get(2);
                rewardList.set(2,here+1);
            }
            if(correct==5 && lotto.contain(bonusNumber)){
                here = rewardList.get(3);
                rewardList.set(3,here+1);
            }
            if(correct==6){
                here = rewardList.get(4);
                rewardList.set(4,here+1);
            }
        }
    }


    void printReward(){
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 ("+reward.fifth.money+") - "+rewardList.get(0)+"개");
        System.out.println("4개 일치 ("+reward.fourth.money+") - "+rewardList.get(1)+"개");
        System.out.println("5개 일치 ("+reward.third.money+") - "+rewardList.get(3)+"개");
        System.out.println("5개 일치, 보너스 볼 일치 ("+reward.second.money+") - "+rewardList.get(2)+"개");
        System.out.println("6개 일치 ("+reward.first.money+") - "+rewardList.get(4)+"개");
    }


}
