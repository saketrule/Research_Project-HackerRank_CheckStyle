import java.io.Serializable;
import java.math.BigInteger;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

import java.util.Scanner;

public class Solution {
 final static int MAXVALUE = 100000;

 private static class Edge {
//  private Integer from;
  private Integer to;
  private Integer weight;

  public Edge(Integer from, Integer to, Integer weight) {
//   this.setFrom(from);
   this.setTo(to);
   this.setWeight(weight);
  }

//  public Integer getFrom() {
//   return from;
////  }
//
//  public void setFrom(Integer from) {
//   this.from = from;
//  }

  public Integer getTo() {
   return to;
  }

  public void setTo(Integer to) {
   this.to = to;
  }

  public Integer getWeight() {
   return weight;
  }

  public void setWeight(Integer weight) {
   this.weight = weight;
  }
 }

 private static class Graph {
  private List<Edge>[] adj;
  private int numberOfVertices = 0;
  private Integer[] _allVertices = null;

  public int getNumberOfVertices() {
   return numberOfVertices;
  }

  @SuppressWarnings("unchecked")
  public Graph(int maxValue) {
   adj = (List<Edge>[]) new LinkedList[maxValue];
  }

  public void addEdge(Integer from, Integer to, Integer weight) {
   if (adj[from - 1] == null) {
    adj[from - 1] = new LinkedList<Edge>();
    numberOfVertices++;
   }
   adj[from - 1].add(new Edge(from, to, weight));
  }

  public List<Edge> getNeighbors(Integer id) {
   return adj[id - 1];
  }

  public Integer[] allVertices() {
   if (_allVertices == null) {
    Integer[] tmp = new Integer[getNumberOfVertices()];
    for (int i = 0; i < adj.length; i++) {
     if (adj[i] != null) {
      tmp[i] = i+1;
     }
    }
    _allVertices = tmp;
   }
   return _allVertices;
  }
 }

 static class VertexInfo {
  private Integer distance;
  private Integer nodeId;
  private Integer precedence;

  public VertexInfo(Integer id) {
   this.nodeId = id;
  }

  public VertexInfo(Integer id, Integer distance, Integer precedence) {
   this.distance = distance;
   this.nodeId = id;
   this.precedence = precedence;
  }

  public Integer getDistance() {
   return this.distance;
  }

  public void setDistance(Integer value) {
   this.distance = value;
  }

  public Integer getNodeId() {
   return nodeId;
  }

  public Integer getPrecedence() {
   return precedence;
  }

  public void setPrecedence(Integer precedence) {
   this.precedence = precedence;
  }
 }

 static class VertexInfoDistanceComparator implements Comparator<VertexInfo> {
  public int compare(VertexInfo o1, VertexInfo o2) {
   if ((o1.getDistance() == null) && (o2.getDistance() == null))
    return 0;
   if (o2.getDistance() == null)
    return -1;
   if (o1.getDistance() == null)
    return 1;
   return o1.getDistance().compareTo(o2.getDistance());
  }
 }

 static class UpdatableHeap<O> extends Heap<O> {

  private static final long serialVersionUID = 1L;

  private HashMap<O, Integer> index = new HashMap<O, Integer>();

  public UpdatableHeap() {
   super();
  }

  public UpdatableHeap(int size) {
   super(size);
  }

  /**
   * Constructor with comparator
   * 
   * @param comparator
   *            Comparator
   */
  public UpdatableHeap(Comparator<? super O> comparator) {
   super(comparator);
  }

  /**
   * Constructor with predefined size and comparator
   * 
   * @param size
   *            Size
   * @param comparator
   *            Comparator
   */
  public UpdatableHeap(int size, Comparator<? super O> comparator) {
   super(size, comparator);
  }

  @Override
  public void clear() {
   super.clear();
   index.clear();
  }

