# December 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

h = (0, 0)
t = (0, 0)
s = set()
for line in file:
	line = line.strip().split(" ")
	m = line[0]
	d = int(line[1])
	for _ in range(d):
		c = None
		if m == "U":
			c = (0, 1)
		elif m == "D":
			c = (0, -1)
		elif m == "L":
			c = (-1, 0)
		else:
			c = (1, 0)
		h = (h[0] + c[0], h[1] + c[1])
		if abs(h[0] - t[0]) >= 2:
			t = (t[0] + c[0], h[1])
		elif abs(h[1] - t[1]) >= 2:
			t = (h[0], t[1] + c[1])
		s.add(t)
print(len(s))
