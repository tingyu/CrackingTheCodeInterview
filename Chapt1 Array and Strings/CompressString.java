/**
Implement a method to perform basic string compression using the counts of
repeated characters. For example, the string aabcccccaaa would become
a2blc5a3. If the "compressed" string would not become smaller than the original
string, your method should return the original str
*/

/*
At first glance, implementing this method seems fairly straightforward, but perhaps
a bit tedious. We iterate through the string, copying characters to a new string and
counting the repeats. How hard could it be?
*/

public String compressBad(String str){
	String mystr = "";
	char last = str.charAt(0);
	int count = 1;
	for(int i = 1; i < str.length(); i++){
		if(str.charAt(i) == last){ //Found repeat char
			count++;
		} else{ //insert char count, and update last char
			mystr += last + "" + count;
			last = str.charAt(i);
			count = 1;
		}
	}
	//why mystr + last + count, not mystr??????????????????????????
	return mystr + last + count;
} 


/*
This code doesn't handle the case when the compressed string is longer than the original
string, but it otherwise works. Is it efficient though? Take a look at the runtime of
this code.

The runtime is 0(p + k^2), where p is the size of the original string and k is the number
of character sequences. ????????????????????????????????????????

For example, if the string is aabccdeeaa, then there are six character sequences. 
It's slow because string concatenation operates in 0(n^2) time (see
StringBuffer in Chapter 1).
*/


//We can make this somewhat better by using a StringBuffer.
String compressBetter(String str){
	//check if compression would create a longer String
	int size = countCompression(str);
	if(size >= str.length()){
		return str;
	}

	StringBuffer mystr = new StringBuffer();
	char last = str.charAt(0);
	int count = 1;
	for(int i = 1; i < str.length(); ++i){
		if(str.charAt(i) == last){ //Found repeated char
			count++;
		} else { //insert char count, and update last char
			mystr.append(last);	//insert char
			mystr.append(count); //insert count
			last = str.charAt(i);
			count = 1;
		}
	}

	/* In lines 15 - 16 above, characters are inserted when the
	* repeated character changes. We need to update the string at
	* the end of the method as well, since the very last set of
	* repeated characters wouldn't be set in the compressed string
	* yet. */  //??????????????????????????????????????????????????
	mystr.append(last);
	mystr.append(count);
	return mystr;
}


int countCompression(String str){
	if(str == null || str.isEmpty()) return 0;
	char last = str.charAt(0);
	int size = 0;
	int count = 1;
	for(int i = 1; i < str.length(); i++){
		if(str.charAt(i) == last){
			count++;
		}else{
			last = str.charAt(i);
			size +=1 + String.valueOf(count).length();
			coumt = 1;
		}
	}
	size += 1 + String.valueOf(count).length();
	return size;
}

/**
If we don't want to (or aren't allowed to) use a StringBuffer, we can still solve this
problem efficiently. In line 2, we compute the end size of the string. This allows us to
create a char array of the correct size, so we can implement the code as follows
*/
String compressAlternate(String str){
	//check if compression would create a longer String
	int size = countCompression(str);
	if(size >= str.length()){
		return str;
	}

	char[] array = new char[size];
	int index = 0;
	char last = str.charAt(0);
	int count = 1;
	for(int i = 1; i < str.length(); i++){
		if(str.charAt(i) == last) { //Found repeated character
			count++;
		}else{
			//update the repeated character count
			index  = setChar(array, last, index, count);
			last = str.charAt(i);
			count = 1;
		}
	}

	//update String with the last set of repeated characters
	index = setChar(array, last, index, count);
	return String.valueOf(array);
}

????????????????????????????????????????????????????????????????????????????????
int setChar(char[] array, char c, int index, int count){
	array[index] = c;
	index++;

	//convert the count to a String, then to an array of chars
	char[] cnt = String.valueOf(count).toCharArray();

	//copy characters from biggest digit to smallest
	for(char x: cnt){
		array[index] = x;
		index ++;
	}
	return index;
}