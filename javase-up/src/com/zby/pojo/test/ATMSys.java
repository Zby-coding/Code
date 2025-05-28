package com.zby.pojo.test;

import com.zby.pojo.entity.Account;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class ATMSys {
    public static void main(String[] args) {
        LinkedList<Account> accounts = new LinkedList<>();
        System.out.println("欢迎来到黄科ATM系统");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. 登陆账户");
            System.out.println("2. 注册账户");
            System.out.println("请您选择操作");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    // 登录账户
                    login(accounts, sc);
                    break;
                case 2:
                    // 注册账户
                    register(accounts, sc);
                    break;
            }
        }

    }

    private static void login(LinkedList<Account> accounts, Scanner sc) {
        while (true) {
            System.out.println("=======欢迎来到登录界面=========");
            System.out.println("请输入您的卡号:");
            String cardNum = sc.next();
            // 通过卡号判断是否在集合当中
            Account account = getAccountByCardNum(accounts, cardNum);
            if (account == null) {
                System.out.println("您输入的卡号有误，请重新输入");
            } else {
                while (true) {
                    System.out.println("请您输入您的密码");
                    String pwd = sc.next();
                    if (pwd.equals(account.getPassword())) {
                        System.out.println("欢迎您:" + account.getName() + "先生/女士进入黄科ATM系统，可以办理您的业务了！");
                        // 办理的业务
                        showCommand(account, accounts, sc);
                        return;
                    } else {
                        System.out.println("您输入的密码有误，请重新输入！");
                    }
                }
            }
        }
    }

    private static void showCommand(Account account, LinkedList<Account> accounts, Scanner sc) {
        while (true) {
            System.out.println("========欢迎您进入操作界面==========");
            System.out.println("1. 查询");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("4. 转账");
            System.out.println("5. 修改密码");
            System.out.println("6. 退出");
            System.out.println("7. 注销账户");
            System.out.println("请选择您的操作");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    queryAccount(account);
                    break;
                case 2:
                    saveMoney(account, sc);
                    break;
                case 3:
                    drawMoney(account, sc);
                    break;
                case 4:
                    transferMoney(account, accounts, sc);
                    break;
                case 5:
                    changePassword(account, sc);
                    break;
                case 6:
                    System.out.println("您已经退出了黄科ATM系统，欢迎下次再来");
                    return;
                case 7:
                    accounts.remove(account);
                    System.out.println("您已经注销了账户，欢迎下次再来");
                    return;
            }
        }

    }

    private static void changePassword(Account account, Scanner sc) {
        System.out.println("=========欢迎来到修改密码界面============");
        while (true) {
            System.out.println("请输入您的旧密码");
            String old_pwd = sc.next();
            if (!old_pwd.equals(account.getPassword())) {
                System.out.println("您输入的旧密码有误，请重新输入");
            } else {
                while (true) {
                    System.out.println("请输入您的新密码");
                    String new_pwd = sc.next();
                    System.out.println("请再次输入您的新密码");
                    String new_pwd2 = sc.next();
                    if (new_pwd.equals(new_pwd2)) {
                        account.setPassword(new_pwd);
                        System.out.println("您的新密码为：" + new_pwd + " 请牢记!");
                        return;
                    } else {
                        System.out.println("您两次输入的密码不一致，请重新输入");
                        continue;
                    }
                }
            }
        }
    }

    private static void transferMoney(Account account, LinkedList<Account> accounts, Scanner sc) {
        System.out.println("=========欢迎来到转账界面============");
        while (true) {
            System.out.println("请输入您要转账的卡号");
            String cardNum = sc.next();
            Account transAccount = getAccountByCardNum(accounts, cardNum);
            if (transAccount == null) {
                System.out.println("未查询到您要转账的账户信息，请重新输入！");
            } else {
                System.out.println("请输入您要转账的金额：");
                double money = sc.nextDouble();
                if (account.getMoney() < money) {
                    System.out.println("您输入的转账金额超过了您的账户金额，请重新输入！");
                } else {
                    if (transAccount.getMoney() + money > transAccount.getLimitMoney()) {
                        System.out.println("您输入的转账金额超过了对方账户限额，请重新输入！");
                    } else {
                        account.setMoney(account.getMoney() - money);
                        transAccount.setMoney(transAccount.getMoney() + money);
                        System.out.println("恭喜您，转账成功，您的余额为:" + account.getMoney());
                        break;
                    }
                }
            }
        }

    }

    private static void drawMoney(Account account, Scanner sc) {
        System.out.println("=========欢迎来到取款界面============");
        System.out.println("请您输入您的取款金额");
        double money = sc.nextDouble();
        while (true) {
            if (account.getMoney() < money) {
                System.out.println("您输入的款金额超过了您的账户金额，请重新输入！");
                break;
            } else {
                if (money > account.getLimitMoney()) {
                    System.out.println("您输入的金额超过了账户限额，请重新输入！");
                    break;
                } else {
                    account.setMoney(account.getMoney() - money);
                    System.out.println("您的账户金额为:" + account.getMoney() + "您取款的金额为:" + money);
                    break;
                }
            }
        }


    }

    private static void saveMoney(Account account, Scanner sc) {
        System.out.println("==========欢迎来到存款界面===========");
        while (true) {
            System.out.println("请输入存款金额");
            double money = sc.nextDouble();
            account.setMoney(account.getMoney() + money);
            System.out.println("您存款成功，您的余额为:" + account.getMoney());
            queryAccount(account);
            return;
        }
    }

    private static void queryAccount(Account account) {
        System.out.println("============用户的详细信息如下===============");
        System.out.println("您的用户名为:" + account.getName());
        System.out.println("您的卡号为:" + account.getCardNum());
        System.out.println("您的存款有:" + account.getMoney());
        System.out.println("您要取现的额度为:" + account.getLimitMoney());
    }

    private static Account getAccountByCardNum(LinkedList<Account> accounts, String cardNum) {
        for (Account account : accounts) {
            if (account.getCardNum().equals(cardNum)) {
                return account;
            }
        }
        return null;
    }


    private static void register(LinkedList<Account> accounts, Scanner sc) {
        System.out.println("欢迎来到开户界面");
        Account account = new Account();
        // 密码校验
        while (true) {
            System.out.println("请您输入账户姓名");
            String name = sc.next();
            System.out.println("请您输入密码");
            String pwd = sc.next();
            System.out.println("请您再次输入密码进行确认");
            String pwd_ok = sc.next();
            if (!(pwd.equals(pwd_ok))) {
                System.out.println("两次密码输入不一致，请重新输入");
                continue;
            } else {
                account.setName(name);
                account.setPassword(pwd);
                break;
            }
        }
        System.out.println("您的取现额度为：");
        double limit = sc.nextDouble();
        account.setLimitMoney(limit);
        // 卡号校验
        while (true) {
            String cardNum = createCardNum(accounts);
            if (cardNum == null) {
                continue;
            } else {
                account.setCardNum(cardNum);
                account.setMoney(0.0);
                break;
            }
        }
        // 添加
        accounts.add(account);
        System.out.println("恭喜您，开户成功，您的卡号是" + account.getCardNum());


    }

    private static String createCardNum(LinkedList<Account> accounts) {
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 8; i++) {
            int i1 = random.nextInt(10);
            str += i1;
        }
        // 校验卡号是否和原来已存在的卡号重复
        for (Account acc : accounts) {
            if (acc.getCardNum().equals(str)) {
                return null;
            }
        }
        return str;
    }
}

