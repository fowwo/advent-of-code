# December 23rd, 2024

file = open(f"{__file__}/../input.txt", "r")

kp, dp = dict(), dict()
for r, row in enumerate("789/456/123/ 0A".split('/')):
	for c, x in enumerate(row):
		kp[x] = (r, c)
for r, row in enumerate(" ^A/<v>".split('/')):
	for c, x in enumerate(row):
		dp[x] = (r, c)

def f(s):
	t = ""
	(r, c) = dp['A']
	for x in s:
		(i, j) = dp[x]

		# There's a chance the order of these keypresses matters (like for the numeric keypad),
		# but it gave me the correct answer for my puzzle input.
		# It may not be correct for all puzzle inputs.
		t += "v" * (i - r)
		t += ">" * (j - c)
		t += "<" * (c - j)
		t += "^" * (r - i)
		t += "A"
		(r, c) = (i, j)
	return t

n = 0
while (line := file.readline().strip()):
	s = ""
	(r, c) = kp['A']
	for x in line:
		(i, j) = kp[x]

		# The order of these keypresses seems to matter for a reason I don't quite know.
		if r == 3 and j == 0:
			s += "^" * (r - i)
			s += "<" * (c - j)
		else:
			s += "<" * (c - j)
			s += "^" * (r - i)
		if c == 0 and i == 3:
			s += ">" * (j - c)
			s += "v" * (i - r)
		else:
			s += "v" * (i - r)
			s += ">" * (j - c)
		s += "A"
		(r, c) = (i, j)
	n += len(f(f(s))) * int(line[:-1])
file.close()

print(n)