  @Override
  public synchronized boolean offer(O e) {
   Integer pos = index.get(e);
   if (pos == null) {
    // LoggingUtil.logExpensive(Level.INFO, "Inserting: "+e);
    // insert
    return super.offer(e);
   } else {
    // update
    if (compareExternal(e, pos) < 0) {
     // LoggingUtil.logExpensive(Level.INFO,
     // "Updating value: "+e+" vs. "+castQueueElement(pos));
     modCount++;
     putInQueue(pos, e);
     heapifyUpParent(pos);
     // We have changed - return true according to {@link
     // Collection#put}
     return true;
    } else {
     // LoggingUtil.logExpensive(Level.INFO,
     // "Keeping value: "+e+" vs. "+castQueueElement(pos));
     // Ignore, no improvement. Return success anyway.
     return true;
    }
   }
  }

  @Override
  protected void putInQueue(int pos, Object e) {
   super.putInQueue(pos, e);
   // Keep index up to date
   if (e != null) {
    O n = castQueueElement(pos);
    index.put(n, pos);
   }
  }

  @Override
  protected synchronized O removeAt(int pos) {
   O node = super.removeAt(pos);
   // Keep index up to date
   index.remove(node);
   return node;
  }

  /**
   * Remove the given object from the queue.
   * 
   * @param e
   *            Obejct to remove
   * @return Existing entry
   */
  public O removeObject(O e) {
   Integer pos = index.get(e);
   if (pos != null) {
    return removeAt(pos);
   } else {
    return null;
   }
  }

  @Override
  public O poll() {
   O node = super.poll();
   index.remove(node);
   return node;
  }
 }

 static class Heap<E> extends AbstractQueue<E>implements Serializable {

  private static final long serialVersionUID = 1L;
  private Object[] queue;
  protected int size = 0;

  /**
   * The comparator or {@code null}
   */
  private final Comparator<? super E> comparator;

  /**
   * (Structural) modification counter. Used to invalidate iterators.
   */
  public transient int modCount = 0;

  /**
   * Default initial capacity
   */
  private static final int DEFAULT_INITIAL_CAPACITY = 11;

  /**
   * Default constructor: default capacity, natural ordering.
   */
  public Heap() {
   this(DEFAULT_INITIAL_CAPACITY, null);
  }

  /**
   * Constructor with initial capacity, natural ordering.
   * 
   * @param size
   *            initial size
   */
  public Heap(int size) {
   this(size, null);
  }

  /**
   * Constructor with {@link Comparator}.
   * 
   * @param comparator
   *            Comparator
   */
  public Heap(Comparator<? super E> comparator) {
   this(DEFAULT_INITIAL_CAPACITY, comparator);
  }

  /**
   * Constructor with initial capacity and {@link Comparator}.
   * 
   * @param size
   *            initial capacity
   * @param comparator
   *            Comparator
   */
  public Heap(int size, Comparator<? super E> comparator) {
   super();
   this.size = 0;
   this.queue = new Object[size];
   this.comparator = comparator;
  }

  @Override
  public synchronized boolean offer(E e) {
   // resize when needed
   considerResize(size + 1);
   final int parent = parent(size);
   // append element
   modCount++;
   putInQueue(size, e);
   this.size = size + 1;
   heapifyUp(parent);
   // We have changed - return true according to {@link Collection#put}
   return true;
  }

  @Override
  public synchronized E peek() {
   if (size == 0) {
    return null;
   }
   return castQueueElement(0);
  }

  @Override
  public E poll() {
   return removeAt(0);
  }

  /**
   * Remove the element at the given position.
   * 
   * @param pos
   *            Element position.
   */
  protected synchronized E removeAt(int pos) {
   if (pos < 0 || pos >= size) {
    return null;
   }
   modCount++;
   E ret = castQueueElement(0);
   // remove!
   putInQueue(pos, queue[size - 1]);
   size = size - 1;
   // avoid dangling references!
   putInQueue(size, null);
   heapifyDown(pos);
   return ret;
  }

  /**
   * Compute parent index in heap array.
   * 
   * @param pos
   *            Element index
   * @return Parent index
   */
  private int parent(int pos) {
   return (pos - 1) / 2;
  }

  /**
   * Compute left child index in heap array.
   * 
   * @param pos
   *            Element index
   * @return left child index
   */
  private int leftChild(int pos) {
   return 2 * pos + 1;
  }

  /**
   * Compute right child index in heap array.
   * 
   * @param pos
   *            Element index
   * @return right child index
   */
  private int rightChild(int pos) {
   return 2 * pos + 2;
  }

