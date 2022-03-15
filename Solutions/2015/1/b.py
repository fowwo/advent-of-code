# November 8th, 2020

file = open(f"{__file__}/../input.txt", "r")
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
