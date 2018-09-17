import java.util.*;

public class T9 {

	static Map<Character, Character> letterNumberMap;
	Map<String, ArrayList<Integer>> sequencesWords;
	ArrayList<String> words;

	static {
		letterNumberMap = new HashMap<Character, Character>();

		letterNumberMap.put('a', '2');
		letterNumberMap.put('b', '2');
		letterNumberMap.put('c', '2');
		letterNumberMap.put('d', '3');
		letterNumberMap.put('e', '3');
		letterNumberMap.put('f', '3');
		letterNumberMap.put('g', '4');
		letterNumberMap.put('h', '4');
		letterNumberMap.put('i', '4');
		letterNumberMap.put('j', '5');
		letterNumberMap.put('k', '5');
		letterNumberMap.put('l', '5');
		letterNumberMap.put('m', '6');
		letterNumberMap.put('n', '6');
		letterNumberMap.put('o', '6');
		letterNumberMap.put('p', '7');
		letterNumberMap.put('q', '7');
		letterNumberMap.put('r', '7');
		letterNumberMap.put('s', '7');
		letterNumberMap.put('t', '8');
		letterNumberMap.put('u', '8');
		letterNumberMap.put('v', '8');
		letterNumberMap.put('w', '9');
		letterNumberMap.put('x', '9');
		letterNumberMap.put('y', '9');
		letterNumberMap.put('z', '9');
	}

	public T9() {
		words = new ArrayList<String>();
		sequencesWords = new HashMap<String, ArrayList<Integer>>();
	}

	private int addWord(String word) {
		words.add(word);
		return words.size() - 1;
	}

	public void addWordToDictionary(String word) {
		String wordTmp = word.toLowerCase();

		if (word.length() > 0 && !words.contains(word)) {
			int wIndex = addWord(word);

			StringBuilder newSequence = new StringBuilder();

			for (int i = 0; i < word.length(); i++) {
				newSequence.append(letterNumberMap.get(wordTmp.charAt(i)));

				if (sequencesWords.containsKey(newSequence.toString())) {
					ArrayList<Integer> indices = sequencesWords.get(newSequence.toString());
					indices.add(wIndex);
				} else {
					sequencesWords.put(newSequence.toString(), new ArrayList<Integer>(Arrays.asList(wIndex)));
				}
			}
		}
	}

	public ArrayList<String> getWords(String sequence) {
		ArrayList<String> wordsToReturn = new ArrayList<String>();
		int sequenceLength = sequence.length();
		
		if (sequencesWords.containsKey(sequence)) {
			ArrayList<Integer> indices = sequencesWords.get(sequence);

			for (Integer index : indices) {
				String wordToAdd = words.get(index).substring(0, sequenceLength);
				if(!wordsToReturn.contains(wordToAdd))
					wordsToReturn.add(wordToAdd);
			}
		}

		Collections.sort(wordsToReturn, new Comparator<String>() {
			@Override
			public int compare(String word1, String word2) {

				return word1.compareTo(word2);
			}
		});

		return wordsToReturn;
	}

	public static void main(String[] args) {
		T9 t9 = new T9();
		
		//sequences typed
		String[] sequences = {"8", "87", "873", "8733", "4", "46", "466", "4663", "83", "8322437", "8368873"};
		
		// add words to our dictionary
		t9.addWordToDictionary("used");
		t9.addWordToDictionary("tree");
		t9.addWordToDictionary("teacher");
		t9.addWordToDictionary("home");
		t9.addWordToDictionary("venture");
		t9.addWordToDictionary("us");
		t9.addWordToDictionary("to");

		for(int i=0; i< sequences.length; i++) {
			String seq = sequences[i];
			
			ArrayList<String> words = t9.getWords(seq);
			
			System.out.println("-------Sequence " + seq);
			
			for (String word : words) {
				System.out.println(word);
			}
		}
	}
}
