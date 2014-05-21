/**
1.2
Implement a function void reverse(char* str) in C or C++ which reverses a null-termi-
nated string.
*/

//This is a classic interview question. The only "gotcha" is totry todo it in place, and to be
//careful for the null character

void reverse(char *str){
	char* end = str;
	char tmp;

	if(str){
		while(*end){ //find the end of the String
			++end;
		}
		--end; //set one char back, since the last char is null
	
	//swap char from the start of the String to the end of the String, until the pointers meet in the middle
		while(str < end){
			tmp = *str;
			*str++ = *end;
			*end--=tmp;
		}
	}
}

//This is just one of many ways to implement this solution. We could even implement this code recursively 
//(but we wouldn't recommend it).

