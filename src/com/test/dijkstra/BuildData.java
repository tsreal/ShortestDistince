package com.test.dijkstra;

import java.util.*;

//数据定义,构建地铁站点信息
class BuildData {

    private static List<Station> line1 = new ArrayList<>();//1号线
    private static List<Station> line2 = new ArrayList<>();//2号线
    private static List<Station> line3 = new ArrayList<>();//3号线
    private static List<Station> line4 = new ArrayList<>();//4号线
    private static List<Station> line10 = new ArrayList<>();//10号线
    private static List<Station> lineS1 = new ArrayList<>();//s1号线
    private static List<Station> lineS8 = new ArrayList<>();//s8号线
    static Set<List<Station>> lineSet = new HashSet<>();//所有线集合
    static Map<String, List<Station>> lineMap = new HashMap<>();
    static Map<String, String[]> model = new HashMap<>();




    //被static修饰后的成员，在编译时由内存分配一块内存空间，直到程序停止运行才会释放，那么就是说该类的所有对象都会共享这块内存空间
    static int totalStaion = 0;//总的站点数量
    static {
        //1号线
        String line1Str = "迈皋桥站、红山动物园站、南京站、新模范马路站、玄武门站、鼓楼站、珠江路站、新街口站、张府园站、三山街站、中华门站、安德门站、天隆寺站、软件大道站、花神庙站、南京南站、双龙大道站、河定桥站、胜太路站、百家湖站、小龙湾站、竹山路站、天印大道站、龙眠大道站、南医大·江苏经贸学院站、南京交院站、中国药科大学站";
        String[] line1Arr = line1Str.split("、");
        model.put("line1", line1Arr);
        for(String s : line1Arr){
            line1.add(new Station(s));
        }
        for(int i =0;i<line1.size();i++){
            line1.get(i).setCross(false);
            if(i<line1.size()-1){
                line1.get(i).next = line1.get(i+1);
                line1.get(i+1).prev = line1.get(i);
            }
        }
        line1.get(line1.indexOf(new Station("南京站"))).setCross(true);
        line1.get(line1.indexOf(new Station("鼓楼站"))).setCross(true);
        line1.get(line1.indexOf(new Station("新街口站"))).setCross(true);
        line1.get(line1.indexOf(new Station("安德门站"))).setCross(true);
        line1.get(line1.indexOf(new Station("南京南站"))).setCross(true);


        line1.get(0).setDistance(line1.get(1),1142);
        line1.get(1).setDistance(line1.get(2),1121);
        line1.get(2).setDistance(line1.get(3),1691);
        line1.get(3).setDistance(line1.get(4),1061);
        line1.get(4).setDistance(line1.get(5),1254);
        line1.get(5).setDistance(line1.get(6),862);
        line1.get(6).setDistance(line1.get(7),1147);
        line1.get(7).setDistance(line1.get(8),1125);
        line1.get(8).setDistance(line1.get(9),909);
        line1.get(9).setDistance(line1.get(10),1914);
        line1.get(10).setDistance(line1.get(11),2093);
        line1.get(11).setDistance(line1.get(12),1455);
        line1.get(12).setDistance(line1.get(13),1283);
        line1.get(13).setDistance(line1.get(14),1076);
        line1.get(14).setDistance(line1.get(15),1853);
        line1.get(15).setDistance(line1.get(16),2276);
        line1.get(16).setDistance(line1.get(17),1345);
        line1.get(17).setDistance(line1.get(18),904);
        line1.get(18).setDistance(line1.get(19),1332);
        line1.get(19).setDistance(line1.get(20),1475);
        line1.get(20).setDistance(line1.get(21),1135);
        line1.get(21).setDistance(line1.get(22),1919);
        line1.get(22).setDistance(line1.get(23),1312);
        line1.get(23).setDistance(line1.get(24),1550);
        line1.get(24).setDistance(line1.get(25),2720);
        line1.get(25).setDistance(line1.get(26),1964);

        line1.get(1).setDistance(line1.get(0),1142);
        line1.get(2).setDistance(line1.get(1),1121);
        line1.get(3).setDistance(line1.get(2),1691);
        line1.get(4).setDistance(line1.get(3),1061);
        line1.get(5).setDistance(line1.get(4),1254);
        line1.get(6).setDistance(line1.get(5),862);
        line1.get(7).setDistance(line1.get(6),1147);
        line1.get(8).setDistance(line1.get(7),1125);
        line1.get(9).setDistance(line1.get(8),909);
        line1.get(10).setDistance(line1.get(9),1914);
        line1.get(11).setDistance(line1.get(10),2093);
        line1.get(12).setDistance(line1.get(11),1455);
        line1.get(13).setDistance(line1.get(12),1283);
        line1.get(14).setDistance(line1.get(13),1076);
        line1.get(15).setDistance(line1.get(14),1853);
        line1.get(16).setDistance(line1.get(15),2276);
        line1.get(17).setDistance(line1.get(16),1345);
        line1.get(18).setDistance(line1.get(17),904);
        line1.get(19).setDistance(line1.get(18),1332);
        line1.get(20).setDistance(line1.get(19),1475);
        line1.get(21).setDistance(line1.get(20),1135);
        line1.get(22).setDistance(line1.get(21),1919);
        line1.get(23).setDistance(line1.get(22),1312);
        line1.get(24).setDistance(line1.get(23),1550);
        line1.get(25).setDistance(line1.get(24),2720);
        line1.get(26).setDistance(line1.get(25),1964);







        //2号线
        String line2Str = "油坊桥站、雨润大街站、元通站、奥体东站、兴隆大街站、集庆门大街站、云锦路站、莫愁湖站、汉中门站、上海路站、新街口站、大行宫站、西安门站、明故宫站、苜蓿园站、下马坊站、孝陵卫站、钟灵街站、马群站、金马路站、仙鹤门站、学则路站、仙林中心站、羊山公园站、南大仙林校区站、经天路站";
        String[] line2Arr = line2Str.split("、");
        model.put("line2", line2Arr);
        for(String s : line2Arr){
            line2.add(new Station(s));
        }
        for(int i =0;i<line2.size();i++){
            line2.get(i).setCross(false);
            if(i<line2.size()-1){
                line2.get(i).next = line2.get(i+1);
                line2.get(i+1).prev = line2.get(i);
            }
        }
        line2.get(line2.indexOf(new Station("元通站"))).setCross(true);
        line2.get(line2.indexOf(new Station("新街口站"))).setCross(true);
        line2.get(line2.indexOf(new Station("大行宫站"))).setCross(true);
        line2.get(line2.indexOf(new Station("金马路站"))).setCross(true);



        line2.get(0).setDistance(line2.get(1),2631);
        line2.get(1).setDistance(line2.get(2),1622);
        line2.get(2).setDistance(line2.get(3),1262);
        line2.get(3).setDistance(line2.get(4),1379);
        line2.get(4).setDistance(line2.get(5),1473);
        line2.get(5).setDistance(line2.get(6),1312);
        line2.get(6).setDistance(line2.get(7),1257);
        line2.get(7).setDistance(line2.get(8),1002);
        line2.get(8).setDistance(line2.get(9),874);
        line2.get(9).setDistance(line2.get(10),745);
        line2.get(10).setDistance(line2.get(11),1022);
        line2.get(11).setDistance(line2.get(12),1024);
        line2.get(12).setDistance(line2.get(13),1300);
        line2.get(13).setDistance(line2.get(14),1521);
        line2.get(14).setDistance(line2.get(15),1307);
        line2.get(15).setDistance(line2.get(16),937);
        line2.get(16).setDistance(line2.get(17),1168);
        line2.get(17).setDistance(line2.get(18),2779);
        line2.get(18).setDistance(line2.get(19),3015);
        line2.get(19).setDistance(line2.get(20),1675);
        line2.get(20).setDistance(line2.get(21),1326);
        line2.get(21).setDistance(line2.get(22),1519);
        line2.get(22).setDistance(line2.get(23),1066);
        line2.get(23).setDistance(line2.get(24),1947);
        line2.get(24).setDistance(line2.get(25),1846);

        line2.get(1).setDistance(line2.get(0),2631);
        line2.get(2).setDistance(line2.get(1),1622);
        line2.get(3).setDistance(line2.get(2),1262);
        line2.get(4).setDistance(line2.get(3),1379);
        line2.get(5).setDistance(line2.get(4),1473);
        line2.get(6).setDistance(line2.get(5),1312);
        line2.get(7).setDistance(line2.get(6),1257);
        line2.get(8).setDistance(line2.get(7),1002);
        line2.get(9).setDistance(line2.get(8),874);
        line2.get(10).setDistance(line2.get(9),745);
        line2.get(11).setDistance(line2.get(10),1022);
        line2.get(12).setDistance(line2.get(11),1024);
        line2.get(13).setDistance(line2.get(12),1300);
        line2.get(14).setDistance(line2.get(13),1521);
        line2.get(15).setDistance(line2.get(14),1307);
        line2.get(16).setDistance(line2.get(15),937);
        line2.get(17).setDistance(line2.get(16),1168);
        line2.get(18).setDistance(line2.get(17),2779);
        line2.get(19).setDistance(line2.get(18),3015);
        line2.get(20).setDistance(line2.get(19),1675);
        line2.get(21).setDistance(line2.get(20),1326);
        line2.get(22).setDistance(line2.get(21),1519);
        line2.get(23).setDistance(line2.get(22),1066);
        line2.get(24).setDistance(line2.get(23),1947);
        line2.get(25).setDistance(line2.get(24),1846);

        //3号线
        String line3Str = "林场站、星火路站、东大成贤学院站、泰冯路站、天润城站、柳洲东路站、上元门站、五塘广场站、小市站、南京站、南京林业大学·新庄站、鸡鸣寺站、浮桥站、大行宫站、常府街站、夫子庙站、武定门站、雨花门站、卡子门站、大明路站、明发广场站、南京南站、宏运大道站、胜太西路站、天元西路站、九龙湖站、诚信大道站、东大九龙湖校区站、秣周东路站";
        String[] line3Arr = line3Str.split("、");
        model.put("line3", line3Arr);
        for(String s : line3Arr){
            line3.add(new Station(s));
        }
        for(int i =0;i<line3.size();i++){
            line3.get(i).setCross(false);
            if(i<line3.size()-1){
                line3.get(i).next = line3.get(i+1);
                line3.get(i+1).prev = line3.get(i);
            }
        }
        line3.get(line3.indexOf(new Station("泰冯路站"))).setCross(true);
        line3.get(line3.indexOf(new Station("南京站"))).setCross(true);
        line3.get(line3.indexOf(new Station("鸡鸣寺站"))).setCross(true);
        line3.get(line3.indexOf(new Station("大行宫站"))).setCross(true);
        line3.get(line3.indexOf(new Station("南京南站"))).setCross(true);

        line3.get(0).setDistance(line3.get(1),2537);
        line3.get(1).setDistance(line3.get(2),1049);
        line3.get(2).setDistance(line3.get(3),1127);
        line3.get(3).setDistance(line3.get(4),1407);
        line3.get(4).setDistance(line3.get(5),1854);
        line3.get(5).setDistance(line3.get(6),3535);
        line3.get(6).setDistance(line3.get(7),961);
        line3.get(7).setDistance(line3.get(8),1845);
        line3.get(8).setDistance(line3.get(9),1397);
        line3.get(9).setDistance(line3.get(10),2017);
        line3.get(10).setDistance(line3.get(11),2851);
        line3.get(11).setDistance(line3.get(12),858);
        line3.get(12).setDistance(line3.get(13),859);
        line3.get(13).setDistance(line3.get(14),931);
        line3.get(14).setDistance(line3.get(15),1067);
        line3.get(15).setDistance(line3.get(16),1308);
        line3.get(16).setDistance(line3.get(17),1143);
        line3.get(17).setDistance(line3.get(18),977);
        line3.get(18).setDistance(line3.get(19),1153);
        line3.get(19).setDistance(line3.get(20),1137);
        line3.get(20).setDistance(line3.get(21),1222);
        line3.get(21).setDistance(line3.get(22),1105);
        line3.get(22).setDistance(line3.get(23),1826);
        line3.get(23).setDistance(line3.get(24),1791);
        line3.get(24).setDistance(line3.get(25),2343);
        line3.get(25).setDistance(line3.get(26),1526);
        line3.get(26).setDistance(line3.get(27),1301);
        line3.get(27).setDistance(line3.get(28),2995);

        line3.get(1).setDistance(line3.get(0),2537);
        line3.get(2).setDistance(line3.get(1),1049);
        line3.get(3).setDistance(line3.get(2),1127);
        line3.get(4).setDistance(line3.get(3),1407);
        line3.get(5).setDistance(line3.get(4),1854);
        line3.get(6).setDistance(line3.get(5),3535);
        line3.get(7).setDistance(line3.get(6),961);
        line3.get(8).setDistance(line3.get(7),1845);
        line3.get(9).setDistance(line3.get(8),1397);
        line3.get(10).setDistance(line3.get(9),2017);
        line3.get(11).setDistance(line3.get(10),2851);
        line3.get(12).setDistance(line3.get(11),858);
        line3.get(13).setDistance(line3.get(12),859);
        line3.get(14).setDistance(line3.get(13),931);
        line3.get(15).setDistance(line3.get(14),1067);
        line3.get(16).setDistance(line3.get(15),1308);
        line3.get(17).setDistance(line3.get(16),1143);
        line3.get(18).setDistance(line3.get(17),977);
        line3.get(19).setDistance(line3.get(18),1153);
        line3.get(20).setDistance(line3.get(19),1137);
        line3.get(21).setDistance(line3.get(20),1222);
        line3.get(22).setDistance(line3.get(21),1105);
        line3.get(23).setDistance(line3.get(22),1826);
        line3.get(24).setDistance(line3.get(23),1791);
        line3.get(25).setDistance(line3.get(24),2343);
        line3.get(26).setDistance(line3.get(25),1526);
        line3.get(27).setDistance(line3.get(26),1301);
        line3.get(28).setDistance(line3.get(27),2995);

        //4号线
        String line4Str = "龙江站、南艺·二师·草场门站、云南路站、鼓楼站、鸡鸣寺站、九华山站、岗子村站、蒋王庙站、王家湾站、聚宝山站、苏宁总部·徐庄站、金马路站、汇通路站、灵山站、东流站、孟北站、西岗桦墅站、仙林湖站";
        String[] line4Arr = line4Str.split("、");
        model.put("line4", line4Arr);
        for (String s : line4Arr) {
            line4.add(new Station(s));
        }
        for(int i=0; i<line4.size(); i++) {
            line4.get(i).setCross(false);
            if (i < line4.size() - 1) {
                line4.get(i).next = line4.get(i + 1);
                line4.get(i + 1).prev = line4.get(i);
            }
        }
        line4.get(line4.indexOf(new Station("鼓楼站"))).setCross(true);
        line4.get(line4.indexOf(new Station("鸡鸣寺站"))).setCross(true);
        line4.get(line4.indexOf(new Station("金马路站"))).setCross(true);

        line4.get(0).setDistance(line4.get(1),1574);
        line4.get(1).setDistance(line4.get(2),1828);
        line4.get(2).setDistance(line4.get(3),821);
        line4.get(3).setDistance(line4.get(4),1359);
        line4.get(4).setDistance(line4.get(5),807);
        line4.get(5).setDistance(line4.get(6),1391);
        line4.get(6).setDistance(line4.get(7),2018);
        line4.get(7).setDistance(line4.get(8),1128);
        line4.get(8).setDistance(line4.get(9),2749);
        line4.get(9).setDistance(line4.get(10),2569);
        line4.get(10).setDistance(line4.get(11),2564);
        line4.get(11).setDistance(line4.get(12),3233);
        line4.get(12).setDistance(line4.get(13),1187);
        line4.get(13).setDistance(line4.get(14),1938);
        line4.get(14).setDistance(line4.get(15),2454);
        line4.get(15).setDistance(line4.get(16),2185);
        line4.get(16).setDistance(line4.get(17),3212);

        line4.get(1).setDistance(line4.get(0),1574);
        line4.get(2).setDistance(line4.get(1),1828);
        line4.get(3).setDistance(line4.get(2),821);
        line4.get(4).setDistance(line4.get(3),1359);
        line4.get(5).setDistance(line4.get(4),807);
        line4.get(6).setDistance(line4.get(5),1391);
        line4.get(7).setDistance(line4.get(6),2018);
        line4.get(8).setDistance(line4.get(7),1128);
        line4.get(9).setDistance(line4.get(8),2749);
        line4.get(10).setDistance(line4.get(9),2569);
        line4.get(11).setDistance(line4.get(10),2564);
        line4.get(12).setDistance(line4.get(11),3233);
        line4.get(13).setDistance(line4.get(12),1187);
        line4.get(14).setDistance(line4.get(13),1938);
        line4.get(15).setDistance(line4.get(14),2454);
        line4.get(16).setDistance(line4.get(15),2185);
        line4.get(17).setDistance(line4.get(16),3212);

        //10号线
        String line10Str = "雨山路站、文德路站、龙华路站、南京工业大学站、浦口万汇城站、临江站、江心洲站、绿博园站、梦都大街站、奥体中心站、元通站、中胜站、小行站、安德门站";
        String[] line10Arr = line10Str.split("、");
        model.put("line10", line10Arr);
        for(String s : line10Arr){
            line10.add(new Station(s));
        }
        for(int i =0;i<line10.size();i++){
            line10.get(i).setCross(false);
            if(i<line10.size()-1){
                line10.get(i).next = line10.get(i+1);
                line10.get(i+1).prev = line10.get(i);
            }
        }
        line10.get(line10.indexOf(new Station("元通站"))).setCross(true);
        line10.get(line10.indexOf(new Station("安德门站"))).setCross(true);

        line10.get(0).setDistance(line10.get(1),1729);
        line10.get(1).setDistance(line10.get(2),1154);
        line10.get(2).setDistance(line10.get(3),1427);
        line10.get(3).setDistance(line10.get(4),1134);
        line10.get(4).setDistance(line10.get(5),833);
        line10.get(5).setDistance(line10.get(6),4545);
        line10.get(6).setDistance(line10.get(7),1350);
        line10.get(7).setDistance(line10.get(8),1512);
        line10.get(8).setDistance(line10.get(9),848);
        line10.get(9).setDistance(line10.get(10),1865);
        line10.get(10).setDistance(line10.get(11),1400);
        line10.get(11).setDistance(line10.get(12),1307);
        line10.get(12).setDistance(line10.get(13),2157);

        line10.get(1).setDistance(line10.get(0),1729);
        line10.get(2).setDistance(line10.get(1),1154);
        line10.get(3).setDistance(line10.get(2),1427);
        line10.get(4).setDistance(line10.get(3),1134);
        line10.get(5).setDistance(line10.get(4),833);
        line10.get(6).setDistance(line10.get(5),4545);
        line10.get(7).setDistance(line10.get(6),1350);
        line10.get(8).setDistance(line10.get(7),1512);
        line10.get(9).setDistance(line10.get(8),848);
        line10.get(10).setDistance(line10.get(9),1865);
        line10.get(11).setDistance(line10.get(10),1400);
        line10.get(12).setDistance(line10.get(11),1307);
        line10.get(13).setDistance(line10.get(12),2157);


        //s1号线
        String lineS1Str = "南京南站、翠屏山站、河海大学·佛城西路站、吉印大道站、正方中路站、翔宇路北站、翔宇路南站、禄口机场站";
        String[] lineS1Arr = lineS1Str.split("、");
        model.put("lineS1", lineS1Arr);
        for(String s : lineS1Arr){
            lineS1.add(new Station(s));
        }
        for(int i =0;i<lineS1.size();i++){
            lineS1.get(i).setCross(false);
            if(i<lineS1.size()-1){
                lineS1.get(i).next = lineS1.get(i+1);
                lineS1.get(i+1).prev = lineS1.get(i);
            }
        }
        lineS1.get(lineS1.indexOf(new Station("南京南站"))).setCross(true);

        lineS1.get(0).setDistance(lineS1.get(1),4077);
        lineS1.get(1).setDistance(lineS1.get(2),3252);
        lineS1.get(2).setDistance(lineS1.get(3),3242);
        lineS1.get(3).setDistance(lineS1.get(4),4723);
        lineS1.get(4).setDistance(lineS1.get(5),7283);
        lineS1.get(5).setDistance(lineS1.get(6),4234);
        lineS1.get(6).setDistance(lineS1.get(7),7926);

        lineS1.get(1).setDistance(lineS1.get(0),4077);
        lineS1.get(2).setDistance(lineS1.get(1),3252);
        lineS1.get(3).setDistance(lineS1.get(2),3242);
        lineS1.get(4).setDistance(lineS1.get(3),4723);
        lineS1.get(5).setDistance(lineS1.get(4),7283);
        lineS1.get(6).setDistance(lineS1.get(5),4234);
        lineS1.get(7).setDistance(lineS1.get(6),7926);


        //s8号线
        String lineS8Str = "泰山新村站、泰冯路站、高新开发区站、信息工程大学站、卸甲甸站、大厂站、葛塘站、长芦站、化工园站、六合开发区站、龙池站、雄州站、凤凰山公园站、方州广场站、沈桥站、八百桥站、金牛湖站";
        String[] lineS8Arr = lineS8Str.split("、");
        model.put("lineS8", lineS8Arr);
        for(String s : lineS8Arr){
            lineS8.add(new Station(s));
        }
        for(int i =0;i<lineS8.size();i++){
            lineS8.get(i).setCross(false);
            if(i<lineS8.size()-1){
                lineS8.get(i).next = lineS8.get(i+1);
                lineS8.get(i+1).prev = lineS8.get(i);
            }
        }
        lineS8.get(lineS8.indexOf(new Station("泰冯路站"))).setCross(true);

        lineS8.get(0).setDistance(lineS8.get(1),1236);
        lineS8.get(1).setDistance(lineS8.get(2),2741);
        lineS8.get(2).setDistance(lineS8.get(3),2830);
        lineS8.get(3).setDistance(lineS8.get(4),1401);
        lineS8.get(4).setDistance(lineS8.get(5),1829);
        lineS8.get(5).setDistance(lineS8.get(6),2151);
        lineS8.get(6).setDistance(lineS8.get(7),3880);
        lineS8.get(7).setDistance(lineS8.get(8),1567);
        lineS8.get(8).setDistance(lineS8.get(9),2839);
        lineS8.get(9).setDistance(lineS8.get(10),2077);
        lineS8.get(10).setDistance(lineS8.get(11),2375);
        lineS8.get(11).setDistance(lineS8.get(12),1606);
        lineS8.get(12).setDistance(lineS8.get(13),1475);
        lineS8.get(13).setDistance(lineS8.get(14),6112);
        lineS8.get(14).setDistance(lineS8.get(15),4979);
        lineS8.get(15).setDistance(lineS8.get(16),5475);

        lineS8.get(1).setDistance(lineS8.get(0),1236);
        lineS8.get(2).setDistance(lineS8.get(1),2741);
        lineS8.get(3).setDistance(lineS8.get(2),2830);
        lineS8.get(4).setDistance(lineS8.get(3),1401);
        lineS8.get(5).setDistance(lineS8.get(4),1829);
        lineS8.get(6).setDistance(lineS8.get(5),2151);
        lineS8.get(7).setDistance(lineS8.get(6),3880);
        lineS8.get(8).setDistance(lineS8.get(7),1567);
        lineS8.get(9).setDistance(lineS8.get(8),2839);
        lineS8.get(10).setDistance(lineS8.get(9),2077);
        lineS8.get(11).setDistance(lineS8.get(10),2375);
        lineS8.get(12).setDistance(lineS8.get(11),1606);
        lineS8.get(13).setDistance(lineS8.get(12),1475);
        lineS8.get(14).setDistance(lineS8.get(13),6112);
        lineS8.get(15).setDistance(lineS8.get(14),4979);
        lineS8.get(16).setDistance(lineS8.get(15),5475);

        lineSet.add(line1);
        lineSet.add(line2);
        lineSet.add(line3);
        lineSet.add(line4);
        lineSet.add(line10);
        lineSet.add(lineS1);
        lineSet.add(lineS8);

        lineMap.put("line1", line1);
        lineMap.put("line2", line2);
        lineMap.put("line3", line3);
        lineMap.put("line4", line4);
        lineMap.put("line10", line10);
        lineMap.put("lines1", lineS1);
        lineMap.put("lines8", lineS8);
        totalStaion  = line1.size() + line2.size() + line3.size() +line4.size() + line10.size() + lineS1.size() + lineS8.size();
        System.out.println("总的站点数量："+totalStaion);
    }
}



