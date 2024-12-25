# December 24th, 2024

from functools import cache

file = open(f"{__file__}/../input.txt", "r")

kp, dp = dict(), dict()
for r, row in enumerate("789/456/123/ 0A".split('/')):
	for c, x in enumerate(row):
		kp[x] = (r, c)
for r, row in enumerate(" ^A/<v>".split('/')):
	for c, x in enumerate(row):
		dp[x] = (r, c)

def path(r, c, i, j):
	s = ""

	# The order of these keypresses seems to matter for a reason I don't quite know.
	if i == 1 and j == 0:
		s += 'v' * (i - r)
		s += '<' * (c - j)
	else:
		s += '<' * (c - j)
		s += 'v' * (i - r)
	if i == 0 and c == 0:
		s += '>' * (j - c)
		s += '^' * (r - i)
	else:
		s += '^' * (r - i)
		s += '>' * (j - c)
	s += 'A'
	return s

@cache
def f(s, d):
	if d == 0: return len(s)
	n = 0
	(r, c) = dp['A']
	for x in s:
		(i, j) = dp[x]
		p = path(r, c, i, j)
		n += f(p, d - 1)
		(r, c) = (i, j)
	return n

n = 0
while (line := file.readline().strip()):
	s = ""
	(r, c) = kp['A']
	for x in line:
		i, j = kp[x]

		# The order of these keypresses seems to matter for a reason I don't quite know.
		if r == 3 and j == 0:
			s += '^' * (r - i)
			s += '<' * (c - j)
		else:
			s += '<' * (c - j)
			s += '^' * (r - i)
		if c == 0 and i == 3:
			s += '>' * (j - c)
			s += 'v' * (i - r)
		else:
			s += 'v' * (i - r)
			s += '>' * (j - c)
		s += 'A'
		(r, c) = (i, j)
	n += f(s, 25) * int(line.strip()[:-1])
file.close()

print(n)
