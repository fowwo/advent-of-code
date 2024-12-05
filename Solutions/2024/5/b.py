# December 4th, 2024

from collections import defaultdict
from functools import cmp_to_key

order = defaultdict(set)

file = open(f"{__file__}/../input.txt", "r")
while (line := file.readline().strip()):
	x, y = map(int, line.split('|'))
	order[x].add(y)

incorrect = []
while (line := file.readline().strip()):
	pages = tuple(map(int, line.split(',')))
	s = set()
	b = True
	for x in pages:
		if any(y in s for y in order[x]):
			incorrect.append(pages)
			break
		s.add(x)
file.close()

def cmp(x, y):
	if y in order[x]: return -1
	if x in order[y]: return 1
	return 0

print(sum(sorted(pages, key = cmp_to_key(cmp))[len(pages) // 2] for pages in incorrect))
