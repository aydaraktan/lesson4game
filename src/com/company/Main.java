package com.company;

import java.util.Random;

public class Main {
    public static int torDamage=300;
    public static int berserkHealth=300;
    public static int berserkHit=20;
    public static int lovHealth=250;
    public static int tankerHealth=300;

    public static int tankerHelp=3;
    public static int tankerHit=10;
    public static int medicHealth=250;
    public static int medicHelp=5;
    public static int bossHealth = 2000; //жизнь босса
    public static int bossDamage = 50;  //урон от босса
    public static String bossDefenceType = " "; //защита босса
    public static int[] heroesHealth = {250, 250, 250}; //жизнь героев
    public static int[] heroesDamage = {20, 20, 20};    //урон от героев
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental"};    //тип атаки от героев


    public static void main(String[] args) {

        fightInfo(); //показать инфо состояния

        while (!isFinished()) { //пока исФинишед тру - идет след раунд
            round();
        }

    }

    public static void round() {
        changeBossDefence();    //изменить защиту босса
        bossHit();              //удар босса
        medicHelp();
        tanker();
        lov();
        berserk();
        tor();
        heroesHit();            //удар героев
        fightInfo();            //инфо
    }

    public static void changeBossDefence() {
        Random random = new Random();                                   //вызвали рандом
        int randomIndex = random.nextInt(heroesAttackType.length);      //то есть от 1 до 3
        bossDefenceType = heroesAttackType[randomIndex];                //защита босса = типа атаки героев
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {                              //если жизнь босса <=0
            System.out.println("Heroes won!!!");            //герои выиграли
            return true;                                    //возврати тру
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {     //если жизнь героев <=0
            System.out.println("Boss won!!!");                                          //босс выиграл
            return true;                                                                //возврати тру
        }
        return false;   //возврати фолс
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {     //пока i<3

            if (heroesHealth[i] - bossDamage < 0) {         //если жизнь героев - удар босса < 0
                heroesHealth[i] = 0;                              //жизнь геороев = 0


            } else {                            //иначе
                heroesHealth[i] = (heroesHealth[i] - bossDamage);

            }

        }
        if (medicHealth - bossDamage < 0) {
            medicHealth = 0;
        } else {
            medicHealth = medicHealth - bossDamage;
        }

        if (tankerHealth - bossDamage < 0) {
            tankerHealth = 0;
        } else {
            tankerHealth = (tankerHealth - bossDamage);
        }

        if (berserkHealth - bossDamage < 0) {
            berserkHealth = 0;
        } else {
            berserkHealth = berserkHealth - bossDamage;
        }
        Random rand =new Random();
        boolean r= rand.nextBoolean();
        if(r==true)
        {
            System.out.println("тор вырубил босса на один раунд, и герои не получили ущерба");
            for (int i = 0; i < heroesHealth.length; i++) {
                heroesHealth[i]=heroesHealth[i] + bossDamage;
            }
        }else {
            System.out.println("тор не участвовал в бою");
        }


    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {     //пока i<3
            if (heroesHealth[i] > 0 && bossHealth > 0) {         // если жизнь героев>0 и жизнь босса>0
                if (bossDefenceType.equals(heroesAttackType[i])) {  //если защита босса = типу атаки героев
                    Random random = new Random();           //вызвали ранодом
                    int koeff = random.nextInt(9) + 2;      //рандом коеф равен от 2 до 10
                    if (bossHealth - heroesDamage[i] * koeff < 0) {     //если жизнь босса - урон от героев * коеф(2-10) <0
                        bossHealth = 0;         //жизнь босса = 0
                    } else {                    //иначе
                        bossHealth = (bossHealth - heroesDamage[i] * koeff)+tankerHit; //жизнь босса = жизнь босса - урон от героев * коеф
                    }
                    System.out.println(heroesAttackType[i] + " critical hit " + heroesDamage[i] * koeff); //показ тип атаки героев + критич. атака + урон от героев*коеф
                } else {        //иначе
                    if (bossHealth - heroesDamage[i] < 0) {     //если жизнь босса - урон от героев < 0
                        bossHealth = 0;                         //то жизнь босса = 0
                    } else {                                    //инче
                        bossHealth = bossHealth - heroesDamage[i];      //жизнь босса = жизнь босса - урон от героев
                    }
                }
            }
        }
    }
    public static void medicHelp()
    {
        Random med=new Random();
        int y; y=med.nextInt(20)+1;
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i]>0)
            {
                heroesHealth[i]=heroesHealth[i]+y;

            }

        }
        System.out.println("медик накинул каждому герою по "+ y);
    }
    public static void   tanker()
    {
        Random rand=new Random();
        int y= rand.nextInt(10)+1;
        bossHealth=bossHealth-y;
        System.out.println("танк нанес удар "+y);
        Random rand2=new Random();
        int y2= rand.nextInt(10)+1;
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i]=heroesHealth[i]+y2;
        }
        System.out.println("танк принял на себя "+y2);

    }
    public static void lov()
    {
        Random rand=new Random();
        boolean y=rand.nextBoolean();
        if(y==true)
        {
            System.out.println("ловкач уклонился от удара босса");
        }
        else
        {
            lovHealth=lovHealth-bossDamage;
            System.out.println("ловкач принял удар босса");

        }
    }
    public static void berserk()
    {
        int u;
        Random rand=new Random();
        int y= rand.nextInt(25)+5;
        u=bossDamage-y;

        bossHealth=bossHealth-u;
        if(bossHealth<0)
        {
            bossHealth=0;
        }else
            System.out.println("берсерк заблокировал "+ y+ " и нанес удар: "+u);

    }
    public static void tor()
    {

    }

    public static void fightInfo() {
        System.out.println("_______________________________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);       //жизнь босса + тип защиты босса
        System.out.println("Hero1 health: " + heroesHealth[0]);           //жизнь героя #1
        System.out.println("Hero1 health: " + heroesHealth[1]);             //жизнь героя #2
        System.out.println("Hero1 health: " + heroesHealth[2]);           //жизнь героя #3
        System.out.println("medic health: " + medicHealth);
        System.out.println("tanker health: " + tankerHealth);
        System.out.println("ловкач health: "+lovHealth);
        System.out.println("berserk health: "+berserkHealth);
        System.out.println("_______________________________");
    }
}