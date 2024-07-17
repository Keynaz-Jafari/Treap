public class main {
    public static void main(String[] args) {

        int sum = 0;
        for (int j = 0; j< 1000; j++) {
            Treap<Integer> treap = new Treap<>();
            for (int i = 0; i < 100000; i++) {
                treap.add(i);
            }
            //System.out.println(treap.getTreapHeight());
            sum += treap.getTreapHeight();
        }
        System.out.println(sum/1000);

//        Treap<Integer> treap2 = new Treap<>();
//        treap2.add(1);
//        treap2.add(2);
//        treap2.add(10);
//        treap2.add(5);
//        treap2.add(35);
//        treap2.add(7);
//        treap2.add(90);
//        treap2.delete(7);
//        System.out.println(treap2.getMin().getKey());
//        System.out.println(treap2.getMax().getKey());
//        Integer kth = treap2.kthSmallestElement(4);
//        if(kth!=null)
//            System.out.println(kth.intValue());
//        System.out.println(treap2.orderedKeys());
    }
}