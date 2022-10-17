/*
Name: Mohammad Haroon
Date: 10/14/2022
Program Description: In this program we have one main abstract class which is called Monster and it has four child class
.The four subclasses implementing the abstracts methods of Monster class. It is a game program which make attacks against each
other.
 */


import java.lang.annotation.ElementType;

public class WeirdTurtle extends Monster{

    // WeirdTurtle class fields names
    private final int DEFENSE_MIN =3; // final value default set
    private final int DEFENSE_MAX = 8; // final value default set
    private final int ATTACK_MIN = 3; // final value default set
    private final int ATTACK_MAX =8; // final value default set


    //Constructor that take string as argemnt
    WeirdTurtle(String name){
        super(name , ElementalType.valueOf("WATER")); // super constructor call

        // sets methods inherited and passing the values
       this.setAttackMin(ATTACK_MIN);
        this.setAttackMax(ATTACK_MAX);
        this.setDefenseMin(DEFENSE_MIN);
        this.setDefenseMax(DEFENSE_MAX);

    }


    // implementing abstract methods
    @Override
    public void setDefensePoints() {
     defensePoints = Dice.roll(ATTACK_MIN , ATTACK_MAX); // calling roll method through Dice class
    }

    // implementing abstract methods
    public void setAttackPoints(){
     attackPoints= Dice.roll(DEFENSE_MIN,DEFENSE_MAX);// calling roll method through Dice class
    }
}
