package com.test.dijkstra;
import java.util.*;

import static com.test.dijkstra.BuildData.lineMap;

/*
票价政策
南京地铁票制已进行调整，改为按里程计价，新票价在以里程计算费用的城市地铁中为最低。
2014年7月1日起，起步价2元可乘10公里，10公里以上部分，每1元晋级里程为6、6、8、8、10、10、12、14公里。
 */
public class Subway {

    String output = "";

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    private List<Station> outList = new ArrayList<>();//记录已经分析过的站点

    //计算从s1站到s2站的最短经过路径
    void calculate(Station s1, Station s2){

        String info;
        int distance = 0;
        Set<String> set = lineMap.keySet();


        int exist01 = -1;
        int exist02 = -1;

        for (String lineNum : set) {

            List<Station> value = lineMap.get(lineNum);
            if (value.contains(s1)) {
                exist01 = 1;
            }
            if (value.contains(s2)) {
                exist02 = 1;
            }
        }
        if (exist01 == -1 || exist02 == -1) {

            output = "请检查输入值";
            return;
        }






        //在同一条线上的站点计算规则
        for (String lineNum : set) {
            String info_1 = "";
            List<Station> value = lineMap.get(lineNum);
            if (value.contains(s1) && value.contains(s2)) {
                Station prevStation = s1;
                int a = value.indexOf(s1);
                int b = value.indexOf(s2);
                int num;
                if (a > b) {
                    num = a - b;

                    for (int i = a; i >= b; i--) {
                        info_1 += value.get(i).getName() + "->";
                        if (!value.get(i).equals(prevStation)) {
                            distance += value.get(i).getDistance(prevStation);
                            prevStation = value.get(i);
                        }
                    }
                } else if (a < b) {
                    num = b - a;
                    for (int i = a; i <= b; i++) {
                        info_1 += value.get(i).getName() + "->";
                        if (!value.get(i).equals(prevStation)) {
                            distance += value.get(i).getDistance(prevStation);
                            prevStation = value.get(i);
                        }
                    }
                } else {
                    output = "无需乘车,请检查输入";
                    System.out.println("无需乘车,请检查输入");
                    return;
                }
                System.out.println("共经过" + num + "站");
                info = "(" + lineNum + "):" + info_1;
                System.out.println(info.substring(0, info.length() - 2));
                System.out.println("总里程为:" + distance + "米");
                System.out.println("需花费:" + getPrice(distance) + "元");

                output = "共经过" + num + "站" + "\n" + info.substring(0, info.length() - 2) + "\n" + "总里程为:" + distance + "米" + "\n" + "需花费:" + getPrice(distance) + "元";

                return;
            } else {

            }
        }

        //dijkstra穷举寻找最短路径
        if(outList.size() == BuildData.totalStaion){
            System.out.println("找到目标站点："+s2.getName()+"，共经过"+(s1.getAllPassedStations(s2).size()-1)+"站");
            String infoBuilder = "";
            String Cross = "";
            String lineInfo = "";
            //记录是否为中转站
            Station prevStation = s1;
            for(Station station : s1.getAllPassedStations(s2)){
               int count = 0;
                //引入infoBuilder解决若info未定义问题
                infoBuilder += station.getName();


                for (String lineNum : set) {
                    List<Station> value = lineMap.get(lineNum);
                    if (value.contains(station)) {
                        count++;
                        if(value.get(value.indexOf(station)).isCross()){
                            count = 2;
                        }
                        if (!Objects.equals(lineInfo, lineNum) && count == 1) {
                            lineInfo = lineNum;
                            infoBuilder += "<" + lineInfo + ">";
                        }
                    }
                }
                infoBuilder += "->";

                if (!station.equals(prevStation)) {
                    distance += station.getDistance(prevStation);
                    prevStation = station;
                }



//
            }
            info = infoBuilder;
            System.out.println(info.substring(0,info.length()-2));
            System.out.println("总里程为:" + distance+"米");
            System.out.println("需花费:"+getPrice(distance)+"元");


            output = info.substring(0,info.length()-2)+"\n"+"总里程为:"+distance+"米"+"\n"+"需花费:"+getPrice(distance)+"元";


            return;
        }
        if(!outList.contains(s1)){
            outList.add(s1);
//            System.out.println("添加s1后outList大小:"+outList.size());
        }
        //如果起点站的OrderSetMap为空，则第一次用起点站的前后站点初始化之
        if(s1.getOrderSetMap().isEmpty()){
            List<Station> Linkedstations = getAllLinkedStations(s1);
            for(Station s : Linkedstations){
                //每一条可能路径都记录
                s1.getAllPassedStations(s).add(s);
            }
        }
        Station parent = getShortestPath(s1);//获取距离起点站s1最近的一个站（有多个的话，随意取一个）
        if(parent == s2){
            System.out.println("找到目标站点："+s2+"，共经过"+(s1.getAllPassedStations(s2).size()-1)+"站");
            for(Station station : s1.getAllPassedStations(s2)){
                System.out.print(station.getName()+"->");
            }
            return;
        }
        for(Station child : getAllLinkedStations(parent)){
            if(outList.contains(child)){
                continue;
            }
//            -----------------

//            int shortestPath0 = Integer.MAX_VALUE;
//            for(Station path : s1.getAllPassedStations(parent)){
//                if(path.equals(s1)) {
//                    continue;
//                }
//
//            }
//             ----------------------
            int shortestPath = (s1.getAllPassedStations(parent).size()-1) + 1;//前面这个1表示计算路径需要去除自身站点，后面这个1表示增加了1站距离
            if(s1.getAllPassedStations(child).contains(child)){
                //如果s1已经计算过到此child的经过距离，那么比较出最小的距离
                if((s1.getAllPassedStations(child).size()-1) > shortestPath){
                    //重置S1到周围各站的最小路径
                    s1.getAllPassedStations(child).clear();
                    s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                    s1.getAllPassedStations(child).add(child);
                }
            } else {
                //如果s1还没有计算过到此child的经过距离
                s1.getAllPassedStations(child).addAll(s1.getAllPassedStations(parent));
                s1.getAllPassedStations(child).add(child);
            }
        }
        outList.add(parent);
        calculate(s1,s2);//重复计算，往外面站点扩展
    }

