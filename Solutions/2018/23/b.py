# August 5th, 2022

file = open("input.txt", 'r')

nanobots = []
for line in file:
	s = line.strip().split(", ")
	r = int(s[1][2:])
	s = [ int(x) for x in s[0][5:-1].split(',') ]
	nanobots.append((s[0], s[1], s[2], r))
file.close()

def distance(a, b):
	return abs(a[0] - b[0]) + abs(a[1] - b[1]) + abs(a[2] - b[2])
def inRange(p, n):
	return distance(p, n[:3]) <= n[3]
def overlaps(a, b):
	return distance(a[:3], b[:3]) <= a[3] + b[3]

# Count overlaps
c = [ 0 for _ in nanobots ]
for i in range(len(nanobots) - 1):
	for j in range(i + 1, len(nanobots)):
		if overlaps(nanobots[i], nanobots[j]):
			c[i] += 1
			c[j] += 1

# Remove fewest overlaps until all equal
while min(c) != max(c):
	index = c.index(min(c))
	for i in range(len(nanobots)):
		if overlaps(nanobots[index], nanobots[i]): c[i] -= 1
	del nanobots[index]
	del c[index]

# Find region of overlaps
lb = (max(n[0] - n[3] for n in nanobots), max(n[1] - n[3] for n in nanobots), max(n[2] - n[3] for n in nanobots))
ub = (min(n[0] + n[3] for n in nanobots), min(n[1] + n[3] for n in nanobots), min(n[2] + n[3] for n in nanobots))
p = ((ub[0] - lb[0]) // 2 + lb[0], (ub[1] - lb[1]) // 2 + lb[1], (ub[2] - lb[2]) // 2 + lb[2])
nr = distance(lb, ub) // 2
while nr > 0:
	o = nr
	nr //= 1.5
	o -= nr
	for s in [(o,0,0),(0,o,0),(0,0,o),(-o,0,0),(0,-o,0),(0,0,-o)]:
		zone = (p[0] + s[0], p[1] + s[1], p[2] + s[2], nr)
		count = 0
		for n in nanobots:
			if overlaps(zone, n):
				count += 1
		if count == len(nanobots):
			p = zone[:3]
			break

# Minimize distance
b = True
while b:
	b = False
	for s in [(1,0,0),(0,1,0),(0,0,1)]:
		q = (p[0] - s[0], p[1] - s[1], p[2] - s[2])
		count = 0
		for n in nanobots:
			if inRange(q, n):
				count += 1
		if count == len(nanobots):
			p = q
			b = True
			break
print(int(sum(p))) # Seems like there is only one point of minimum distance, which I already found before minimizing
