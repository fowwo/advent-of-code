# March 9th, 2022

file = open("input.txt", "r")

components = []
for line in file:
	split = line.strip().split('/')
	components.append((int(split[0]), int(split[1])))

bfs = [[(0, 0)]]
memo = [ set() ]
store = []
while len(bfs) > 0:
	bfs2 = []
	memo2 = []
	for i in range(0, len(bfs)):
		store.append(bfs[i])
		for component in components:
			if component not in memo[i] and tuple(reversed(component)) not in memo[i] and bfs[i][-1][1] in component:
				path = [x for x in bfs[i]]
				if component[0] == bfs[i][-1][1]:
					path.append(component)
				else:
					path.append(tuple(reversed(component)))
				m = set(memo[i])
				m.add(component)
				bfs2.append(path)
				memo2.append(m)
	bfs = bfs2
	memo = memo2

# Find longest bridges
longest = []
length = 0
for bridge in store:
	if len(bridge) > length:
		longest = [ bridge ]
		length = len(bridge)
	elif len(bridge) == length:
		longest.append(bridge)

maximum = 0
for bridge in longest:
	strength = 0
	for x in bridge:
		strength += x[0] + x[1]
	maximum = max(maximum, strength)
print(maximum)
