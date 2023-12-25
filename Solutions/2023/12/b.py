# December 24th, 2023

file = open(f"{__file__}/../input.txt", "r")
lines = [ line.strip().split() for line in file ]

def arrange(line, bi = 0):
	if (k := f"{'.'.join(line)},{bi}") in memo: return memo[k]
	if bi == len(b): return 0 if any('#' in x for x in line) else 1
	if not line: return 0

	c = 0
	i, j = 0, b[bi]
	group = line[0]
	while j < len(group):
		if group[j] == '?':
			d = list(line)
			d[0] = d[0][j + 1:]
			if not d[0]: del d[0]
			c += arrange(d, bi + 1)
		if group[i] == '#': break
		i += 1
		j += 1
	if j == len(group): c += arrange(line[1:], bi + 1)
	if '#' not in group: c += arrange(line[1:], bi)
	memo[k] = c
	return c

s = 0
for a, b in lines:
	memo = dict()
	a = '?'.join([ a.replace('.', ' ') ] * 5).split()
	b = tuple(map(int, b.split(','))) * 5
	s += arrange(a)
print(s)
