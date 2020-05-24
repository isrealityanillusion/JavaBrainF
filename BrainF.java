import java.util.Scanner;
public class BrainF {
    private static int[] tape;
    private static int pointer = 0;
    private static String input;
    private static Scanner scanner = new Scanner(System.in);

    private static void initTape() {
	tape = new int[30000];
	for (int i = 0; i < 30000; i++)
	    tape[i] = 0;
    }

    private static String readInput() {
	String output = "";
	while (scanner.hasNext())
	    output += scanner.nextLine().replaceAll("[^]<>+-.,\\[]","");
	return output;
    }

    private static int execCmd(char c, int index) {
	switch (c) {
	case '<':
	    pointer--;
	    break;
	case '>':
	    pointer++;
	    break;
	case '+':
	    tape[pointer]++;
	    break;
	case '-':
	    tape[pointer]--;
	    break;
	case '.':
	    System.out.print((char)tape[pointer] + "");
	    break;
	case ',':
	    tape[pointer] = scanner.next().charAt(0);
	    break;
	case '[':
	    return findMatchingBrace(c, index);
	case ']':
	    return findMatchingBrace(c, index);
	}
	return index;
    }

    private static int findMatchingBrace(char c, int i) {
	int count = 0;
	switch (c) {
	case ']':
	    if (tape[pointer] != 0) {
		i--;
		while (i >= 0) {
		    if (input.charAt(i) == ']') {
			count++;
			i--;
		    }
		    else if (input.charAt(i) != '[')
			i--;
		    else if (input.charAt(i) == '[' && count == 0)
			break;
		    else {
			count--;
			i--;
		    }
		}
	    }
	    break;
	case '[':
	    if (tape[pointer] == 0) {
		i++;
		while (i >= 0) {
		    if (input.charAt(i) == '['){
			count++;
			i++;
		    }
		    else if (input.charAt(i) != ']')
			i++;
		    else if (input.charAt(i) == ']' && count == 0) {
			i++;
			break;
		    }
		    else {
			count--;
			i++;
		    }
		}
	    }
	    break;
	}
	// System.out.println(i);
	return i;
    }
    
    public static void main(String[] args) {
	if (args.length == 1) {
	    input = args[0];
	}
	// System.out.println(input);
	initTape();
	for (int i = 0; i < input.length(); i++) {
	    i = execCmd(input.charAt(i), i);
	}
    }
}
