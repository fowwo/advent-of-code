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
						portal[portal[s]] = (r,c)
						portal[(r,c)] = portal[s]
						del portal[s]
aa = portal["AA"]
zz = portal["ZZ"]
del portal["AA"]
del portal["ZZ"]

# BFS
paths = [(aa[0], aa[1], 0)]
memo = set()
while len(paths):
	new = []
	for x in paths:
		x2 = (x[0], x[1])
		if x2 == zz:
			print(x[2])
			exit()
		if x2 in portal:
			new.append((portal[x2][0],portal[x2][1],x[2] + 1))
			del portal[portal[x2]]
			del portal[x2]
		else:
			for y in [(x[0]+1,x[1]),(x[0],x[1]+1),(x[0]-1,x[1]),(x[0],x[1]-1)]:
				if y not in memo and maze[y[0]][y[1]] == '.':
					memo.add(y)
					new.append((y[0], y[1], x[2] + 1))
	paths = new
