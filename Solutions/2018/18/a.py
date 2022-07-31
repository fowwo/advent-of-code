# July 30th, 2022

file = open("input.txt", 'r')
field = [ [ x for x in line.strip() ] for line in file ]
file.close()

for _ in range(10):
	copy = [ [ y for y in x ] for x in field ]
	for i in range(50):
		for j in range(50):
			adj = [ (k, l) for k in range(i - 1, i + 2) for l in range(j - 1, j + 2) ]
			adj.remove((i, j))
			for pos in set(adj):
				if pos[0] < 0 or pos[0] >= 50 or pos[1] < 0 or pos[1] >= 50:
					adj.remove(pos)
			for k in range(len(adj)): adj[k] = field[adj[k][0]][adj[k][1]]
			if field[i][j] == '.':
				trees = 0
				for x in adj:
					if x == '|':
						trees += 1
				if trees >= 3: copy[i][j] = '|'
			elif copy[i][j] == '|':
				lumberyards = 0
				for x in adj:
					if x == '#':
						lumberyards += 1
				if lumberyards >= 3: copy[i][j] = '#'
			else:
				if '|' not in adj or '#' not in adj: copy[i][j] = '.'
	field = copy

trees = 0
lumberyards = 0
for i in range(50):
	for j in range(50):
		if field[i][j] == '|': trees += 1
		elif field[i][j] == '#': lumberyards += 1
print(trees * lumberyards)
