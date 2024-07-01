package org.koreait.motivation.controller;


import org.koreait.Container;
import org.koreait.Rq;
import org.koreait.motivation.entity.Motivation;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
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


    private Motivation findById(int id) {
        for (Motivation motivation : motivations) {

            if (motivation.getId() == id) {
                return motivation;
            }
        }
        return null;
    }

    public void edit(Rq rq) {
        int id;
        String source;
        String body;

        try {
            id = Integer.parseInt(rq.getParams("id"));
        } catch (NumberFormatException e) {
            System.out.println("정수 입력 오류");
            return;
        }

        Motivation motivation = findById(id);

        if(motivation == null) {
            System.out.printf("%d번 motivation은 없다\n", id);
            return;
        }


        System.out.print("body: ");
        body = motivation.getBody();
        body = Container.getScanner().nextLine();
        System.out.print("source: ");
        source = motivation.getSource();
        source = Container.getScanner().nextLine();

        // 내용을 입력 받기만 하고 그 내용을 받아주지는 못함..
        // 첫번째 motivation.getBody()를 하지 않아도 됨..
        // id로 findById에서 같은 내용을 찾고 그 motivation을 return 시켜주었기 때문에



        System.out.printf("%d번 motivaiton이 수정 되었습니다.\n", id);

    }
}