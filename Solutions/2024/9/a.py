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

s = i = 0
while blocks:
	x, n = blocks.pop(0)
	for _ in range(n):
		s += i * x
		i += 1
	if not blocks: break
	for _ in range(spaces.pop(0)):
		s += i * blocks[-1][0]
		i += 1
		blocks[-1][1] -= 1
		if blocks[-1][1] == 0:
			blocks.pop()
			if not blocks: break
print(s)
