# December 2nd, 2022

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file.readlines():
	line = line.strip()
	a = line[:len(line) // 2]
	b = line[len(line) // 2:]
	for x in a:
		if x in b:
			s += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".find(x) + 1
			break
print(s)
