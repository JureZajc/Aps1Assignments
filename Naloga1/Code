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
    String toStringg();
}

interface Stack<T> extends Collection {
    T top() throws CollectionException;
    void push(T x) throws CollectionException;
    T pop() throws CollectionException;
    void dup() throws CollectionException;
    void dup2() throws CollectionException;
    void echo() throws CollectionException;
    void swap() throws CollectionException;
    void reverse();
    void even() throws CollectionException;
    void odd() throws CollectionException;
    void faculty() throws CollectionException;
    void greaterSmaller() throws CollectionException;
    void lessThan() throws CollectionException;
    void lessEqual() throws CollectionException;
    void equal() throws CollectionException;
    void greater() throws CollectionException;
    void greaterEqual() throws CollectionException;
    void addition() throws CollectionException;
    void substraction() throws CollectionException;
    void multiplication() throws CollectionException;
    void division() throws CollectionException;
    void remainderAtDivision() throws CollectionException;
    void concate() throws CollectionException;
    void rnd() throws CollectionException;   
}

interface Deque<T> extends Collection {
    T front() throws CollectionException;
    T back() throws CollectionException;
    void enqueue(T x) throws CollectionException;
    void enqueueFront(T x) throws CollectionException;
    T dequeue() throws CollectionException;
    T dequeueBack() throws CollectionException;
}


interface Sequence<T> extends Collection {
    static final String ERR_MSG_INDEX = "Wrong index in sequence.";
    T get(int i) throws CollectionException;
    T set(int i, T x) throws CollectionException;
}

/**
 * @author Jure Zajc
 *
 * @param <T>
 */

class ArrayDeque<T> implements Deque<T>, Stack<T>, Sequence<T> {
   
    private static final int DEFAULT_CAPACITY = 64;

    @SuppressWarnings("unchecked")
	T[] items = (T[]) new Object[DEFAULT_CAPACITY];
	int front;
	int back;
	int count;

	@SuppressWarnings("unchecked")
    public ArrayDeque() {
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

    public String toStringg() {
        StringBuilder finalString = new StringBuilder("[");
        if (count > 0) {
                finalString.append(items[front]);
                for (int i = 1; i < count; i++) {
                        finalString.append(", ");
                        finalString.append(items[(front + i) % items.length]);
                }
        }
        finalString.append("]");
        return finalString.toString();
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

    public T pop() throws CollectionException {
        if (isEmpty())
        	throw new CollectionException(ERR_MSG_EMPTY);
        return dequeueBack();
    }

    public void push(T x) throws CollectionException {
    	if (isFull())
        	throw new CollectionException(ERR_MSG_FULL);
        enqueue(x);
    }

    public T top() throws CollectionException {
    	if (isEmpty())
        	throw new CollectionException(ERR_MSG_EMPTY);
        return back();
    }

    public void dup() throws CollectionException {
    	if (isEmpty())
        	throw new CollectionException(ERR_MSG_EMPTY);
		enqueue(back());
    }

    public void dup2() throws CollectionException {
    	if (isEmpty())
        	throw new CollectionException(ERR_MSG_EMPTY);
        T temp1 = dequeueBack();
        T temp2 = dequeueBack();
        enqueue(temp2); enqueue(temp1); enqueue(temp2); enqueue(temp1);
    }

    public void echo() throws CollectionException {
    	if (isEmpty())
    		System.out.println("");
    	else
    		System.out.println(back());
    }

    public void swap() throws CollectionException {
    	if (isEmpty())
    		throw new CollectionException(ERR_MSG_EMPTY);
    	T temp1 = dequeueBack();
    	T temp2 = dequeueBack();
    	enqueue(temp1); enqueue(temp2);
    }

    @SuppressWarnings("unchecked")
    public void reverse() {
    	T[] elements = (T[]) new Object[DEFAULT_CAPACITY];
    	for (int i = 0; i < count; i++) {
    		elements[i] = items[count - 1 - i];
    	}
    	items = elements;
    }

    public T get(int i) throws CollectionException {
        if (i < 0 || i >= count)
                throw new CollectionException(ERR_MSG_INDEX);
        return items[(front + i) % items.length];
    }

    public T set(int i, T x) throws CollectionException {
        if (i < 0 || i > count)
                throw new CollectionException(ERR_MSG_INDEX);
        int pos = (front + i) % items.length;
        T temp = items[pos];
        items[pos] = x;
        if (i == count) {
                count++;
                back = (back + 1) % items.length;
        }
        return temp;
    }

	@SuppressWarnings("unchecked")
	public void even() throws CollectionException {
		if (Integer.parseInt((String) dequeueBack()) %2 ==0)
			push((T) "1");
		else 
			push((T) "0");
	}

	@SuppressWarnings("unchecked")
	public void odd() throws CollectionException {
		if (Integer.parseInt((String) dequeueBack()) %2 ==0)
			push((T) "0");
		else 
			push((T) "1");
	}

	@SuppressWarnings("unchecked")
	public void faculty() throws CollectionException {
		int top = Integer.parseInt((String) dequeueBack());
		int facul = 1;
		for (int i = top; i > 0; i--) {
			facul *= i;
		}
		push((T) Integer.toString(facul));;
		
	}

	@SuppressWarnings("unchecked")
	public void greaterSmaller() throws CollectionException {
		String top1 = (String) dequeueBack();
		String top2 = (String) dequeueBack();
		String compare;
		if (!top2.equals(top1))
			compare = "1";
		else
			compare = "0";
		push((T) compare);	
	}

	@SuppressWarnings("unchecked")
	public void lessThan() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		String compare;
		if (top2 < top1)
			compare = "1";
		else
			compare = "0";
		push((T) compare);		
	}

	@SuppressWarnings("unchecked")
	public void lessEqual() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		String compare;
		if (top2 <= top1)
			compare = "1";
		else
			compare = "0";
		push((T) compare);		
	}

