# December 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

knots = [ (0, 0) for _ in range(10) ]
s = set()
for line in file:
	line = line.strip().split(" ")
	m = line[0]
	d = int(line[1])
	for _ in range(d):
		c = None
		if m == "U":
			c = (0, -1)
		elif m == "D":
			c = (0, 1)
		elif m == "L":
			c = (-1, 0)
		else:
			c = (1, 0)
		h = knots[0]
		knots[0] = (h[0] + c[0], h[1] + c[1])
		for i in range(1, len(knots)):
			a = knots[i]
			b = knots[i - 1]
			cx = 1 if a[0] < b[0] else -1 if a[0] > b[0] else 0
			cy = 1 if a[1] < b[1] else -1 if a[1] > b[1] else 0
			if abs(b[0] - a[0]) >= 2 or abs(b[1] - a[1]) >= 2:
				knots[i] = (a[0] + cx, a[1] + cy)
		s.add(knots[9])
print(len(s))