  /**
   * Execute a "Heapify Upwards" aka "SiftUp". Used in insertions.
   * 
   * @param pos
   *            insertion position
   */
  protected void heapifyUp(int pos) {
   if (pos < 0 || pos >= size) {
    return;
   }
   // precondition: both child trees are already sorted.
   final int parent = parent(pos);
   final int lchild = leftChild(pos);
   final int rchild = rightChild(pos);

   int min = pos;
   if (lchild < size) {
    if (compare(min, lchild) > 0) {
     min = lchild;
    }
   }
   if (rchild < size) {
    if (compare(min, rchild) > 0) {
     min = rchild;
    }
   }
   if (min != pos) {
    swap(pos, min);
    heapifyUp(parent);
   }
  }

  /**
   * Start a heapify up at the parent of this node, since we've changed a
   * child
   * 
   * @param pos
   *            Position to start the modification.
   */
  protected void heapifyUpParent(int pos) {
   heapifyUp(parent(pos));
  }

  /**
   * Execute a "Heapify Downwards" aka "SiftDown". Used in deletions.
   * 
   * @param pos
   *            re-insertion position
   */
  protected void heapifyDown(int pos) {
   if (pos < 0 || pos >= size) {
    return;
   }
   final int lchild = leftChild(pos);
   final int rchild = rightChild(pos);

   int min = pos;
   if (lchild < size) {
    if (compare(min, lchild) > 0) {
     min = lchild;
    }
   }
   if (rchild < size) {
    if (compare(min, rchild) > 0) {
     min = rchild;
    }
   }
   if (min != pos) {
    // swap with minimal element
    swap(pos, min);
    // recurse down
    heapifyDown(min);
   }
  }


  protected void putInQueue(int index, Object e) {
   queue[index] = e;
  }

  protected void swap(int a, int b) {
   Object oa = queue[a];
   Object ob = queue[b];
   putInQueue(a, ob);
   putInQueue(b, oa);
   modCount++;
  }

  @SuppressWarnings("unchecked")
  protected int compare(int pos1, int pos2) {
   if (comparator != null) {
    return comparator.compare(castQueueElement(pos1), castQueueElement(pos2));
   }
   try {
    Comparable<E> c = (Comparable<E>) castQueueElement(pos1);
    return c.compareTo(castQueueElement(pos2));
   } catch (ClassCastException e) {
    throw e;
   }
  }

  @SuppressWarnings("unchecked")
  protected int compareExternal(E o1, int pos2) {
   if (comparator != null) {
    return comparator.compare(o1, castQueueElement(pos2));
   }
   try {
    Comparable<E> c = (Comparable<E>) o1;
    return c.compareTo(castQueueElement(pos2));
   } catch (ClassCastException e) {
    throw e;
   }
  }

  @SuppressWarnings("unchecked")
  protected int compareExternalExternal(E o1, E o2) {
   if (comparator != null) {
    return comparator.compare(o1, o2);
   }
   try {
    Comparable<E> c = (Comparable<E>) o1;
    return c.compareTo(o2);
   } catch (ClassCastException e) {
    throw e;
   }
  }

  @SuppressWarnings("unchecked")
  protected E castQueueElement(int n) {
   return (E) queue[n];
  }

  @Override
  public int size() {
   return this.size;
  }


  private void considerResize(int requiredSize) {
   if (requiredSize > queue.length) {
    // Double until 64, then increase by 50% each time.
    int newCapacity = ((queue.length < 64) ? ((queue.length + 1) * 2) : ((queue.length / 2) * 3));
    // overflow?
    if (newCapacity < 0) {
     newCapacity = Integer.MAX_VALUE;
    }
    if (requiredSize > newCapacity) {
     newCapacity = requiredSize;
    }
    grow(newCapacity);
   }
  }

  private void grow(int newsize) {
   // check for overflows
   if (newsize < 0) {
    throw new OutOfMemoryError();
   }
   if (newsize == queue.length) {
    return;
   }
   modCount++;
   queue = Arrays.copyOf(queue, newsize);
  }

  @Override
  public void clear() {
   modCount++;
   for (int i = 0; i < size; i++) {
    queue[i] = null;
   }
   this.size = 0;
  }

