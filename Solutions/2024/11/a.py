# December 10th, 2024

file = open(f"{__file__}/../input.txt", "r")
ints = [ int(x) for x in file.readline().strip().split() ]
file.close()

for _ in range(25):
	n = []
	for x in ints:
		if x == 0:
			n.append(1)
		elif len(str(x)) & 1 == 0:
			s = str(x)
			n.append(int(s[:len(s) // 2]))
			n.append(int(s[len(s) // 2:]))
		else:
			n.append(x * 2024)
	ints = n

print(len(ints))
