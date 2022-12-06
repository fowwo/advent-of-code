# December 5th, 2022

file = open(f"{__file__}/../input.txt", "r")

x = file.readline().strip()

for i in range(len(x)):
	s = x[i:i+14]
	if len(set(s)) == 14:
		print(i + 14)
		exit()
