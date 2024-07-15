public class main {
    public static void main(String[] args) {

        int sum = 0;
        for (int j = 0; j< 1000; j++) {
            Treap<Integer> treap = new Treap<>();
            for (int i = 0; i < 100000; i++) {
                treap.add(i);
            }
            System.out.println(treap.getTreapHeight());
            sum += treap.getTreapHeight();
        }
        System.out.println(sum/1000);
        Treap<Integer> treap2 = new Treap<>();
        treap2.add(10);
        treap2.add(12);
        treap2.add(30);
        treap2.add(40);
        treap2.add(4);
        Integer kth = treap2.kthSmallestElement(2);
        System.out.println(kth.intValue());


    }
}