# December 8th, 2024

file = open(f"{__file__}/../input.txt", "r")
disk = [ int(x) for x in file.readline().strip() ]
file.close()

blocks = []
spaces = []
for i in range(0, len(disk) - 1, 2):
	blocks.append([ i // 2, disk[i] ])
	spaces.append(disk[i + 1])
blocks.append([ len(blocks), disk[-1] ])
spaces.append(0)

i = len(blocks) - 1
moved = set()
while i:
	x, n = blocks[i]
	if x in moved:
		i -= 1
		continue

	fit = False
	for j, y in enumerate(spaces):
		if j == i: break
		if n <= y:
			blocks.insert(j + 1, blocks.pop(i))
			spaces[i - 1] += n + spaces.pop(i)
			spaces[j] -= n
			spaces.insert(j, 0)
			moved.add(x)
			fit = True
			break
	if not fit: i -= 1

s = i = 0
for x, n in blocks:
	for _ in range(n):
		s += i * x
		i += 1
	i += spaces.pop(0)
print(s)