    //取参数站点station到各个站的最短距离，相隔1站，距离为1，依次类推
    private Station getShortestPath(Station station){
        int minPatn = Integer.MAX_VALUE;
        Station rets = null;
        for(Station s :station.getOrderSetMap().keySet()){
            if(outList.contains(s)){
                continue;
            }
            LinkedHashSet<Station> set  = station.getAllPassedStations(s);//参数station到s所经过的所有站点的集合
            if(set.size() < minPatn){
                minPatn = set.size();
                rets = s;
            }
        }
        return rets;
    }

    //获取参数station直接相连的所有站，包括交叉线上面的站
    //即获取所有与改点相邻的站点
    private List<Station> getAllLinkedStations(Station station){
        List<Station> linkedStaions = new ArrayList<>();
        for(List<Station> line : BuildData.lineSet){
            if(line.contains(station)){//如果某一条线包含了此站，注意由于重写了hashcode方法，只有name相同，即认为是同一个对象
                Station s = line.get(line.indexOf(station));
                if(s.prev != null){
                    linkedStaions.add(s.prev);
                }
                if(s.next != null){
                    linkedStaions.add(s.next);
                }
            }
        }
        return linkedStaions;
    }

    public static void main(String[] args) {
    /*
    line1:27
    line2:26
    line3:29
    line4:18
    line10:14
    lineS1:8
    lineS8:17
     */



        long t1 = System.currentTimeMillis();
        Subway sw = new Subway();
//        sw.calculate(new Station("南京站"), new Station("奥体东站"));
        sw.calculate(new Station("江心洲站"), new Station("中胜站"));
        long t2 = System.currentTimeMillis();
        System.out.println();
        System.out.println("耗时："+(t2-t1)+"ms");
    }

    int getPrice(int distance) {
        if (distance <= 10 * 1000) {
            return 2;
        } else if (distance <= 16000) {
            return 3;
        } else if (distance <= 22000) {
            return 4;
        } else if (distance <= 30000) {
            return 5;
        } else if (distance <= 38000) {
            return 6;
        } else {
            return 6 + (distance - 38000)/10000 + 1;
        }
    }
}