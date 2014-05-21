/**
1.3
Given two strings, write a method to decide if one is a permutation of the other
*/

/*
Like in many questions, we should confirm some details with our interviewer. We should
understand if the anagram comparison is case sensitive. That is, is God an anagram of
dog? Additionally, we should ask if whitespace is significant.
We will assume for this problem that the comparison is case sensitive and whitespace is
significant. So, "god
" is different from "dog".

Whenever we compare two strings, we know that if they are different lengths then they
cannot be anagrams.

There are two easy ways to solve this problem, both of which use this optimization.
*/
/**
Solution #1: Sort the strings.

If two strings are anagrams, then we know they have the same characters, but in different
orders. Therefore, sorting the strings will put the characters from two anagrams in the
same order. We just need to compare the sorted versions of the strings.
*/

public String sort(String s){
	char[] content = s.toCharArray();
	java.util.Arrays.sort(content);
	return new String(content);
}

public boolean permutation(String s, String t){
	if(s.length()!=t.length()){
		return false;
	}
	return sort(s).equals(sort(t));
}


/**
Though this algorithm is not as optimal in some senses, it may be preferable in one
sense: it's clean, simple and easy to understand. In a practical sense, this may very well
be a superior way to implement the problem.

However, if efficiency is very important, we can implement it a different way.


Solution #2: Check if the two strings have identical character counts.
We can also use the definition of an anagram—two words with the same character
counts—to implement this algorithm. We simply iterate through this code, counting
how many times each character appears.Then, afterwards, we compare the two arrays.
*/

public boolean permutation(String s, String t){
	if(s.length()!= t.length()){
		return false;
	}

	int[] letters = new int[256]; //Assumption

	char[] s_array = s.toCharArray();
	for(char c: s_array){ //count number of each char in s
		letters[c]++;
	}

	for(int i=0; i < t.length(); i++){
		int c = (int) t.charAt(i);
		if(--letters[c]<0)
			return false;
	}
	return true;
}

//Note the assumption on line 6. In your interview, you should always check with your
//interviewer about the size of the character set. We assumed that the character set was
//ASCII.
