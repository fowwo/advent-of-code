# March 28th, 2022
import math

file = open("input.txt", 'r')

planets = []
for line in file:
	split = line.strip().split(", ")
	a = int(split[0].split("=")[1])
	b = int(split[1].split("=")[1])
	c = int(split[2][0:-1].split("=")[1])
	planets.append(([a, b, c], [0, 0, 0]))
initial = planets

def toString(c):
	string = ""
	for x in planets:
		string += f"{x[0][c]},{x[1][c]},"
	return string[0:-1]

# Find cycle length of each coordinate
cycles = [0, 0, 0]
for c in range(0, 3):
	memo = set()
	string = toString(c)
	planets = initial
	step = 0
	while string not in memo:
		memo.add(string)
		copy = [x for x in planets]
		for i in range(0, 3):
			for j in range(i + 1, 4):
				v = 0
				if planets[i][0][c] > planets[j][0][c]: v = -1
				elif planets[i][0][c] < planets[j][0][c]: v = 1
				copy[i][1][c] += v
				copy[j][1][c] -= v
		planets = copy
		for x in planets:
			x[0][c] += x[1][c]
		string = toString(c)
		step += 1
	cycles[c] = step

# Find least common multiple of cycle lengths
print(math.lcm(*cycles))
