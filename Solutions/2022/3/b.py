# December 2nd, 2022

file = open(f"{__file__}/../input.txt", "r")

s = 0
lines = file.readlines()
for i in range(len(lines) // 3):
	a = lines[3 * i].strip()
	b = lines[3 * i + 1].strip()
	c = lines[3 * i + 2].strip()
	for x in a:
		if x in b and x in c:
			s += "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".find(x) + 1
			break
print(s)