  @Override
  public boolean contains(Object o) {
   if (o != null) {
    // TODO: use order to reduce search space?
    for (int i = 0; i < size; i++) {
     if (o.equals(queue[i])) {
      return true;
     }
    }
   }
   return false;
  }


  @Override
  public Iterator<E> iterator() {
   return new Itr();
  }

  protected final class Itr implements Iterator<E> {
   private int cursor = 0;
   private int expectedModCount = modCount;

   @Override
   public boolean hasNext() {
    return cursor < size;
   }

   @Override
   public E next() {
    if (expectedModCount != modCount) {
     throw new ConcurrentModificationException();
    }
    if (cursor < size) {
     return castQueueElement(cursor++);
    }
    throw new NoSuchElementException();
   }

   @Override
   public void remove() {
    if (expectedModCount != modCount) {
     throw new ConcurrentModificationException();
    }
    if (cursor > 0) {
     cursor--;
    } else {
     throw new IllegalStateException();
    }
    expectedModCount = modCount;
   }
  }

  /**
   * Return the heap as a sorted array list, by repeated polling. This
   * will empty the heap!
   * 
   * @return new array list
   */
  public ArrayList<E> toSortedArrayList() {
   ArrayList<E> ret = new ArrayList<E>(size());
   while (!isEmpty()) {
    ret.add(poll());
   }
   return ret;
  }
 }

 static class Dijkstra {
  static int INFINITY = Integer.MAX_VALUE;

  private final Graph graph;
  private final UpdatableHeap<VertexInfo> queueDistance;
  private final VertexInfo[] vertexInfo;
  private final Integer source;

  public VertexInfo getVertexInfo(Integer node) {
   return vertexInfo[node - 1];
  }

  private void setVertexInfo(Integer node, VertexInfo vi) {
   vertexInfo[node - 1] = vi;
  }

  public Dijkstra(Graph g, Integer source) {
   this.graph = g;
   this.queueDistance = new UpdatableHeap<VertexInfo>(new VertexInfoDistanceComparator());
   this.vertexInfo = new VertexInfo[graph.getNumberOfVertices()];
   this.source = source;
   initialize();
  }

  public void initialize() {
   for (Integer i : graph.allVertices()) {
    VertexInfo p = new VertexInfo(i, source.equals(i) ? 0 : INFINITY, null);
    queueDistance.add(p);
    setVertexInfo(i, p);
   }
  }

  public void calculate() {
   while (queueDistance.size() > 0) {
    VertexInfo uInfo = queueDistance.remove();
    Integer u = uInfo.getNodeId();
    List<Edge> neighbors = graph.getNeighbors(u);
    for (Edge edge : neighbors) {
     int alt = uInfo.getDistance();
     if (alt <= INFINITY - edge.getWeight()) {
      alt += edge.getWeight();
     }

     Integer v = edge.getTo();
     VertexInfo vInfo = getVertexInfo(v);

     if (alt < vInfo.getDistance()) {
      vInfo.setDistance(alt);
      queueDistance.remove(vInfo);
      queueDistance.add(vInfo);
      vInfo.setPrecedence(u);
     }
    }
   }
  }

  public long printAll() {
   long result = 0;
   for (int i = source + 1; i <= graph.allVertices().length; i++) {
    long distance = getVertexInfo(i).getDistance();
    result += distance;
    // System.out.printf("(%d,%d)=%d\n", source, i, distance);
   }
//   System.out.println();
   return result;
  }
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);

  int N = in.nextInt();
  int M = in.nextInt();

  Graph g = new Graph(N);

  for (int i = 0; i < M; i++) {
   Integer A = in.nextInt();
   Integer B = in.nextInt();
   Integer weight = 2;
   int C = in.nextInt();
   if (C == 0) {
    weight = 1;
   } else {
    weight = 2 << C - 1;
   }
   g.addEdge(A, B, weight);
   g.addEdge(B, A, weight);
  }
  in.close();

  long sum = 0;
  Integer[] allVertices = g.allVertices();
  for (Integer v : allVertices) {
   Dijkstra dij = new Dijkstra(g, v);
   dij.calculate();
   sum += dij.printAll();
  }
  System.out.println(Long.toBinaryString(sum));

  // g.printAllDistances();
 }

}