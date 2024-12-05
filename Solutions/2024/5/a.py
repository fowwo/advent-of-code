# December 4th, 2024

from collections import defaultdict

order = defaultdict(set)

file = open(f"{__file__}/../input.txt", "r")
while (line := file.readline().strip()):
	x, y = map(int, line.split('|'))
	order[x].add(y)

n = 0
while (line := file.readline().strip()):
	pages = tuple(map(int, line.split(',')))
	s = set()
	b = True
	for x in pages:
		if any(y in s for y in order[x]):
			b = False
			break
		s.add(x)
	if b:
		n += pages[len(pages) // 2]
file.close()

print(n)
