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

memo = dict()
routes = [ (0, 0, 1) ]
memo[(0, 0, 1)] = 0
while len(routes):
	new = []
	for route in routes:
		x = route[0]
		y = route[1]
		tool = route[2]
		time = memo[route]
		if (target[0], target[1], 1) in memo and memo[route] >= memo[(target[0], target[1], 1)]: continue
		for next in [(x - 1, y, tool), (x + 1, y, tool), (x, y - 1, tool), (x, y + 1, tool)]:
			# Move
			if next[0] < 0 or next[1] < 0: continue
			if next not in memo or memo[next] > time + 1:
				e = el[(next[0], next[1])] if (next[0], next[1]) in el else calculateErosionLevel(next[0], next[1])
				if e % 3 != tool:
					new.append(next)
					memo[next] = time + 1
		for next in [(x, y, (tool + 1) % 3), (x, y, (tool + 2) % 3)]:
			# Switch tools
			if next not in memo or memo[next] > time + 7:
				e = el[(x, y)] if (x, y) in el else calculateErosionLevel(x, y)
				if e % 3 != next[2]:
					new.append(next)
					memo[next] = time + 7
	routes = new
print(memo[(target[0], target[1], 1)])
