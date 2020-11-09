# November 8th, 2020

file = open("../../input/2015/1.txt", "r")
string = file.readline()

floor = 1
for i in range(len(string)):
	if string[i] == "(":
		floor += 1
	else:
		floor -= 1
	if floor == -1:
		print(i)
		exit()
