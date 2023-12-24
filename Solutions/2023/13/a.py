# December 24th, 2023

file = open(f"{__file__}/../input.txt", "r")

m = []
while line := file.readline().strip():
	n = [ line ]
	while line := file.readline().strip():
		n.append(line)
	m.append(n)

def reflect(n):
	for i in range(1, len(n[0])):
		v = True
		for row in n:
			if any(x != y for x, y in zip(row[i - 1::-1], row[i:])):
				v = False
				break
		if v: return i

s = 0
for n in m:
	if x := reflect(n): s += x
	else: s += 100 * reflect(list(zip(*n)))
print(s)
