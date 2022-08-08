# August 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

active = set()
i = 0
for line in file:
	j = 0
	for c in line.strip():
		if c == '#': active.add((i, j, 0))
		j += 1
	i += 1
file.close()

def activeNeighbors(x, y, z):
	c = 0
	for i in range(-1, 2):
		for j in range(-1, 2):
			for k in range(-1, 2):
				if i == j == k == 0: continue
				if (x + i, y + j, z + k) in active:
					c += 1
	return c

width = i
for cycle in range(6):
	new = set()
	for i in range(-1 - cycle, 1 + width + cycle):
		for j in range(-1 - cycle, 1 + width + cycle):
			for k in range(-1 - cycle, 1 + width + cycle):
				v = activeNeighbors(i, j, k)
				if v == 3 or ((i, j, k) in active and v == 2):
					new.add((i, j, k))
	active = new

print(len(active))
