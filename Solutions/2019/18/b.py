# May 2nd, 2022

file = open("input.txt", 'r')

alphabet = "abcdefghijklmnopqrstuvwxyz"
valid = set()
keys = {}
doors = {}
r = 0
for line in file:
	c = 0
	for symbol in line.strip():
		if symbol != '#':
			valid.add((r, c))
			if symbol in alphabet:
				keys[(r, c)] = symbol
			elif symbol in alphabet.upper():
				doors[(r, c)] = symbol.lower()
		c += 1
	r += 1

# Split maze
valid.remove((39, 40))
valid.remove((40, 39))
valid.remove((40, 40))
valid.remove((40, 41))
valid.remove((41, 40))

dist = [ { '@': {} }, { '@': {} }, { '@': {} }, { '@': {} } ]
for i in range(0, 4):
	for x in alphabet:
		dist[i][x] = {}
		for y in alphabet:
			dist[i][x][y] = -1
		dist[i][x][x] = 0

# Find keys required (doors) for each key
req = []
for i in range(0, 2):
	for j in range(0, 2):
		paths = [(40 + 2 * i - 1, 40 + 2 * j - 1, "")]
		memo = set([(40 + 2 * i - 1, 40 + 2 * j - 1)])
		depth = 0
		req.append({})
		while len(paths):
			depth += 1
			new = []
			for path in paths:
				r = path[0]
				c = path[1]
				d = path[2]
				for nxt in [(r+1,c),(r,c+1),(r-1,c),(r,c-1)]:
					if nxt in valid and nxt not in memo:
						t = d
						if nxt in doors:
							t += doors[nxt]
						elif nxt in keys:
							req[2 * i + j][keys[nxt]] = d
							dist[2 * i + j]['@'][keys[nxt]] = depth
							t += keys[nxt]
						new.append((nxt[0], nxt[1], t))
						memo.add((nxt[0], nxt[1]))
			paths = new

# Find distance between keys
for key in keys:
	q = 0
	for i in range(0, 4):
		if keys[key] in req[i]:
			q = i
			break
	paths = [(key[0], key[1], "")]
	memo = set([(key[0], key[1])])
	name = keys[key]
	need = set([x for x in req[q] if x != name])
	depth = 0
	while len(need):
		depth += 1
		new = []
		for path in paths:
			r = path[0]
			c = path[1]
			d = path[2]
			for nxt in [(r+1,c),(r,c+1),(r-1,c),(r,c-1)]:
				if nxt in valid and nxt not in memo:
					if nxt in keys and keys[nxt] in need:
						dist[q][name][keys[nxt]] = depth
						dist[q][keys[nxt]][name] = depth
						need.remove(keys[nxt])
					new.append((nxt[0], nxt[1], d))
					memo.add((nxt[0], nxt[1]))
		paths = new

minimum = 1000000000
paths = [(['@','@','@','@'], set([x for x in alphabet]), 0)]
while len(paths):
	new = []
	for path in paths:
		current = path[0]
		needed = path[1]
		depth = path[2]
		for nxt in needed:
			for i in range(0, 4):
				head = current[i]
				if nxt not in req[i]: continue
				possible = True
				for y in req[i][nxt]:
					if y in needed:
						possible = False
						break
				if possible:
					copy = set(needed)
					copy.remove(nxt)
					if len(copy) == 0:
						minimum = min(minimum, depth + dist[i][head][nxt])
					else:
						nxtCopy = [x for x in current]
						nxtCopy[i] = nxt
						new.append((nxtCopy, copy, depth + dist[i][head][nxt]))
				break
	paths = new

	# Remove duplicates
	i = 0
	while i < len(paths) - 1:
		j = i + 1
		while j < len(paths):
			if paths[i][0] == paths[j][0] and paths[i][1] == paths[j][1]:
				if paths[i][2] > paths[j][2]:
					paths[i] = paths[j]
				del paths[j]
			else:
				j += 1
		i += 1
print(minimum)
