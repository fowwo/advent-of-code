# December 7th, 2023

from math import lcm

file = open(f"{__file__}/../input.txt", "r")

n = file.readline().strip()
file.readline()

A = []
d = dict()
for line in file:
	[ a, b ] = line.strip().split(" = ")
	d[a] = b[1:-1].split(", ")
	if a.endswith("A"): A.append(a)

m = 1
for k in A:
	c = 0
	b = False
	while not b:
		for x in n:
			c += 1
			k = d[k][0 if x == 'L' else 1]
			if k.endswith("Z"):
				m = lcm(m, c)
				b = True
				break
print(m)
