package com.test.dijkstra;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


public class Station {

    private String name; //地铁站名称，假设具备唯一性

    Station prev; //本站在lineNo线上面的前一个站

    Station next; //本站在lineNo线上面的后一个站

    public boolean isCross() {
        return isCross;
    }

    public void setCross(boolean cross) {
        isCross = cross;
    }

    private boolean isCross;

    //本站到某一个目标站(key)所经过的所有站集合(value)，保持前后顺序
    private Map<Station,LinkedHashSet<Station>> orderSetMap = new HashMap<>();

    Station(String name){
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    LinkedHashSet<Station> getAllPassedStations(Station station) {
        if(orderSetMap.get(station) == null){
            LinkedHashSet<Station> set = new LinkedHashSet<>();
            set.add(this);
            orderSetMap.put(station, set);
        }
        return orderSetMap.get(station);
    }

    Map<Station, LinkedHashSet<Station>> getOrderSetMap() {
        return orderSetMap;
    }


    private Map<Station,Integer> DistanceMap = new HashMap<>();
    //设置同一号线上的各站间距
    void setDistance(Station sta, Integer value){
        DistanceMap.put(sta, value);
    }
    Integer getDistance(Station sta){
        return DistanceMap.get(sta) == null? Integer.MAX_VALUE : DistanceMap.get(sta);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        } else if(obj instanceof Station){
            Station s = (Station) obj;
            return s.getName().equals(this.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}