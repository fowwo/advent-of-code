# August 1st, 2022

file = open("input.txt", 'r')
depth = int(file.readline().strip().split(": ")[1])
s = [ int(x) for x in file.readline().strip().split(": ")[1].split(',') ]
target = (s[0], s[1])
file.close()

gi = dict()
el = dict()
def calculateGeologicIndex(x, y):
	if (x, y) == (0, 0) or (x, y) == target: gi[(x, y)] = 0
	elif x == 0: gi[(x, y)] = y * 48271
	elif y == 0: gi[(x, y)] = x * 16807
	else:
		a = el[(x - 1, y)] if (x - 1, y) in el else calculateErosionLevel(x - 1, y)
		b = el[(x, y - 1)] if (x, y - 1) in el else calculateErosionLevel(x, y - 1)
		gi[(x, y)] = a * b
	return gi[(x, y)]
def calculateErosionLevel(x, y):
	g = gi[(x, y)] if (x, y) in gi else calculateGeologicIndex(x, y)
	el[(x, y)] = (g + depth) % 20183
	return el[(x, y)]

risk = 0
for x in range(target[0] + 1):
	for y in range(target[1] + 1):
		if (x, y) not in el:
			calculateErosionLevel(x, y)
		risk += el[(x, y)] % 3
print(risk)
