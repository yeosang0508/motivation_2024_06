package org.koreait.motivation.controller;

import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.util.ArrayList;
import java.util.List;

public class MotivationController {
//
    int lastId;
    List<Motivation> motivations;
    // 가변적이기에 arraylist 사용

    // 메서드내가 아니기에 지역변수가 아닌 전역변수
    // 프로그램이 끝나면 사라짐 (영속성이 없다)


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
        System.out.printf("%d번 motivation을 삭제했습니다\n", id);
    }

    public void edit(Rq rq) {
        System.out.println("edit 실행");

        int id;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e) {
            System.out.println("정수 입력 오류");
            return;
        }

        Motivation motivation = findById(id);

        if (motivation == null) {
            System.out.printf("%d번 motivation은 없어\n", id);
            return;
        }
        // 불러온 motivation의 인스턴스변수에 접근
        System.out.println("body(기존) : " + motivation.getBody());
        System.out.println("source(기존) : " + motivation.getSource());

        System.out.print("body : ");
        String body = Container.getScanner().nextLine();
        System.out.print("source : ");
        String source = Container.getScanner().nextLine();

        // 불러온 motivation의 인스턴스변수 수정
        motivation.setBody(body);
        motivation.setSource(source);

        System.out.printf("%d번 motivation을 수정했습니다\n", id);

    }

    // 입력된 id와 일치하는 motivation 찾기
    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {
            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }


}