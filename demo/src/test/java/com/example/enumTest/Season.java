package com.example.enumTest;

public enum Season {
    SPRING(0), SUMMER(1), AUTUMN(2), WINTER(3);

    private int value;
    private Season (int value){
        this.value = value;
    }

    public static Season getNextSeason(Season nowSeason){
        int nextDayValue = nowSeason.value;
        if(++nextDayValue == 3){
            nextDayValue = 0;
        }
        return getSeasonByValue(nextDayValue);
    }

    public static Season getSeasonByValue(int value){
        for(Season s : Season.values()){
            if(s.value == value){
                return s;
            }
        }
        return null;
    }

}
