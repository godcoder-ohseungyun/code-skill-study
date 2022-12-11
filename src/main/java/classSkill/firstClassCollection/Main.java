package classSkill.firstClassCollection;

public class Main {
    public static void main(String[] args) {
        Lotto lotto1 = Lotto.createAuto();
        System.out.println(lotto1);

        Lotto lotto2 = Lotto.createAuto();
        System.out.println(lotto2);

        System.out.println(lotto1.getSameNumberCount(lotto2));
    }
}
