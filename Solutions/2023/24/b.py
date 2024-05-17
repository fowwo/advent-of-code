# May 16th, 2024
from math import sqrt

file = open(f"{__file__}/../input.txt", "r")
hailstones = [ tuple(tuple(map(int, hailstone.split(", "))) for hailstone in line.strip().split(' @ ')) for line in file ]
file.close()

def dotProduct(a, b):
	return sum(x * y for x, y in zip(a, b))
def crossProduct(a, b):
	i, j, k = a
	x, y, z = b
	return (j * z - y * k, x * k - i * z, i * y - x * j)
def magnitude(a):
	x, y, z = a
	return sqrt(x ** 2 + y ** 2 + z ** 2)
def signedDistance(a, b):
	(p1, v1) = a
	(p2, v2) = b
	c = crossProduct(v1, v2)
	d = tuple(x - y for x, y in zip(p2, p1))
	e = dotProduct(c, d)
	return e / magnitude(c) # Division by zero if parallel.
def position(a, t):
	return tuple(p + t * v for p, v in zip(a[0], a[1]))

a, b, c, d = hailstones[0], hailstones[1], hailstones[2], hailstones[3] # Hailstone paths must be skew lines (guaranteed by puzzle input).
p = [[]] # Generate all permutations of these four hailstones.
for x in [ a, b, c, d ]:
	n = []
	for q in p:
		for i in range(len(q) + 1):
			m = list(q)
			m.insert(i, x)
			n.append(m)
	p = n

def f(target, t1, t2):
	x = position(a, t1)
	y = position(b, t2)
	v = (x, tuple(b - a for a, b in zip(x, y)))
	return signedDistance(v, target)

for a, b, c, d in p: # Try all permutations to find the right order of collisions.
	i1, j1 = 0, 1000000000000 # I am assuming that the four hailstones will have collided within a trillion nanoseconds.
	while i1 <= j1:
		t1 = (i1 + j1) >> 1
		i2, j2 = 0, 1000000000000 
		while i2 <= j2:
			t2 = (i2 + j2) >> 1
			d2, y1, y2 = f(c, t1, t2), f(c, t1, i2), f(c, t1, j2)
			if d2 < 0:
				if y1 < 0: i2 = t2 + 1
				else: j2 = t2 - 1
			elif d2 > 0:
				if y1 > 0: i2 = t2 + 1
				else: j2 = t2 - 1
			else:
				# Found the hailstone path.
				x = position(a, t1)
				y = position(b, t2)
				v = tuple((b - a) // (t2 - t1) for a, b in zip(x, y))
				p = position((x, v), -t1)
				print(sum(p))
				exit()
		d1, y1, y2 = f(d, t1, t2), f(d, i1, t2), f(d, j1, t2)
		if d1 < 0:
			if y1 < 0: i1 = t1 + 1
			else: j1 = t1 - 1
		elif d1 > 0:
			if y1 > 0: i1 = t1 + 1
			else: j1 = t1 - 1
