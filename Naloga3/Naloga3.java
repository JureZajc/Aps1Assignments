import java.util.Scanner;


@SuppressWarnings("serial")
class CollectionException extends Exception {
    public CollectionException(String msg) {
         super(msg);
    }
}


interface Collection {
    static final String ERR_MSG_EMPTY = "Collection is empty.";
    static final String ERR_MSG_FULL = "Collection is full.";
    boolean isEmpty();
    boolean isFull();
    int count();
    String toStg();
}

class Edge {

	int vert1; int vert2;

	public Edge(int v1, int v2) {
		this.vert1 = v1; this.vert2 = v2;
	}
}
//snippet of Naloga1
class Deque<T> implements Collection {
    private static final int DEFAULT_CAPACITY = 256;

    @SuppressWarnings("unchecked")
	T[] items = (T[]) new Object[DEFAULT_CAPACITY];
	int front;
	int back;
	int count;

	@SuppressWarnings("unchecked")
    public Deque() {
        @SuppressWarnings("unused")
		T[] items = (T[]) new Object[DEFAULT_CAPACITY];
        @SuppressWarnings("unused")
		int front = 0;
        @SuppressWarnings("unused")
		int back = 0;
        @SuppressWarnings("unused")
		int count = 0;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == items.length;
    }
    
    public String toStg() {
        StringBuilder sb = new StringBuilder("[");
        if (count > 0) {
                sb.append(items[front]);
                for (int i = 1; i < count; i++) {
                        sb.append(", ");
                        sb.append(items[(front + i) % items.length]);
                }
        }
        sb.append("]");
        return sb.toString();
    }

    public T front() throws CollectionException {
        if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
        return items[front];
    }

    public T back() throws CollectionException {
        if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
        return items[(back - 1 + items.length) % items.length];
    }

    public void enqueue(T x) throws CollectionException {
        if (isFull())
                throw new CollectionException(ERR_MSG_FULL);
        items[back] = x;
        back = (back + 1) % items.length;
        count++;
    }

    public void enqueueFront(T x) throws CollectionException {
        if (isFull())
                throw new CollectionException(ERR_MSG_FULL);
        front = (front - 1 + items.length) % items.length;
        items[front] = x;
        count++;
    }

    public T dequeue() throws CollectionException {
        if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
        T temp = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        count--;
        return temp;
    }

    public T dequeueBack() throws CollectionException {
        if (isEmpty())
                throw new CollectionException(ERR_MSG_EMPTY);
        T temp = items[(back - 1 + items.length) % items.length];
        items[(back - 1 + items.length) % items.length] = null;
        back = (back - 1 + items.length) % items.length;
        count--;
        return temp;
    }
}

/**
 * 
 */
class Graph {

	public String type;//directed or undirected
	public int nVerts;
	public Edge[] arrEdges;

	private String entry;
	private String exit;
	private String order;
	private int[] paths;

	public Graph(String type, int nVerts, Edge[] edges) {
		this.type = type;
		this.nVerts = nVerts;
		this.arrEdges = edges;
	}

	public void walks(int length) {
		int[][] matrix = new int[nVerts][nVerts];
		for (int i = 0; i < arrEdges.length; i++) {
			matrix[arrEdges[i].vert1][arrEdges[i].vert2] = 1;
			if (type.equals("undirected"))
				matrix[arrEdges[i].vert2][arrEdges[i].vert1] = 1;//dodamo še drugo povezavo
		}
		int[][] walks = matrix;
		for (int i = 1; i < length; i++) {
			walks = multiplyMatrices(walks, matrix);
		}
		for (int i = 0; i < nVerts; i++) {
			for (int j = 0; j < nVerts; j++) {
				System.out.print(walks[i][j] + " ");	
			}
			System.out.println();
		}
	}

	public void dfsF() {
		boolean[] visited = new boolean[nVerts];
		entry = "";//brez tega vrača null na začetku
		exit = "";
		for (int i = 0; i < nVerts; i++) {
			if (!visited[i])
				dfs(i, visited);
		}
		System.out.println(entry);
		System.out.println(exit);
	}

	private void dfs(int v, boolean[] visited) {
		entry += Integer.toString(v) + " ";
		visited[v] = true;
		int[] neighbors = neighbours(v);
		for (int i = 0; i < neighbors.length; i++) {
			if (!visited[neighbors[i]])
				dfs(neighbors[i], visited);
		}
		exit += Integer.toString(v) + " ";
	}

	public void bfsF() throws Exception {
		boolean[] visited = new boolean[nVerts];
		order = "";
		for (int i = 0; i < nVerts; i++) {
			if (!visited[i])
				bfs(i, visited);
		}
		System.out.println(order);
	}
	
