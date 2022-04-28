# April 28th, 2022

file = open("input.txt", 'r')
maze = file.read().splitlines()
file.close()

# Find portals
alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
portal = {}
for r in range(2, len(maze) - 2):
	for c in range(2, len(maze[0]) - 2):
		if maze[r][c] == '.':
			for x in [((r-2,c),(r-1,c)), ((r+1,c),(r+2,c)), ((r,c-2),(r,c-1)), ((r,c+1),(r,c+2))]:
				a = maze[x[0][0]][x[0][1]]
				b = maze[x[1][0]][x[1][1]]
				if a in alphabet and b in alphabet:
					s = a + b
					if s not in portal:
						portal[s] = (r,c)
					else:
						d = -1
						if c == 2 or c == len(maze[0]) - 3 or r == 2 or r == len(maze) - 3: d = 1
						portal[portal[s]] = (r,c,d)
						portal[(r,c)] = (portal[s][0],portal[s][1],-d)
						del portal[s]
aa = portal["AA"]
zz = portal["ZZ"]
del portal["AA"]
del portal["ZZ"]

# BFS
paths = [(aa[0], aa[1], 0, 0)]
memo = set()
while len(paths):
	new = []
	for x in paths:
		x2 = (x[0], x[1])
		if x2 == zz and x[2] == 0:
			print(x[3])
			exit()
		if x2 in portal:
			nxt = (portal[x2][0],portal[x2][1],x[2] + portal[x2][2])
			if nxt[2] >= 0 and nxt not in memo:
				memo.add(nxt)
				new.append((nxt[0],nxt[1],nxt[2],x[3] + 1))
				continue
		for y in [(x[0]+1,x[1],x[2]),(x[0],x[1]+1,x[2]),(x[0]-1,x[1],x[2]),(x[0],x[1]-1,x[2])]:
			if y not in memo and maze[y[0]][y[1]] == '.':
				memo.add(y)
				new.append((y[0], y[1], y[2], x[3] + 1))
	paths = new
