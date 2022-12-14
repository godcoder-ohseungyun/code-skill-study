package librarySkill.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableCopy {

    private final Map<String, Integer> map = new HashMap<>();

    public Map<String, Integer> getMap() {
        //TODO: 방어적 복사를 이용해 외부로 부터 보호
        return Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        UnmodifiableCopy unmodifiableCopy = new UnmodifiableCopy();

        try {
            unmodifiableCopy.getMap().put("비허용추가", 1);
        } catch (Exception e) {
            System.out.println("읽기 전용 입니다");
        }

    }
}
