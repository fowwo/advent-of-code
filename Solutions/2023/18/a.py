# December 18th, 2023

file = open(f"{__file__}/../input.txt", "r")
plans = [ line.strip().split() for line in file ]

r = c = 0
s = set()
for d, v, _ in plans:
	i, j = { "U": (-1, 0), "D": (1, 0), "L": (0, -1), "R": (0, 1) }[d]
	for _ in range(int(v)):
		s.add((r := r + i, c := c + j))

m = [ (1, 1) ]
while m:
	n = []
	for (r, c) in m:
		for p in [ (r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1) ]:
			if p not in s:
				n.append(p)
				s.add(p)
	m = n
print(len(s))
