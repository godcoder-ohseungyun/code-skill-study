package librarySkill.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 일급 컬랙션이나 vo는 방어적 복사를 통해 getter시 원문을 보호한다
 * <p>
 * List나 Map만 가능함으로 Set 같은 기타 컬랙션은 해당 형식으로 변경 후 활용한다
 */
public class UnmodifiableCopy {

    static class Log {

    }

    private final Map<String, Integer> map = new HashMap<>();

    private final List<Log> logs = new ArrayList<>();
    private final Set<Log> logs2 = new HashSet<>();

    public Map<String, Integer> getMap() {
        //TODO: 방어적 복사를 이용해 외부로 부터 보호 Map
        return Collections.unmodifiableMap(map);
    }

    public List<Log> getLogs() {
        //TODO: 방어적 복사를 이용해 외부로 부터 보호 List
        return Collections
                .unmodifiableList(logs);
    }

    public List<Log> getLogs2() {
        //TODO: 방어적 복사를 이용해 외부로 부터 보호 Set은 List로 변경 후 사용가능
        return Collections
                .unmodifiableList(logs2.stream().collect(Collectors.toList()));
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
