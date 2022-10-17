/*
Name: Mohammad Haroon
Date: 10/14/2022
Program Description: In this program we have one main abstract class which is called Monster and it has four child class
.The four subclasses implementing the abstracts methods of Monster class. It is a game program which make attacks against each
other.
 */





import java.lang.annotation.ElementType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Monster {

    // Fields members of Monster class
    private int defenseMin = 1;
    private int defenseMax = 10;
    private int attackMin = 1;
    private int attackMax = 10;
    private static double MAX_HP = 20.0;
    private String name = "";
    private String phrase = "";
    private Double healthPoints = MAX_HP;
    protected int attackPoints = 10;
    protected int defensePoints = 10;
    private boolean fainted = false;
    private List<ElementalType> elements = new ArrayList<>();

 // End of fields

    // Constructor which take String and ElementalType as arugments
    // adding elemntal type to the arraylist
    // The constructor also calling setPhrase method
    public Monster(String name , ElementalType element){
        this.name = name; // setting name field with the value pass to constructor

        elements.add(element); // adding elements to Arraylist

     setPhrase(this); // calling setPhrase method by passing current object

    }


    //enum special class that keep the values for each monsters
    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

    // attack method which take Monster as argument and calculate attacks point for the current object
    public double attack(Monster monster){
        // Check if current object is fainted print the statement and return the value
        if(this.healthPoints == 0){ // IF START
            System.out.println(this.getName() + " isn't conscious... it can't attack.");
            return 0.0;
        } // IF END

        System.out.println(this.getName() + " is attacking " + monster); // change the
        System.out.println(getPhrase()); // print getPhrase method
        // calling calculateAttackPoints method which tak current object and List as argments
        double calAttack = calculateAttackPoints(this , monster.getElements());

        // print current object name and attack points
        System.out.println(this.getName() + " is attacking with " + calAttack);


       // calling  takeDamage method which takes the value got from calculateAttackPoints method
        double resultOfDamage =  monster.takeDamage(calAttack);

        // check if current object equale with Monster object pass in attack method and the value got
        // from takeDamge method is greater then 0
        if(this.equals(monster)  && resultOfDamage > 0)
            System.out.println(this.getName() + " hurt itself in the confusion");

       return resultOfDamage; // returning the calculated value from takeDamage method


    }

    // attackModifer method take ElementalType as argument and checking if the current object and the value
    // pass as defending are the same or different
    protected double attackModifier(ElementalType defending){

        // switch using defending value which pass as arguemnt
           switch (defending){
               case ELECTRIC :  // check the case if defending is ELECTRIC

                   // checking the current list
                   if(this.elements.contains(ElementalType.ELECTRIC)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.FIRE)){
                       return 1.0;
                   }
                   else if(this.elements.contains(ElementalType.GRASS)){
                       return 1.0;
                   }
                   else if(this.elements.contains(ElementalType.WATER)){
                       return 1.0;
                   }
                   break;
               case FIRE:  // check the case if defending is FIRE

                   // checking the current list
                   if(this.elements.contains(ElementalType.ELECTRIC)){
                       return 1.0;
                   }
                   else if(this.elements.contains(ElementalType.FIRE)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.GRASS)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.WATER)){
                       return 2.0;
                   }
                   break;
               case GRASS:  // check the case if defending is GRASS

                   //// checking the current list
                   if(this.elements.contains(ElementalType.ELECTRIC)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.FIRE)){
                       return 2.0;
                   }
                   else if(this.elements.contains(ElementalType.GRASS)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.WATER)){
                       return 0.5;
                   }
                  break;
               case WATER:  // check the case if defending is WATER

                   // checking the current list and return value
                   if(this.elements.contains(ElementalType.ELECTRIC)){
                       return 2.0;
                   }
                   else if(this.elements.contains(ElementalType.FIRE)){
                       return 0.5;
                   }
                   else if(this.elements.contains(ElementalType.GRASS)){
                       return 2.0;
                   }
                   else if(this.elements.contains(ElementalType.WATER)){
                       return 0.5;
                   }
                   break;

           }




           return 1.0;  // default value




    }


    // calculateAttackPoints method which will calculate attack points by using Monster and List as
    // arguments
    public double calculateAttackPoints(Monster monster , List<ElementalType> enemyType){
                      // calling Dice object and roll method to get attck points
        double attackValue = Dice.roll(monster.attackMin , monster.attackMax);

        double modifier = 1.0; // set and declare double variable

       // for each loop which will to each enemyType
        for(ElementalType str : enemyType) {

             modifier *= attackModifier(str); // calling attackModifier method and multiplied each value

        }
        System.out.println(monster + " rolls a " + modifier);

        if(modifier > 2.0){  // check if the the value of modifier is greater or equal
            System.out.println("It's su-- *cough* very effective!");
        }


           // returning attack value which multiple with modifier value
        return  attackValue = (attackValue* modifier);
    }

    //calculateDefensePoints  method which take monster as argument
    protected double calculateDefensePoints(Monster monster){
        // calling Dice object and roll method to get defense points
        int defenseValue = Dice.roll(defenseMin , defenseMax);

         // checking if defense value is even or less the half of defenseMax value
        if(defenseValue % 2 == 0 && defenseValue < (defenseMax/2) ){ // STRAT IF
            defenseValue =  (defenseValue +1 ) * 2; // adding one to defense point
//            defenseValue *=2;  // multiple defense points
            System.out.println(this.getName() + " finds courage in the desperate situation");
        } // END IF


        // checking if defense point and current object defenseMin value equals
        if(defenseValue == monster.defenseMin){
                System.out.println(this.getName() + " is clearly not paying attention.");
            }
            return defenseValue; // returning defense points



    }

    // takeDamage method  which take double attackValue as arguments
    private double takeDamage(Double attackValue){
             // calling calculateDefensePoints method get defense points
        double calDefensePoint = calculateDefensePoints(this);

        //subtracting attackValue which pass from defense point got from calculateDefensePoints method
        double result =( attackValue -  calDefensePoint );

       // checking if points is greater then zero print and subtract from current HP
        if(result > 0){
            System.out.println(this.getName() + " is hit for " + result + " damage!.");
              this.healthPoints -= result;
        }

        if(result == 0){ // cheking points equals zero
            System.out.println(this.getName() + " is nearly hit!.");
        }
        if(result < (calDefensePoint/2.0)){ // checking points less than calculateDefensePoints half
            System.out.println(this.getName() + " shrugs off the puny attack.");
        }
       if(this.healthPoints <= 0){ // checking if current HP points is less or equals 0
            System.out.println(this.getName() + " has faint-- passed out. It's passed out.");
            setFainted(true); // set  fainted to true value
        }
       else{
            System.out.println(this.getName() + " has " + this.healthPoints + "/" + this.MAX_HP + "HP remaining");
        }


        return result; // returning the points
    }


    // setPhrase method which take monster as argument and return object
    private static Monster setPhrase(Monster monster){
//
           // checking if the current object is  FireLizard
        if(monster instanceof FireLizard){
            monster.setPhrase("Deal with it"); //set phrase
            return monster; // returning monster object
        }
        else if(monster instanceof ElectricRat){   // checking if the current object is  ElectricRat
            monster.setPhrase("'Lectric!");  //set phrase
            return monster;
        }
        else if(monster instanceof FlowerDino){     // checking if the current object is  FlowerDino
            monster.setPhrase("Flowah!");  //set phrase
            return monster;
        }
        else if(monster instanceof  WeirdTurtle){  // checking if the current object is  WeirdTurtle
            monster.setPhrase("'Urtle!");  //set phrase
            return monster; // returning monster object
        }
        else{  // checking if the current object is  Unknown
            monster.setPhrase("No phrase for me!");  //set phrase
            return monster; // returning monster object
        }




    }




    // abstract methods which will be implements in subclasses

    public abstract void setDefensePoints();
    public abstract void setAttackPoints();


    // Setter and Getter methods  Starts
    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public Integer getAttackMax() {
        return this.attackMax = attackPoints;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }





    public String getName() {
        return name;
    }



    public String getPhrase() {
        return this.phrase + this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }



    public int getDefensePoints() {
        return defensePoints;
    }





    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    // END OF SETTER AND GETTERS METHODS

    //setType Method get Elemental as argument which return int value

   public int setType(ElementalType type){

        // check if list have that value return
        if(this.elements.contains(type)){
            System.out.println(type + " already set!");
            return 1; // return the value
        }

         // checking if value from attackModifier method is greater than 1.0
         if(attackModifier(type) > 1.0){
             System.out.println("Can't have conflicting types!");
             return -1; // return
         }
         else{ // otherwise add type to Arraylist
             elements.add(type);

             System.out.println(this.getName() + " now has " + type + ",");
             return 0; // return
         }



   }
     public List<ElementalType> getElements(){
        return elements;
     }


     // need some changes
    public void setAttackPoints(int attackPoints){
        this.attackPoints = attackPoints;

    }



     // toString methoid which return two types of value if object fainted one value otherwsie different value
    @Override
    public String toString() {
       if(!this.isFainted()){

           return getName() + " has " + this.getHealthPoints() +"/" + MAX_HP +"hp.\n"
                   +"Elemental type:" + this.getElements().toString().replace("[","").replace("]","" +",");
       }
       else{
           return this.getName() + " has fainted.\n " + "Elemental type:" + this.getElements().toString().replace("[" , "").replace("]","" +",");
       }

    }
}