	private void bfs(int v, boolean[] visited) throws CollectionException {
		Deque<Integer> deque = new Deque<Integer>();
		visited[v] = true;
		deque.enqueue(v);
		while (!deque.isEmpty()) {
			v = deque.dequeue();
			order += v + " ";
			int[] neighbours = neighbours(v);
			for (int i = 0; i < neighbours.length; i++) {
				if (!visited[neighbours[i]]) {
					visited[neighbours[i]] = true;
					deque.enqueue(neighbours[i]);
				}
			}
		}
	}
				
	

	@SuppressWarnings("unused")
	private void bfs2(int v, boolean[] visited, String mode) throws Exception {
		Deque<Integer> deque = new Deque<Integer>();
		if (mode.equals("sp"))
			paths[v] = 0;
		visited[v] = true;
		deque.enqueue(v);
		while (!deque.isEmpty()) {
			v = deque.dequeue();
			if (mode.equals("bfs"))
				order += v + " ";
			int[] neighbours = neighbours(v);
			for (int i = 0; i < neighbours.length; i++) {
				if (!visited[neighbours[i]]) {
					if (mode.equals("sp"))
						paths[neighbours[i]] = paths[v] + 1;
					visited[neighbours[i]] = true;
					deque.enqueue(neighbours[i]);
				}
			}
		}
	}

	public void sp(int v) throws Exception {
		boolean[] visited = new boolean[nVerts];
		paths = new int[nVerts];
		for (int i = 0; i < nVerts; i++) {
			paths[i] = -1;
		}
		bfs(v, visited, "sp");
		for (int i = 0; i < nVerts; i++) {
			System.out.println(i + " " + paths[i]);
		}
	}

	private int[][] multiplyMatrices(int[][] matrix, int[][] matrix1) {
		int[][] multipliedMatrix = new int[matrix.length][matrix.length];
		int sum = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix1[0].length; j++) {
				for (int k = 0; k < matrix[0].length; k++) {
					sum += matrix[i][k] * matrix1[k][j];
				}
				multipliedMatrix[i][j] = sum;
				sum = 0;
			}
		};
		return multipliedMatrix;
	}

	private int countOccurencies(int element) {
		int count = 0;
		for (int i = 0; i < arrEdges.length; i++) {
			if (type.equals("directed")) {//samo enosmerna povezava
				if (arrEdges[i].vert1 == element)
					count++;
			} else {
				if (arrEdges[i].vert1 == element || arrEdges[i].vert2 == element) {//dodamo obe povezavi
					count++;
				}
			}
		}
		return count;
	}

	private int[] neighbours(int v) {
		int[] neighbours = new int[countOccurencies(v)];
		int index = 0;
		for (int i = 0; i < arrEdges.length; i++) {
			if (arrEdges[i].vert1 == v) {
				neighbours[index] = arrEdges[i].vert2;
				index++;
			}
			if (type.equals("undirected")) {
				if (arrEdges[i].vert2 == v) {
					neighbours[index] = arrEdges[i].vert1;//dodamo obe povezavi
					index++;
				}
			}
		}
		insertionSort(neighbours);
		return neighbours;
	}
	//basic insertion sort
	private void insertionSort(int[] arr) 
    { 
        int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j>=0 && arr[j] > key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
    } 

}
/**
 * @author Jure Zajc
 *
 */
public class Naloga3 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String[] firstLine = scanner.nextLine().split(" ");//vzamemo prvo vrstico in jo splittamo po presledkih
		int nVerts = Integer.parseInt(scanner.nextLine().trim());
		StringBuilder stringBuilder = new StringBuilder();//pomagamo si s SB, za lažje delo
		StringBuilder stringBuilder1 = new StringBuilder();
		while (scanner.hasNextLine()) {
			String[] line = scanner.nextLine().trim().split(" ");
			stringBuilder.append(line[0] + ":");
			stringBuilder1.append(line[1] + ":");			
		}
		String[] vert1 = stringBuilder.toString().split(":");
		String[] vert2 = stringBuilder1.toString().split(":");
		Edge[] edges = new Edge[vert1.length];
		for (int i = 0; i < vert1.length; i++) {
			edges[i] = new Edge(Integer.parseInt(vert1[i]), Integer.parseInt(vert2[i]));
		}
		Graph graph = new Graph(firstLine[0], nVerts, edges);
		if (firstLine[1].equals("walks")) graph.walks(Integer.parseInt(firstLine[2]));//walks
		else if (firstLine[1].equals("dfs")) graph.dfsF();//dfs
		else if (firstLine[1].equals("bfs")) graph.bfsF();//bfs
		else if (firstLine[1].equals("sp")) graph.sp(Integer.parseInt(firstLine[2]));
	}
}