	@SuppressWarnings("unchecked")
	public void equal() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		String compare;
		if (top2 == top1)
			compare = "1";
		else
			compare = "0";
		push((T) compare);
	}

	@SuppressWarnings("unchecked")
	public void greater() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		String compare;
		if (top2 > top1)
			compare = "1";
		else
			compare = "0";
		push((T) compare);		
	}

	@SuppressWarnings("unchecked")
	public void greaterEqual() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		String compare;
		if (top2 >= top1)
			compare = "1";
		else
			compare = "0";
		push((T) compare);			
	}

	@SuppressWarnings("unchecked")
	public void addition() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		push((T)Integer.toString(top2 + top1));		
	}

	@SuppressWarnings("unchecked")
	public void substraction() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		push((T) Integer.toString(top2 - top1));
		
	}

	@SuppressWarnings("unchecked")
	public void multiplication() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		push((T) Integer.toString(top2 * top1));
		
	}

	@SuppressWarnings("unchecked")
	public void division() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		push((T) Integer.toString(top2 / top1));
		
	}

	@SuppressWarnings("unchecked")
	public void remainderAtDivision() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		push((T) Integer.toString(top2 % top1)); 
		
	}
	
	@SuppressWarnings("unchecked")
	public void concate() throws CollectionException {
		String concate1 = (String) dequeueBack();
		String concate2 = (String) dequeueBack();
		push((T) (concate2 + concate1));
		
	}

	@SuppressWarnings("unchecked")
	public void rnd() throws CollectionException {
		int top1 = Integer.parseInt((String) dequeueBack());
		int top2 = Integer.parseInt((String) dequeueBack());
		int rnd = (int) (Math.random() * (top2 - top1 + 1)) + top1;
		push((T) Integer.toString(rnd));	  
		
	}
}
//glavni class
public class Naloga1 {

	static Sequence<Stack<String>> allStacks;
	static boolean condition;

	public static void main(String[] args) throws Exception { 
		String[] commands = new String[0];	
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String newLine = null;
		while (sc.hasNextLine()) {//naredimo 42 stackov
			allStacks = new ArrayDeque<Stack<String>>();
			for (int i = 0; i < 42; i++) {
				allStacks.set(i, new ArrayDeque<String>());
			}
			condition = false;
			newLine = sc.nextLine();
			if (!newLine.trim().isEmpty()) {
				commands = newLine.trim().split("\\s+");
				parseCommands(commands);
			}
		}
				
	}

