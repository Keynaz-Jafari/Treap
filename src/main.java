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

    }
}