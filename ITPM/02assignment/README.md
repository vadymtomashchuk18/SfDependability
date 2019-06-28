How to fill test file? Every line have another meaning:
1st line - number of criterias
2nd line - name of criterias splited by comma ','
3rd line - number of alternatives
4th line - names of alternatives splited by comma ','

Every next line is line of comparison matrix (every criteria with each other and diagonal values are 1). Values are splited with one or more spaces. Every [i,j] and [j,i] are conversed, it means [i,j] = 1/([j,i]). Example for 3 criterias:
  1  1vs2 1vs3   or      1     1vs2   1vs3
2vs1   1  2vs3       1/(1vs2)    1    2vs3
3vs1 3vs2   1        1/(1vs3) 1/(2vs3)   1

Then input matrixes for each criteria. You can divide them by empty lines.

The whole example you can see below:
4
C1, C2, C3, C4
3
A, B, C
1    5   3  7
1/5  1  1/3 5
1/3  3   1  6
1/7 1/5 1/6 1

1    6  8
1/6  1  4
1/8 1/4 1

1   7 1/5
1/7 1 1/8
5   8  1

1   8  6
1/8 1 1/4
1/6 4  1

1   5  4
1/5 1 1/3
1/4 3  1

FOR EXECUTING:
1) You can create your own test file as was explained earlier or use test01.out or test02.txt file
	file_path = "test02.out"
2) Create an object of class AHP
	my_ahp_obj = AHP(file_path)
3) Call the method final_vector()
	my_ahp_obj.final_vector()
