# March 22nd, 2022

file = open("input.txt", 'r')

asteroids = set()
i = 0
for line in file:
	for c in range(0, len(line)):
		if line[c] == '#':
			asteroids.add(f"{i},{c}")
	i += 1

high = 0
for a in asteroids:
	count = 0
	for b in asteroids:
		if a == b: continue
		split = a.split(',')
		ar = int(split[0])
		ac = int(split[1])
		split = b.split(',')
		br = int(split[0])
		bc = int(split[1])
		dr = br - ar
		dc = bc - ac
		if dc == 0:
			dr = dr / abs(dr)
		else:
			dr /= abs(dc)
			dc = dc / abs(dc)
		r = ar + dr
		c = ac + dc
		sight = True
		tr = round(100 * r) / 100 # Floating-point error
		while tr != br or c != bc:
			if tr % 1 == 0 and f"{int(tr)},{int(c)}" in asteroids:
				sight = False
				break
			r += dr
			c += dc
			tr = round(100 * r) / 100
		if sight:
			count += 1
	high = max(high, count)
print(high)
