import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int Q = readInt();
        char[] buffer = new char[1];
        List<MySet> mySets = new ArrayList<>();
        for (int q = 0; q < Q; q++) {
            readWord(buffer);
            switch (buffer[0]) {
                case 'A': {
                    mySets.add(new MySet(mySets.size(), readInt()));
                    break;
                }
                case 'B': {
                    int x = readInt() - 1, y = readInt() - 1;
                    MySet X = mySets.get(x);
                    MySet Y = mySets.get(y);
                    X.links.add(Y);
                    Y.links.add(X);
                    break;
                }
                case 'C': {
                    int x = readInt() - 1;
                    mergeComponent(mySets, x);
                    break;
                }
            }
        }
        int size = mySets.size();
        for (int i = 0; i < size; i++) {
            MySet X = mySets.get(i);
            if (X != null) mergeComponent(mySets, i);
        }
        int result = 0;
        for (int i = size, size2 = mySets.size(); i < size2; i++) {
            result += mySets.get(i).nodes;
        }
        System.out.println(result);
    }

    private static void mergeComponent(List<MySet> mySets, int x) {
        MySet X = mySets.get(x);
        Set<MySet> component = X.getComponent();
        for (MySet set : component) mySets.set(set.index, null); // nullify all these sets

        mySets.add(new MySet(mySets.size(), getNodes(component, new HashSet<MySet>())));
    }

    static class MySet {
        final int index;
        final int nodes;
        final int independant;
        List<MySet> links = new ArrayList<>();

        public MySet(int index, int nodes) {
            this(index, nodes, nodes);
        }

        public MySet(int index, int nodes, int independant) {
            this.index = index;
            this.nodes = nodes;
            this.independant = independant;
        }

        public Set<MySet> getComponent() {
            Set<MySet> c = new HashSet<>();
            getComponent(c);
            return c;
        }

        private void getComponent(Set<MySet> c) {
            if (!c.contains(this)) {
                c.add(this);
                for (MySet set : links) {
                    set.getComponent(c);
                }
            }
        }

        @Override
        public String toString() {
            return "node " + index;
        }
    }

    static int getNodes(Set<MySet> component, Set<MySet> removed) {
        if (component.size() == 0) return 0;
        if (component.size() == 1) {
            return component.iterator().next().nodes;
        }
        MySet mySet = component.iterator().next();

        component.remove(mySet); removed.add(mySet);
        int nodes = getNodes(component, removed);

        int sizeBefore = component.size();
        component.removeAll(mySet.links);
        if (component.size() == sizeBefore) {
            component.add(mySet); removed.remove(mySet);
            return mySet.nodes + nodes;
        }

        Set<MySet> newRemoved = new HashSet<>(mySet.links);
        newRemoved.addAll(removed);
        nodes = Math.max(nodes, mySet.nodes + getNodes(component, newRemoved));
        for (MySet link : mySet.links) {
            if (!removed.contains(link)) component.add(link);
        }
        component.add(mySet); removed.remove(mySet);
        return nodes;
    }

    static InputStream in = System.in;

    static int readInt() {
        try {
            int c = in.read();
            while (c <= 32) {
                c = in.read();
            }
            boolean minus = false;
            if (c == '-') {
                minus = true;
                c = in.read();
            }
            int result = (c - '0');
            c = in.read();
            while (c >= '0') {
                result = result * 10 + (c - '0');
                c = in.read();
            }
            return minus ? -result : result;
        } catch (IOException e) {
            return -1; // should not happen
        }
    }

    static String readWord(char[] buffer) {
        try {
            int c = in.read();
            while (c <= 32) {
                c = in.read();
            }
            int length = 0;
            while (c > 32) {
                buffer[length] = (char) c;
                c = in.read();
                length++;
            }
            return "";//String.valueOf(buffer, 0, length);
        } catch (IOException ex) {
            return null; // should not happen
        }
    }
}