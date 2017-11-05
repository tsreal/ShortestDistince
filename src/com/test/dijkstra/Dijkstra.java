/*
A       C           B       E       F           G
A-B 14  4+9=13
A-C 4
A-D *   4+11=15
A-E *   *           13+7=20
A-F 25                      20+3=23
A-G *   *           *       *       23+8=31
A-H *   *           *       20+9=29             

 */
package com.test.dijkstra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;


public class Dijkstra {

    private List<Node> openList = new ArrayList<>();//未访问过

    private List<Node> closeList = new ArrayList<>();//已访问过

    private Node A = new Node("A");
    private Node B = new Node("B");
    private Node C = new Node("C");
    private Node D = new Node("D");
    private Node E = new Node("E");
    private Node F = new Node("F");
    private Node G = new Node("G");
    private Node H = new Node("H");

    //初始化数据节点之间的关系
    private void init(){

        A.linkedNode.add(B);
        A.linkedNode.add(C);
        A.linkedNode.add(F);
        A.setValue(B,14);
        A.setValue(C,4);
        A.setValue(F,25);

        B.linkedNode.add(A);
        B.linkedNode.add(C);
        B.linkedNode.add(E);
        B.setValue(A, 14);
        B.setValue(C, 9);
        B.setValue(E, 7);

        C.linkedNode.add(A);
        C.linkedNode.add(B);
        C.linkedNode.add(D);
        C.setValue(A, 4);
        C.setValue(B, 9);
        C.setValue(D, 11);

        D.linkedNode.add(C);
        D.linkedNode.add(E);
        D.linkedNode.add(H);
        D.setValue(C, 11);
        D.setValue(E, 12);
        D.setValue(H, 5);

        E.linkedNode.add(B);
        E.linkedNode.add(D);
        E.linkedNode.add(F);
        E.linkedNode.add(H);
        E.setValue(B, 7);
        E.setValue(D, 12);
        E.setValue(F, 3);
        E.setValue(H, 9);

        F.linkedNode.add(A);
        F.linkedNode.add(E);
        F.linkedNode.add(G);
        F.setValue(A, 25);
        F.setValue(E, 3);
        F.setValue(G, 8);

        G.linkedNode.add(F);
        G.linkedNode.add(H);
        G.setValue(F, 8);
        G.setValue(H, 17);

        H.linkedNode.add(D);
        H.linkedNode.add(E);
        H.linkedNode.add(G);
        H.setValue(D, 5);
        H.setValue(E, 9);
        H.setValue(G, 17);

        openList.add(A);
        openList.add(B);
        openList.add(C);
        openList.add(D);
        openList.add(E);
        openList.add(F);
        openList.add(G);
        openList.add(H);
    }

    //计算从start到end，走过的路径
    private void calculate(Node start, Node end){
        if(closeList.size() == openList.size()){
            System.out.println(start.getName()+"->"+end.getName()+" min.length.length:"+start.getValue(end));
            return;
        }
        Node childNode = getMinValueNode(start);//找到目前除已经分析过的节点之外的距离start节点最近的节点
        start.getAllPassNodes(childNode).add(childNode);//记录扩展到当前最近节点所有经过的节点
        if(childNode == end){
            System.out.println(start.getName()+"->"+end.getName()+" min.length:"+start.getValue(end));
            return;
        }
        //System.out.println("当前距离"+start.getName()+"最近节点为："+childNode.getName());
        for(Node ccNode : childNode.linkedNode){
            if(closeList.contains(ccNode)){
                continue;
            }
            //a->c+c->b=4+9=13 目前的距离
            int ccnodeValue = start.getValue(childNode)+childNode.getValue(ccNode);//超过最大值之后，会变成负数
            if(Math.abs(ccnodeValue) < start.getValue(ccNode)){
                start.setValue(ccNode,ccnodeValue);
                System.out.println(start.getName()+"->"+ccNode.getName()+"的目前最短距离是："+ccnodeValue);//这个最短距离只是暂时的，只要分析没有结束，最短距离可能进一步缩小
                start.getAllPassNodes(ccNode).clear();//临时最短距离缩小，所经过路径也清除重新添加
                start.getAllPassNodes(ccNode).addAll(start.getAllPassNodes(childNode));
                start.getAllPassNodes(ccNode).add(ccNode);
            }
        }
        closeList.add(childNode);
        calculate(start,end);//重复计算A到所有点的最短距离之后，再取距离A最短的节点，对其进行子节点分析【往外面节点扩展分析】
    }

    //取跟入参节点距离最近的节点，如果有多个相同距离的节点，则随便取其中一个
    private Node getMinValueNode(Node node){
        Node retNode = null;
        int minValue = Integer.MAX_VALUE;
        for(Node n : node.getValueMap().keySet()){
            if(closeList.contains(n)){
                continue;
            }
            if(node.getValue(n) < minValue){
                minValue = node.getValue(n);
                retNode = n;
            }
        }
        return retNode;
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        d.init();
        d.closeList.add(d.A);
        d.calculate(d.A, d.G);
        //打印路径
        String info = "";
        for(Node node : d.A.getAllPassNodes(d.G)){
//            System.out.print(node.getName()+"->");
            info += node.getName()+"->";
        }
        System.out.println(info.substring(0,info.length()-2));
    }
}


class Node {

    private String name;

    //记录本Node所有相连的Node
    List<Node> linkedNode = new ArrayList<>();

    //记录本Node与其它Node的最短距离
    private Map<Node,Integer> valueMap = new HashMap<>();

    //记录从本Node到其它Node之间最短距离时所有经过的节点，并保持前后顺序，其实与valueMap对应
    private Map<Node,LinkedHashSet<Node>> orderSetMap = new HashMap<Node,LinkedHashSet<Node>>();

    Node(String name){
        this.name = name;
    }
    //该点的最短路径值
    void setValue(Node node, Integer value){
        valueMap.put(node, value);
    }

    //如果没有本节点到参数节点的取值，则默认最大值
    Integer getValue(Node node){
        return valueMap.get(node) == null? Integer.MAX_VALUE : valueMap.get(node);
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //返回某一节点到子节点最短路径
    Map<Node, Integer> getValueMap() {
        return valueMap;
    }

    //取本节点到参数节点经过的所有节点集合
    LinkedHashSet<Node> getAllPassNodes(Node node) {
        if(orderSetMap.get(node) == null){
            LinkedHashSet<Node> set = new LinkedHashSet<>();
            //添加Node(A)点
            set.add(this);
            orderSetMap.put(node, set);
            //key: Node C
            //value: Node A
        }
        return orderSetMap.get(node);
    }

}