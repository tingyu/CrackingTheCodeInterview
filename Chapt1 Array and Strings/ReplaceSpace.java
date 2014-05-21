/**
Write a method to replace all spaces in a string with '%20'. You may assume that the
string has sufficient space at the end of the string to hold the additional characters,
and that you are given the "true" length of the string. (Note: if implementing in Java,
please use a character array so that you can perform this operation in place.)
*/

/*
A common approach in string manipulation problems is to edit the string starting from
the end and work backwards. This is useful because we have extra buffer at the end,
which allows us to change characters without worrying about what we're overwriting.
??????????

We will use this approach in this problem. The algorithm works through a two scan approach. 
In the first scan, we count how many spaces there are in the string. This is
used to compute how long the final string should be. 

In the second pass, which is done in reverse order, we actually edit the string. 
When we see a space, we copy %20 into the next spots. 
If there is no space, then we copy the original character.
*/

public void replaceSpaces(char[] str, int length){
	int spaceCount = 0, newLength, i;
	for(i = 0; i < length; i++){
		if(str[i] == ' '){
			spaceCount++;
		}
	}

	newLength = length + spaceCount*2;
	str[newLength] = '\0';
	for(i = length-1; i >=0; i--){
		if(str[i] == ' '){
			str[newLength - 1] = '0';
			str[newLength - 2] = '2';
			str[newLength - 3] = '%';
			newLength = newLength -3;
		}else{
			str[newLength -1] = str[i];
			newLength = newLength -1;
		}
	}
}

/**
We have implemented this problem using character arrays, since Java strings are immutable.
If we used strings directly, this would require returning a new copy of the string,
but it would allow us to implement this in just one pass.
*/