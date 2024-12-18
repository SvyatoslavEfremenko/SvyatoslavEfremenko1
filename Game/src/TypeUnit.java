public enum TypeUnit {


    Healer,
    DamageDealer,
    Tank,
    Flang;


    public static double getProbability(TypeUnit typeOfUnit){
        double returnValue = 0.0;
        switch (typeOfUnit){
            case Tank -> returnValue = 0.03;
            case Healer -> returnValue = 0.05;
            case Flang -> returnValue = 0.15;
            case DamageDealer -> returnValue = 0.1;
        }
        return returnValue;
    }


    public static int additionalDamage(GameUnit unit){
        int returnValue = 0;
        switch (unit.getType()){
            case Tank -> returnValue = 10*(unit.getWeight()/unit.getHeight());
            case Healer, Flang -> returnValue = 2;
            case DamageDealer -> returnValue = 3;
        }
        return returnValue;
    }


    public static TypeUnit defenitionOfType(String typeOfUnit) {
        TypeUnit returnValue = TypeUnit.Healer;
        switch (typeOfUnit.toLowerCase()){
            case "damage dealer" -> returnValue = TypeUnit.DamageDealer;
            case "tank" -> returnValue = TypeUnit.Tank;
            case "flang" -> returnValue = TypeUnit.Flang;
        }
        return returnValue;
    }


    public static void heal(GameUnit unit){
        if (unit.getType() == TypeUnit.Healer){
            unit.setHealPoints(unit.getHealPoints() + 50);
        }
    }


}