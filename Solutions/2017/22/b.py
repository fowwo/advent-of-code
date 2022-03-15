# March 3rd, 2022

file = open("input.txt", 'r')

i = 0
infected = set()
weakened = set()
flagged = set()
for line in file:
	line = line.strip()
	for j in range(0, len(line)):
		if line[j] == '#':
			infected.add(f"{i},{j}")
	i += 1
file.close()

r = 12
c = 12
direction = 0
count = 0
for _ in range(0, 10000000):
	node = f"{r},{c}"
	if node in infected:
		infected.remove(node)
		flagged.add(node)
		direction = (direction + 1) % 4
	elif node in weakened:
		weakened.remove(node)
		infected.add(node)
		count += 1
	elif node in flagged:
		flagged.remove(node)
		direction = (direction + 2) % 4
	else:
		weakened.add(node)
		direction = (direction - 1) % 4
	if direction == 0:
		r -= 1
	elif direction == 1:
		c += 1
	elif direction == 2:
		r += 1
	else:
		c -= 1
print(count)