	public static void parseCommands(String[] commands) throws CollectionException {
		Stack<String> mainStack = allStacks.get(0);
		for (int i = 0; i < commands.length; i++) {
			if (commands[i].toCharArray()[0] == '?') {
				if (condition)
					commands[i] = commands[i].substring(1);
			}
			 switch (commands[i]) 
		        {
		            case "echo": mainStack.echo();break;
		            case "pop":  mainStack.pop();break;
		            case "dup":  mainStack.dup();break;
		            case "dup2": mainStack.dup2();break;
		            case "swap": mainStack.swap();break;
		            case "char": mainStack.push(Character.toString((char) Integer.parseInt(mainStack.pop())));break;
		            case "even": mainStack.even();break;
		            case "odd":	 mainStack.odd();;break;
		            case "!":    mainStack.faculty();break;
		            case "len":  mainStack.push(Integer.toString(mainStack.pop().length()));break;
		            case "<>": 	 mainStack.greaterSmaller();break;
		            case "==":   mainStack.equal();break;
		            case "<":    mainStack.lessThan();break;
		            case "<=":   mainStack.lessEqual();break;
		            case ">": 	 mainStack.greater();break;
		            case ">=":   mainStack.greaterEqual();break;
		            case "+":    mainStack.addition();break;
		            case "-":    mainStack.substraction();break;
		            case "*":    mainStack.multiplication();break;
		            case "/":    mainStack.division();break;
		            case "%": 	 mainStack.remainderAtDivision();break;
		            case ".": 	 mainStack.concate();break;
		            case "rnd":  mainStack.rnd();break;
		            case "then": 
		            	String state = mainStack.pop();
						if (state.equals("0")) {
							condition = false;
						} else {
							condition = true;
						}break;
		            case "else":  condition = !condition;break;
		            case "print": 
		            	Stack<String> printStack = allStacks.get(Integer.parseInt(mainStack.pop()));
						int len = printStack.count();
						printStack.reverse();
						String[] elements = new String[len];
						for (int j = 0; j < len; j++) {
							elements[j] = printStack.pop();
							System.out.print(elements[j] + " ");
						}
						for (int j = 0; j < elements.length; j++) {
							printStack.push(elements[j]);
						}System.out.println(); break;
		            case "clear": 
		            	Stack<String> clearStack = allStacks.get(Integer.parseInt(mainStack.pop()));
						int sizeClear = clearStack.count();
						for (int j = 0; j < sizeClear; j++) {
							clearStack.pop();
						} break;
		            case "run": 
		            	Stack<String> runStack = allStacks.get(Integer.parseInt(mainStack.pop()));
						int lenRun = runStack.count();
						runStack.reverse();
						String[] finalElements = new String[lenRun];
						for (int j = 0; j < lenRun; j++) {
							finalElements[j] = runStack.pop();
						}
						for (int j = 0; j < finalElements.length; j++) {
							runStack.push(finalElements[j]);
						}
						parseCommands(finalElements);break;
		            case "loop": 
		            	Stack<String> loopStack = allStacks.get(Integer.parseInt(mainStack.pop()));
						int lenLoopAgainThisCase = loopStack.count();
						int numRepeats = Integer.parseInt(mainStack.pop());
						for (int j = 0; j < numRepeats; j++) {
							loopStack.reverse();
							String[] elementsWhyIDoneWithCases = new String[lenLoopAgainThisCase];
							for (int k = 0; k < lenLoopAgainThisCase; k++) {
								elementsWhyIDoneWithCases[k] = loopStack.pop();
							}
							for (int k = 0; k < elementsWhyIDoneWithCases.length; k++) {
								loopStack.push(elementsWhyIDoneWithCases[k]);
							}
							parseCommands(elementsWhyIDoneWithCases);
						} break;
		            case "fun":
		            	Stack<String> justAnotherStack = allStacks.get(Integer.parseInt(mainStack.pop()));
						int numCommands = Integer.parseInt(mainStack.pop());
						for (int j = 0; j < numCommands; j++) {
							justAnotherStack.push(commands[i + 1 + j]);
						}
						i += numCommands;break;
		            case "move": 
		            	Stack<String> whyWeCantHaveSameVariableNames = allStacks.get(Integer.parseInt(mainStack.pop()));
						int someCommandsToDo = Integer.parseInt(mainStack.pop());
						for (int j = 0; j < someCommandsToDo; j++) {
							whyWeCantHaveSameVariableNames.push(mainStack.pop());
						}break;
		            case "reverse":  allStacks.get(Integer.parseInt(mainStack.pop())).reverse();break;	            
		            default:
		            	if (commands[i].charAt(0) != '?') {
		            		mainStack.push(commands[i]);
		            	}break;
		        }
			}			
		}
}
