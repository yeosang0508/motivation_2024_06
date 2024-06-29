package org.koreait;

import org.koreait.motivation.controller.MotivationController;
import org.koreait.system.controller.SystemController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public void run() {
        System.out.println("== motivation execution ==");

        SystemController systemController = new SystemController();
        MotivationController motivationController = new MotivationController();

        while (true) {
            System.out.print("command) ");
            String cmd = Container.getScanner().nextLine().trim();

            if (cmd.equals("exit")) {
                systemController.exit();
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력해");
                continue;
            }

            if (cmd.equals("add")) {
                motivationController.add();
            } else if (cmd.equals("list")) {
                motivationController.list();
            } else if (cmd.startsWith("delete")) {
                // parsing
                String[] cmdBits = cmd.split("\\?", 2);

                String actionMethod = cmdBits[0]; // delete
                Map<String, String> params = new HashMap<>();
                String[] paramBits = cmdBits[1].split("&");

                for (String paramStr : paramBits) {
                    String[] paramStrBits = paramStr.split("=", 2);
                    String key = paramStrBits[0];
                    String value = paramStrBits[1];
                    System.out.println("paramStr: " + paramStr + " key: " + key + " value: " + value);
                    params.put(key, value);
                }

                System.out.println("Arrays.toString(cmdBits) : " + Arrays.toString(cmdBits));
                System.out.println("actionMethod: " + actionMethod);
                System.out.println("params: " + params);
                System.out.println("paramBits: " + Arrays.toString(paramBits));

//                motivationController.delete();
            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }
    }
}