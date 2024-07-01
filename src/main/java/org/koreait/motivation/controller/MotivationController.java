package org.koreait.motivation.controller;


import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;

public class MotivationController {

    int lastId;
    List<Motivation> motivations;

    public MotivationController() {
        lastId = 0;
        motivations = new ArrayList<>();
    }

    public void add() {
        int id = lastId + 1;
        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

        Motivation motivation = new Motivation(id, body, source);

        motivations.add(motivation);

        System.out.printf("%d번 motivation이 등록 되었습니다\n", id);
        lastId++; // 마지막 번호 증가
    }

    public void list() {
        if (motivations.size() == 0) {
            System.out.println("등록된 motivation 없음");
            return;
        }
        System.out.println("== motivation list ==");
        System.out.printf("  id   //   source   //   body  \n");
        System.out.println("=".repeat(35));

        for (int i = motivations.size() - 1; i >= 0; i--) {
            Motivation motivation = motivations.get(i);

            if (motivation.getSource().length() > 7) {
                System.out.printf("   %d  //    %s    //    %s  \n", motivation.getId(), motivation.getSource().substring(0, 5) + "...", motivation.getBody());
                continue;
            }

            System.out.printf("   %d  //    %s     //    %s  \n", motivation.getId(), motivation.getSource(), motivation.getBody());
        }
    }

    public void delete(Rq rq) {
        System.out.println("delete 실행");

        int id;

        try {
            id = Integer.parseInt(rq.getParams("id"));
            // try-catch를 하지 않을 시 정수를 입력하지 않았을 경우 에러 발생하므로 try-catch 사용하여
            // 에러발생시 정수 입력 오류라는 문구가 뜨도록 설정
        } catch (NumberFormatException e) {
            System.out.println("정수 입력 오류");
            return;
        }

        Motivation motivation = findById(id);

        if (motivation == null) {
            System.out.printf("%d번 motivation은 없어\n", id);
            return;
        }

        motivations.remove(motivation);

        // 인덱스를 지운것이 아닌 object를 지움으로써 id 뿐아니라 다른
        // source나 motivation도 같이 지움
        System.out.printf("%d번 motivation을 삭제했습니다\n", id);
    }

    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            // arraylist 배열에 하나하나 비교하여 찾고자하는 id가 있으면 return 없으면 null

            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }
}