# March 28th, 2022

file = open("input.txt", 'r')

planets = []
for line in file:
	split = line.strip().split(", ")
	a = int(split[0].split("=")[1])
	b = int(split[1].split("=")[1])
	c = int(split[2][0:-1].split("=")[1])
	planets.append(([a, b, c], [0, 0, 0]))

for _ in range(0, 1000):
	copy = [x for x in planets]
	for i in range(0, 3):
		for j in range(i + 1, 4):
			for c in range(0, 3):
				v = 0
				if planets[i][0][c] > planets[j][0][c]: v = -1
				elif planets[i][0][c] < planets[j][0][c]: v = 1
				copy[i][1][c] += v
				copy[j][1][c] -= v
	planets = copy
	for x in planets:
		for c in range(0, 3):
			x[0][c] += x[1][c]

# Energy
energy = 0
for x in planets:
	p = 0
	k = 0
	for c in range(0, 3):
		p += abs(x[0][c])
		k += abs(x[1][c])
	energy += p * k
print(energy)
