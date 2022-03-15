# March 3rd, 2022

file = open("input.txt", 'r')

i = 0
infected = set()
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
for _ in range(0, 10000):
	node = f"{r},{c}"
	if node in infected:
		infected.remove(node)
		direction = (direction + 1) % 4
	else:
		infected.add(node)
		direction = (direction - 1) % 4
		count += 1
	if direction == 0:
		r -= 1
	elif direction == 1:
		c += 1
	elif direction == 2:
		r += 1
	else:
		c -= 1
print(count)
