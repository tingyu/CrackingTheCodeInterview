/**
Assume you have a method isSubstring which checks if one word is a substring
of another. Given two strings, si and s2, write code to check If s2 is a rotation of s1
using only onecalltoisSubstring (e.g., "waterbottLe" is a rotation of "erbottLewat").
pg74
*/

/*
If we imagine that s2 is a rotation of si, then we can ask what the rotation point is. For
example, if you rotate waterbottle after wat, you get erbottlewat. In a rotation, we
cut s1 into two parts, x and y, and rearrange them to get s2.

s1 = xy = waterbottle
x = wat
y = erbottle
s2 = yx = erbottlewat
So, we need to check if there's a way to split s1 into x and y such that xy = s1 and yx =
s2. Regardless of where the division between x and y is, we can see that yx will always
be a substring of xyxy.That is, s2 will always be a substring of slsl.
And this is precisely how we solve the problem: simply do isSubstringtslsl, s2).
*/

public boolean isRotation(String s1, String s2){
	int len = s1.length();
	//check that s1 and s2 are equal length and not empty
	if(len = s2.length() && len > 0){
		//concatenate s1 and s1 within new buffer
		String s1s1 = s1 + s1;
		return isSubstring(s1s1, s2);
	}
	return false;
}