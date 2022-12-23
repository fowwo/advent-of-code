# December 22nd, 2022

file = open(f"{__file__}/../input.txt", "r")

m = set()
for i, line in enumerate(file):
	for j, c in enumerate(line.strip()):
		if c == "#": m.add((i, j))

directions = [[(-1, -1), (-1, 0), (-1, 1)], [(1, -1), (1, 0), (1, 1)], [(-1, -1), (0, -1), (1, -1)], [(-1, 1), (0, 1), (1, 1)] ]
for _ in range(10):
	prop = dict()
	propc = dict()
	new = set()
	for e in m:
		r, c = e
		v = True
		for i in range(-1, 2):
			for j in range(-1, 2):
				if i == 0 and j == 0: continue
				if (r + i, c + j) in m:
					v = False
					break
		if v:
			prop[e] = e
			if e not in propc: propc[e] = 0
			propc[e] += 1
			continue
		for d in directions:
			ar, ac = d[0]
			br, bc = d[1]
			cr, cc = d[2]
			if (r + ar, c + ac) not in m and (r + br, c + bc) not in m and (r + cr, c + cc) not in m:
				x = (r + br, c + bc)
				prop[e] = x
				if x not in propc: propc[x] = 0
				propc[x] += 1
				break
		if e not in prop:
			prop[e] = e
			if e not in propc: propc[e] = 0
			propc[e] += 1
	for e in prop:
		if propc[prop[e]] == 1:
			new.add(prop[e])
		else:
			new.add(e)
	m = new
	directions = directions[1:] + [ directions[0] ]
	
w = max(x[1] for x in m) - min(x[1] for x in m) + 1
h = max(x[0] for x in m) - min(x[0] for x in m) + 1
print(w * h - len(m))
